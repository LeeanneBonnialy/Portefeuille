(ns leebonn.util
  (:require
    ["react" :as react]
    [leebonn.i18n :as i18n]
    [reagent.core :as r]))


(defn combine-style
  ([{ca :class sa :style :as a} {cb :class sb :style :as b}]
   (-> (merge a b)
       (assoc :class (str ca " " cb))
       (assoc :style (merge sa sb))))
  ([a b c & more]
   (reduce combine-style
           (-> a
               (combine-style b)
               (combine-style c))
           more)))


(defn ratio
  [width height min-ratio max-ratio min-val max-val]
  (let [ratio (/ width height)
        dr    (- max-ratio min-ratio)
        ar    (- ratio min-ratio)
        t     (max 0 (min (/ ar dr) 1))]
    (+ min-val (* t (- max-val min-val)))))


(defn header
  [kw]
  [:h1.text-2xl.font-bold [i18n/text kw]])


(defn attach-visibility-observer
  [element callback]
  (.observe (js/IntersectionObserver.
              (fn [entries _observer]
                (doseq [entry entries]
                  (callback (> (.-intersectionRatio entry) 0)))))
            element))


(defn detach-visibility-observer
  [observer]
  (.disconnect observer))


(defn observer
  [callback]
  (let [end-ref (react/createRef)
        obs     (atom nil)]
    (r/create-class
      {:component-did-mount
       (fn [_]
         (attach-visibility-observer (.-current end-ref) callback))
       :component-will-unmount
       (fn [_]
         (when-let [o @obs]
           (detach-visibility-observer o)))
       :reagent-render
       (fn [_]
         [:span.w-1.h-1.bottom-0 {:class "mt-[-4px]" :ref end-ref}])})))


(defn fly-in
  [{:keys [offset transition text-colour bg-colour]} content before after]
  (let [translation {:style
                     {:transform (case offset
                                   -1 before
                                   1 after
                                   "translate(0%, 0%)")}}]
    (when (contains? #{-1 0 1 2} offset)
      [:div (combine-style transition
                           {:class "font-sans absolute text-xl w-full h-full flex"
                            :style {:color    text-colour
                                    :background-color bg-colour}}
                           {:class (case offset
                                     -1 " pointer-events-none opacity-0"
                                     0 " opacity-100 pointer-events-auto"
                                     1 " pointer-events-none opacity-100"
                                     2 " pointer-events-none opacity-100")})
       (when (contains? #{-1 0 1} offset)
         [:div (combine-style transition
                              {:class "flex w-full h-full relative"}
                              translation)
          content])])))
