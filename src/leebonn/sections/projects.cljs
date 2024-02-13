(ns leebonn.sections.projects
  (:require
    [clojure.string :as str]
    [leebonn.i18n :as i18n]
    [leebonn.image-loader :as img]
    [leebonn.navigation :as nav]
    [leebonn.util :as util]
    [reagent.core :as r]))


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
         [img/deferred-image "sncf/sncf_comics2.jpg" {:class "rounded-tl-full object-cover w-full h-full"}]]]

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
(def current-project (r/atom nil))
(def detail-opened (r/atom false))


(def projects
  [{:anchor        :seazon
    :detail-anchor :seazon-detail
    :image         "seazon/seazon.jpg"
    :title         "seazon"
    :abstract      "seazon abstract"
    :detail        (repeat 1000 "B\n")}

   {:anchor        :sncf
    :detail-anchor :sncf-detail
    :image         "sncf/sncf_comics2.jpg"
    :title         "SNCF"
    :abstract      "sncf abstract"
    :detail        (repeat 1000 "A\n")}

   {:anchor        :lart
    :detail-anchor :lart-detail
    :image         "l-art/lartdanslarue.png"
    :title         "L'art"
    :abstract      "L'art abstract"
    :detail        (repeat 1000 "C\n")}])


(defn close-detail
  [v]
  (when (= -1 v)
    (reset! detail-opened nil)
    (nav/go-to-anchor (:anchor @current-project))
    (nav/clear-scroll)))


(defn project-item
  [_context {:keys [anchor detail-anchor] :as proj}]
  (let [click (fn []
                (do (nav/go-to-anchor detail-anchor)
                    (nav/set-scroll (fn [v]
                                      (when (= -1 v)
                                        (reset! detail-opened nil)
                                        (nav/go-to-anchor anchor)
                                        (nav/clear-scroll)))
                                    nil)
                    (reset! current-project proj)
                    (reset! detail-opened (system-time))))]
    (fn [_ {:keys [image]}]
      [:div {:class "relative row-span-1 col-span-1 flex min-h-0 min-w-0 h-full w-full squiggly"}
       [img/deferred-image image {:class    "transition-all duration-500 flex object-cover w-full h-full rounded-xl
                                             hover:scale-95 shadow-xl hover:shadow-lg pointer-events-auto cursor-pointer"
                                  :on-click click}]])))


(def factors
  {1 [1 1]
   2 [2 1]
   3 [3 1]
   4 [2 2]})


(defn max-project-fit
  [{:keys [width height]}]
  (let [[w h width] (if (> height width)
                      [(:dim1 desired-size) (:dim2 desired-size) width]
                      [(:dim2 desired-size) (:dim1 desired-size) (* width (/ 10 12))])
        x (min 2 (max 1 (int (/ width w))))
        y (min 2 (max 1 (int (/ height h))))]
    [x y]))


(defn desired-project-fit
  [{:keys [narrow?] :as context} projects]
  (let [orientation (get factors (count projects) (max-project-fit context))]
    (if narrow?
      (vec (reverse orientation))
      orientation)))


(defn project-grid
  [{:keys [target]} _projects]
  (let [anchor           (:anchor target)
        selected-project (->> projects
                              (some #(when (= anchor (:detail-anchor %))
                                       %)))]
    (when selected-project
      (reset! current-project selected-project)
      (reset! detail-opened (system-time))
      (nav/set-scroll close-detail
                      nil))
    (fn [{:keys [narrow?] :as context} projects]
      (let [[x y] (desired-project-fit context projects)]
        (into [:div {:class (str "grid h-full p-16 gap-16 "
                                 (if narrow?
                                   " w-full "
                                   " w-10/12 mx-auto"))
                     :style {:grid-template-columns (str/join " " (repeat x "1fr"))
                             :grid-template-rows    (str/join " " (repeat y "1fr"))}}]
              (for [project projects]
                [project-item context project]))))))


(defn project-page
  [projects before after context]
  [util/fly-in context
   [project-grid context projects]
   before after])


(defn project-detail
  [_]
  (let [kill-event     (fn [event]
                         (.preventDefault event)
                         (.stopPropagation event))
        hover-close    (r/atom false)
        do-enter-close #(let [now (system-time)]
                          (kill-event %)
                          (when (> (- now (or @detail-opened now)) 500)
                            (reset! hover-close true)))
        do-leave-close #(do (kill-event %)
                            (reset! hover-close false))
        closer         (fn [event]
                         (reset! hover-close false)
                         (kill-event event)
                         (close-detail -1))
        closer-dash    9]
    (fn [{:keys [narrow? modal-text-colour] :as context}]
      (let [open?   @detail-opened
            project @current-project
            shift   (if open? " translate-x-[0%] "
                        " translate-x-[100%] ")]
        [:<>
         [:div {:class          (str "bg-white transition-all duration-500 top-0 left-0 right-0 bot-0 fixed w-full h-full cursor-pointer "
                                     (if open? " pointer-events-auto " " pointer-events-none "))
                :on-click       closer
                :on-mouse-over  do-enter-close
                :on-mouse-leave do-leave-close
                :style          {:background-color (if open?
                                                     "rgba(255,255,255,0.3)"
                                                     "rgba(255,255,255,0)")}}
          [:div {:class          (str "bg-white transition-all duration-500 top-0 right-0 fixed h-full pointer-events-auto cursor-auto "
                                      shift
                                      (if narrow? " w-full " " w-10/12 "))
                 :on-mouse-over  do-leave-close
                 :on-mouse-leave kill-event
                 :on-click       kill-event}
           [:svg
            {:class          "w-16 h-16 mb-[-64px] cursor-pointer"
             :on-click       closer
             :on-mouse-over  do-enter-close
             :on-mouse-leave do-leave-close
             :aria-hidden    "true"
             :xmlns          "http://www.w3.org/2000/svg"
             :fill           "none"
             :color          modal-text-colour
             :viewBox        "0 0 24 24"}
            [:path {:stroke (str modal-text-colour "55") :stroke-linecap "round" :stroke-linejoin "round" :stroke-width "2" :d "M6 18 18 6m0 12L6 6"}]
            [:path {:class             "transition-all duration-300"
                    :stroke            "currentColor"
                    :stroke-dasharray  (if @hover-close 8 closer-dash)
                    :stroke-dashoffset (if @hover-close 0 closer-dash)
                    :stroke-linecap    "round"
                    :stroke-linejoin   "round"
                    :stroke-width      "2" :d "M12 12 6 18M12 12 18 6M12 12 6 6M12 12 18 18"}]]
           [:div {:class "w-full h-full my-16 pb-20 overflow-y-auto"}
            [:div {:class "w-11/12 mx-auto text-2xl font-sans"
                   :style {:color modal-text-colour}}
             (:detail project)]]]]]))))
