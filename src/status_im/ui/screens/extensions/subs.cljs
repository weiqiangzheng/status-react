(ns status-im.ui.screens.extensions.subs
  (:require [re-frame.core :as re-frame]))

(defn inspect [a]
  (println "INSPECTING" a)
  a)
(re-frame/reg-sub :extensions (comp inspect vals :extension/extensions))
