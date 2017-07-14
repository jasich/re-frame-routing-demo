(ns re-frame-routing-demo.events
  (:require [re-frame.core :as re-frame]
            [re-frame-routing-demo.db :as db]
            [re-frame-routing-demo.routes :as routes]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-fx
  :set-active-panel
  (fn [{:keys [db]} [_ active-panel route-params]]
    (let [event-name (get routes/preload active-panel)
          route-id   (:id route-params)
          event      (if event-name [event-name route-id] nil)]
      (if event
        {:db       (-> db
                       (assoc :active-panel active-panel)
                       (assoc :route-id route-id))
         :dispatch event}
        {:db       (-> db
                       (assoc :active-panel active-panel)
                       (assoc :route-id route-id))}))))

(re-frame/reg-event-db
  :fetch-songs
  (fn [db _]
    (assoc db :songs {1 {:id 1 :name "Friday" :artist "Rebecca Black" :year "2011"}
                      2 {:id 2 :name "Miracles" :artist "Insane Clown Posse " :year "2010"}
                      3 {:id 3 :name "Baby" :artist "Justin Bieber" :year "2010"}
                      4 {:id 4 :name "Rockstar" :artist "Nickelback" :year "2006"}})))
