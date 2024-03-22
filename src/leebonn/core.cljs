(ns leebonn.core
  (:require
    ["react" :as react]
    [leebonn.navigation :as nav]
    [leebonn.sections.carousel :as carousel]
    [leebonn.sections.contact :as contact]
    [leebonn.sections.intro :as intro]
    [leebonn.sections.overlay :as overlay]
    [leebonn.sections.projects :as projects]
    [leebonn.sections.title :as title]
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
     [:defs
      [:filter {:id "squiggly"}
       [:feTurbulence#turbulence {:baseFrequency "0.02" :numOctaves "3" :result "noise" :seed (mod seed 1000)}]
       [:feDisplacementMap {:in "SourceGraphic" :in2 "noise" :scale (+ 5 (mod seed 3))}]]]
     [:defs
      [:filter {:id "squiggly-lite"}
       [:feTurbulence#turbulence {:baseFrequency "0.02" :numOctaves "3" :result "noise" :seed (mod seed 1000)}]
       [:feDisplacementMap {:in "SourceGraphic" :in2 "noise" :scale (+ 2 (mod seed 3))}]]]]))


(defn test-view
  [ctx]
  [:div (str ctx)])


(defn with-context
  [self-context menu-anchors view origin-index {:keys [current-index scene-transition-ms] :as nav-context}]
  (let [{:keys [width height]} @screen
        narrow?           (> height width)
        transition        {:class "transition-all"
                           :style {:transition-duration (str scene-transition-ms "ms")}}

        overlay (get self-context (/ (get-in nav-context [:target :index]) 2))
        text-colour       "#FFFFFF"
        modal-text-colour (if (or @overlay/open-atom?
                                  @projects/detail-opened)
                            (:text-colour overlay "#F472B6")
                            "#FFFFFF")

        bg-colour         "#F9A8D4"
        offset            (- current-index (* 2 origin-index))
        context           (-> nav-context
                              (assoc
                                :menu-anchors menu-anchors
                                :offset offset
                                :bg-colour bg-colour
                                :width width
                                :height height
                                :narrow? narrow?
                                :transition transition
                                :text-colour text-colour
                                :modal-text-colour modal-text-colour)
                              (merge overlay))]
    (into view [context])))


(defn group-projects
  [projects per-page]
  (->> (partition-by (juxt :bg-colour :text-colour) projects)
       (mapcat #(partition-all per-page %))))


(defn group-badness
  [groups desired-per-page]
  (->> groups
       (map (fn [g] (abs (- desired-per-page (count g)))))
       (reduce + 0)))


(defn projects-per-page
  [ctx _projects]
  (let [[x y] (projects/max-project-fit ctx)
        max-per-page      (* x y)
        ;; options           (map inc (range (min max-per-page 4)))
        ;; option-preference (fn [o]
        ;;                    (- (group-badness (group-projects projects o) o)))
        ;; [best-option] (sort-by option-preference options)
        ]
    max-per-page))


(defn set-scene
  [ctx]
  (let [projects-per-page (projects-per-page ctx projects/projects)

        intro             [{:id      :title
                            :anchors [:home]
                            :view    [title/title]}
                           {:id      :intro
                            :anchors [:intro :intro-detail]
                            :view    [intro/intro]}]

        project-groups    (group-projects projects/projects projects-per-page)
        project-views     (->> project-groups
                               (map-indexed (fn [i projects]
                                              (let [last? (= i (dec (count project-groups)))]
                                                {:id      (str "projects-slice-" i)
                                                 :anchors (mapcat (juxt :anchor :detail-anchor) projects)
                                                 :view    [projects/project-page
                                                           projects
                                                           (if (zero? i)
                                                             "translate(0%,100%)"
                                                             "translate(100%,0%)")
                                                           (if last?
                                                             "translate(0%,-100%)"
                                                             "translate(-100%,0%)")]
                                                 :context (let [bg-colour   (some :bg-colour projects)
                                                                text-colour (some :text-colour projects)]
                                                            (cond-> {}
                                                              bg-colour (assoc :bg-colour bg-colour)
                                                              text-colour (assoc :text-colour text-colour)))}))))

        after             [{:id      :contact
                            :anchors [:contact]
                            :view    [contact/contact-page
                                      "translate(0%,100%)"
                                      "translate(0%,-100%)"]}
                           {:id   :project-modal
                            :view [projects/project-detail]}
                           {:id   :menu
                            :view [overlay/overlay]}
                           #_{:id   :carousel
                            :view [carousel/carousel-overlay]}
                           {:id   :svg-filters
                            :view [svg-defs]}]

        all-parts         (:parts (reduce
                                    (fn [{:keys [index] :as agg} {:keys [anchors] :as part}]
                                      (if anchors
                                        (-> agg
                                            (update :index inc)
                                            (update :parts conj (assoc part :index index)))
                                        (update agg :parts conj part)))
                                    {:index 0
                                     :parts []}
                                    (concat intro
                                            project-views
                                            after)))

        menu-anchors      (concat
                            [{:title  "home"
                              :anchor :home}
                             {:title  intro/title
                              :anchor :intro-detail}]
                            (mapv #(assoc % :anchor (:detail-anchor %)) projects/projects)
                            [{:title  "contact"
                              :anchor :contact}])

        self-context      (->> all-parts
                               (filter :index)
                               (filter :context)
                               (map (juxt :index :context))
                               (into {}))

        {:keys [views index->anchors]} (reduce
                                         (fn [agg {:keys [index anchors view id]}]
                                           (if anchors
                                             (-> agg
                                                 (update :views conj (vary-meta [with-context self-context menu-anchors view index] assoc :id id))
                                                 (update :index->anchors assoc index anchors))
                                             (update agg :views conj (vary-meta [with-context self-context menu-anchors view 0] assoc :id id))))
                                         {:views          []
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
                               (if @touch
                                 (swap! touch update :touches concat (.-changedTouches e))
                                 (reset! touch {:time    (ms-now)
                                                :touches (.-changedTouches e)})))
        touch-end-listener   (fn [e]
                               (let [{start-t :time touches :touches} @touch
                                     start (first touches)
                                     end-t (ms-now)
                                     end   (first (.-changedTouches e))
                                     dx    (- (.-screenX start) (.-screenX end))
                                     dy    (- (.-screenY start) (.-screenY end))
                                     dt    (/ (max 1 (- end-t start-t)) 1000)]
                                 (reset! touch nil)
                                 (when (= 1 (.-length touches))
                                   (nav/mimic-scroll dt dx dy))))]
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
