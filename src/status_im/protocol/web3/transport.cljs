(ns status-im.protocol.web3.transport
  (:require [status-im.protocol.web3.utils :as u]
            [cljs.spec.alpha :as s]
            [status-im.protocol.validation :refer-macros [valid?]]
            [taoensso.timbre :refer-macros [debug]]
            [re-frame.core :as re-frame]))

(s/def :shh/payload string?)
(s/def :shh/message
  (s/keys
   :req-un [:shh/payload :message/ttl :message/sig :message/topic]))

(defn post-ping!
  [web3 message _]
  {:pre [(valid? :shh/message message)]}
  (debug :post-ping-message message)
  (let [shh      (u/shh web3)
        message' (assoc message
                        :powTarget 0.001
                        :powTime 1)]
    (re-frame/dispatch [:handle-whisper-message nil (clj->js message') {:web3 web3 :callback #(re-frame/dispatch [:incoming-message %1 %2])}])))


(defn post-message!
  [web3 message callback]
  {:pre [(valid? :shh/message message)]}
  (debug :post-message message)
  (let [shh      (u/shh web3)
        message' (assoc message
                        :powTarget 0.001
                        :powTime 1)]
    (.post shh (clj->js message') callback)))
