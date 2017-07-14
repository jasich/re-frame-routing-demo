(ns re-frame-routing-demo.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [re-frame-routing-demo.events]
            [re-frame-routing-demo.subs]
            [re-frame-routing-demo.views :as views]
            [re-frame-routing-demo.routes :as routes]
            [re-frame-routing-demo.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
