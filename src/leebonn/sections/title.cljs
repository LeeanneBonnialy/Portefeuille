(ns leebonn.sections.title
  (:require
    [leebonn.image-loader :as img]
    [leebonn.util :as util]))


(defn title
  [{:keys [scene width height narrow? transition bg-colour]}]
  (let [image-src             (if narrow?
                                "leeanne_2.JPG"
                                "leeanne_1.JPG")
        common-classes        "p-4 leading-none"
        scene-one-top-class   {:class (str common-classes " squiggly w-full "
                                           (if narrow?
                                             " text-yellow-400 "
                                             " whitespace-nowrap text-pink-400 "))
                               :style (if narrow?
                                        {:margin-top (str (- height (* 2 0.23 width) 25) "px")
                                         :font-size  (str (* 0.23 width) "px")}
                                        {:font-size (str (util/ratio width height 1 (/ 1920 1080)
                                                                     (* 0.15 width)
                                                                     (* 0.121 width))
                                                         "px")})}
        scene-one-title-class {:class (str "origin-left scale-[100%] translate-x-[0px] translate-y-[0px]"
                                           (if narrow?
                                             " text-yellow-400 "
                                             " text-pink-400 "))}
        scene-two-title-class {:class (if narrow?
                                        "origin-left scale-[10000%] translate-x-[-260%] translate-y-[-2000%]"
                                        "origin-left scale-[18000%] translate-x-[-240%] translate-y-[-6220%]")
                               :style {:color bg-colour}}]
    [:<>
     (when (contains? #{1 2} scene)
       [img/deferred-image nil nil image-src
        {:id    "background"
         :class "absolute object-cover w-full h-full"}])
     (when (contains? #{1 2 3} scene)
       [:div {:id    "title-container"
              :class (str "flex h-full absolute leading-none"
                          (if narrow?
                            " w-2/3"
                            " w-full"))}
        [:div scene-one-top-class
         [:div#title (util/combine-style transition
                                         (if (= scene 1)
                                           scene-one-title-class
                                           scene-two-title-class))
          "Leeanne Bonnialy"]]])]))
