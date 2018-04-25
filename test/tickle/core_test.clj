(ns tickle.core-test
  (:require [midje.sweet :refer :all]
            [tickle.core :refer :all]))

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

(facts "either"
       (valid? (either number string) "sss") => true
       (valid? (either number string) 12345) => true
       (valid? (either number string) true) => false
       (explain (either number string) true) => "None of the specs (number,string) matched java.lang.Boolean: true")