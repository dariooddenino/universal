(ns universal.server
  (:require [clojure.java.io :as io]
            [compojure.core :refer [ANY GET PUT POST DELETE defroutes]]
            [compojure.route :refer [resources]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.middleware.logger :refer [wrap-with-logger]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [hiccup.page :as hiccup])
  (:gen-class))

(defn index-el []
  (hiccup/html5
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:http-equiv "X-UA-Compatible"
             :content "IE=edge"}]
     [:title "Universal"]
     (hiccup/include-css "/css/style.css")]
    [:body
     [:section#app "Loading..."]
     (hiccup/include-js "/js/compiled/universal.js")]))


(defroutes routes
  (GET "/" _  (index-el))
    ;;{:status 200
    ;; :headers {"Content-Type" "text/html; charset=utf-8"}
     ;;:body (io/input-stream (io/resource "public/index.html"))
     ;;:body (index)
    ;; }

  (resources "/"))


(def http-handler
  (-> routes
      (wrap-defaults api-defaults)
      wrap-with-logger
      wrap-gzip))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 10555))]
    (run-jetty http-handler {:port port :join? false})))
