(ns leebonn.image-loader
  (:require
    [clojure.string :as str]))


(def empty-image
  "data:image/png;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=")


(defn image-mips
  [image-name]
  (let [[file-name] (str/split image-name #"\.")]
    [(str "img/" file-name "_LQIP.png")
     (str "img/" image-name)]))


(def global-image-cache
  (memoize
    (fn [image-name]
      (let [mips (image-mips image-name)]
        (atom mips)))))


(defn current-src!
  [image-cache]
  (let [[[src] _] (swap-vals! image-cache (fn [x]
                                            (if (= 1 (count x))
                                              x
                                              (rest x))))]
    src))


(defn img-improver
  [img-element-id image-cache]
  (fn []
    (let [elem       (.getElementById js/document img-element-id)
          loaded-src (.-src elem)
          best-src   (current-src! image-cache)]
      (when (not (str/ends-with? loaded-src best-src))
        (set! (.-src elem) best-src)))))


(defn deferred-image
  ([width height image-name]
   (deferred-image width height image-name nil))
  ([width height image-name more-attributes]
   (let [id          (str (random-uuid))
         image-cache (global-image-cache image-name)
         improver    (img-improver id image-cache)]
     [:img (merge more-attributes
                  {:id       id
                   :width    width
                   :height   height
                   :on-load  improver
                   :on-error improver
                   :src      (current-src! image-cache)})])))
