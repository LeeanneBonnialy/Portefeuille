(ns leebonn.sections.title
  (:require
    [leebonn.i18n :as i18n]
    [leebonn.image-loader :as img]
    [leebonn.util :as util]))


(defn title
  [{:keys [offset width height narrow? transition bg-colour]}]
  (let [image-src             (if narrow?
                                "home/leeanne_4_edit.JPG"
                                "home/leeanne_1.JPG")
        font-size (if narrow?
                    (util/ratio width height (/ 1080 1920) 1
                                (* 0.25 width)
                                (* 0.15 width))

                    #_(* 0.23 width)
                    (util/ratio width height 1 (/ 1920 1080)
                                (* 0.15 width)
                                (* 0.121 width)))
        narrow-two-lines? (> (* 10 (/ font-size (- width 16 16))) 1.902)

        scene-one-top-class   {:class (str "p-4 leading-none squiggly w-full h-full font-slim"
                                           (if narrow?
                                             " pt-16 text-pink-400 text-center"
                                             " pt-4 whitespace-nowrap text-pink-400 "))
                               :style {:font-size (str font-size "px")}}
        scene-one-title-class {:class (str "scale-[100%] translate-x-[0px] translate-y-[0px]"
                                           (cond
                                             narrow-two-lines? " w-min mx-auto"
                                             narrow? " w-max mx-auto"
                                             :else " origin-left w-min")
                                           (if narrow?
                                             " text-pink-400 "
                                             " text-pink-400 "))}
        scene-two-title-class {:class (cond
                                        narrow-two-lines? "scale-[32000%] translate-x-[1500%] translate-y-[13000%]"
                                        narrow? "scale-[32000%] translate-x-[-40%] translate-y-[8600%]"
                                        :else "origin-left scale-[32000%] translate-x-[-600%] translate-y-[-210%]")
                               :style {:color bg-colour
                                       :background-color bg-colour}}]
    [:<>
     (when (contains? #{0 1} offset)
       [img/deferred-image image-src
        {:id    "background"
         :class (str "object-cover absolute w-full h-full"
                     (when narrow? " object-bottom"))}])
     (if (contains? #{0 1 2} offset)
       [:div {:id    "title-container"
              :class (str "flex h-full absolute leading-none"
                          (if narrow?
                            " w-full mx-auto"
                            " w-full"))}
        [:div scene-one-top-class
         [:div#title (util/combine-style transition
                                         {:class "pointer-events-auto space-y-8"}
                                         (if (zero? offset)
                                           scene-one-title-class
                                           scene-two-title-class))
          [:div "Leeanne Bonnialy"]
          [:div {:style {:font-size (str (* font-size 0.5) "px")}}
           [i18n/text :job-title]]]]]
       [:div {:class "w-full h-full"
              :style {:background-color bg-colour}}])]))
