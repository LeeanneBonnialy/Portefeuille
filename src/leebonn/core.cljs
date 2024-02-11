(ns leebonn.core
  (:require
   ["react" :as react]
   [reagent.core :as r]
   [reagent.dom.client :as dom.client]
   [leebonn.sections.projects :as projects]
   [leebonn.sections.title :as title]
   [leebonn.sections.overlay :as overlay]))


(def debug (r/atom nil))
(def transition-ms 500)
(def initial-scene 1)
(def timer (r/atom 0))
(def scene-atom (r/atom initial-scene))


(def scene-transition
  (r/atom {:total-delay     transition-ms
           :per-scene-delay transition-ms
           :from            initial-scene
           :to              initial-scene
           :changing        false
           :min-scene       1
           :max-scene       5}))


(defn set-scene
  [new-scene]
  (let [{:keys [total-delay changing]} @scene-transition
        current-scene   @scene-atom
        scene-change    (- new-scene current-scene)
        step            (cond
                          (> scene-change 0) inc
                          (< scene-change 0) dec)
        steps           (abs (- new-scene current-scene))
        per-scene-delay (int (/ total-delay steps))]
    (when (and (not changing) (not= current-scene new-scene))
      (swap! scene-transition assoc
             :per-scene-delay per-scene-delay
             :changing true
             :from current-scene
             :to new-scene)
      ((fn looper
         []
         (if (= (swap! scene-atom step) new-scene)
           (swap! scene-transition assoc :changing false)
           (js/setTimeout looper
                          per-scene-delay)))))))


(defn shift-scene
  [desired-change]
  (let [{:keys [min-scene max-scene]} @scene-transition
        current-scene @scene-atom
        desired-scene (+ current-scene desired-change)
        new-scene     (max min-scene (min max-scene desired-scene))]
    (set-scene new-scene)))


(defn mimic-scroll
  [dt dx dy]
  (let [dt        (max (/ 1 60) dt)
        threshold 200
        change    (cond
                    (> (abs dx) (abs dy)) 0
                    (> (/ dy dt) threshold) 2
                    (< (/ dy dt) (- threshold)) -2
                    :else 0)]
    #_(reset! debug [dt (/ dy dt) change])
    (shift-scene change)))


(defn on-scroll
  [v]
  (mimic-scroll (/ 1 60) (.-deltaX v) (.-deltaY v)))


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
  [seed]
  [:svg {:xmlns "http://www.w3.org/2000/svg" :version "1.1"
         :class "w-0 h-0"}
   (into [:defs
          [:filter {:id "squiggly"}
           [:feTurbulence#turbulence {:baseFrequency "0.02" :numOctaves "3" :result "noise" :seed (mod seed 1000)}]
           [:feDisplacementMap {:in "SourceGraphic" :in2 "noise" :scale (+ 5 (mod seed 3))}]]])])


(defn app
  [screen]
  (let [{:keys [width height]} @screen
        scene              @scene-atom
        narrow?            (> height width)
        transition         (str " transition-all duration-[" (:per-scene-delay @scene-transition) "ms] ")
        raw-text-colour    (cond
                             (and narrow?
                                  (= 1 scene)) "yellow-100 "
                             narrow? "yellow-800"
                             :else "white")
        common-text-colour (str " text-" raw-text-colour " ")
        bg-colour-raw      (if narrow?
                             "yellow-300"
                             "pink-300")
        bg-colour          (str " bg-" bg-colour-raw " ")
        context            {:scene              scene
                            :bg-colour          bg-colour
                            :bg-colour-raw      bg-colour-raw
                            :width              width
                            :height             height
                            :narrow?            narrow?
                            :transition         transition
                            :raw-text-colour    raw-text-colour
                            :common-text-colour common-text-colour}]
    [:div {:class (str "w-[" width "px] h-[" height "px] overflow-hidden"
                       (str " transition-colors duration-[" (:per-scene-delay @scene-transition) "ms] ")
                       bg-colour)
           :style {:transform "translateZ(0)"}}
     [title/title context]
     [projects/projects context]
     [overlay/overlay context]
     [svg-defs @timer]
     [:div.absolute.text-white.font-sans (str @debug)]]))


(def stop-ctx (atom nil))


(defn ^:dev/before-load stop
  []
  (let [{:keys [stop-fn]} @stop-ctx]
    (when stop-fn
      (stop-fn))
    (js/console.log "Stopping...")))


(defn on-resize
  [_event screen-atom]
  (reset! screen-atom {:width  (.-innerWidth js/window)
                       :height (.-innerHeight js/window)})
  (reset! debug {:width  (.-innerWidth js/window)
                 :height (.-innerHeight js/window)}))


(defn ^:dev/after-load start
  []
  (js/console.log "Starting...")
  (let [screen               (r/atom {:width  (.-innerWidth js/window)
                                      :height (.-innerHeight js/window)})
        stop-timer           (start-loop 200 (fn [timestamp]
                                               (reset! timer timestamp)))
        root                 (dom.client/create-root (.getElementById js/document "app"))
        resize-listener      #(on-resize % screen)
        scroll-listener      on-scroll
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
                                 (mimic-scroll dt dx dy)))]
    (.addEventListener js/window "resize" resize-listener)
    (.addEventListener js/window "wheel" scroll-listener)
    (.addEventListener js/window "touchstart" touch-start-listener)
    (.addEventListener js/window "touchend" touch-end-listener)
    (dom.client/render
     root
     [app screen])
    (reset! stop-ctx {:stop-fn (fn []
                                 (stop-timer)
                                 (.removeEventListener js/window "resize" resize-listener)
                                 (.removeEventListener js/window "wheel" scroll-listener)
                                 (.removeEventListener js/window "touchstart" touch-start-listener)
                                 (.removeEventListener js/window "touchend" touch-end-listener)
                                 (dom.client/unmount root))})))


(defn ^:export init
  []
  (start))
