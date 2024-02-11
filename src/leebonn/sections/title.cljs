(ns leebonn.sections.title
  (:require [leebonn.image-loader :as img]
            [leebonn.util :as util]))

(defn title
  [{:keys [scene width height narrow? transition bg-colour-raw]}]
  (let [image-src             (if narrow?
                                "leeanne_2.JPG"
                                "leeanne_1.JPG")
        common-classes        "p-4 leading-none"
        scene-one-top-class   (str common-classes
                                   (if narrow?
                                     (str " squiggly mt-[" (- height (* 2 0.23 width) 25) "px] w-full text-[" (* 0.23 width) "px]")
                                     (str " squiggly w-full whitespace-nowrap"
                                          " text-[" (util/ratio width height 1 (/ 1920 1080)
                                                                (* 0.15 width)
                                                                (* 0.121 width)) "px]")))
        scene-one-title-class "origin-left scale-[100%] translate-x-[0px] translate-y-[0px]"
        scene-two-title-class (if narrow?
                                "origin-left scale-[10000%] translate-x-[-250%] translate-y-[-2000%]"
                                "origin-left scale-[18000%] translate-x-[-240%] translate-y-[-6220%]")]
    [:<>
     (case scene
       (1 2) [img/deferred-image nil nil image-src
              {:id    "background"
               :class "absolute object-cover w-full h-full"}]
       nil)
     [:div {:id    "title-container"
            :class (str "flex h-full absolute leading-none"
                        (if narrow?
                          " w-2/3"
                          " w-full"))}
      [:div {:class (str transition
                         (case scene
                           1 scene-one-top-class
                           2 scene-one-top-class
                           3 scene-one-top-class
                           " hidden "))}
       [:div#title {:class (str transition
                                (case scene
                                  1 (str scene-one-title-class (if narrow?
                                                                 " text-yellow-400"
                                                                 " text-pink-400"))
                                  2 (str scene-two-title-class (str " text-" bg-colour-raw))
                                  3 (str scene-two-title-class (str " text-" bg-colour-raw))
                                  " hidden "))}
        "Leeanne Bonnialy"]]]]))
