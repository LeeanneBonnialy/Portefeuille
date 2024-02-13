(ns leebonn.navigation
  (:require
    [clojure.string :as str]
    [reagent.core :as r]))


(def transition-ms 500)
(def state-atom (r/atom {}))
(def scroll-atom (r/atom {}))


(defn converge
  []
  (let [init-state @state-atom
        start-ms   (system-time)
        end-ms     (+ start-ms transition-ms)
        current-i  (get-in init-state [:current :index])
        target-i   (get-in init-state [:target :index])]
    (when (and (not (:changing init-state))
               (not= current-i target-i))
      (swap! state-atom assoc :changing true)
      ((fn do-loop
         []
         (let [init-state          @state-atom
               remaining-ms        (- end-ms (system-time))
               current-i           (get-in init-state [:current :index])
               target-i            (get-in init-state [:target :index])
               diff                (- target-i current-i)
               shift               (when-not (zero? diff)
                                     (/ diff (abs diff)))
               changes             (abs (- target-i current-i))
               scene-transition-ms (max 100 (/ remaining-ms changes))]
           (if shift
             (do (reset! state-atom
                         (-> init-state
                             (update-in [:current :index] + shift)
                             (assoc :scene-transition-ms scene-transition-ms)))
                 (js/setTimeout do-loop
                                scene-transition-ms))
             (swap! state-atom assoc :changing false)))))
      nil)))


(defn set-fragment
  [anchor]
  (set! (.-hash (.-location js/window)) (name anchor)))


(defn go-to-index
  [index]
  (let [{:keys [index->anchors min-index max-index]} @state-atom
        possible-i (min max-index (max index min-index))
        anchor     (first (index->anchors possible-i))]
    (set-fragment anchor)
    (swap! state-atom assoc :target {:index  possible-i
                                     :anchor anchor})
    (converge)))


(defn go-to-anchor
  [desired-anchor]
  (let [{:keys [anchor->index index->anchors min-index]} @state-atom
        desired-index (anchor->index desired-anchor)

        [index anchor] (if desired-index
                         [desired-index desired-anchor]
                         [min-index (first (index->anchors min-index))])]
    (set-fragment anchor)
    (swap! state-atom assoc :target {:index  index
                                     :anchor anchor})
    (converge)))


(defn- shift-index
  [shift]
  (let [{:keys [target current changing min-index]} @state-atom
        current-i (:index current min-index)
        target-i  (:index target min-index)

        same-dir? (pos? (* shift (- target-i current-i)))

        desired-i (cond
                    (or (nil? current-i) (nil? target-i)) nil
                    changing (cond
                               same-dir? nil
                               (= current-i target-i) (+ current-i shift shift)
                               (even? current-i) current-i
                               :else (+ current-i shift))
                    :else (+ current-i shift shift))]
    (when desired-i
      (go-to-index desired-i))))


(defn go-to-prev
  []
  (shift-index -1))


(defn go-to-next
  []
  (shift-index 1))


(defn set-scene
  [views index->anchors]
  (let [index->anchors   (update-keys index->anchors #(* 2 %))
        anchor->index    (->> index->anchors
                              (mapcat (fn [[i anchors]]
                                        (map (fn [anchor]
                                               [anchor i])
                                             anchors)))
                              (into {}))
        indexes          (keys index->anchors)
        state            (swap! state-atom assoc
                                :views views
                                :anchor->index anchor->index
                                :index->anchors index->anchors
                                :min-index (apply min indexes)
                                :max-index (apply max indexes))

        current-anchor   (get-in state [:target :anchor])
        frag             (.-hash (.-location js/window))
        requested-anchor (when (and (string? frag) (str/starts-with? frag "#"))
                           (keyword (subs frag 1)))]
    (go-to-anchor (or current-anchor requested-anchor))))


(def default-scroll
  {:x nil
   :y (fn [v]
        (case v
          1 (go-to-prev)
          -1 (go-to-next)))})


(defn clear-scroll
  []
  (reset! scroll-atom nil))


(defn set-scroll
  [x y]
  (reset! scroll-atom {:x x
                       :y y}))


(defn mimic-scroll
  [dt dx dy]
  (let [dt        (max (/ 1 60) dt)
        threshold 200
        dx (- dx)

        [dir v] (cond
                  (> (abs dx) (* 2 (abs dy))) [:x dx]
                  (> (abs dy) (* 2 (abs dx))) [:y dy]
                  :else [nil 0])
        norm-v    (cond
                    (> (/ v dt) threshold) -1
                    (< (/ v dt) (- threshold)) 1
                    :else 0)

        scroll-fn (get @scroll-atom dir (get default-scroll dir))]
    (when (and dir (not (zero? norm-v)) scroll-fn)
      (scroll-fn norm-v))))


(defn scroll-view
  []
  (let [{:keys [current views min-index] :as ctx} @state-atom
        index (:index current min-index)]
    (when index
      (into
        [:div.relative.w-full.h-full]
        (->> views
             (keep
               (fn [view]
                 (when view
                   [:div {:id    (:id (meta view))
                          :class "w-full h-full overflow-hidden z-0 absolute pointer-events-none"}
                    (into view [(assoc ctx :current-index index)])]))))))))


(defn offset-view
  [origin-index view {:keys [current-index] :as ctx}]
  (into view [(assoc ctx :offset (- (* 2 origin-index) current-index))]))
