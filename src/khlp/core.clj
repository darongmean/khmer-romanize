(ns khlp.core
  (:gen-class)
  ;(:require [khlp.word :as word])
  (:import [com.ibm.icu.text Transliterator Normalizer2]))

;(defn -main
;  "I don't do a whole lot ... yet."
;  [& args]
;  (println "Hello, World!"))
;
;
;(= (word/romanize "ក") "ka'")
;
;
;(sort (enumeration-seq (Transliterator/getAvailableIDs)))
;
;(Transliterator/getDisplayName "k")
;
;(def input "កាគា")
;
;(let [rules ":: NFD (NFC);
;             ក > k|'~A';
;             គ > k|'~O';
;       '~A' ា > a;
;       '~O' ា > ea;
;          ['~A' '~O'] > ;
;
;             :: NFC (NFD);"
;      trans  (Transliterator/createFromRules "Khmer-UNGEN/GNKC" rules Transliterator/FORWARD)]
;  (.transliterate trans input))

;(let [nfc (Normalizer2/getNFCInstance)
;      nfd (Normalizer2/getNFDInstance)
;      nfkcc (Normalizer2/getNFKCCasefoldInstance)
;      nfkc (Normalizer2/getNFKCInstance)
;      nfkd (Normalizer2/getNFKDInstance)]
;  (.normalize nfkd input))

(def latin-kfg-rules
  "
  \\u1780 > ka;
  \\u1781 > kha;
  \\u1782 > ko;
  \\u1783 > kho;
  \\u1784 > ngo;

  \\u1785 > cha;
  \\u1786 > chha;
  \\u1787 > cho;
  \\u1788 > chho;
  \\u1789 > nho;

  \\u178a > da;
  \\u178b > tha;
  \\u178c > do;
  \\u178d > tho;
  \\u178e > na;

  \\u178f > ta;
  \\u1790 > tha;
  \\u1791 > to;
  \\u1792 > tho;
  \\u1793 > no;

  \\u1794\\u17D2\\u179a > pra;
  \\u1794 > ba;
  \\u1795 > pha;
  \\u1796 > p|o;
  \\u1797 > pho;
  \\u1798 > mo;

  \\u1799 > yo;
  \\u179a > ro;
  \\u179b > lo;
  \\u179c > vo;
  \\u179f > sa;
  \\u17a0 > ha;
  \\u17a1 > la;
  \\u17a2 > a;
  \\u17a3 > a;

  \\u17b6 > a;
  \\u17b7 > e;
  \\u17b8 > ei;
  \\u17b9 > oe;
  \\u17ba > eu;
  \\u17bb > o;
  \\u17bc > au;
  \\u17bd > uo;
  \\u17be > ae;
  \\u17bf > ue;
  \\u17c0 > ie;
  \\u17c1 > e;
  \\u17c2 > e;
  \\u17c3 > ai;
  \\u17c4 > ao;
  \\u17c5 > aw;
  \\u17c6 > om;
  \\u17c7 > ah;

  ::Null; # Re-parse string from the beginning

  [ao] \\u17D2 > ; #(CUOENG) special character convert to empty string
  ")

;(let [rules "
;             \\u1780 > ka;
;             \\u1781 > kha;
;             \\u1782 > ko;
;             \\u1783 > kho;
;             \\u1784 > ngo;
;
;             \\u1785 > cha;
;             \\u1786 > chha;
;             \\u1787 > cho;
;             \\u1788 > chho;
;             \\u1789 > nho;
;
;             \\u178a > da;
;             \\u178b > tha;
;             \\u178c > do;
;             \\u178d > tho;
;             \\u178e > na;
;
;             \\u178f > ta;
;             \\u1790 > tha;
;             \\u1791 > to;
;             \\u1792 > tho;
;             \\u1793 > no;
;
;             \\u1794\\u17D2\\u179a > pra;
;             \\u1794 > ba;
;             \\u1795 > pha;
;             \\u1796 > p|o;
;             \\u1797 > pho;
;             \\u1798 > mo;
;
;             \\u1799 > yo;
;             \\u179a > ro;
;             \\u179b > lo;
;             \\u179c > vo;
;             \\u179f > sa;
;             \\u17a0 > ha;
;             \\u17a1 > la;
;             \\u17a2 > a;
;             \\u17a3 > a;
;
;             \\u17b6 > a;
;             \\u17b7 > e;
;             \\u17b8 > ei;
;             \\u17b9 > oe;
;             \\u17ba > eu;
;             \\u17bb > o;
;             \\u17bc > au;
;             \\u17bd > uo;
;             \\u17be > ae;
;             \\u17bf > ue;
;             \\u17c0 > ie;
;             \\u17c1 > e;
;             \\u17c2 > e;
;             \\u17c3 > ai;
;             \\u17c4 > ao;
;             \\u17c5 > aw;
;             \\u17c6 > om;
;             \\u17c7 > ah;
;
;             ::Null; # Re-parse string from the beginning
;
;             [ao] \\u17D2 > ; #(CUOENG) special character
;             "
;      ;; todo: pra, pro; \\u179d, \\u179e, \\u17a5 - \\u17b5; om, am, oh, eh, aoh
;      trans (Transliterator/createFromRules "Khmer-Latin/FKG" rules Transliterator/FORWARD)]
;  (.transliterate trans))

(defn to-latin [word]
  (->
    (Transliterator/createFromRules "Khmer-Latin/FKG" latin-kfg-rules Transliterator/FORWARD)
    (.transliterate word)))

(def to-hex (Transliterator/getInstance "Any-Hex/Java"))