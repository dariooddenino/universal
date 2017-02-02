(ns universal.core
  #?(:cljs  (:require
            [untangled.client.core :as uc]
            [universal.ui :refer [Root]]
            ))
  )

(def initial-state {:ui/react-key "abc"
                    :main-list {:list/id 1
                                :list/name "My List"
                                :list/items [{:item/id 1 :item/label "A"}
                                             {:item/id 2 :item/label "B"}]}})

#?(:cljs (defonce app (atom (uc/new-untangled-client :initial-state initial-state))))

#?(:cljs (uc/mount @app Root "app"))

;; (ns universal.core
;;   #?(:clj (:refer-clojure :exclude [read]))
;;   (:require #?@(:cljs [[goog.dom :as gdom]
;;                       [om.next :as om :refer-macros [defui]]
;;                       ]
;;               :clj  [[om.next :as om :refer [defui]]]
;;                )
;;             [universal.client-parser :as p]
;;             [om.next :as om :refer [defui]]
;;             [om.dom :as dom]))

;; #?(:cljs (enable-console-print!))

;; #?(:cljs (js/console.log "HElloS"))

;; (def app-state (atom {:count 0}))


;; (defui Counter
;;   static om/IQuery
;;   (query [this]
;;     [:count])
;;   Object
;;   (render [this]
;;     (let [{:keys [count]} (om/props this)]
;;       (dom/div nil
;;         (dom/span nil (str "Count: " count))
;;         (dom/button #?(:clj  nil
;;                       :cljs  #js {:onClick (fn [e] (om/transact! this '[(increment)]))}
;;                       "Click me!"))))))

;; ;; #?(:clj
;; ;;         (defn make-reconciler []
;; ;;           (om/reconciler
;; ;;             {:state app-state
;; ;;              :normalize true
;; ;;              :parser (om/parser {:read p/read :mutate p/mutate})})))


;; #?(:clj (def reconciler
;;           (om/reconciler
;;            {:state (atom {:count 1})
;;             :parser (om/parser {:read p/read :mutate p/mutate})})))

;; #?(:cljs (def reconciler
;;          (om/reconciler
;;           {:state app-state
;;            :parser (om/parser {:read p/read :mutate p/mutate})})))

;; #?(:cljs (om/add-root! reconciler
;;            Counter (gdom/getElement "app")))
