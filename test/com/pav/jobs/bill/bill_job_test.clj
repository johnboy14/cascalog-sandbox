(ns com.pav.jobs.bill.bill-job-test
  (:use midje.sweet)
  (:use midje.cascalog)
  (:require [com.pav.jobs.bill.bill-jobs :as jobs]))

(facts "Given a bills directory, When specifying the bill type (e.g :sres for senate resolution), Then return only those bills"
  (jobs/retrieve-bills-at "dev-resources/bills/" :sres) => (produces [["sres9-114" "sres" "114" "2015-01-06" "2015-08-22T06:49:28-04:00"
                                                                          "McConnell, Mitch" "01395"
                                                                          "A resolution notifying the President of the United States of the election of the Secretary of the Senate."
                                                                          "PASSED:SIMPLERES" "Congress"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :sconres) => (produces [["sconres1-114" "sconres" "114" "2015-01-07" "2015-08-22T06:51:28-04:00"
                                                                          "Vitter, David" "01609"
                                                                          "A concurrent resolution expressing the sense of Congress that a carbon tax is not in the economic interest of the United States."
                                                                          "REFERRED" "Taxation"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :sjres) => (produces [["sjres1-114" "sjres" "114" "2015-01-06" "2015-08-22T06:49:14-04:00"
                                                                        "Vitter, David" "01609"
                                                                        "A joint resolution proposing an amendment to the Constitution of the United States relative to limiting the number of terms that a Member of Congress may serve."
                                                                        "REFERRED"
                                                                        "Congress"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :s) => (produces [["s295-114" "s" "114" "2015-01-28" "2015-08-01T06:54:29-04:00"
                                                                       "Hatch, Orrin G." "01351"
                                                                       "A bill to amend section 2259 of title 18, United States Code, and for other purposes."
                                                                       "PASS_OVER:SENATE" "Crime and law enforcement"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :hr) => (produces [["hr102-114" "hr" "114" "2015-01-06" "2015-08-22T06:20:00-04:00"
                                                                     "Conyers, John, Jr." "00229"
                                                                     "To establish a corporate crime database, and for other purposes."
                                                                     "REFERRED"
                                                                     "Crime and law enforcement"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :hres) => (produces [["hres100-114" "hres" "114" "2015-02-10" "2015-08-22T06:16:32-04:00"
                                                                       "Woodall, Rob" "02008"
                                                                       "Providing for consideration of the bill (S. 1) to approve the Keystone XL Pipeline, and providing for proceedings during the period from February 16, 2015, through February 23, 2015."
                                                                       "PASSED:SIMPLERES"
                                                                       "Congress"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :hjres) => (produces [["hjres10-114" "hjres" "114" "2015-01-07" "2015-08-22T06:48:10-04:00"
                                                                        "Johnson, Sam" "00603"
                                                                        "Providing for the reappointment of David M. Rubenstein as a citizen regent of the Board of Regents of the Smithsonian Institution."
                                                                        "ENACTED:SIGNED" "Government operations and politics"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :hconres) => (produces [["hconres10-114" "hconres" "114" "2015-01-27" "2015-08-22T06:48:42-04:00" "Fattah, Chaka" "00371"
                                                                          "Recognizing the challenges and burdens associated with the rising costs of a college education."
                                                                          "REFERRED"
                                                                          "Education"]])

  (jobs/retrieve-bills-at "dev-resources/bills/" :all) => (produces [["s295-114" "s" "114" "2015-01-28" "2015-08-01T06:54:29-04:00" "Hatch, Orrin G." "01351" "A bill to amend section 2259 of title 18, United States Code, and for other purposes." "PASS_OVER:SENATE" "Crime and law enforcement"]
                                                                        ["hjres10-114" "hjres" "114" "2015-01-07" "2015-08-22T06:48:10-04:00" "Johnson, Sam" "00603" "Providing for the reappointment of David M. Rubenstein as a citizen regent of the Board of Regents of the Smithsonian Institution." "ENACTED:SIGNED" "Government operations and politics"]
                                                                        ["sjres1-114" "sjres" "114" "2015-01-06" "2015-08-22T06:49:14-04:00" "Vitter, David" "01609" "A joint resolution proposing an amendment to the Constitution of the United States relative to limiting the number of terms that a Member of Congress may serve." "REFERRED" "Congress"]
                                                                        ["hres100-114" "hres" "114" "2015-02-10" "2015-08-22T06:16:32-04:00" "Woodall, Rob" "02008" "Providing for consideration of the bill (S. 1) to approve the Keystone XL Pipeline, and providing for proceedings during the period from February 16, 2015, through February 23, 2015." "PASSED:SIMPLERES" "Congress"]
                                                                        ["hr102-114" "hr" "114" "2015-01-06" "2015-08-22T06:20:00-04:00" "Conyers, John, Jr." "00229" "To establish a corporate crime database, and for other purposes." "REFERRED" "Crime and law enforcement"]
                                                                        ["hconres10-114" "hconres" "114" "2015-01-27" "2015-08-22T06:48:42-04:00" "Fattah, Chaka" "00371" "Recognizing the challenges and burdens associated with the rising costs of a college education." "REFERRED" "Education"]
                                                                        ["sres9-114" "sres" "114" "2015-01-06" "2015-08-22T06:49:28-04:00" "McConnell, Mitch" "01395" "A resolution notifying the President of the United States of the election of the Secretary of the Senate." "PASSED:SIMPLERES" "Congress"]
                                                                        ["sconres1-114" "sconres" "114" "2015-01-07" "2015-08-22T06:51:28-04:00" "Vitter, David" "01609" "A concurrent resolution expressing the sense of Congress that a carbon tax is not in the economic interest of the United States." "REFERRED" "Taxation"]]))



(facts "Given a bills directory and a bill type, Then retrieve all bill summaries for that type"
  (jobs/retrieve-bill-summaries-at "dev-resources/bills/" :hconres) => (produces [["hconres10-114" "Recognizes the challenges and burdens associated with rising college costs.\n\nAcknowledges the benefits of community colleges in providing an affordable and cost-effective way to obtain a college degree.\n\n Expresses support for the goals and ideals of the America's College Promise proposal.\n\nExpresses a commitment to pursuing strategies in the 114th Congress that will make college more affordable and accessible for all students."]])

  (jobs/retrieve-bill-summaries-at "dev-resources/bills/" :all) => (produces-some [["s295-114" "Amy and Vicky Child Pornography Victim Restitution Improvement Act of 2015\n\n (Sec. 3) Amends the federal criminal code to expand the definition of \"full amount of the victim's losses\" for purposes of provisions governing mandatory restitution of victims of offenses involving sexual exploitation and other abuse of children to include medical services, physical and occupational therapy or rehabilitation, and lost income for the victim's lifetime, as well as any losses suffered by the victim from any sexual act or contact or sexually explicit conduct in preparation for or during the production of child pornography depicting the victim involved in the offense.\n\nSets forth guidelines for determining restitution where the victim of of a specified child pornography offense was harmed by one defendant (requiring restitution for not less than the full amount of the victim's losses) or by more than one defendant (requiring restitution for not more than the full amount of the victim's losses and not less than specified minimum amounts for certain offenses).\n\nRequires joint and several liability where there are multiple defendants and allows each defendant who is ordered to pay restitution and who has made full payment to the victim equal to or exceeding the specified minimum amount to recover contribution from any other defendant ordered to pay. Sets forth contribution claim procedures.\n\nRequires the Attorney General to report to Congress within one year after enactment of this Act on any progress of the Department of Justice in obtaining restitution for victims of such offenses."] ["hjres10-114" "(This measure has not been amended since it was introduced. The summary of that version is repeated here.)\n\nReappoints David M. Rubenstein as a citizen regent of the Board of Regents of the Smithsonian Institution."]]))


(facts "Given a bills directory and a bill type, Then retrieve all bills and subsequent subjects"
  (jobs/retrieve-bill-subject-terms-at "dev-resources/bills/" :s) => (produces [["s295-114" "Child safety and welfare"] ["s295-114" "Congressional oversight"] ["s295-114" "Crime and law enforcement"] ["s295-114" "Crime victims"] ["s295-114" "Crimes against children"] ["s295-114" "Criminal procedure and sentencing"] ["s295-114" "Domestic violence and child abuse"] ["s295-114" "Pornography"] ["s295-114" "Sex offenses"]]))

(fact "Given a bills directory and a bill type, retrieve all bill and co-sponsor relationships"
  (jobs/retrieve-bill-cosponsor-relationships-at "dev-resources/bills/" :hjres) => (produces [["hjres10-114" "Becerra, Xavier" "2015-01-07" "CA" "00070"]
                                                                                             ["hjres10-114" "Cole, Tom" "2015-01-07" "OK" "01742"]]))

(fact "Given a bills directory and a bill type, retrieve all bill actions"
  (jobs/retrieve-bill-actions-at "dev-resources/bills/" :hjres) => (produces [["hjres10-114" "2015-01-07" "Referred to the House Committee on House Administration."]
                                                                              ["hjres10-114" "2015-03-26T12:29:00-04:00" "Mr. Davis, Rodney asked unanimous consent to discharge from committee and consider."]
                                                                              ["hjres10-114" "2015-03-26T12:29:00-04:00" "Committee on House Administration discharged."]
                                                                              ["hjres10-114" "2015-03-26T12:29:00-04:00" "Considered by unanimous consent."]
                                                                              ["hjres10-114" "2015-03-26T12:30:00-04:00" "On passage Passed without objection."]
                                                                              ["hjres10-114" "2015-03-26T12:30:00-04:00" "Motion to reconsider laid on the table Agreed to without objection."]
                                                                              ["hjres10-114" "2015-03-26" "Received in the Senate, read twice."]
                                                                              ["hjres10-114" "2015-03-27" "Passed Senate without amendment by Unanimous Consent."]
                                                                              ["hjres10-114" "2015-03-27" "Message on Senate action sent to the House."]
                                                                              ["hjres10-114" "2015-04-01" "Presented to President."]
                                                                              ["hjres10-114" "2015-04-07" "Signed by President."]
                                                                              ["hjres10-114" "2015-04-07" "Became Public Law No: 114-9."]]))
