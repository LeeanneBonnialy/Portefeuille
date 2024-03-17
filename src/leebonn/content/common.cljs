(ns leebonn.content.common
  (:require
    [clojure.string :as str]
    [leebonn.i18n :as i18n]
    [leebonn.image-loader :as img]
    [leebonn.sections.carousel :as carousel-overlay]
    [reagent.core :as r]))


(defn carousel
  [& _images]
  (let [index (r/atom 0)]
    (fn [& images]
      [:div
       {:class "w-full lg:w-2/3 mx-auto py-12"}
       [carousel-overlay/carousel-view
        index
        (atom images)
        carousel-overlay/click-scroll-carousel]])))


(defn horizontal-rule
  []
  [:hr {:class "w-full mt-4 h-0.5 bg-pink-300"}])


(defn inline-img
  [image]
  [:div.w-full
   [:div {:class "w-full lg:w-2/3 mx-auto px-8 md:px-14 py-12"}
    [img/deferred-image
     image
     {:class "rounded-lg shadow-lg"}]]])


(defn download
  [description file]
  (let [file-name (last (str/split file #"/"))]
    [:div.w-full
     [:div
      {:class "w-11/12 md:w-2/3 mx-auto pointer-events-auto transition-all cursor-pointer hover:scale-95 z-30"}
      [:a
       {:target   "_blank"
        :download file-name
        :href     file}
       [:svg.w-16.h-16.mx-auto {:aria-hidden "true" :xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :fill "none" :viewBox "0 0 24 24"}
        [:path {:stroke "currentColor" :stroke-linecap "round" :stroke-linejoin "round" :stroke-width "2" :d "M4 15v2a3 3 0 0 0 3 3h10a3 3 0 0 0 3-3v-2m-8 1V4m0 12-4-4m4 4 4-4"}]]
       [:blockquote.w-full.py-4.text-center
        [:p.text-2xl.font-medium [i18n/text description]]]]]]))


(defn title
  [text]
  [i18n/text {:class "font-slim text-6xl text-pink-500 pb-4"} text])


(defn header
  [text]
  [:<>
   [i18n/text {:class "pt-8 font-slim text-4xl text-pink-500"} text]
   [horizontal-rule]])


(defn body
  [text]
  [i18n/text text])


(defn quote
  [text]
  [:blockquote.w-full.py-4.text-center
   [:p.text-2xl.italic.font-medium {:class "w-11/12 md:w-2/3 mx-auto"} [i18n/text text]]])


(defn pill
  [i18n-key]
  [:div {:class "col-span-1 row-span-1 bg-pink-300 text-white rounded-full w-max h-min px-4 text-sm mr-2 my-2"}
   [i18n/text {:class "whitespace-nowrap"} i18n-key]])


(defn competencies
  [& i18ns]
  [:div {:class "justify-items-center items-center h-min-fit h-min w-full flex flex-wrap"}
   [i18n/text {:class "font-slim text-2xl text-pink-500 pr-4"} {:fr "Compétences mobilisées"
                                                                :en "Acquired skills"}]
   (for [i18n i18ns]
     ^{:key i18n} [pill i18n])])


(defn page-end-buffer
  []
  [:div {:class "h-[10vh] w-full"}])
