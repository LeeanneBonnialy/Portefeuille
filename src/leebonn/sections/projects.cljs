(ns leebonn.sections.projects
  (:require
    [clojure.string :as str]
    [leebonn.content.arte :as arte]
    [leebonn.content.ptit-bleds :as ptit-bleds]
    [leebonn.content.roger :as roger]
    [leebonn.content.seazon :as seazon]
    [leebonn.i18n :as i18n]
    [leebonn.image-loader :as img]
    [leebonn.navigation :as nav]
    [leebonn.util :as util]
    [reagent.core :as r]))


(def desired-size {:dim1 400 :dim2 750})
(def current-project (r/atom nil))
(def detail-opened (r/atom false))
(def hover-close (r/atom false))


(defn open-project
  [{:keys [anchor detail-anchor] :as proj}]
  (nav/go-to-anchor detail-anchor)
  (nav/set-scroll nil
                  nil)
  (reset! current-project proj)
  (reset! detail-opened (system-time)))


(defn project-item
  [_context proj]
  (fn [_ {:keys [image context image-position]
          :or   {image-position "object-cover"}}]
    (let [[c0 c1 c2] context]
      [:div {:class "row-span-1 col-span-1 flex min-h-0 min-w-0 h-full w-full p-4"}
       [:div {:class "relative w-full h-full transition-all duration-500 hover:scale-95"}
        [:div {:class "absolute w-full h-full"}
         (when c0 [:div {:class "text-2xl absolute bottom-4 left-[-29px]"
                         :style {:writing-mode     "vertical-rl"
                                 :transform        "rotate(180deg)"
                                 :text-orientation "mixed"}} [i18n/text c0]])
         (when c1 [:div {:class "text-2xl absolute left-4 top-[-29px]"} [i18n/text c1]])
         (when c2 [:div {:class "text-2xl absolute top-4 right-[-29px]"
                         :style {:writing-mode     "vertical-rl"
                                 :text-orientation "mixed"}} [i18n/text c2]])]
        [img/deferred-image image {:class    (str "transition-all duration-500 absolute object-contain w-full h-full rounded-xl
                                            shadow-xl hover:shadow-lg pointer-events-auto cursor-pointer" " " image-position)
                                   :on-click (partial open-project proj)}]]])))


(def projects
  [{:anchor         :seazon
    :detail-anchor  :seazon-detail
    :image          "seazon/main_1.jpg"
    :image-position "bg-white object-contain object-center"
    :title          seazon/seazon-title
    :detail         [seazon/project-detail]
    ;; :context       seazon/tab-context
    :view           project-item}

   {:anchor         :roger
    :detail-anchor  :roger-detail
    :image          "roger/main_1.jpg"
    :image-position "bg-white object-contain object-center"
    :title          roger/title
    :detail         [roger/project-detail]
    :view           project-item}

   {:anchor         :arte
    :detail-anchor  :arte-detail
    :image          "arte/title.png"
    :image-position "bg-white object-contain object-right-bottom"
    :title          arte/title
    :detail         [arte/project-detail]
    :view           project-item}

   {:anchor         :ptit-bleds
    :detail-anchor  :ptit-bleds-detail
    :image          "ptit-bleds/main_1.jpg"
    :image-position "bg-[#fef2c2] object-contain object-center"
    :title          ptit-bleds/title
    :detail         [ptit-bleds/project-detail]
    :view           project-item}


   {:bg-colour      "#19A8D4"
    :anchor         :ptit-bleds2
    :detail-anchor  :ptit-bleds2-detail
    :image          "ptit-bleds/main_1.jpg"
    :image-position "bg-[#fef2c2] object-contain object-center"
    :title          ptit-bleds/title
    :detail         [ptit-bleds/project-detail]
    :view           project-item}

   #_{:anchor        :lart5
      :detail-anchor :lart-detail5
      :image         "l-art/lartdanslarue.png"
      :title         "L'art"
      :detail        (repeat 1000 "C\n")
      :view          project-item}])


