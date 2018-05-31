(defproject tickle "0.0.1-SNAPSHOT"
  :description "Functional combinators implementation of spec"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [egamble/let-else "1.0.7"]]
  :profiles {:dev {:dependencies [[midje "1.9.1" :exclusions [[org.clojure/clojure]]]]
                   :plugins [[lein-midje "3.2.1"]]}

             ;; You can add dependencies that apply to `lein midje` below.
             ;; An example would be changing the logging destination for test runs.
             :midje {}})
             ;; Note that Midje itself is in the `dev` profile to support
             ;; running autotest in the repl.


