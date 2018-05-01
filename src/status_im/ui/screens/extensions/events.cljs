(ns status-im.ui.screens.extensions.events
  (:require [status-im.utils.handlers :as handlers]
            [re-frame.core :as re-frame]
            [status-im.utils.extensions :as extensions]
            [status-im.utils.random :as random]
            [status-im.i18n :as i18n]))

(handlers/register-handler-fx
  ::fetched-extension
  (fn [_ [_ extension]]
    (println extension)
    nil))

(handlers/register-handler-fx
  ::fetched-extension-ls
  (fn [_ [_ extension]]
    (extensions/fetch-all "https://gateway.ipfs.io" (map :extension-id (vals extension)) #(re-frame/dispatch [::fetched-extension %]))
    nil))

(handlers/register-handler-fx
  :fetch-extension
  (fn [_ [_ extension-id]]
    (println "ID" extension-id)
    (extensions/list-all "https://gateway.ipfs.io" extension-id #(re-frame/dispatch [::fetched-extension-ls %]))
    nil))

(handlers/register-handler-fx
  ::fetched-extensions
  (fn [{:keys [db]} [_ extensions]]
   {:db (assoc db :extension/extensions extensions)}))

(handlers/register-handler-fx
  :initialize-extensions
  []
  (fn [_]
    (extensions/list-all "https://gateway.ipfs.io" "QmbttDMZowoW8gF4s3MUXY4E7tMkrxhGxSP7dh9J2QjSxt" #(re-frame/dispatch [::fetched-extensions %]))
    nil))



