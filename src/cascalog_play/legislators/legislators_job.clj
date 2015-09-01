(ns cascalog-play.legislators.legislators-job
  (:use cascalog.api)
  (:require [cascalog.more-taps :as taps]
            [cascalog-play.utils.utils :refer [byte-writable-to-str get-nested-field]]
            [clj-yaml.core :as yaml]))

(defmapcatop parse-legislator-yaml [legislators]
  (yaml/parse-string legislators))

(defn retrieve-legislators-at [location]
  (??<- [?thomas ?bioguide ?lis ?govtrack ?opensecrets ?cspan
         ?wikipedia ?ballotpedia ?maplight
         ?washington_post ?icpsr ?votesmart
         ?first ?last]
        ((taps/hfs-wholefile location) ?filename ?file-content)
        (byte-writable-to-str ?file-content :> ?line)
        (parse-legislator-yaml ?line :> ?legislator)
        (get-in ?legislator [:id] :> ?id)
        (get-nested-field [:thomas :bioguide :lis
                           :govtrack :opensecrets
                           :cspan :wikipedia
                           :ballotpedia
                           :maplight :washington_post
                           :icpsr :votesmart] ?id
                          :> ?thomas ?bioguide ?lis ?govtrack ?opensecrets ?cspan
                             ?wikipedia ?ballotpedia ?maplight
                             ?washington_post ?icpsr ?votesmart)
        (get-in ?legislator [:name] :> ?name)
        (get-nested-field [:first :last] ?name :> ?first ?last)))
