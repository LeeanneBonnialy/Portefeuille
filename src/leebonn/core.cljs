(ns leebonn.core
  (:require
    ["react" :as react]
    [leebonn.navigation :as nav]
    [leebonn.sections.overlay :as overlay]
    [leebonn.sections.projects :as projects]
    [leebonn.sections.title :as title]
    [leebonn.util :as util]
    [reagent.core :as r]
    [reagent.dom.client :as dom.client]))


(def debug (r/atom nil))
(def timer (r/atom 0))
(def screen (r/atom {:width 0 :height 0}))


(defn debouncer
  "Returns a function that accepts threshold, f, & args.
  It will call f with args only after threshold has passed without new calls
  to the function."
  []
  (let [t (atom nil)]
    (fn [threshold f & args]
      (when-let [current-timeout @t] (js/clearTimeout current-timeout))
      (reset! t (js/setTimeout #(do
                                  (reset! t nil)
                                  (apply f args))
                               threshold)))))


(defn debounce
  "Returns a function that will call f only after threshold has passed without new calls
  to the function."
  [threshold f]
  (partial (debouncer) threshold f))


(def can-scroll? (atom true))
(def accumulated-scroll (atom nil))
(def scroll-timeout (atom nil))


(defn do-scroll
  []
  (let [{:keys [start end dx dy]} @accumulated-scroll]
    (reset! can-scroll? false)
    (reset! accumulated-scroll nil)
    (js/setTimeout #(reset! can-scroll? true)
                   (/ nav/transition-ms 3))
    (nav/mimic-scroll (/ (- end start) 1000) dx dy)))


(defn on-scroll
  [v]
  (when @can-scroll?
    (when-let [t @scroll-timeout] (js/clearTimeout t))
    (let [{:keys [dx dy start]} @accumulated-scroll
          now    (system-time)
          start  (or start (- now (/ 1 60)))
          dx     (+ dx (.-deltaX v))
          dy     (+ dy (.-deltaY v))

          passed (- now start)]
      (if (> passed 100)
        (do-scroll)
        (do
          (reset! accumulated-scroll {:start start :end now :dx dx :dy dy})
          (reset! scroll-timeout (js/setTimeout do-scroll 50)))))))


(defn ms-now
  []
  (.valueOf (js/Date.)))


(defn start-loop
  [cycle f]
  (let [run?    (atom true)
        kill-fn (fn [] (reset! run? false))
        looper  (fn looper
                  []
                  (when @run?
                    (f (ms-now))
                    (js/setTimeout looper cycle)))]
    (looper)
    kill-fn))


