(defproject io.axrs/dominium "0.0.0-SNAPSHOT"
  :description "ClojureScript Browser Utilities"
  :url "https://github.com/axrs/dominium"
  :license {:name         "Eclipse Public License - v 2.0"
            :url          "https://www.eclipse.org/legal/epl-v20.html"
            :distribution :repo}
  :plugins [[lein-codox "0.10.7"]]
  :min-lein-version "2.8.1"
  :dependencies []
  :clean-targets ["target"]
  :source-paths ["src"]
  :test-paths ["test"]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.1"]
                                  [org.clojure/clojurescript "1.10.764"]]}}
  :codox {:language    :clojurescript
          :output-path "docs"
          :source-uri "https://github.com/axrs/dominium/blob/{version}/{filepath}#L{line}"})
