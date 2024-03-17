(ns leebonn.sections.contact
  (:require
    [leebonn.content.common :as c]
    [leebonn.i18n :as i18n]
    [leebonn.sections.intro :as intro]
    [leebonn.util :as util]))


(def title
  {:fr "contact"
   :en "contact"})


(defn insta
  [url]
  [:a {:href url}
   [:svg.w-full.h-full
    {:xmlns   "http://www.w3.org/2000/svg"
     :fill    "currentColor"
     :viewBox "0 0 16 16"}
    [:path {:d "M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.9 3.9 0 0 0-1.417.923A3.9 3.9 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.9 3.9 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.9 3.9 0 0 0-.923-1.417A3.9 3.9 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599s.453.546.598.92c.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.5 2.5 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.5 2.5 0 0 1-.92-.598 2.5 2.5 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233s.008-2.388.046-3.231c.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92s.546-.453.92-.598c.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92m-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217m0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334"}]]])


(defn mail
  [url]
  [:a {:href url}
   [:svg.w-full.h-full {:xmlns   "http://www.w3.org/2000/svg"
                        :fill    "currentColor"
                        :viewBox "0 0 120 120"}
    [:path {:d "M 1.3177771,108.10134 V 7.0250892 C 2.2630662,2.468924 7.2414067,0.39613823 11.470816,1.2079148 H 113.35176 c 4.55616,0.9452972 6.62895,5.9236353 5.81717,10.1530452 v 101.88093 c -1.0477,5.10049 -6.7314,6.62768 -11.23115,5.81081 C 74.0588,118.91168 14.386464,118.7624 8.6044933,118.71847 2.8225228,118.67454 1.1455848,114.69818 1.3177771,108.10134 Z M 103.06911,40.489148 C 101.43822,37.547824 65.504014,67.280921 60.478363,67.280862 55.452712,67.280803 18.640133,37.63336 17.421054,40.489148 c -1.219079,2.855788 -2.600999,55.596753 -0.143968,58.843306 2.457031,3.246556 83.616564,2.162446 85.929904,0 2.31334,-2.162446 1.49301,-55.901982 -0.13788,-58.843306 z m 4.30397,-19.362999 c -0.98843,-2.495381 -92.026764,-2.936307 -94.251775,-0.01672 -2.225011,2.91959 43.301134,33.889238 47.144047,33.590828 1.24317,-0.09654 47.725258,-32.015075 47.107728,-33.574111 z"}]]])


(defn cv-download
  []
  [:a
   {:target   "_blank"
    :download "CV Leeanne Bonnialy.pdf"
    :href     "assets/CV Leeanne Bonnialy.pdf"}
   [:div.grid.grid-cols-2
    [:div.bottom-0.h-min.mt-auto.mb-8.squiggly [c/download-icon]]
    [:div {:class "ml-[-80px] text-6xl"
           :style {:writing-mode     "vertical-rl"
                   :text-orientation "mixed"
                   :transform        "rotate(180deg)"}}
     [i18n/text {:fr "ou\ntéléchargez mon CV"
                 :en "or\ndownload my CV"}]]]])


(defn linkedin
  [url]
  [:a {:href url}
   [:svg.w-full.h-full
    {:aria-hidden "true" :xmlns "http://www.w3.org/2000/svg" :fill "currentColor" :viewBox "0 0 72 72"}
    [:path {:fill-rule "evenodd" :clip-rule "evenodd" :d "M 8 0 C 3.5817264 8.1162369e-16 0 3.5817264 0 8 L 0 64 C 5.4108246e-16 68.418274 3.5817264 72 8 72 L 64 72 C 68.418274 72 72 68.418274 72 64 L 72 8 C 72 3.5817264 68.418274 -1.1842367e-15 64 0 L 8 0 z M 16.349609 10 C 19.856897 10 22.697266 12.863838 22.697266 16.396484 C 22.697266 19.929131 19.856897 22.794922 16.349609 22.794922 C 12.842321 22.794922 10 19.929131 10 16.396484 C 10 12.863838 12.842321 10 16.349609 10 z M 49.382812 26.273438 C 56.73593 26.273438 62 30.764049 62 40.050781 L 62 62 L 51.316406 62 L 51.316406 43.802734 C 51.316406 38.813379 49.419788 36.025391 45.470703 36.025391 C 41.174614 36.025391 38.929687 38.926735 38.929688 43.802734 L 38.929688 62 L 28.632812 62 L 28.632812 27.333984 L 38.929688 27.333984 L 38.929688 32.001953 C 38.929688 32.001953 42.026309 26.273437 49.382812 26.273438 z M 11.033203 27.333984 L 21.769531 27.333984 L 21.769531 62 L 11.033203 62 L 11.033203 27.333984 z "}]]])


(defn contacts
  [{:keys [narrow? height width]}]
  (let []
    [:div {:class "w-full flex m-4"}
     [:div {:class "my-auto mx-8 font-slim text-8xl md:text-9xl space-y-8"}
      [i18n/text {:fr "Alors, on match ? Rencontrons-nous !"
                  :en "So we match? Let’s connect!"}]
      [:div
       {:class "mx-auto flex pointer-events-auto justify-items-end"}
       [:div.flex.mr-4.squiggly.mt-auto [linkedin "https://www.linkedin.com/in/%F0%9F%8C%9Eleeanne-bonnialy%F0%9F%8C%9E-242b441b1/"]]
       [:div.flex.mr-4.squiggly.mt-auto [mail "mailto:bonnigeorgina@gmail.com?subject=Bonjour Leeanne"]]
       [:div.flex.mr-4
        [cv-download]]]]]))


(defn contact-page
  [before after context]
  [util/fly-in context
   [contacts context]
   before after])
