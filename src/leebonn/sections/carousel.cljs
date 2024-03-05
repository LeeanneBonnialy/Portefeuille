(ns leebonn.sections.carousel
  (:require
    [leebonn.image-loader :as img]
    [leebonn.navigation :as nav]
    [reagent.core :as r]))


(def open? (r/atom false))
(def content (r/atom nil))
(def index (r/atom 0))

(def old-scroll (atom nil))


(defn close-carousel
  []
  (reset! open? false)
  (let [{:keys [x y]} @old-scroll]
    (nav/set-scroll x y)))


(defn scroll-image
  [v]
  (swap! index #(min (max 0 (+ % v)) (dec (count @content)))))


(defn open-carousel
  [images]
  (reset! index 0)
  (reset! old-scroll (nav/get-scroll))
  (nav/set-scroll scroll-image
                  (fn [_v]
                    (close-carousel)))
  (reset! content images)
  (reset! open? true))


(defn carousel-image
  [src offset]
  (when (contains? #{-1 0 1} offset)
    [img/deferred-image
     src
     {:class (str "absolute duration-500 px-14 transition-all w-full h-full object-contain "
                  (case offset
                    -1 " translate-x-[110%]"
                    0 " translate-x-[0%]"
                    1 " translate-x-[-110%]"))}]))


(defn cross
  []
  [:svg.h-8.w-8
   {:xmlns    "http://www.w3.org/2000/svg"
    :stroke   "currentColor"
    :fill     "currentColor"
    :viewBox  "0 0 16 16"}
   [:path {:stroke-width   2
           :stroke-linecap "round"
           :d              "M1,4.25 15,11.25"}]
   [:path {:stroke-width   2
           :stroke-linecap "round"
           :d              "M1,11.25 15,4.25"}]])


(defn arrow-right
  [active]
  [:svg.h-full
   {:xmlns    "http://www.w3.org/2000/svg"
    :stroke   "currentColor"
    :fill     "none"
    :viewBox  "0 0 12 16"}
   [:path {:class "transition-all duration-500"
           :stroke-width   2
           :stroke-linecap "round"
           :d              (if active
                             "M1,11.25 8,8 1,4.25"
                             "M4.5,11.25 4.5,8 4.5,4.25")}]])


(defn arrow-left
  [active]
  [:svg.h-full
   {:xmlns    "http://www.w3.org/2000/svg"
    :stroke   "currentColor"
    :fill     "none"
    :viewBox  "0 0 12 16"}
   [:path {:class "transition-all duration-500"
           :stroke-width   2
           :stroke-linecap "round"
           :d (if active
                "M11,11.25 4,8 11,4.25"
                "M7.5,11.25 7.5,8 7.5,4.25")}]])


(defn arrow
  [index content direction]
  (let [active (case direction
                 -1 (not= index 0)
                 1 (not= index (dec (count content))))]
    [:div {:class    "transition-all hover:scale-95 cursor-pointer opacity-50 hover:opacity-100"
           :on-click #(scroll-image direction)}
     (case direction
       -1 [arrow-left active]
       1 [arrow-right active])]))


(defn carousel-overlay
  []
  (let [open?   @open?
        index   @index
        content @content]
    [:div {:class (str "bg-[#000000ee] w-full relative h-full transition-all"
                       (if open?
                         " opacity-100 pointer-events-auto"
                         " opacity-0 pointer-events-none"))}
     (map-indexed
       (fn [i image]
         ^{:key i} [carousel-image image (- index i)])
       content)
     [:div {:class "absolute w-full h-full flex"
            :on-click (fn [e]
                        (scroll-image (if (> (.-clientX e) (/ (.-innerWidth js/window) 2))
                                        1
                                        -1)))}
      [:div {:class "w-full h-16 my-auto flex justify-between text-pink-300"}
       [arrow index content -1]
       [arrow index content 1]]]
     [:div {:class "transition-all top-0 right-0 p-4 absolute text-pink-300 cursor-pointer opacity-50 hover:opacity-100"
            :on-click (fn [e] (close-carousel))}
      [cross]]]))
