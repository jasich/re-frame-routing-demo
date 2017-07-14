(ns re-frame-routing-demo.routes
  (:require   [bidi.bidi     :as bidi]
              [pushy.core    :as pushy]
              [re-frame.core :as re-frame]))

(def routes ["/" [[""       :home]           ;; "/"
                  ["songs/" {[:id] :songs}]  ;; "/songs/33"
                  [true     :not-found]]])   ;; everything else

(def preload {:home :fetch-songs})

(defn- parse-url [url]
  (bidi/match-route routes url))

(defn- dispatch-route [matched-route]
  (let [handler (:handler matched-route)
        params  (:route-params matched-route)]
    (re-frame/dispatch [:set-active-panel handler params])))

(def history (pushy/pushy dispatch-route parse-url))

(defn app-routes []
  (pushy/start! history))

(defn change-route [route]
  (pushy/set-token! history route))

(defn path-for-song [song-id]
  (bidi/path-for routes :songs :id song-id))
