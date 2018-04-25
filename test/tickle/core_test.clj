(ns tickle.core-test
  (:require [midje.sweet :refer :all]
            [tickle.core :refer :all]))

(facts "string"
       (valid? string "moo") => true
       (valid? string 12345) => false
       (explain string 1234) => "java.lang.Long: 1234 is not a string")

(facts "number"
       (valid? number 1234) => true
       (valid? number 1234.56) => true
       (valid? number "12345") => false
       (explain number "12345") => "java.lang.String: \"12345\" is not a number")

