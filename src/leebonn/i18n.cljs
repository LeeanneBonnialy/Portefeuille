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

   :competency-1 {:en "very cute"}
   :competency-2 {:en "best smile"}
   :competency-3 {:en "did i mention cute"}
   :competency-4 {:en "courageous"}
   :competency-5 {:en "most well read"}
   :competency-6 {:en "light of my heart"}

   :sncf-comics {:fr (str/join "\n" (repeat 100 (str/join " " (repeat 100 "À"))))
                 :en (str/join "\n" (repeat 100 (str/join " " (repeat 100 "A"))))}


   :thanks {:en "Thanks for reading"}})


(defn text
  [kw]
  (if-let [t (get-in translations [kw @lang])]
    [:div.whitespace-pre-line t]
    [:span.text-red-500 "missing[" (str kw) "]"]))


(defn set-lang!
  [l]
  (reset! lang l))
