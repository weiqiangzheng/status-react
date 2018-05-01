(ns status-im.utils.extensions
  (:require
    [status-im.utils.types :as types]
    [status-im.utils.http :as http]))

(defn- ipfs->extension [ipfs-extension]
  {:extension-id (:Hash ipfs-extension)
   :name         (:Name ipfs-extension)})

(defn parse-directory [response]
  (->> response
       types/json->clj
       :Objects
       first
       :Links
       (map ipfs->extension)
       (reduce #(assoc %1 (:extension-id %2) %2) {})))

(defn error [a]
  (println "ERROR" a))

(defn list-all [gateway-url directory cb]
  (http/get (str gateway-url "/api/v0/ls?arg=" directory) (comp cb parse-directory)))

(defn fetch
  ([gateway-url extension]
   (fetch gateway-url extension nil))
  ([gateway-url extension cb]
   (http/get (str gateway-url "/api/v0/cat?arg=" extension) cb)))

(defn fetch-all [gateway-url extensions cb]
  (let [promises (js/Promise.all (clj->js (mapv #(fetch gateway-url % identity) extensions)))]

  (.then promises
         (fn [result]
           (println (js->clj result))))))
