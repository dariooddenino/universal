(ns universal.core
  (:require [om.next :as om :refer-macros [defui]]
            [goog.dom :as gdom]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:count 0}))

(defn read [{:keys [state] :as env} key params]
  (let [st @state]
    (if-let [[_ value] (find st key)])))