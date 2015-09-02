(ns com.pav.jobs.main
  (:use cascalog.api)
  (:require [com.pav.jobs.bill.bill-jobs :as bills])
  (:gen-class))

(defmain BillDetailsJob [input output]
    (bills/run-bill-detail-job input output))
