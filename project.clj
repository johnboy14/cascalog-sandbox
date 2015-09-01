(defproject cascalog-play "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [cascalog "2.1.1"]
                 [org.apache.hadoop/hadoop-core "1.2.1"]
                 [cheshire "5.5.0"]
                 [midje "1.6.3"]
                 [midje-cascalog "0.4.0"]
                 [clj-yaml "0.4.0"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-midje "3.1.3"]])
