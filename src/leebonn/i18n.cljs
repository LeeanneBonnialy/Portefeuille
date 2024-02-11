(ns leebonn.i18n
  (:require
    [reagent.core :as r]))


(def lang (r/atom :fr))


(def translations
  {:home               {:fr "FRhome"
                        :en "home"}
   :read-more          {:fr "FRread more"
                        :en "read more"}
   :sncf-comics-header {:fr "SNCF Bandeau"
                        :en "SNCF Comics"}
   :sncf-comics        {:fr "SNCF est drole"
                        :en "SNCF comics was cool.






                  SNCF comics was cool.

                  SNCF comics was cool. SNCF comics was cool. SNCF comics was cool."}})


(defn text
  [kw]
  (if-let [t (get-in translations [kw @lang])]
    [:div.whitespace-pre-line t]
    [:span.text-red-500 "missing[" (str kw) "]"]))


(defn set-lang!
  [l]
  (reset! lang l))
