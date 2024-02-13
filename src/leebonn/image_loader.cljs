(ns leebonn.image-loader
  (:require
    [clojure.string :as str]))


(def empty-image
  "data:image/png;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs=")


(defn image-mips
  [image-name]
  (let [[file-name] (str/split image-name #"\.")]
    [(str "img/" file-name "_LQIP.jpg")
     (str "img/" image-name)]))


(def global-image-cache
  (memoize
    (fn [image-name]
      (let [mips (image-mips image-name)]
        (atom {:mips   mips
               :loaded nil})))))


(defn current-src
  [image-cache]
  (let [{:keys [loaded mips]} @image-cache
        src (if loaded
              (nth mips loaded)
              (first mips))]
    src))


(defn succeed-load!
  [id image-cache]
  (let [elem         (.getElementById js/document id)
        loaded-src   (.-src elem)

        {:keys [mips loaded] :as ic} @image-cache
        loaded-index (first (keep-indexed (fn [i mip]
                                            (when (str/ends-with? loaded-src mip)
                                              i))
                                          mips))

        new-load?    (or (nil? loaded)
                         (> loaded-index loaded))

        new-state    (if new-load?
                       (assoc ic :loaded loaded-index)
                       ic)

        next-src     (nth mips (inc (:loaded new-state)) nil)]
    (reset! image-cache new-state)
    (when next-src
      (set! (.-src (.getElementById js/document id)) next-src))))


(defn fail-load!
  [id image-cache]
  (let [elem            (.getElementById js/document id)
        attempted-src   (.-src elem)

        {:keys [mips]} @image-cache
        attempted-index (first (keep-indexed (fn [i mip]
                                               (when (str/ends-with? attempted-src mip)
                                                 i))
                                             mips))

        next-src        (nth mips (inc attempted-index) nil)]
    (when next-src
      (set! (.-src (.getElementById js/document id)) next-src))))


(defn deferred-image
  [image-name more-attributes]
  (let [id          (str (random-uuid))
        image-cache (global-image-cache image-name)]
    [:img (merge more-attributes
                 {:id       id
                  :on-load  (partial succeed-load! id image-cache)
                  :on-error (partial fail-load! id image-cache)
                  :src      (current-src image-cache)})]))
