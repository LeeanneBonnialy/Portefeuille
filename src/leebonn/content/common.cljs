(ns leebonn.content.common
  (:require
    [leebonn.i18n :as i18n]
    [leebonn.image-loader :as img]
    [leebonn.sections.carousel :as carousel-overlay]
    [reagent.core :as r]))


(defn carousel
  [& [image :as images]]
  [:div.w-full [img/deferred-image
                image
                {:class    "w-11/12 md:w-2/3 mx-auto rounded-lg shadow-lg hover:scale-95 transition-all cursor-pointer"
                 :on-click (fn [_e]
                             (println "here")
                             (carousel-overlay/open-carousel images))}]])


(defn inline-img
  [image]
  [:div.w-full [img/deferred-image
                image
                {:class "w-11/12 md:w-2/3 mx-auto rounded-lg shadow-lg"}]])


(defn title
  [text]
  [i18n/text {:class "font-slim text-6xl text-pink-500"} text])


(defn header
  [text]
  [i18n/text {:class "pt-4 font-slim text-4xl text-pink-500"} text])


(defn body
  [text]
  [i18n/text text])


(defn pill
  [i18n-key]
  [:div {:class "col-span-1 row-span-1 bg-pink-300 text-white rounded-full w-max h-min px-4"}
   [i18n/text {:class "whitespace-nowrap"} i18n-key]])


(defn competencies
  [& i18ns]
  [:div {:class "justify-items-center h-min-fit gap-2 h-min w-full flex flex-wrap"}
   (for [i18n i18ns]
     ^{:key i18n} [pill i18n])])
