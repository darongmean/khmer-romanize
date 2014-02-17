(ns khlp.core-test
  (:use midje.sweet)
  (:require [khlp.word :as word]))

;; Util
;; -----
(defmacro time-ms [f]
  `(let [start# (System/nanoTime)]
    ~@f
    (/ (- (System/nanoTime) start#) 1e6)))

;; logic check
;; ------------
(tabular
 (fact "recursive calculate levenshtein between two strings"
      (word/levenshtein ?a ?b) => ?c)
 ?a ?b ?c
 "" "" 0
 "" "sitting" 7
 "kitten" "" 6
 "kitten" "sitting" 3
 "saturday" "sunday" 3)

;; performance check
;; -----------------
(tabular
 (fact "recursive calculate levenshtein distance between two strings should be between 0.03ms"
      (time-ms (word/levenshtein ?a ?b)) => #(< 0 % 0.03))
 ?a ?b
 "" ""
 "" "sitting"
 "kitten" ""
 "kitten" "sitting"
 "saturday" "sunday"
 "aaaaaawwwaaaaaaaaaauuuaaaaaaaaaa" "bbbbbbbbcccbbbbbbbnnnbbb")


;; logic check
;; ------------
(tabular
 (fact "iterative calculate levenshtein between two strings"
      (last (word/levenshtein-itr ?a ?b)) => ?c)
 ?a ?b ?c
 "" "" 0
 "" "sitting" 7
 "kitten" "" 6
 "kitten" "sitting" 3
 "saturday" "sunday" 3)

;; performance check
;; -----------------
(tabular
 (fact "iterative calculate levenshtein distance between two strings should be between 0.03ms"
      (time-ms (word/levenshtein-itr ?a ?b)) => #(< 0 % 0.03))
 ?a ?b
 "" ""
 "" "sitting"
 "kitten" ""
 "kitten" "sitting"
 "saturday" "sunday"
 "aaaaaawwwaaaaaaaaaauuuaaaaaaaaaa" "bbbbbbbbcccbbbbbbbnnnbbb")
