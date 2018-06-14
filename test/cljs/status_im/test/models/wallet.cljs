(ns status-im.test.models.wallet
  (:require [cljs.test :refer-macros [deftest is testing]]
            [status-im.utils.money :as money]
            [status-im.models.wallet :as model]))

(deftest valid-min-gas-price-test
  (testing "not an number"
    (is (= :invalid-number (model/invalid-send-parameter? :gas-price nil))))
  (testing "a number less than the minimum"
    (is (= :not-enough-wei (model/invalid-send-parameter? :gas-price (money/bignumber 0.1)))))
  (testing "a number greater than the mininum"
    (is (not (model/invalid-send-parameter? :gas-price 3))))
  (testing "the minimum"
    (is (not (model/invalid-send-parameter? :gas-price 1)))))

(deftest valid-gas
  (testing "not an number"
    (is (= :invalid-number (model/invalid-send-parameter? :gas nil))))
  (testing "a number"
    (is (not (model/invalid-send-parameter? :gas 1)))))
