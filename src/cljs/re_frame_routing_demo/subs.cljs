(ns re-frame-routing-demo.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :active-panel
 (fn [db]
   (:active-panel db)))

(re-frame/reg-sub
  :songs
  (fn [db]
    (:songs db)))

(re-frame/reg-sub
  :route-id
  (fn [db]
    (int (:route-id db))))

(re-frame/reg-sub
  :current-song
  (fn [_ _]
    [(re-frame/subscribe [:songs])
     (re-frame/subscribe [:route-id])])

  (fn [[songs route-id] _]
    (get songs route-id)))
