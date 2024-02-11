(ns leebonn.sections.overlay
  (:require
    [leebonn.i18n :as i18n]))


(defn lang-selection
  [{:keys [transition common-text-colour]}]
  [:div {:class (str transition "pointer-events-none p-4 absolute text-xl w-full h-full text-right top-0 right-0 font-sans"
                     common-text-colour)}
   [:a {:class    "hover-squiggly pointer-events-auto"
        :on-click #(i18n/set-lang! :fr)} "FR"]
   [:span " | "]
   [:a {:class    "hover-squiggly pointer-events-auto"
        :on-click #(i18n/set-lang! :en)} "EN"]])


(defn overlay
  [context]
  [lang-selection context])
