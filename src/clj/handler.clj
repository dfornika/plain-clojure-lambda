(ns handler
  (:require [hiccup2.core :as h]
            [hiccup.page :as page]
            [muuntaja.core :as m]
            [reitit.ring :as ring]
            [reitit.ring.coercion :as rrc]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [ring.util.response :as response]))

(def head
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
   [:link {:rel "shortcut icon" :type "image/x-icon" :href "favicon.ico"}]
   [:link {:rel "stylesheet" :href "css/style.css"}]])

(def body 
  [:body
   [:div {:id "app"}]
   [:script {:src "js/main.js" :type "text/javascript"}]])


;; Probably a silly example considering lambda is suppose to be stateless
(def counter (atom 0))
(defn get-counter [_]
  (response/response {:counter @counter}))
(defn inc-counter [_]
  (response/response {:counter (swap! counter inc)}))

(def app
  (ring/ring-handler
    (ring/router
      [["/" (constantly
             (-> (page/html5 head body)
                 (str)
                 (response/response)
                 (response/content-type "text/html")))]
       ["/api/counter" {:get #'get-counter
                        :post #'inc-counter}]]
      {:data {:muuntaja m/instance
              :middleware [muuntaja/format-middleware
                           rrc/coerce-request-middleware
                           rrc/coerce-response-middleware]}})
    (ring/routes
     (ring/create-resource-handler {:root "public"
                                    :path "/"})
     (ring/create-default-handler))))
