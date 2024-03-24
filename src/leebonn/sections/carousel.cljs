(ns leebonn.sections.carousel
  (:require
    ["react" :as react]
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
  [index content v]
  (swap! index #(min (max 0 (+ % v)) (dec (count @content)))))


(defn open-carousel
  [initial-index images]
  (reset! index initial-index)
  (reset! old-scroll (nav/get-scroll))
  (nav/set-scroll (fn [v]
                    (scroll-image index content v))
                  (fn [_v]
                    (close-carousel)))
  (reset! content images)
  (reset! open? true))


(defn carousel-image
  [src offset]
  (when (contains? #{-1 0 1} offset)
    [:div {:class (str "absolute top-0 duration-500 px-8 md:px-14 transition-all w-full h-full flex"
                       (case offset
                         -1 " translate-x-[110%] opacity-0"
                         0 " translate-x-[0%] opacity-100"
                         1 " translate-x-[-110%] opacity-0"))}
     [img/deferred-image
      src
      {:class "rounded-lg shadow-lg max-h-[80vh] m-auto"}]]))


(defn cross
  []
  [:svg.h-8.w-8
   {:xmlns   "http://www.w3.org/2000/svg"
    :stroke  "currentColor"
    :fill    "currentColor"
    :viewBox "0 0 16 16"}
   [:path {:stroke-width   2
           :stroke-linecap "round"
           :d              "M1,4.25 15,11.25"}]
   [:path {:stroke-width   2
           :stroke-linecap "round"
           :d              "M1,11.25 15,4.25"}]])


(defn arrow-right
  [active]
  [:svg.h-full
   {:xmlns   "http://www.w3.org/2000/svg"
    :stroke  "currentColor"
    :fill    "none"
    :viewBox "0 0 12 16"}
   [:path {:class          "transition-all duration-500"
           :stroke-width   2
           :stroke-linecap "round"
           :d              (if active
                             "M1,11.25 8,8 1,4.25"
                             "M4.5,11.25 4.5,8 4.5,4.25")}]])


(defn arrow-left
  [active]
  [:svg.h-full
   {:xmlns   "http://www.w3.org/2000/svg"
    :stroke  "currentColor"
    :fill    "none"
    :viewBox "0 0 12 16"}
   [:path {:class          "transition-all duration-500"
           :stroke-width   2
           :stroke-linecap "round"
           :d              (if active
                             "M11,11.25 4,8 11,4.25"
                             "M7.5,11.25 7.5,8 7.5,4.25")}]])


(defn kill-event
  [event]
  (when event
    (.preventDefault event)
    (.stopPropagation event)))


(defn arrow
  [index content direction]
  (let [active (case direction
                 -1 (not= @index 0)
                 1 (not= @index (dec (count @content))))]
    [:div {:class    "transition-all w-6 md:w-12 hover:scale-95 cursor-pointer hover:opacity-100"
           :on-click #(do (kill-event %)
                          (scroll-image index content direction))}
     (case direction
       -1 [arrow-left active]
       1 [arrow-right active])]))


(defn carousel-view
  [& _args]
  (let [ref (react/createRef)]
    (fn [index content on-click {:keys [classes]}]
      [:div.w-full.relative.overflow-hidden
       [img/deferred-image
        (first @content)
        {:class "px-8 md:px-14 opacity-0 max-h-[80vh]"
         :ref   ref}]
       (doall (map-indexed
                (fn [i image]
                  ^{:key i} [carousel-image image (- @index i)
                             {:class "max-h-screen"}])
                @content))
       [:div {:class    "absolute top-0 w-full h-full flex cursor-pointer"
              :on-click (fn [e] (on-click e (.-current ref) index content))}
        [:div {:class (str "h-8 md:h-16 w-full my-auto flex justify-between " (or classes "text-pink-300"))}
         [arrow index content -1]
         [arrow index content 1]]]])))


(defn click-scroll-carousel
  [e element index content]
  (let [rect (.getBoundingClientRect element)
        l    (.-left rect)
        r    (.-right rect)
        mid  (/ (+ l r) 2)]
    (scroll-image index content
                  (if (> (.-clientX e) mid)
                    1
                    -1))))


(defn carousel-overlay
  []
  [:div {:class (str "bg-[#000000ee] w-full relative h-full transition-all"
                     (if @open?
                       " opacity-100 pointer-events-auto"
                       " opacity-0 pointer-events-none"))}
   [carousel-view index content click-scroll-carousel]
   [:div {:class    "transition-all top-0 right-0 p-4 absolute text-pink-300 cursor-pointer opacity-50 hover:opacity-100"
          :on-click (fn [e] (close-carousel))}
    [cross]]])
