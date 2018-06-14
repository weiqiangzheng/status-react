(ns status-im.models.wallet
  (:require [status-im.utils.money :as money]))

(def min-gas-price-wei (money/bignumber 1))

(defmulti invalid-send-parameter? (fn [type _] type))

(defmethod invalid-send-parameter? :gas-price [_ value]
  (cond
   (not value) :invalid-number
   (< value min-gas-price-wei) :not-enough-wei))

(defmethod invalid-send-parameter? :default [_ value]
  (when-not value
    :invalid-number))
