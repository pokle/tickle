(ns tickle.core-test
  (:require [midje.sweet :refer :all]
            [tickle.core :refer :all]))

(facts "string"
       (valid? string "moo") => true
       (valid? string 12345) => false
       (explain string "moo") => "Valid"
       (explain string 1234) => "Not a string: java.lang.Long: 1234")

(facts "regexp"
       (valid? (regexp #"moo") "moo") => true
       (valid? (regexp #"moo") 12345) => false
       (explain (regexp #"moo") "moo") => "Valid"
       (explain (regexp #"moo") 12345) => "Regular expression #\"moo\" does not match java.lang.Long: 12345"
       (explain (regexp #"moo") "poo") => "Regular expression #\"moo\" does not match java.lang.String: \"poo\"")

(facts "number"
       (valid? number 1234) => true
       (valid? number 1234.56) => true
       (valid? number "12345") => false
       (explain number "12345") => "Not a number: java.lang.String: \"12345\"")

(facts "either"
       (valid? (either number string) "sss") => true
       (valid? (either number string) 12345) => true
       (valid? (either number string (regexp #"meh")) "meh") => true
       (valid? (either number string) true) => false
       (explain (either number string) true) => "None of the specs (number,string) matched java.lang.Boolean: true")

(facts "all"

       (valid? (all string (regexp #"(ba){1,3}")) "bababa") => true
       (valid? (all string (regexp #"(ba){1,3}")) "mooooo") => false
       (explain (all string (regexp #"(ba){1,3}")) "mooooo") => "Not all the specs (string,regexp) matched java.lang.String: \"mooooo\""
        )