(defn close-detail
  [v]
  (when (= -1 v)
    (reset! detail-opened nil)
    (nav/go-to-anchor (:anchor @current-project))
    (nav/clear-scroll)))


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
  [{:keys [target]} projects]
  (let [anchor           (:anchor target)
        selected-project (->> projects
                              (some #(when (= anchor (:detail-anchor %))
                                       %)))]
    (when selected-project
      (reset! current-project selected-project)
      (reset! detail-opened (system-time))
      (nav/set-scroll nil nil))
    (fn [{:keys [narrow?] :as context} projects]
      (let [[x y] (desired-project-fit context projects)]
        (into [:div {:class (str "grid h-full p-16 gap-16 "
                                 (if narrow?
                                   " w-full "
                                   " w-10/12 mx-auto"))
                     :style {:grid-template-columns (str/join " " (repeat x "1fr"))
                             :grid-template-rows    (str/join " " (repeat y "1fr"))}}]
              (for [project projects]
                [(:view project) context project]))))))


(defn project-page
  [projects before after context]
  [util/fly-in context
   [project-grid context projects]
   before after])


(defn kill-event
  [event]
  (when event
    ;; (.preventDefault event)
    (.stopPropagation event)))


(defn close-modal
  [event]
  (when @detail-opened
    (reset! hover-close false)
    (kill-event event)
    (close-detail -1)))


(defn project-scroll
  [modal-text-colour project]
  [:div {:key (:anchor project) :class "relative w-full h-full pt-16 pointer-events-none"}
   [:div {:class "w-full h-full overflow-y-auto pb-2 pointer-events-auto"}
    [:div {:class "transition-all duration-500 w-11/12 mx-auto text-2xl font-sans"
           :style {:color modal-text-colour}}
     (:detail project)]]])


(defn project-detail
  [_]
  (let [do-enter-close #(let [now (system-time)]
                          (kill-event %)
                          (when (> (- now (or @detail-opened now)) 500)
                            (reset! hover-close true)))
        do-leave-close #(do (kill-event %)
                            (reset! hover-close false))
        closer-dash    9]
    (fn [{:keys [narrow? modal-text-colour]}]
      (let [open? @detail-opened
            shift (if open? " translate-x-[0%] "
                      " translate-x-[100%] ")]
        [:<>
         [:div {:class          (str "bg-white transition-all duration-500 top-0 left-0 right-0 bot-0 fixed w-full h-full cursor-pointer "
                                     (if open? " pointer-events-auto " " pointer-events-none "))
                :on-click       close-modal
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
            {:class          "transition-all duration-500 w-8 h-8 mb-[-64px] mt-4 ml-3 cursor-pointer"
             :on-click       close-modal
             :on-mouse-over  do-enter-close
             :on-mouse-leave do-leave-close
             :aria-hidden    "true"
             :xmlns          "http://www.w3.org/2000/svg"
             :fill           "none"
             :color          modal-text-colour
             :viewBox        "0 0 16 16"}
            [:path {:stroke (str modal-text-colour "55") :stroke-linecap "round" :stroke-linejoin "round" :stroke-width "2"
                    :d      "M1,11.25 15,4.25 M1,4.25 15,11.25"}]
            [:path
             {:class             "transition-all duration-300"
              :stroke            "currentColor"
              :stroke-dasharray  (if @hover-close 8 closer-dash)
              :stroke-dashoffset (if @hover-close 0 closer-dash)
              :stroke-linecap    "round"
              :stroke-linejoin   "round"
              :stroke-width      "2"
              :d                 "M7.5,7.5 1,11.25 M7.5,7.5 15,4.25 M7.5,7.5 1,4.25 M7.5,7.5 15,11.25"}]]
           [project-scroll modal-text-colour @current-project]]]]))))
