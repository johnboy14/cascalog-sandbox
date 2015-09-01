(ns cascalog-play.utils.utils
  (:require [clojure.string :as string]))

(defn byte-writable-to-str [bw]
  "convert byte writable to stirng"
  [(string/replace (apply str (map char (. bw (getBytes)))) #"\u0000" "")])

(defn get-nested-field [fields map]
  (mapv #(% map) fields))