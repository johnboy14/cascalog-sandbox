(ns cascalog-play.bill-jobs
  (:use cascalog.api)
  (:require [cheshire.core :as ch]
            [cascalog.logic.ops :as c]
            [cascalog.logic.vars :as v]))


(defn parse-json [json]
  (map (ch/parse-string json true) [:bill_id :bill_type :congress :introduced_at :updated_at :sponsor :official_title :status :subjects_top_term]))

(defn get-nested-field [fields map]
  (mapv #(% map) fields))

(defn get-file-pattern-by-type [type]
  (case type
    (:hr)  "*_hr_*data1.json"
    (:sres) "*_sres_*data1.json"
    :default "*data1.json"))

(defn file-textline [dir pattern]
  (let [source (lfs-textline dir :source-pattern (get-file-pattern-by-type pattern))]
    (<- [?line]
        (source ?line))))

(defn retrieve-bills-at [dir type]
  (let [text-tap (file-textline dir type)]
    (<- [?bill-id ?bill-type ?congress ?introduced-date ?updated_at ?sponsor_name ?sponsor_thomas_id ?official_title ?status ?subjects_top_term]
        (text-tap ?line)
        (parse-json ?line :> ?bill-id ?bill-type ?congress ?introduced-date ?updated_at ?sponsor ?official_title ?status ?subjects_top_term)
        (get-nested-field [:name :thomas_id] ?sponsor  :> ?sponsor_name ?sponsor_thomas_id))))