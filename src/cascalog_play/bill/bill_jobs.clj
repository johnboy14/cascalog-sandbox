(ns cascalog-play.bill.bill-jobs
  (:use cascalog.api)
  (:require [cheshire.core :as ch]
            [cascalog.logic.ops :as c]
            [cascalog.logic.def :as def]
            [cascalog.logic.vars :as v]
            [cascalog.more-taps :as taps])
  (:gen-class))


(defn parse-json [json]
  (map (ch/parse-string json true) [:bill_id :bill_type :congress :introduced_at :updated_at :sponsor :official_title :status :subjects_top_term]))

(defn parse-json-with [json fields]
  (map (ch/parse-string json true) fields))

(defn get-nested-field [fields map]
  (mapv #(% map) fields))

(defn get-file-pattern-by-type2 [type]
  (case type
    (:hr)  "/hr/*/data.json"
    (:hres) "/hres/*/data.json"
    (:hjres) "/hjres/*/data.json"
    (:hconres) "/hconres/*/data.json"
    (:sres) "/sres/*/data.json"
    (:sjres) "/sjres/*/data.json"
    (:sconres) "/sconres/*/data.json"
    (:s) "/s/*/data.json"
    (:all) "/*/*/data.json"))

(defn- byte-writable-to-str [bw]
  "convert byte writable to stirng"
  [(apply str (map char (. bw (getBytes))))])

(defn retrieve-bills-at [dir type]
  (<- [?bill-id ?bill-type ?congress ?introduced-date ?updated_at ?sponsor_name ?sponsor_thomas_id ?official_title ?status ?subjects_top_term]
      ((taps/hfs-wholefile dir
                           :source-pattern (get-file-pattern-by-type2 type)) ?filename ?file-content)
      (byte-writable-to-str ?file-content :> ?line)
      (parse-json ?line :> ?bill-id ?bill-type ?congress ?introduced-date ?updated_at ?sponsor ?official_title ?status ?subjects_top_term)
      (get-nested-field [:name :thomas_id] ?sponsor  :> ?sponsor_name ?sponsor_thomas_id)))

(defn retrieve-bill-summaries-at [dir type]
    (<- [?bill_id ?summary_text]
        ((taps/hfs-wholefile dir
                             :source-pattern (get-file-pattern-by-type2 type)) ?filename ?file-content)
        (byte-writable-to-str ?file-content :> ?line)
        (parse-json-with ?line [:bill_id :summary] :> ?bill_id ?summary)
        (get-nested-field [:text] ?summary :> ?summary_text)))

(defn retrieve-bill-subject-terms-at [dir type]
    (<- [?bill_id ?term]
        ((taps/hfs-wholefile dir
                             :source-pattern (get-file-pattern-by-type2 type)) ?filename ?file-content)
        (byte-writable-to-str ?file-content :> ?line)
        (parse-json-with ?line [:bill_id :subjects] :> ?bill_id ?subjects)
        ((mapcatop identity) ?subjects :> ?term)))

(defn retrieve-bill-cosponsor-relationships-at [dir type]
    (<- [?bill_id ?name ?sponsored_at ?state ?thomas_id]
        ((taps/hfs-wholefile dir
                             :source-pattern (get-file-pattern-by-type2 type)) ?filename ?file-content)
        (byte-writable-to-str ?file-content :> ?line)
        (parse-json-with ?line [:bill_id :cosponsors] :> ?bill_id ?cosponsors)
        ((mapcatop identity) ?cosponsors :> ?sponsor)
        (get-nested-field [:name :sponsored_at :state :thomas_id] ?sponsor :> ?name ?sponsored_at ?state ?thomas_id)))

(defn retrieve-bill-actions-at [dir type]
    (<- [?bill_id ?acted_at ?text]
        ((taps/hfs-wholefile dir
                             :source-pattern (get-file-pattern-by-type2 type)) ?filename ?file-content)
        (byte-writable-to-str ?file-content :> ?line)
        (parse-json-with ?line [:bill_id :actions] :> ?bill_id ?actions)
        ((mapcatop identity) ?actions :> ?action)
        (get-nested-field [:acted_at :text] ?action :> ?acted_at ?text)))

(defn run-bill-detail-job [input output]
  (println "IN")
  (?- (taps/hfs-delimited output :sinkmode :replace :delimiter ",")
      (retrieve-bills-at input :all)))
