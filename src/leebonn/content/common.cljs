(ns leebonn.content.common
  (:require
    [clojure.string :as str]
    [leebonn.i18n :as i18n]
    [leebonn.image-loader :as img]
    [leebonn.sections.carousel :as carousel-overlay]
    [reagent.core :as r]))


(defn carousel
  [_images & {:as opts}]
  (let [index (r/atom 0)]
    (fn [images]
      [:div
       {:class "w-full lg:w-2/3 mx-auto py-12"}
       [carousel-overlay/carousel-view
        index
        (atom images)
        carousel-overlay/click-scroll-carousel
        opts]])))


(defn carousel-dir
  [matcher slide-count]
  [carousel (for [i (map inc (range slide-count))
                  :let [is (str i)]]
              (str/replace matcher #"%s" (str (apply str (repeat (- 4 (count is)) \0)) is)))])


(defn horizontal-rule
  [& {:keys [classes]}]
  [:hr {:class (str "w-full mt-4 h-0.5 " (or classes "bg-pink-300"))}])


(defn inline-img
  [image & {:keys [href]}]
  [:div.w-full
   [:div {:class "w-full lg:w-2/3 mx-auto px-8 md:px-14 py-12"}
    (if href
      [:a {:href href}
       [img/deferred-image
        image
        {:class "max-h-[70vh] rounded-lg shadow-lg"}]]
      [img/deferred-image
       image
       {:class "max-h-[70vh] rounded-lg shadow-lg"}])]])


(defn download-icon
  []
  [:svg.w-full {:aria-hidden "true" :xmlns "http://www.w3.org/2000/svg" :fill "none" :viewBox "0 0 24 24"}
   [:path {:stroke "currentColor" :stroke-linecap "round" :stroke-linejoin "round" :stroke-width "2" :d "M4 15v2a3 3 0 0 0 3 3h10a3 3 0 0 0 3-3v-2m-8 1V4m0 12-4-4m4 4 4-4"}]])


(defn download
  [description file]
  (let [file-name (last (str/split file #"/"))]
    [:div.w-full.pt-8
     [:div
      {:class "w-11/12 md:w-2/3 mx-auto pointer-events-auto transition-all cursor-pointer hover:scale-95 z-30"}
      [:a
       {:target   "_blank"
        :download file-name
        :href     file}
       [:div.w-16.h-16.mx-auto [download-icon]]
       [:blockquote.w-full.py-4.text-center
        [:p.text-2xl.font-medium [i18n/text description]]]]]]))


(defn title
  [text & {:keys [classes]}]
  [i18n/text {:class (str "font-slim text-6xl pb-4 " (or classes "text-pink-500"))} text])


(defn header
  [text & {:keys [classes rule-classes padding]}]
  [:<>
   [i18n/text {:class (str "font-slim text-4xl " (or padding "pt-10") " " (or classes "text-pink-500"))} text]
   [horizontal-rule :classes (or rule-classes "bg-pink-300")]])


(defn body
  [text]
  [i18n/text text])


(defn quote
  [text]
  [:blockquote.w-full.py-4.text-center
   [:p.text-2xl.italic.font-medium {:class "w-11/12 md:w-2/3 mx-auto"} [i18n/text text]]])


(defn pill
  [i18n-key & {:keys [classes]}]
  [:div {:class (str "col-span-1 row-span-1 rounded-full w-max h-min px-4 text-sm mr-2 my-2 " (or classes "bg-pink-300 text-white"))}
   [i18n/text {:class "whitespace-nowrap"} i18n-key]])


(defn competencies
  [i18ns & {:keys [classes pill-classes]}]
  [:div {:class "justify-items-center items-center h-min-fit h-min w-full flex flex-wrap"}
   [i18n/text {:class (str "font-slim text-2xl pr-4 " (or classes "text-pink-500"))}
    {:fr "Compétences mobilisées"
     :en "Acquired skills"}]
   (for [i18n i18ns]
     ^{:key i18n} [pill i18n {:classes pill-classes}])])


(defn small
  [x]
  [:span {:class "text-[21px]"} x])


(defn bullet-list
  [& items]
  (into [:ul.list-outside.list-disc
         {:class "ml-6 sm:ml-12"}]

        (map-indexed (fn [index item] ^{:key index} [:li item]) items)))


(defn page-end-buffer
  []
  [:div {:class "h-[10vh] w-full"}])
