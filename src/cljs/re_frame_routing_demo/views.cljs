(ns re-frame-routing-demo.views
  (:require [re-frame.core                :as re-frame]
            [re-frame-routing-demo.routes :as routes]))

(defn song-item [[id data]]
  ^{:key id}[:li [:a {:href (routes/path-for-song id)} (:name data)]])

(defn home []
  (let [songs (re-frame/subscribe [:songs])]
    (fn []
      [:div
        [:h2 "Worst Songs"]
        [:ul
          (map song-item @songs)]
        [:h5 "Source: " [:a {:href "https://en.wikipedia.org/wiki/List_of_music_considered_the_worst#Songs"}
                            "List of music considered the worst"]]])))

(defn song []
  (let [current-song (re-frame/subscribe [:current-song])]
    (fn []
      [:div
        [:h2 (:name @current-song)]
        [:dl
          [:dt "Artist"]
          [:dd (:artist @current-song)]
          [:dt "Year"]
          [:dd (:year @current-song)]]])))


(defn not-found []
  [:h2 "Not Found"])

(defn- panels [panel-name]
  (case panel-name
    :home       [home]
    :songs      [song]
    [not-found]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [:div
        [:div.container
          [show-panel @active-panel]]])))
