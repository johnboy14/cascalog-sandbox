(ns cascalog-play.legislators.legislators-job-test
  (:use midje.sweet)
  (:use midje.cascalog)
  (:require [cascalog-play.legislators.legislators-job :as jobs]))

(facts "Given a legislator file location, then verify the following query types"
       (jobs/retrieve-legislators-at "dev-resources/legislators/legislators-current.yaml")
       => (produces [["00136" "B000944" "S307" 400050 "N00003535" 5051 "Sherrod Brown" "Sherrod Brown" 168 "gIQA3O2w9O" 29389 27018 "Sherrod" "Brown"]
                     ["00172" "C000127" "S275" 300018 "N00007836" 26137 "Maria Cantwell" "Maria Cantwell" 544 "gIQAZxKkDP" 39310 27122 "Maria" "Cantwell"]
                     ["01983" "M001183" "S338" 412391 "N00032838" 62864 "Joe Manchin" "Joe Manchin III" 1372 "gIQA82bVBP" 40915 7547 "Joe" "Manchin"]
                     ["01965" "B001267" "S330" 412330 "N00030608" 1031622 "Michael Bennet" "Michael Bennet" 1092 "gIQA8vao9O" 40910 110942 "Michael" "Bennet"]
                     ["01558" "B001230" "S354" 400013 "N00004367" 57884 "Tammy Baldwin" "Tammy Baldwin" 136 "gIQAgHwPAP" 29940 3470 "Tammy" "Baldwin"]]))

; bioguide: B000944
;thomas: '00136'
;lis: S307
;govtrack: 400050
;opensecrets: N00003535
;votesmart: 27018
;cspan: 5051
;wikipedia: Sherrod Brown
;ballotpedia: Sherrod Brown
;maplight: 168
;washington_post: gIQA3O2w9O
;icpsr: 29389

;name:
;first: Sherrod
;last: Brown
;official_full: Sherrod Brown