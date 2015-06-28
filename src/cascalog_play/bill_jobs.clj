(ns cascalog-play.bill-jobs
  (:use cascalog.api)
  (:require [cheshire.core :as ch]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as def]
            [cascalog.logic.vars :as v]))


(defn parse-json [json]
  (map (ch/parse-string json true) [:bill_id :bill_type :congress :introduced_at :updated_at :sponsor :official_title :status :subjects_top_term]))

(defn parse-json-with [json fields]
  (map (ch/parse-string json true) fields))

(defn get-nested-field [fields map]
  (mapv #(% map) fields))

(defn get-file-pattern-by-type [type]
  (case type
    (:hr)  "*_hr_*data1.json"
    (:hres) "*_hres_*data1.json"
    (:hjres) "*_hjres_*data1.json"
    (:hconres) "*_hconres_*data1.json"
    (:sres) "*_sres_*data1.json"
    (:sjres) "*_sjres_*data1.json"
    (:sconres) "*_sconres_*data1.json"
    (:s) "*_s_*data1.json"
    "*data1.json"))

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

(defn retrieve-bill-summaries-at [dir type]
  (let [text-tap (file-textline dir type)]
    (<- [?bill_id ?summary_text]
        (text-tap ?line)
        (parse-json-with ?line [:bill_id :summary] :> ?bill_id ?summary)
        (get-nested-field [:text] ?summary :> ?summary_text))))

(defn retrieve-bill-subject-terms-at [dir type]
  (let [text-tap (file-textline dir type)]
    (<- [?bill_id ?term]
        (text-tap ?line)
        (parse-json-with ?line [:bill_id :subjects] :> ?bill_id ?subjects)
        ((mapcatop identity) ?subjects :> ?term))))

(defn retrieve-bill-cosponsor-relationships-at [dir type]
  (let [text-tap (file-textline dir type)]
    (<- [?bill_id ?name ?sponsored_at ?state ?thomas_id]
        (text-tap ?line)
        (parse-json-with ?line [:bill_id :cosponsors] :> ?bill_id ?cosponsors)
        ((mapcatop identity) ?cosponsors :> ?sponsor)
        (get-nested-field [:name :sponsored_at :state :thomas_id] ?sponsor :> ?name ?sponsored_at ?state ?thomas_id))))

(defn retrieve-bill-actions-at [dir type]
  (let [text-tap (file-textline dir type)]
    (<- [?bill_id ?acted_at ?text]
        (text-tap ?line)
        (parse-json-with ?line [:bill_id :actions] :> ?bill_id ?actions)
        ((mapcatop identity) ?actions :> ?action)
        (get-nested-field [:acted_at :text] ?action :> ?acted_at ?text))))
