(ns status-im.protocol.chat
  (:require [cljs.spec.alpha :as s]
            [status-im.protocol.web3.filtering :as f]
            [status-im.protocol.web3.delivery :as d]
            [taoensso.timbre :refer-macros [debug] :as log]
            [status-im.protocol.validation :refer-macros [valid?]]
            [status-im.utils.datetime :as datetime]
            [status-im.utils.random :as random]))

(def message-defaults
  {:topics [f/status-topic]})

(s/def ::timestamp int?)
(s/def ::user-message
  (s/merge
   :protocol/message
   (s/keys :req-un [:message/to :chat-message/payload])))

(defn send!
  [{:keys [web3 message]}]
  {:pre [(valid? ::user-message message)]}
  (let [topics  (f/get-topics (:to message))
        message' (assoc message
                        :topics        topics
                        :type          :message
                        :requires-ack? true)]
    (debug :send-user-message message')
    (d/add-pending-message! web3 message')))

(def timer (atom nil))

(defn send-ping
  ([chat-id]
   (reset! timer (datetime/now-ms))
   (let [db @re-frame.db/app-db]
     (send-ping (:web3 db) (:current-public-key db) chat-id {:counter 0})))
  ([web3 id chat-id {:keys [counter]}]
   (println id chat-id web3 counter)
   (let [message {:to chat-id
                  :from id
                  :message-id (random/id)
                  :topics ["0x01010202"]
                  :payload {:counter (inc counter)}
                  :type :system/ping
                  :requires-ack? false}]
     (log/info "Received ping" counter)
     (println counter)
     (if (> counter 2)
       (let [timer (- (datetime/now-ms) @timer)]
         (println "timer for 100 pings:" timer)
         (log/info "Tme for 100 pings" timer))
       (d/add-pending-message! web3 message)))))

(s/def ::seen-message
  (s/merge :protocol/message (s/keys :req-un [:message/to])))

(defn send-seen!
  [{:keys [web3 message]}]
  {:pre [(valid? ::seen-message message)]}
  (debug :send-seen message)
  (d/add-pending-message!
   web3
   (merge message-defaults
          (-> message
              (assoc
                :type :seen
                :requires-ack? false)
              (assoc-in [:payload :group-id] (:group-id message))
              (dissoc :group-id)))))
