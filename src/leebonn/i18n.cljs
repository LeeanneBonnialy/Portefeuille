(ns leebonn.i18n
  (:require
    [clojure.string :as str]
    [reagent.core :as r]))


(defn get-default-language
  []
  (let [options      (concat (.-languages js/navigator)
                             [(.-useLanguage js/navigator)]
                             [(.-language js/navigator)]
                             [(.-browserLanguage js/navigator)])
        best-default (some #(when (string? %)
                              (cond
                                (= % "en") :en
                                (= % "fr") :fr
                                (str/starts-with? % "en-") :en
                                (str/starts-with? % "fr-") :fr
                                :else nil))
                           options)]
    (or best-default :en)))


(def lang (r/atom (get-default-language)))


(def translations
  {:job-title   {:en "Junior Creative Strategist"}
   :thanks      {:en "Thanks for reading"}
   :sncf-comics {:fr (str/join "\n" (repeat 100 (str/join " " (repeat 100 "À"))))
                 :en (str/join "\n" (repeat 100 (str/join " " (repeat 100 "A"))))}})


(defn text
  [kw]
  (if-let [t (get-in translations [kw @lang])]
    [:div.whitespace-pre-line t]
    [:span.text-red-500 "missing[" (str kw) "]"]))


(defn set-lang!
  [l]
  (reset! lang l))
