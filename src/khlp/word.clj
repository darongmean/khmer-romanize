(ns khlp.word)

(defn words [text] (re-seq #"[a-z]+" (.toLowerCase text)))

(defn train [features]
  (let [toGroupByCountFeature (fn [model feature]
                                (assoc model feature (inc (get model feature 1))))]
    (reduce toGroupByCountFeature {} features)))

(def *nwords* (train (words (slurp "resources/training-data/en/big.txt"))))

(defn levenshtein-recur0 [s len-s t len-t]
  (cond
   (== len-s 0) len-t
   (== len-t 0) len-s
   :else (let [cost (if (= (.charAt s (- len-s 1))
                            (.charAt t (- len-t 1)))
                      0
                      1)]
           (min (+ 1 (levenshtein-recur0 s (- len-s 1) t len-t))
                (+ 1 (levenshtein-recur0 s len-s t (- len-t 1)))
                (+ cost (levenshtein-recur0 s (- len-s 1) t (- len-t 1)))))))

(defn levenshtein
  "Take two words and return how far they are.
   Only inserts, removals, and substitutions are considered; No transposing two characters."
  [word1 word2]
  (levenshtein-recur0 word1 (count word1) word2 (count word2)))
