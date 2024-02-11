(ns leebonn.sections.projects
  (:require [reagent.core :as r]
            [leebonn.i18n :as i18n]
            [leebonn.image-loader :as img]
            [leebonn.util :as util]))


(defn project
  [{:keys [scene narrow? transition common-text-colour]} active-scene content]
  (let [dir         (if narrow?
                      "x"
                      "x")
        translation (condp = scene
                      (dec active-scene) (str " translate-" dir "-[100%] ")
                      active-scene (str " translate-" dir "-[0%] ")
                      (inc active-scene) (str " translate-" dir "-[-100%] ")
                      (str " hidden translate-" dir "-[0%] "))]
    [:div {:class (str transition common-text-colour "font-sans absolute text-xl w-full h-full flex"
                       (condp = scene
                         (dec active-scene) " pointer-events-none opacity-0"
                         active-scene " opacity-100"
                         (inc active-scene) " pointer-events-none opacity-0"
                         " hidden"))}
     [:div {:class (str transition "flex w-full h-full" translation)}
      content]]))


(defn sncf
  [_]
  (let [truncate? (r/atom false)
        with-border-col (fn [classes raw-text-colour]
                          (str classes " border-" raw-text-colour " "))]
    (fn [{:keys [scene transition bg-colour bg-colour-raw self-scene raw-text-colour] :as _context}]
      [:div {:class "w-full h-full flex flex-col"}
       [:div {:class (with-border-col "w-full h-16 border-b flex-shrink-0 flex-grow-0" raw-text-colour)}]

       [:div {:class (with-border-col "w-full h-32 flex border-b flex-shrink-0 flex-grow-0" raw-text-colour)}
        [:div {:class (with-border-col "border-r flex-grow" raw-text-colour)}]
        [:div {:class "w-32 h-full flex justify-end group flex-shrink-0 flex-grow-0 cursor-pointer"}
         [:svg {:class "w-full h-full group-hover:w-1/3 group-hover:h-1/3 transition-all" :viewBox "0 0 100 100" :xmlns "http://www.w3.org/2000/svg"}
          [:circle {:r "45" :cx "50" :cy "50" :fill "currentColor"}]]]]

       [:div {:class "flex flex-grow"}
        [:div {:class "w-16 flex-shrink-0 flex-grow-0"}]
        [:div {:class "flex-grow p-4"}
         [img/deferred-image nil nil "sncf_comics2.jpg" {:class "rounded-tl-full object-cover w-full h-full"}]]

        ]

       #_[:div.col-span-1.row-span-2 {:class "border-b border-black"} "abc"]
       #_[:div.col-span-10.row-span-8 {:class "border-black"} "abc"]]
      #_[:div {:class "m-auto w-2/3 h-2/3"}
       [:div.w-full.h-full.relative
        [img/deferred-image nil nil "sncf_comics2.jpg" {:class "absolute object-cover w-full h-full pb-2 rounded-full"}]
        [:div {:class "relative flex max-h-[100%] w-full"}
         [:span.flex {:class "w-1/2"}]
         [:div {:class (str transition bg-colour "flex max-w-[50%] min-w-[50%] overflow-clip px-4 pb-3 rounded-bl-lg")}
          [:div.flex.flex-col
           [:div.flex [util/header :sncf-comics-header]]
           [:div.flex.truncate.flex-col
            [i18n/text :sncf-comics]
            [util/observer #(reset! truncate? (not %))]]
           (when (and (= scene self-scene) @truncate?)
             [:div.relative
              [:div {:class (str transition "translate-y-[-100%] absolute w-full h-6 bg-gradient-to-t from-" bg-colour-raw " to-transparent")}]
              [:span.flex {:class "mt-[-13px]"} "..."]])
           [:span.flex.pt-2 [i18n/text :read-more]]]]]]])))


(defn lart-dans-la-rue
  [_]
  (let [truncate? (r/atom false)]
    (fn [{:keys [scene transition bg-colour bg-colour-raw self-scene] :as _context}]
      [:div {:class "m-auto w-2/3 h-2/3"}
       [:div.w-full.h-full.relative
        [img/deferred-image nil nil "sncf_comics2.jpg" {:class "absolute object-cover w-full h-full pb-2 rounded-full"}]
        [:div {:class "relative flex max-h-[100%] w-full"}
         [:span.flex {:class "w-1/2"}]
         [:div {:class (str transition bg-colour "flex max-w-[50%] min-w-[50%] overflow-clip px-4 pb-3 rounded-bl-lg")}
          [:div.flex.flex-col
           [:div.flex [util/header :sncf-comics-header]]
           [:div.flex.truncate.flex-col
            [i18n/text :sncf-comics]
            [util/observer #(reset! truncate? (not %))]]
           (when (and (= scene self-scene) @truncate?)
             [:div.relative
              [:div {:class (str transition "translate-y-[-100%] absolute w-full h-6 bg-gradient-to-t from-" bg-colour-raw " to-transparent")}]
              [:span.flex {:class "mt-[-13px]"} "..."]])
           [:span.flex.pt-2 [i18n/text :read-more]]]]]]])))

;; sncf
;; l'art dans la rue
;; dissertation
;; arte
;; petit bleds
;; make up
;; ?paris opera?
;;
;;
;;

(defn projects
  [context]
  (into [:<>]
        (map-indexed (fn [i page]
                       (let [self-scene (+ 3 (* 2 i))]
                         [project context self-scene
                          [page (assoc context :self-scene self-scene)]]))
                     [
                      sncf
                      lart-dans-la-rue
                      ])))

