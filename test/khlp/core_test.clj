(ns khlp.core-test
  (:use midje.sweet)
  (:require [khlp.core :as kh]))

(fact "ប and 'ceoung' រ has special spelling"
  (kh/to-latin "ប្រ") => "pra")

(fact "'ceoung' special character should not preserve"
  (kh/to-latin "ព្រ") => "pro")