(defn svg-defs
  []
  (let [seed @timer]
    [:svg {:xmlns "http://www.w3.org/2000/svg" :version "1.1"
           :class "w-0 h-0"}
     (into [:defs
            [:filter {:id "squiggly"}
             [:feTurbulence#turbulence {:baseFrequency "0.02" :numOctaves "3" :result "noise" :seed (mod seed 1000)}]
             [:feDisplacementMap {:in "SourceGraphic" :in2 "noise" :scale (+ 5 (mod seed 3))}]]])]))


(defn test-view
  [ctx]
  [:div (str ctx)])


(defn with-context
  [view origin-index {:keys [current-index scene-transition-ms] :as nav-context}]
  (let [{:keys [width height]} @screen
        narrow?           (> height width)
        transition        {:class "transition-all"
                           :style {:transition-duration (str scene-transition-ms "ms")}}
        text-colour       (cond
                            (and narrow?
                                 (= 0 current-index)) "#FEF3C7"
                            narrow? "#92400E"
                            :else "#FFFFFF")
        modal-text-colour (cond
                            (and narrow?
                                 (= 0 current-index)) "#FEF3C7"
                            narrow? "#92400E"
                            @projects/detail-opened "#F472B6"
                            :else "#FFFFFF")

        bg-colour         (if narrow?
                            "#FCD34D"
                            "#F9A8D4")
        offset            (- current-index (* 2 origin-index))
        context           (assoc nav-context
                                 :offset offset
                                 :bg-colour bg-colour
                                 :width width
                                 :height height
                                 :narrow? narrow?
                                 :transition transition
                                 :text-colour text-colour
                                 :modal-text-colour modal-text-colour)]
    (into view [context])))


(defn projects-per-page
  [ctx projects]
  (let [[x y] (projects/max-project-fit ctx)
        max-per-page      (* x y)
        min-pages         (count (->> projects
                                      (partition-all max-per-page)))
        c-projects        (count projects)
        options           (range 1 (inc (min max-per-page 4)))

        option-preference (fn [o]
                            (let [r (rem c-projects o)
                                  r (if (zero? r) r (- o r))]
                              [(if (= 1 o) 1000000 r)
                               (- o)]))
        [best-option]          (sort-by option-preference options)]
    best-option))


(defn set-scene
  [ctx]
  (let [projects-per-page (projects-per-page ctx projects/projects)

        title             [{:id      :title
                            :anchors [:home]
                            :view    [title/title]}]

        project-groups    (->> projects/projects
                               (partition-all projects-per-page)
                               (map-indexed (fn [i projects]
                                              {:id      (str "projects-slice-" i)
                                               :anchors (mapcat (juxt :anchor :detail-anchor) projects)
                                               :view    [projects/project-page projects]})))

        after             [{:id   :project-modal
                            :view [projects/project-detail]}
                           {:id   :language
                            :view [overlay/overlay]}
                           {:id   :svg-filters
                            :view [svg-defs]}]

        all-parts         (concat title
                                  project-groups
                                  after)

        {:keys [views index->anchors]} (reduce
                                         (fn [{:keys [index] :as agg} {:keys [anchors view id]}]
                                           (if anchors
                                             (-> agg
                                                 (update :index inc)
                                                 (update :views conj (vary-meta [with-context view index] assoc :id id))
                                                 (update :index->anchors assoc index anchors))
                                             (update agg :views conj (vary-meta [with-context view 0] assoc :id id))))
                                         {:index          0
                                          :views          []
                                          :index->anchors {}}
                                         all-parts)]
    (nav/set-scene views index->anchors)))


(defn get-screen-size
  []
  {:width  (.-innerWidth js/window)
   :height (.-innerHeight js/window)})


(defn on-resize
  []
  (let [new-screen (get-screen-size)]
    (reset! screen new-screen)
    (set-scene new-screen)))


(defn fixed-scroll
  []
  (let [{:keys [width height]} @screen]
    [:div {:class "overflow-hidden"
           :style {:width  (str width "px")
                   :height (str height "px")}}
     [nav/scroll-view]]))


(defn setup-listeners
  []
  (let [stop-timer           (start-loop 200 (fn [timestamp]
                                               (reset! timer timestamp)))
        touch                (r/atom nil)
        touch-start-listener (fn [e]
                               (reset! touch {:time  (ms-now)
                                              :touch (first (.-changedTouches e))}))
        touch-end-listener   (fn [e]
                               (let [{start-t :time start :touch} @touch
                                     end-t (ms-now)
                                     end   (first (.-changedTouches e))
                                     dx    (- (.-screenX start) (.-screenX end))
                                     dy    (- (.-screenY start) (.-screenY end))
                                     dt    (/ (max 1 (- end-t start-t)) 1000)]
                                 (reset! touch nil)
                                 (nav/mimic-scroll dt dx dy)))]
    (.addEventListener js/window "resize" on-resize)
    (.addEventListener js/window "wheel" on-scroll)
    (.addEventListener js/window "touchstart" touch-start-listener)
    (.addEventListener js/window "touchend" touch-end-listener)
    (fn []
      (stop-timer)
      (.removeEventListener js/window "resize" on-resize)
      (.removeEventListener js/window "wheel" on-scroll)
      (.removeEventListener js/window "touchstart" touch-start-listener)
      (.removeEventListener js/window "touchend" touch-end-listener))))


(def stop-ctx (atom nil))


(defn ^:dev/before-load stop
  []
  (let [{:keys [stop-fn]} @stop-ctx]
    (when stop-fn
      (stop-fn))
    (js/console.log "Stopping...")))


(defn ^:dev/after-load start
  []
  (js/console.log "Starting...")
  (let [stop-listeners (setup-listeners)
        root           (dom.client/create-root (.getElementById js/document "app"))]
    (on-resize)
    (dom.client/render
      root
      [fixed-scroll])
    (reset! stop-ctx {:stop-fn (fn []
                                 (stop-listeners)
                                 (dom.client/unmount root))})))


(defn ^:export init
  []
  (start))
