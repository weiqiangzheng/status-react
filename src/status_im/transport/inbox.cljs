(ns ^{:doc "Offline inboxing events and API"}
 status-im.transport.inbox
  (:require [re-frame.core :as re-frame]
            [status-im.native-module.core :as status]
            [status-im.utils.handlers :as handlers]
            [status-im.transport.utils :as transport.utils]
            [status-im.utils.config :as config]
            [taoensso.timbre :as log]
            [status-im.constants :as constants]
            [status-im.utils.ethereum.core :as ethereum]
            [status-im.utils.utils :as utils]
            [status-im.i18n :as i18n]
            [day8.re-frame.async-flow-fx]
            [status-im.constants :as constants]
            [status-im.utils.handlers-macro :as handlers-macro]))

(defn- parse-json
  ;; NOTE(dmitryn) Expects JSON response like:
  ;; {"error": "msg"} or {"result": true}
  [s]
  (try
    (let [res (-> s
                  js/JSON.parse
                  (js->clj :keywordize-keys true))]
      ;; NOTE(dmitryn): AddPeer() may return {"error": ""}
      ;; assuming empty error is a success response
      ;; by transforming {"error": ""} to {:result true}
      (if (and (:error res)
               (= (:error res) ""))
        {:result true}
        res))
    (catch :default e
      {:error (.-message e)})))

(defn- response-handler [error-fn success-fn]
  (fn handle-response
    ([response]
     (let [{:keys [error result]} (parse-json response)]
       (handle-response error result)))
    ([error result]
     (if error
       (error-fn error)
       (success-fn result)))))

(defn get-current-wnode-address [db]
  (let [network  (ethereum/network->chain-keyword (get db :network))
        wnode-id (get-in db [:account/account :settings :wnode network])]
    (get-in db [:inbox/wnodes network wnode-id :address])))

(defn registered-peer? [peers enode]
  (let [peer-ids (into #{} (map :id) peers)
        enode-id (transport.utils/extract-enode-id enode)]
    (contains? peer-ids enode-id)))

(defn add-peer [enode success-fn error-fn]
  (status/add-peer enode (response-handler error-fn success-fn)))

(defn mark-trusted-peer [web3 enode success-fn error-fn]
  (.markTrustedPeer (transport.utils/shh web3)
                    enode
                    (fn [err resp]
                      (if-not err
                        (success-fn resp)
                        (error-fn err)))))

(defn request-inbox-messages [web3 wnode topics to from sym-key-id success-fn error-fn]
  (let [opts (merge {:mailServerPeer wnode
                     :symKeyID       sym-key-id}
                    (when from {:from from})
                    (when to {:to to}))]
    (log/info "offline inbox: request-messages request for topics " topics)
    (doseq [topic topics]
      (let [opts (assoc opts :topic topic)]
        (.requestMessages (transport.utils/shh web3)
                          (clj->js opts)
                          (fn [err resp]
                            (if-not err
                              (success-fn resp)
                              (error-fn err topic))))))))

(re-frame/reg-fx
 ::add-peer
 (fn [{:keys [wnode]}]
   (add-peer wnode
             #(log/debug "offline inbox: add-peer success" %)
             #(log/error "offline inbox: add-peer error" %))))

(re-frame/reg-fx
 ::mark-trusted-peer
 (fn [{:keys [wnode web3]}]
   (mark-trusted-peer web3
                      wnode
                      #(re-frame/dispatch [:inbox/mailserver-trusted %])
                      #(log/error "offline inbox: mark-trusted-peer error" % wnode))))

(re-frame/reg-fx
 ::request-messages
 (fn [{:keys [wnode topics to from sym-key-id web3]}]
   (request-inbox-messages web3
                           wnode
                           topics
                           to
                           from
                           sym-key-id
                           #(log/info "offline inbox: request-messages response" %)
                           #(log/error "offline inbox: request-messages error" %1 %2 to from))))

(defn update-mailserver-status [transition {:keys [db]}]
  (let [state transition]
    {:db (assoc db :mailserver-status state)}))

(defn generate-mailserver-symkey [{:keys [db] :as cofx}]
  (when-not (:inbox/sym-key-id db)
    {:shh/generate-sym-key-from-password {:password   (:inbox/password db)
                                          :web3       (:web3 db)
                                          :on-success (fn [_ sym-key-id]
                                                        (re-frame/dispatch [:inbox/get-sym-key-success sym-key-id]))
                                          :on-error   #(log/error "offline inbox: get-sym-key error" %)}}))

(defn connect-to-mailserver [{:keys [db] :as cofx}]
  (let [web3     (:web3 db)
        wnode    (get-current-wnode-address db)]
    (when config/offline-inbox-enabled?
      (handlers-macro/merge-fx cofx
                               {::add-peer {:wnode wnode}}
                               (update-mailserver-status :connecting)
                               (generate-mailserver-symkey)))))

(defn peers-summary-change-fx [{:keys [db] :as cofx}]
  (if config/offline-inbox-enabled?
    (let [{:keys [peers-summary peers-count]} db]
      (if (zero? peers-count)
        (update-mailserver-status :disconnected cofx)
        (let [wnode (get-current-wnode-address db)
              mailserver-status (:mailserver-status db)]
          (if (registered-peer? peers-summary wnode)
            (handlers-macro/merge-fx cofx
                                     {::mark-trusted-peer {:web3  (:web3 db)
                                                           :wnode wnode}}
                                     (generate-mailserver-symkey))
            (connect-to-mailserver cofx)))))))

(defn get-topics
  [db topics discover?]
  (let [inbox-topics    (:inbox/topics db)
        discovery-topic (transport.utils/get-topic constants/contact-discovery)
        topics          (or topics
                            (map #(:topic %) (vals (:transport/chats db))))]
    (cond-> (apply conj inbox-topics topics)
      discover? (conj discovery-topic))))

(defn request-messages [{:keys [topics discover? should-recover?]
                         :or {should-recover? true
                              discover?       true}}
                        {:keys [db] :as cofx}]
  (let [mailserver-status (:mailserver-status db)
        sym-key-id        (:inbox/sym-key-id db)
        wnode             (get-current-wnode-address db)
        inbox-topics      (get-topics db topics discover?)
        inbox-ready?      (and (= :connected mailserver-status)
                               sym-key-id)]
    (when should-recover?
      (if inbox-ready?
        {::request-messages {:wnode      wnode
                             :topics     (into [] inbox-topics)
                             :sym-key-id sym-key-id
                             :web3       (:web3 db)}}
        {:db (assoc db :inbox/topics inbox-topics)}))))

;;;; Handlers

(handlers/register-handler-fx
 :inbox/mailserver-trusted
 (fn [{:keys [db] :as cofx} _]
   (handlers-macro/merge-fx cofx
                            (update-mailserver-status :connected)
                            (request-messages {}))))

(handlers/register-handler-fx
 :inbox/get-sym-key-success
 (fn [{:keys [db] :as cofx} [_ sym-key-id]]
   (handlers-macro/merge-fx cofx
                            {:db (assoc db :inbox/sym-key-id sym-key-id)}
                            (request-messages {}))))

(handlers/register-handler-fx
 :inbox/request-messages
 (fn [cofx [_ args]]
   (request-messages args cofx)))
