(ns com.pav.jobs.legislators.legislators-job-test
  (:use midje.sweet)
  (:use midje.cascalog)
  (:require [com.pav.jobs.legislators.legislators-job :as jobs]))

(facts "Given a legislator file location, then verify the following query types"
       (jobs/retrieve-legislators-at "dev-resources/legislators/legislators-current.yaml")
       => (produces [["00136" "B000944" "S307" 400050 "N00003535" 5051 "Sherrod Brown" "Sherrod Brown" 168 "gIQA3O2w9O" 29389 27018 "Sherrod" "Brown" "1952-11-09" "M" "Lutheran" "sen" "2013-01-03" "2019-01-03" "OH" "Democrat" 1 "http://www.brown.senate.gov" "713 Hart Senate Office Building Washington DC 20510" "202-224-2315" "202-228-6321" "http://www.brown.senate.gov/contact" "713 Hart Senate Office Building" "senior" "http://www.brown.senate.gov/rss/feeds/?type=all&amp;"]
                     ["00172" "C000127" "S275" 300018 "N00007836" 26137 "Maria Cantwell" "Maria Cantwell" 544 "gIQAZxKkDP" 39310 27122 "Maria" "Cantwell" "1958-10-13" "F" "Roman Catholic" "sen" "2013-01-03" "2019-01-03" "WA" "Democrat" 1 "http://www.cantwell.senate.gov" "511 Hart Senate Office Building Washington DC 20510" "202-224-3441" "202-228-0514" "http://www.cantwell.senate.gov/public/index.cfm/email-maria" "511 Hart Senate Office Building" "junior" "http://www.cantwell.senate.gov/public/index.cfm/rss/feed"]
                     ["01983" "M001183" "S338" 412391 "N00032838" 62864 "Joe Manchin" "Joe Manchin III" 1372 "gIQA82bVBP" 40915 7547 "Joe" "Manchin" "1947-08-24" "M" nil "sen" "2013-01-03" "2019-01-03" "WV" "Democrat" 1 "http://www.manchin.senate.gov" "306 Hart Senate Office Building Washington DC 20510" "202-224-3954" "202-228-0002" "http://www.manchin.senate.gov/public/index.cfm/contact-form" "306 Hart Senate Office Building" "senior" "http://www.manchin.senate.gov/public/index.cfm/rss/feed"]
                     ["01965" "B001267" "S330" 412330 "N00030608" 1031622 "Michael Bennet" "Michael Bennet" 1092 "gIQA8vao9O" 40910 110942 "Michael" "Bennet" "1964-11-28" "M" nil "sen" "2011-01-05" "2017-01-03" "CO" "Democrat" 3 "http://www.bennet.senate.gov" "261 Russell Senate Office Building Washington DC 20510" "202-224-5852" "202-228-5036" "http://www.bennet.senate.gov/contact/email" "261 Russell Senate Office Building" "senior" "http://www.bennet.senate.gov/rss/feeds/?type=news"]
                     ["01558" "B001230" "S354" 400013 "N00004367" 57884 "Tammy Baldwin" "Tammy Baldwin" 136 "gIQAgHwPAP" 29940 3470 "Tammy" "Baldwin" "1962-02-11" "F" nil "sen" "2013-01-03" "2019-01-03" "WI" "Democrat" 1 "http://www.baldwin.senate.gov" "717 Hart Washington DC 20510" "202-224-5653" "202-225-6942" "http://www.baldwin.senate.gov/contact" "717 Hart" "junior" "http://www.baldwin.senate.gov/rss/feeds/?type=all"]]))

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

;bio:
;birthday: '1952-11-09'
;gender: M
;religion: Lutheran

;type: sen
;start: '2013-01-03'
;end: '2019-01-03'
;state: OH
;party: Democrat
;class: 1
;url: http://www.brown.senate.gov
;address: 713 Hart Senate Office Building Washington DC 20510
;phone: 202-224-2315
;fax: 202-228-6321
;contact_form: http://www.brown.senate.gov/contact
;office: 713 Hart Senate Office Building
;state_rank: senior
;rss_url: http://www.brown.senate.gov/rss/feeds/?type=all&amp;