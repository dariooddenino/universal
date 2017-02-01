(ns universal.core
  #?(:clj (:refer-clojure :exclude [read]))
  (:require #?@(:cljs [[goog.dom :as gdom]])
            [om.next :as om :refer [defui]]
            [om.dom :as dom]))

#?(:cljs (enable-console-print!))

(def app-state (atom {:count 0}))

(defn read [{:keys [state] :as env} key params]
  (let [st @state]
    (if-let [[_ value] (find st key)]
    {:value value}
    {:value :not-found})))

(defn mutate [{:keys [state] :as env} key params]
  (if (= 'increment key)
    {:value {:keys [:count]}
     :action #(swap! state update-in [:count] inc)}
    {:value :not-found}))

(defui Counter
  static om/IQuery
  (query [this]
    [:count])
  Object
  (render [this]
    (let [{:keys [count]} (om/props this)]
      (dom/div nil
        (dom/span nil (str "Count: " count))
        (dom/button
          (clj->js {:onClick (fn [e] (om/transact! this '[(increment)]))}) "Click me!")))))

#?(:clj
        (defn make-reconciler [conn]
          (om/reconciler
            {:state app-state
             :normalize true
             :parser (om/parser {:read read :mutate mutate})})))



#?(:cljs (def reconciler
           (om/reconciler
             {:state app-state
              :parser (om/parser {:read read :mutate mutate})})))

#?(:cljs (om/add-root! reconciler
           Counter (gdom/getElement "app")))
