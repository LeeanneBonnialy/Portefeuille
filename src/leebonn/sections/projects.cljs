(ns leebonn.sections.projects
  (:require
    [clojure.string :as str]
    [leebonn.i18n :as i18n]
    [leebonn.image-loader :as img]
    [leebonn.util :as util]
    [reagent.core :as r]))


(defn fly-in
  [{:keys [offset transition text-colour]} content]
  (let [x           "100%"
        y           "0%"

        translation {:style
                     {:transform (case offset
                                   -1 (str "translate(" x ", " y ")")
                                   1 (str "translate(-" x ", -" y ")")
                                   "translate(0%, 0%)")}}]
    (when (contains? #{-1 0 1} offset)
      [:div (util/combine-style transition
                                {:class "font-sans absolute text-xl w-full h-full flex"
                                 :style {:color text-colour}}
                                {:class (case offset
                                          -1 " pointer-events-none opacity-0"
                                          0 " opacity-100"
                                          1 " pointer-events-none opacity-0")})
       [:div (util/combine-style transition
                                 {:class "flex w-full h-full"}
                                 translation)
        content]])
    (when (contains? #{-1 0 1} offset)
      [:div (util/combine-style transition
                                {:class "flex w-full h-full"}
                                translation)
       content])))


(defn sncf
  [_]
  (let [with-border-col (fn [s text-colour]
                          (util/combine-style s {:style {:border-color text-colour}}))]
    (fn [{:keys [text-colour] :as _context}]
      [:div {:class "w-full h-full flex flex-col"}
       [:div (with-border-col {:class "w-full h-16 border-b flex-shrink-0 flex-grow-0"} text-colour)]

       [:div (with-border-col {:class "w-full h-32 flex border-b flex-shrink-0 flex-grow-0"} text-colour)
        [:div (with-border-col {:class "border-r flex-grow"} text-colour)]
        [:div {:class "w-32 h-full flex justify-end group flex-shrink-0 flex-grow-0 cursor-pointer"}
         [:svg {:class "w-full h-full group-hover:w-1/3 group-hover:h-1/3 transition-all" :viewBox "0 0 100 100" :xmlns "http://www.w3.org/2000/svg"}
          [:circle {:r "45" :cx "50" :cy "50" :fill "currentColor"}]]]]

       [:div {:class "flex flex-grow"}
        [:div {:class "w-16 flex-shrink-0 flex-grow-0"}]
        [:div {:class "flex-grow p-4"}
         [img/deferred-image "sncf_comics2.jpg" {:class "rounded-tl-full object-cover w-full h-full"}]]]

       #_[:div.col-span-1.row-span-2 {:class "border-b border-black"} "abc"]
       #_[:div.col-span-10.row-span-8 {:class "border-black"} "abc"]]
      #_[:div {:class "m-auto w-2/3 h-2/3"}
         [:div.w-full.h-full.relative
          [img/deferred-image "sncf_comics2.jpg" {:class "absolute object-cover w-full h-full pb-2 rounded-full"}]
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


;;
;;
;; (defn lart-dans-la-rue
;;  [_]
;;  (let [truncate? (r/atom false)]
;;    (fn [{:keys [scene transition bg-colour bg-colour-raw self-scene] :as _context}]
;;      [:div {:class "m-auto w-2/3 h-2/3"}
;;       [:div.w-full.h-full.relative
;;        [img/deferred-image "sncf_comics2.jpg" {:class "absolute object-cover w-full h-full pb-2 rounded-full"}]
;;        [:div {:class "relative flex max-h-[100%] w-full"}
;;         [:span.flex {:class "w-1/2"}]
;;         [:div {:class (str transition bg-colour "flex max-w-[50%] min-w-[50%] overflow-clip px-4 pb-3 rounded-bl-lg")}
;;          [:div.flex.flex-col
;;           [:div.flex [util/header :sncf-comics-header]]
;;           [:div.flex.truncate.flex-col
;;            [i18n/text :sncf-comics]
;;            [util/observer #(reset! truncate? (not %))]]
;;           (when (and (= scene self-scene) @truncate?)
;;             [:div.relative
;;              [:div {:class (str transition "translate-y-[-100%] absolute w-full h-6 bg-gradient-to-t from-" bg-colour-raw " to-transparent")}]
;;              [:span.flex {:class "mt-[-13px]"} "..."]])
;;           [:span.flex.pt-2 [i18n/text :read-more]]]]]]])))


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

;; (defn projects
;;  [context]
;;  (into [:<>]
;;        (map-indexed (fn [i page]
;;                       (let [self-scene (+ 3 (* 2 i))]
;;                         [project context self-scene
;;                          [page (assoc context :self-scene self-scene)]]))
;;                     [sncf
;;                      ;; lart-dans-la-rue
;;                      ])))


(def desired-size {:dim1 400 :dim2 750})


(def projects
  [{:anchor   :sncf
    :image    "sncf_comics2.jpg"
    :title    "SNCF"
    :abstract "sncf abstract"
    :detail   []}

   {:anchor   :sncf2
    :image    "sncf_comics.jpg"
    :title    "SNCF"
    :abstract "sncf abstract"
    :detail   []}

   {:anchor   :lart
    :image    "lartdanslarue.png"
    :title    "L'art"
    :abstract "L'art abstract"
    :detail   []}])


(defn project-item
  [{:keys [narrow?]} {:keys [image] :as project}]
  (if narrow?
    [:div {:class "row-span-1 col-span-1 flex flex-col h-full w-full"}
     [img/deferred-image image {:class "flex object-cover w-full h-full rounded-xl shadow-xl"}]]

    [:div {:class "row-span-1 col-span-1 min-h-0 min-w-0 flex h-full w-full"}
     [img/deferred-image image {:class "flex object-cover w-full h-full rounded-xl shadow-xl"}]]))


(defn project-fit
  [{:keys [width height]}]
  (let [[w h width] (if (> height width)
                      [(:dim1 desired-size) (:dim2 desired-size) width]
                      [(:dim2 desired-size) (:dim1 desired-size) (* width (/ 10 12))])
        x (min 2 (max 1 (int (/ width w))))
        y (min 2 (max 1 (int (/ height h))))]
    [x y]))


(defn project-grid
  [{:keys [narrow?] :as context} projects]
  (let [[x y] (project-fit context)]
    (into [:div {:class (str "grid h-full p-16 gap-16 "
                             (if narrow?
                               " w-full "
                               " w-10/12 mx-auto"))
                 :style {:grid-template-columns (str/join " " (repeat x "1fr"))
                         :grid-template-rows    (str/join " " (repeat y "1fr"))}}]
          (for [project projects]
            [project-item context project]))))


(defn project-page
  [projects context]
  [fly-in context
   [project-grid context projects]])
