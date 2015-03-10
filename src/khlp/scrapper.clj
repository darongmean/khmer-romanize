(ns khlp.scrapper
  (:require [clj-webdriver.taxi :as browser]
            [clojure.string]
            [clojure.java.io :as io]))


(defn to-translit [word]
  (browser/to "https://translate.google.com/")
  (browser/input-text "#source" word)
  (browser/wait-until #(not (clojure.string/blank? (browser/text "#src-translit"))) 10000)
  (browser/text "#src-translit"))

(defn to-word [word-file]
  (with-open [rdr (io/reader (io/file word-file))]
    (doall (line-seq rdr))))

(defn write-translit [file-name translits]
  (spit
    file-name
    (apply str
      (butlast (interleave (mapcat identity translits) (cycle [\newline "\n%%\n"]))))))