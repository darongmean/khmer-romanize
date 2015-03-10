(defproject khlp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.ibm.icu/icu4j "54.1.1"]
                 [org.languagetool/language-km "2.8"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [clj-webdriver "0.6.1" :exclusions [org.seleniumhq.selenium/selenium-java
                                                     org.seleniumhq.selenium/selenium-api
                                                     org.seleniumhq.selenium/selenium-remote-driver
                                                     org.seleniumhq.selenium/selenium-server]]
                 [org.seleniumhq.selenium/selenium-java "2.44.0"]
                 [midje "1.6.3"]
                 [org.clojure/test.check "0.7.0"]
                 [org.clojure/core.cache "0.6.3"]
                 [org.clojure/core.memoize "0.5.6" :exclusions [org.clojure/core.cache]]]
  :plugins [[lein-midje "3.1.3"]]
  :main ^:skip-aot khlp.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
