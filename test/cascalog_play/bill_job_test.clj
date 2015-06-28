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

  (jobs/retrieve-bills-at "dev-resources/bills/" :sconres) => (produces [["sconres1-114" "sconres" "114" "2015-01-07" "2015-06-27T07:17:17-04:00"
                                                                          "Vitter, David" "01609"
                                                                          "A concurrent resolution expressing the sense of Congress that a carbon tax is not in the economic interest of the United States."
                                                                          "REFERRED" "Taxation"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :sjres) => (produces [["sjres1-114" "sjres" "114" "2015-01-06" "2015-06-27T07:13:10-04:00"
                                                                        "Vitter, David" "01609"
                                                                        "A joint resolution proposing an amendment to the Constitution of the United States relative to limiting the number of terms that a Member of Congress may serve."
                                                                        "REFERRED"
                                                                        "Congress"]])
  (jobs/retrieve-bills-at "dev-resources/bills/" :s) => (produces [["s610-114" "s" "114" "2015-02-27" "2015-06-20T06:50:31-04:00"
                                                                    "Cardin, Benjamin L." "00174"
                                                                    "A bill to authorize the Secretary of the Interior to conduct a special resource study of P.S. 103 in West Baltimore, Maryland and for other purposes."
                                                                    "REFERRED" "Public lands and natural resources"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :hr) => (produces [["hr102-114" "hr" "114" "2015-01-06" "2015-06-27T06:22:03-04:00"
                                                                     "Conyers, John, Jr." "00229"
                                                                     "To establish a corporate crime database, and for other purposes."
                                                                     "REFERRED"
                                                                     "Crime and law enforcement"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :hres) => (produces [["hres100-114" "hres" "114" "2015-02-10" "2015-06-27T06:17:12-04:00"
                                                                       "Woodall, Rob" "02008"
                                                                       "Providing for consideration of the bill (S. 1) to approve the Keystone XL Pipeline, and providing for proceedings during the period from February 16, 2015, through February 23, 2015."
                                                                       "PASSED:SIMPLERES"
                                                                       "Congress"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :hjres) => (produces [["hjres10-114" "hjres" "114" "2015-01-07" "2015-06-27T07:11:04-04:00"
                                                                        "Johnson, Sam" "00603"
                                                                        "Providing for the reappointment of David M. Rubenstein as a citizen regent of the Board of Regents of the Smithsonian Institution."
                                                                        "ENACTED:SIGNED" "Government operations and politics"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :hconres) => (produces [["hconres10-114" "hconres" "114" "2015-01-27" "2015-06-27T07:12:14-04:00" "Fattah, Chaka" "00371"
                                                                          "Recognizing the challenges and burdens associated with the rising costs of a college education."
                                                                          "REFERRED"
                                                                          "Education"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :all) => (produces-some [["hres100-114" "hres" "114" "2015-02-10" "2015-06-27T06:17:12-04:00"
                                                                           "Woodall, Rob" "02008"
                                                                           "Providing for consideration of the bill (S. 1) to approve the Keystone XL Pipeline, and providing for proceedings during the period from February 16, 2015, through February 23, 2015."
                                                                           "PASSED:SIMPLERES"
                                                                           "Congress"]
                                                                          ["hr102-114" "hr" "114" "2015-01-06" "2015-06-27T06:22:03-04:00"
                                                                           "Conyers, John, Jr." "00229"
                                                                           "To establish a corporate crime database, and for other purposes."
                                                                           "REFERRED"
                                                                           "Crime and law enforcement"]]))
