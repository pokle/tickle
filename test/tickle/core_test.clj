(ns tickle.core-test
  (:require [midje.sweet :refer :all]
            [tickle.core :refer [string number] :as t]))

(facts "string"
       (valid? string "moo") => true
       (valid? string 12345) => false
       (explain string "moo") => "Valid"
       (explain string 1234) => "Not a string: java.lang.Long: 1234")

(facts "number"
       (valid? number 1234) => true
       (valid? number 1234.56) => true
       (valid? number "12345") => false
       (explain number "12345") => "Not a number: java.lang.String: \"12345\"")

(facts "or"
       (valid? (t/or number string) "sss") => true
       (valid? (t/or number string) 12345) => true
       (valid? (t/or number string) true) => false
       (explain (t/or number string) true) => "None of the specs (number,string) matched java.lang.Boolean: true")