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


(defn on-scroll
  [v]
  (nav/mimic-scroll (/ 1 60) (.-deltaX v) (.-deltaY v)))


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
        narrow?     (> height width)
        transition  {:class "transition-all"
                     :style {:transition-duration (str scene-transition-ms "ms")}}
        text-colour (cond
                      (and narrow?
                           (= 0 current-index)) "#FEF3C7"
                      narrow? "#92400E"
                      :else "#FFFFFF")

        bg-colour   (if narrow?
                      "#FCD34D"
                      "#F9A8D4")
        offset      (- current-index (* 2 origin-index))
        context     (assoc nav-context
                           :offset offset
                           :bg-colour bg-colour
                           :width width
                           :height height
                           :narrow? narrow?
                           :transition transition
                           :text-colour text-colour)]
    (into view [context])))


(defn set-scene
  [{:keys [width height] :as ctx}]
  (let [[x y] (projects/project-fit ctx)
        projects-per-page (* x y)

        title             [{:anchors [:home]
                            :view    [title/title]}]

        project-groups    (->> projects/projects
                               (partition-all projects-per-page)
                               (map (fn [projects]
                                      {:anchors (map :anchor projects)
                                       :view    [projects/project-page projects]})))

        after             [{:view [overlay/overlay]}
                           {:view [svg-defs]}]

        all-parts         (concat title
                                  project-groups
                                  after)

        {:keys [views index->anchors]} (reduce
                                         (fn [{:keys [index] :as agg} {:keys [anchors view]}]
                                           (if anchors
                                             (-> agg
                                                 (update :index inc)
                                                 (update :views conj [with-context view index])
                                                 (update :index->anchors assoc index anchors))
                                             (update agg :views conj [with-context view 0])))
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
