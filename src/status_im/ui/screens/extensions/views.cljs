(ns status-im.ui.screens.extensions.views
  (:require-macros [status-im.utils.slurp :refer [slurp]]
                   [status-im.utils.views :as views])
  (:require [cljs.reader :as reader]
            [re-frame.core :as re-frame]
            [status-im.ui.components.list.views :as list]
            [status-im.ui.components.react :as react]
            [status-im.ui.components.status-bar.view :as status-bar]
            [status-im.ui.components.toolbar.view :as toolbar.view]
            [status-im.ui.components.webview-bridge :as components.webview-bridge]
            [status-im.utils.js-resources :as js-res]
            [status-im.ui.components.react :as components]
            [reagent.core :as reagent]
            [status-im.ui.components.chat-icon.screen :as chat-icon.screen]
            [status-im.ui.components.icons.vector-icons :as vector-icons]
            [status-im.i18n :as i18n]))

(views/defview extensions []
  (views/letsubs [extensions [:extensions]]
    [react/view
     (for [extension extensions]
        [react/text {:on-press #(re-frame/dispatch [:fetch-extension (:extension-id extension)])} 
         (:name extension)])]))
;    (when (seq extensions)
;      [react/view
;        (for [extension extensions]
;          {^:key extension} [react/text (:name extension)])])))
;    (when (vals extensions
;
;      [react/view
;       [list/flat-list {:data (vals extensions)
;
;                        :key-fn :extension-id
;                        :render-fn (fn [extension]
;                                     (println "EXTENSION" extension)
;                                     [react/touchable-highlight {:on-press #(re-frame/dispatch [:fetch-extension (:extension-id extension)])}
;                                      [react/text {} (:name extension)]])}]])))
