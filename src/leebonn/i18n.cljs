(ns leebonn.i18n
  (:require
    [clojure.string :as str]
    [reagent.core :as r]))


(defn get-default-language
  []
  #_(let [options      (concat (.-languages js/navigator)
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
    (or best-default :en))
  :fr)


(def lang (r/atom (get-default-language)))


(def translations
  {:job-title   {:en "Communication strategist"
                 :fr "Stratège en communication"}

   :competency-1 {:en "very cute"}
   :competency-2 {:en "best smile"}
   :competency-3 {:en "did i mention cute"}
   :competency-4 {:en "courageous"}
   :competency-5 {:en "most well read"}
   :competency-6 {:en "light of my heart"}

   :sncf-comics {:fr (str/join "\n" (repeat 100 (str/join " " (repeat 100 "À"))))
                 :en (str/join "\n" (repeat 100 (str/join " " (repeat 100 "A"))))}


   :thanks {:en "Thanks for reading"}})


(defn synopsis
  [kw]
  (str kw)
  (cond
    (keyword? kw) (name kw)
    (map? kw) (apply str (take 15 (first (vals kw))))
    :else (str kw)))


(defn text
  ([attrs kw]
   (if-let [t (get-in translations [kw @lang])]
     [:div.whitespace-pre-line [:span attrs t]]
     (if-let [t (and (map? kw) (get kw @lang))]
       [:div.whitespace-pre-line [:span attrs t]]
       [:div.whitespace-pre-line.text-red-500 (str "missing[" (synopsis kw) "]")])))
  ([kw]
   (text {} kw)))


(defn set-lang!
  [l]
  (reset! lang l))
