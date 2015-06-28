(ns cascalog-play.bill-job-test
  (:use midje.sweet)
  (:use midje.cascalog)
  (:require [cascalog-play.bill-jobs :as jobs]))

(facts "Given a bills directory, When specifying the type return only those bills"
  (jobs/retrieve-bills-at "dev-resources/bills/" :sres) => (produces [["sres9-114" "sres" "114" "2015-01-06" "2015-06-27T07:13:36-04:00"
                                                                      "McConnell, Mitch" "01395"
                                                                      "A resolution notifying the President of the United States of the election of the Secretary of the Senate."
                                                                      "PASSED:SIMPLERES"
                                                                      "Congress"]])
  (jobs/retrieve-bills-at "dev-resources/bills/" :hr) => (produces [["hr102-114" "hr" "114" "2015-01-06" "2015-06-27T06:22:03-04:00"
                                                                     "Conyers, John, Jr." "00229"
                                                                     "To establish a corporate crime database, and for other purposes."
                                                                     "REFERRED"
                                                                     "Crime and law enforcement"]]))
