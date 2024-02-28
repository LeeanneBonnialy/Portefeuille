(ns leebonn.sections.intro
  (:require
    ["react" :as r]
    [leebonn.i18n :as i18n]
    [leebonn.util :as util]))


(defn page
  [offset]
  (let [offset (- offset 110)
        o      (str "-5.48468," (+ 133.8813 offset))]
    [:svg
     {:xmlns   "http://www.w3.org/2000/svg"
      :fill    "currentColor"
      :viewBox (str "0 0 210 " (+ 297 offset))}
     [:path {:d "M 8.0977115,158.031 C 6.4517167,126.78305 7.1684751,125.07062 3.9705747,80.78648 0.84815951,37.54765 3.6673572,5.32814 3.6673572,5.32814 L 19.11686,5.4475822 19.824007,18.645777 c 0,0 -9.9033494,9.289898 2.217521,9.733755 12.12087,0.443855 1.897003,-10.68686 1.897003,-10.68686 V 6.316866 l 27.563608,-2.166745 0.152232,15.705017 c 0,0 -11.095037,8.552787 1.886696,9.963224 12.981734,1.410437 2.578605,-11.181839 2.578605,-11.181839 L 57.002693,4.1256211 89.018641,3.2362608 90.155062,19.16543 c 0,0 -13.428322,9.408004 0.350905,9.69671 13.779223,0.288701 4.901721,-9.958593 4.901721,-9.958593 V 3.2149702 l 32.946852,1.2141689 -0.17751,16.9626139 c 0,0 -10.81255,10.174261 0.93831,11.051856 11.75086,0.8776 3.54364,-10.327103 3.54364,-10.327103 l 2.13193,-15.8157606 33.24847,4.2423936 0.77121,16.28291 c 0,0 -8.60643,3.553312 0.42956,6.180265 9.03598,2.626953 3.83084,-7.355267 3.83084,-7.355267 l 0.51163,-12.949414 c 0,0 19.62709,3.885002 25.20515,3.885002 0,0 5.39579,36.750799 6.99864,67.267183 1.55398,29.585862 1.15243,57.103822 1.6624,74.177182"}]
     [:path {:d (str "m 207.44881,156.97233 c 0.50998,17.07336 " o " " o " 0,0 -17.23907,-1.20352 -50.28179,-0.69682 -31.80782,0.48777 -147.659354,2.05631 -147.659354,2.05631 0,0 "
                     "5.3211982," (- -111.57713 offset)
                     " 4.0747255,"
                     (- -135.24046 offset))}]]))


(defn letter
  [{:keys [width height] :as ctx}]
  (let [ref      (r/createRef)
        aw       width
        ah       (- height 116 32)
        mx       (str (* width 0.17) "px")
        offset-h (- height 116 32 (* width 1))]
    [:div {:ref   ref
           :class "w-full max-h-full my-auto flex-shrink h-max-fit text-white"}
     #_[:div {:class "absolute"}
        (str "abc" (when-let [el (.-current ref)]
                     (str (.getBoundingClientRect el))))
        #_[page offset-h #_(max -120 (min 0 offset-h))]]
     [:div {:class "text-black font-slim text-3xl top-0"
            :style {:margin-left  mx
                    :margin-right mx}}
      (apply str (interleave (repeat " ") (range 96)))]]))


(defn pill
  [i18n-key]
  [:div {:class "col-span-1 row-span-1 bg-purple-400 rounded-full w-max h-min px-4"}
   [i18n/text i18n-key]])


(defn competencies
  [{:keys [text-colour]}]
  [:div {:class "flex-shrink my-auto justify-items-center h-min-fit gap-4 h-min w-full grid grid-cols-2 grid-rows-3"
         :style {:color text-colour}}
   [pill :competency-1]
   [pill :competency-2]
   [pill :competency-3]
   [pill :competency-4]
   [pill :competency-5]
   [pill :competency-6]])


(defn intro-body
  [{:keys [narrow? height width] :as ctx}]
  (let []
    [:div {:class (str "w-full h-full p-4  flex justify-center pointer-events-auto"
                       (when narrow? " flex-col "))}
     [letter ctx]
     [competencies ctx]]))


(defn intro
  [context]
  [util/fly-in context
   [intro-body context]
   "translate(0%,100%)"
   "translate(0%,-100%)"])