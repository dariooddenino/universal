(ns universal.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [universal.core-test]
   [universal.common-test]))

(enable-console-print!)

(doo-tests 'universal.core-test
           'universal.common-test)
