(ns leebonn.sections.overlay
  (:require
    [leebonn.i18n :as i18n]
    [leebonn.util :as util]))


(defn lang-selection
  [{:keys [transition text-colour]}]
  [:div (util/combine-style transition
                            {:class "pointer-events-none p-4 absolute text-xl w-full h-full text-right top-0 right-0 font-sans"
                             :style {:color text-colour}})
   [:a {:class    "hover-squiggly pointer-events-auto cursor-pointer"
        :on-click #(i18n/set-lang! :fr)} "FR"]
   [:span " | "]
   [:a {:class    "hover-squiggly pointer-events-auto cursor-pointer"
        :on-click #(i18n/set-lang! :en)} "EN"]])


(defn overlay
  [context]
  [lang-selection context])
