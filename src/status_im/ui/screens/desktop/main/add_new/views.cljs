(ns status-im.ui.screens.desktop.main.add-new.views
  (:require-macros [status-im.utils.views :as views])
  (:require [status-im.ui.components.icons.vector-icons :as icons]
            [re-frame.core :as re-frame]
            [taoensso.timbre :as log]
            [status-im.ui.screens.add-new.new-chat.db :as db]
            [status-im.ui.components.react :as react]))

(views/defview new-contact []
  (views/letsubs [new-contact-identity [:get :contacts/new-identity]
                  contacts      [:all-added-people-contacts]
                  error-message [:new-contact-error-message]
                  topic [:get :public-group-topic]
                  account [:get-current-account]]
    [react/view {:style {:flex 1 :background-color :white}}
     ^{:key "newcontact"}
     [react/view {:style {:height 64 :align-items :flex-start :padding-horizontal 11 :justify-content :center}}
      [react/text {:style {:font-size 20 :color :black :font-weight "600"}}
       "New Chat"]]
     [react/view {:style {:height 1 :background-color "#e8ebec" :margin-horizontal 16}}]
     [react/view {:style {:height     90 :margin-horizontal 16 :margin-bottom 16 :background-color :white :border-radius 12}}
                          ;:box-shadow "0 0.5px 4.5px 0 rgba(0, 0, 0, 0.04)"}}
      [react/view {:style {:flex-direction :row :margin-horizontal 16 :margin-top 16}}
       [react/text-input {:placeholder "0x..."
                          :flex 1
                          :on-change   (fn [e]
                                         (let [native-event (.-nativeEvent e)
                                               text (.-text native-event)]
                                           (re-frame/dispatch [:set :contacts/new-identity text])))}]
       [react/touchable-highlight {:on-press #(when-not error-message (re-frame/dispatch [:add-contact-handler new-contact-identity]))}
        [react/view {:style {:width 140 :height 45 :border-radius 8 :background-color (if error-message "#eef2f5" "#4360df") :align-items :center
                             :justify-content :center}}
         [react/text {:style {:font-size 16 :color (if error-message "#939ba1" :white)}} "Start chat"]]]]]
     ^{:key "publicchat"}
     [react/view {:style {:height 64 :align-items :flex-start :padding-horizontal 11 :justify-content :center}}
      [react/text {:style {:font-size 20 :color :black :font-weight "600"}}
       "Join Public Chat"]]
     [react/view {:style {:height 1 :background-color "#e8ebec" :margin-horizontal 16}}]
     [react/view {:style {:height     90 :margin-horizontal 16 :margin-bottom 16 :background-color :white :border-radius 12}}
                          ;:box-shadow "0 0.5px 4.5px 0 rgba(0, 0, 0, 0.04)"}}
      [react/view {:style {:flex-direction :row :margin-horizontal 16 :margin-top 16}}
       [react/view {:style {:flex 1}}
        [react/text-input {:placeholder "#"
                           :flex 1
                           :on-change   (fn [e]
                                          (let [native-event (.-nativeEvent e)
                                                text (.-text native-event)]
                                            (re-frame/dispatch [:set :public-group-topic text])))}]]
       [react/touchable-highlight {:on-press #(re-frame/dispatch [:create-new-public-chat topic])}
        [react/view {:style {:width 150 :height 45 :border-radius 8 :background-color "#4360df" :align-items :center
                             :justify-content :center}}
         [react/text {:style {:font-size 16 :color :white}} "Join public chat"]]]]]]))
