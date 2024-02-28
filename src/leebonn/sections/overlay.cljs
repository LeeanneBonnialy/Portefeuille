(ns leebonn.sections.overlay
  (:require
    [leebonn.i18n :as i18n]
    [leebonn.navigation :as nav]
    [leebonn.sections.projects :as projects]
    [leebonn.util :as util]
    [reagent.core :as r]))


(def open-atom? (r/atom nil))


(defn kill-event
  [event]
  (when event
    (.preventDefault event)
    (.stopPropagation event)))


(defn close-menu
  [navigating? event]
  (when navigating?
    (projects/close-modal event))
  (kill-event event)
  (reset! open-atom? nil)
  (nav/clear-scroll))


(defn open-menu
  []
  (nav/set-scroll (fn [v]
                    (when (= -1 v)
                      (close-menu false nil)
                      (nav/clear-scroll)))
                  nil)
  (reset! open-atom? (system-time)))


(defn menu-lang-selection
  [{:keys [current-index transition modal-text-colour]}]
  (let [open?           @open-atom?
        t2              {:class "transition-all"
                         :style {:transition-duration "300ms"}}
        language?       (contains? #{0 1 2} current-index)
        mid-transition? (= 3 current-index)]
    [:div (util/combine-style transition
                              {:class (str "grid grid-cols-3 pointer-events-none top-0 right-0 p-4 absolute text-2xl font-sans"
                                           (if language?
                                             " translate-x-[0px]"
                                             " translate-x-[28px]"))
                               :style {:color modal-text-colour}})
     [:a (util/combine-style transition
                             {:class    (str "hover-squiggly"
                                             (if language?
                                               " pointer-events-auto cursor-pointer opacity-100 "
                                               " opacity-0 "))
                              :on-click #(i18n/set-lang! :fr)}) "FR"]
     [:svg.h-full
      {:xmlns    "http://www.w3.org/2000/svg"
       :stroke   "currentColor"
       :fill     "currentColor"
       :viewBox  "0 0 16 16"
       :class    (when (not language?) "cursor-pointer pointer-events-auto")
       :on-click #(if open?
                    (close-menu false %)
                    (open-menu))}
      [:path (util/combine-style t2
                                 {:stroke-width   (if mid-transition? 2 2)
                                  :stroke-linecap "round"
                                  :d              (cond
                                                    open? "M1,4.25 15,11.25"
                                                    mid-transition? "M7,4.25 7,4.25"
                                                    language? "M7,1 7,7.5"
                                                    (not language?) "M1,4.25 15,4.25") #_"M7,2 7,2"})]
      [:path (util/combine-style t2
                                 {:stroke-width   (if mid-transition? 2 2)
                                  :stroke-linecap "round"
                                  :d              (cond
                                                    open? "M1,11.25 15,4.25"
                                                    mid-transition? "M7,11.25 7,11.25"
                                                    language? "M7,7.5 7,15"
                                                    (not language?) "M1,11.25 15,11.25") #_"M7,2 7,2"})]]
     [:a (util/combine-style transition
                             {:class    (str "hover-squiggly"
                                             (if language?
                                               " pointer-events-auto cursor-pointer opacity-100 "
                                               " opacity-0 "))
                              :on-click #(i18n/set-lang! :en)}) "EN"]]))


(defn simple-menu-item
  [modal-text-colour title on-click active?]
  [:div {:class    (str "p-4 cursor-pointer h-full w-min"
                        (when active? " border-l-2"))
         :style    {:border-color modal-text-colour}
         :on-click on-click}
   [:div {:class "hover-squiggly"}
    title]])


(defn menu
  [{:keys [narrow? modal-text-colour] :as context}]
  (let [open?           @open-atom?
        shift           (if open? " translate-x-[0%] "
                            " translate-x-[100%] ")
        anchors-on-page (nav/anchors-on-page)
        active?         (fn [& anchors] (some #(contains? anchors-on-page %) anchors))]
    [:<>
     [:div {:class    (str "bg-black transition-all duration-500 top-0 left-0 right-0 bot-0 fixed w-full h-full cursor-pointer "
                           (if open? " pointer-events-auto " " pointer-events-none "))
            :on-click (partial close-menu false)
            :style    {:background-color (if open?
                                           "rgba(255,255,255,0.3)"
                                           "rgba(255,255,255,0)")}}
      [:div {:class    (str "bg-white transition-all duration-500 top-0 right-0 fixed h-full pointer-events-auto cursor-auto "
                            shift
                            (if narrow? " w-full " " w-10/12 "))
             :on-click kill-event}
       [:div {:class "relative w-full h-full py-16 pointer-events-none"}
        [:div {:class "transition-all duration-500 w-full h-full pb-2 pointer-events-auto text-4xl font-slim grid grid-cols-3"
               :style {:color modal-text-colour}}
         [:span {:class "row-span-1"}]
         [:div {:class "row-span-6 col-start-1 col-span-1 p-4 transition-all duration-500 border-r-2 text-right"
                :style {:border-color modal-text-colour}}
          "projects"]
         [:div {:class "row-start-1 col-start-2 col-span-1 row-span-1"}
          [simple-menu-item modal-text-colour "intro" #(do (println "hererere") (close-menu true %) (nav/go-to-anchor :intro)) (active? :intro)]]
         (for [{:keys [title anchor detail-anchor] :as proj} projects/projects]
           ^{:key anchor} [:div {:class "col-start-2 col-span-1 row-span-1"}
                           [simple-menu-item modal-text-colour title #(do (close-menu true %) (projects/open-project proj)) (active? anchor detail-anchor)]])
         [:div {:class "row-start-8 col-start-2 col-span-1 row-span-1"}
          [simple-menu-item modal-text-colour "contact" #(do (close-menu true %) (nav/go-to-anchor :contact)) (active? :contact)]]]]]]]))


(defn overlay
  [context]
  [:<>
   [menu context]
   [menu-lang-selection context]])
