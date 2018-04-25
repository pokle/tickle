# tickle

Clojure Spec, but reimplemented as function combinators. Is it possible? I don't know. But I'm going to try.



## How to run the tests

[![CircleCI](https://circleci.com/gh/pokle/tickle.svg?style=svg)](https://circleci.com/gh/pokle/tickle)

`lein midje` will run all tests.

`lein midje namespace.*` will run only tests beginning with "namespace.".

`lein midje :autotest` will run all the tests indefinitely. It sets up a
watcher on the code files. If they change, only the relevant tests will be
run again.
