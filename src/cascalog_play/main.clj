(ns cascalog-play.main
  (:use cascalog.api)
  (:require [cascalog-play.bill.bill-jobs :as bills])
  (:gen-class))

(defmain BillDetailsJob [input output]
    (bills/run-bill-detail-job input output))
