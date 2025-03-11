(ns leebonn.sections.intro
  (:require
    [leebonn.content.common :as c]
    [leebonn.i18n :as i18n]
    [leebonn.navigation :as nav]
    [leebonn.sections.projects :as projects]))


(def title {:fr "Qui suis-je ?" :en "Let’s meet!"})


(defn letter
  [ctx proj]
  [:div {:class "mx-[-48px] h-min m-auto"}
   [:div {:class "max-w-96 h-min text-xl sm:text-2xl m-auto text-center text-white pointer-events-auto"}
    [i18n/text {:class "text-xl sm:text-2xl italic"}
     {:fr "“I am more than a communication strategist, I am a storyteller and a bold conversation-starter”.\n\n"
      :en "“I am more than a communication strategist, I am a storyteller and a bold conversation-starter”.\n\n"}]
    [i18n/text {:class "text-xl sm:text-2xl"}
     {:fr [:span "Je suis "
           [:b "Leeanne Bonnialy"]
           ", une amoureuse des mots et des questions audacieuses."]
      :en [:span "I am "
           [:b "Leeanne Bonnialy"]
           ", a lover of words and question marks."]}]
    [i18n/text {:class "text-sm mt-4"} {:en "read more about me"
                                        :fr "plus sur moi"}]
    [:svg.w-16.h-12.mx-auto.squiggly-lite {:class       "transition-all hover:scale-90 cursor-pointer"
                                           :on-click    (partial projects/open-project proj)
                                           :aria-hidden "true" :xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :fill "currentColor" :viewBox "0 0 24 24"}
     [:path {:fill-rule "evenodd" :d "M11 4.717c-2.286-.58-4.16-.756-7.045-.71A1.99 1.99 0 0 0 2 6v11c0 1.133.934 2.022 2.044 2.007 2.759-.038 4.5.16 6.956.791V4.717Zm2 15.081c2.456-.631 4.198-.829 6.956-.791A2.013 2.013 0 0 0 22 16.999V6a1.99 1.99 0 0 0-1.955-1.993c-2.885-.046-4.76.13-7.045.71v15.081Z" :clip-rule "evenodd"}]]
    [i18n/text {:class "text-sm"} {:en "discover my projects." :fr "déouvrez mes projets"}]
    [:svg.w-16.h-12.mx-auto.squiggly-lite {:class       "transition-all hover:scale-90 cursor-pointer"
                                           :on-click    (partial nav/go-to-next)
                                           :aria-hidden "true" :xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :fill "none" :viewBox "0 0 24 24"}
     [:path {:stroke "currentColor" :stroke-linecap "round" :stroke-linejoin "round" :stroke-width "2" :d "M12 19V5m0 14-4-4m4 4 4-4"}]]]])


(defn hard-skills
  [{:keys [padding header-classes rule-classes]}]
  [:div.space-y-4
   [c/header {:fr "Hard skills"
              :en "Hard skills"}
    :padding padding
    :classes header-classes
    :rule-classes rule-classes]
   [c/body {:fr "✍\uD83C\uDFFE Plume incisive et sensible \n\uD83E\uDD33\uD83C\uDFFE Créer et piloter une stratégie de contenu social média (de la création de concept à la production) \n\uD83D\uDC65 Community management\n\uD83D\uDCC8 Analyse de KPIs pour optimiser la stratégie \n\uD83D\uDD0D Analyser des stratégies de marque \n\uD83D\uDC40 Garder l’oeil ouvert sur la concurrence \n\uD83D\uDD78\uFE0F Attrapeuse d’insights \n\uD83D\uDCDA Condenser des rapports en une phrase \n\uD83D\uDDD2\uFE0F Conduire une étude qualitative et quantitative \n\n\uD83E\uDD47 Ceinture noire en création graphique (Adobe Creative Suite, Figma et Canva) \n▶\uFE0F Montage vidéo\n\uD83D\uDC4D\uD83C\uDFFE Outils de publication et de gestion de contenu (Notion, HubSpot Meta Business Manager) \n\uD83E\uDD1D\uD83C\uDFFE Copine avec Powerpoint & Google slide \n\uD83E\uDDD1\uD83C\uDFFD\u200D\uD83D\uDCBB Jouer les Big Brother avec Visibrain"
            :en "✍\uD83C\uDFFE Strong writing skills and storytelling \n\uD83E\uDD33\uD83C\uDFFE Designing and executing a targeted social media strategy (from ideation to content production) \n\uD83D\uDC65 Community Management \n\uD83D\uDCC8 Analyzing KPIs to refine strategy and maximize reach\n\uD83D\uDD0D Analyzing brand strategies \n\uD83D\uDC40 Keeping an eye on the competition\n\uD83D\uDD78\uFE0F Insights catcher \n\uD83D\uDCDA Condensing reports into a single sentence\n\uD83D\uDDD2\uFE0F Conducting qualitative and quantitative studies \n\n\uD83E\uDD47 Black belt in graphic design (Adobe Creative Suite, Figma and Canva) \n▶\uFE0F Video editing \n\uD83D\uDC4D\uD83C\uDFFE Social media management tools (Notion, HubSpot, Meta Business Manager) \n\uD83E\uDD1D\uD83C\uDFFE BFFs with PowerPoint & Google Slides \n\uD83E\uDDD1\uD83C\uDFFD\u200D\uD83D\uDCBB Playing Big Brother with Visibrain"}]])


(defn soft-skills
  [{:keys [padding header-classes rule-classes]}]
  [:div.space-y-4
   [c/header {:fr "Soft skills"
              :en "Soft skills"}
    :padding padding
    :classes header-classes
    :rule-classes rule-classes]
   [c/body {:fr "❤\uFE0F Curiosité \n\uD83D\uDCA1 Créativité \n✨ Travail en équipe \n\uD83D\uDCCF Gestion de projet \n\uD83D\uDDE3\uFE0F A l’aise à l’oral \n\uD83E\uDD14 Esprit critique"
            :en "❤\uFE0F Curiosity \n\uD83D\uDCA1 Creative mindset and problem solving  \n✨ Teamwork lover \n\uD83D\uDCCF Project management\n\uD83D\uDDE3\uFE0F Comfortable speaking in public \n\uD83E\uDD14 Critical thinking"}]])


(defn cv-download
  []
  [c/download {:en "Download my CV"
               :fr "Téléchargez mon CV"}
   (get {:en "assets/EN - CV Leeanne Bonnialy.pdf"
         :fr "assets/FR - CV Leeanne Bonnialy.pdf"}
        @i18n/lang)])

(defn large-letter-1
  [_opts]
  [:div
   [:div.space-y-4
    [c/header title :classes "" :rule-classes "bg-white"]
    [c/body {:fr "“I am more than a communication strategist, I am a storyteller and a bold conversation-starter”.\n\nJe suis Leeanne Bonnialy, une amoureuse des mots et des questions audacieuses. Mon histoire est celle d’une culture-lover, formée à la recherche en science humaine,devenue spécialiste en stratégie social media. Passionnée par le spectacle vivant et l’écriture, j’ai d’abord fait mes armes en communication stratégique et évènementielle auprès d’institutions culturelles. Après l’obtention de mon master en communication au CELSA-Sorbonne Université j’ai décidé de réaliser une année post-master pour me spécialiser en stratégie de marque."
             :en "“I am more than a communication strategist, I am a storyteller and a bold conversation-starter”.\n\nI am Leeanne Bonnialy, a lover of words and question marks. My story is one of a culture-aficionado, trained in humanities research who turned communication strategist. Passionate about theater and writing, I honed my skills in strategic communication and event management within cultural institutions. After earning my master's degree in communication from Sorbonne University, I pursued a post-master's year to specialize in brand strategy."}]
    [c/body {:fr "Je me suis tournée vers la stratégie de marque car c’est le point de rencontre idéal entre la curiosité que je nourris pour comprendre les gens et ma passion pour les histoires. Créer des récits de marque fait écho à ce pourquoi je fais de la communication : pour raconter des histoires qui bousculent le monde et créent des rencontres. Mes atouts ? Une fine capacité d’analyse, une belle plume et une bonne dose de créativité pour concevoir des récits de marque en phase avec les publics cibles et les problématiques contemporaines."
             :en "I want to do brand strategy because it’s the perfect intersection between my curiosity to understand people and my passion for storytelling. Creating brand narratives resonates with my reason for starting communication: to tell stories that question the world and foster connections. My strengths? A sharp analytical mind, a strong writing style and a keen creative flair to craft brand narratives that resonate with target audiences and address contemporary challenges."}]
    [cv-download]]])


(defn large-letter-2
  [{:keys [x] :as _opts}]
  [:div
   (when-not (= 1 x) [:div {:class "h-[48px] w-full"}])
   [:div.space-y-4
    [hard-skills {:padding        "pt-4"
                  :header-classes "text-white"
                  :rule-classes   "bg-white"}]
    [soft-skills {:padding        "pt-4"
                  :header-classes "text-white"
                  :rule-classes   "bg-white"}]]])


(defn intro-detail
  []
  [:div.space-y-4.text-pink-400.leading-relaxed
   [c/title title]
   [c/body {:fr "Mon histoire est celle d’une culture-lover, formée à la recherche en science humaine,devenue spécialiste en stratégie social media .Passionnée par le spectacle vivant et l’écriture, j’ai d’abord fait mes armes en communication stratégique et évènementielle auprès d’institutions culturelles. Après l’obtention de mon master en communication au CELSA-Sorbonne Université j’ai décidé de réaliser une année post-master pour me spécialiser en stratégie de marque.\n\nJe me suis tournée vers la stratégie de marque car c’est le point de rencontre idéal entre la curiosité que je nourris pour comprendre les gens et ma passion pour les histoires. Créer des récits de marque fait écho à ce pourquoi je fais de la communication : pour raconter des histoires qui bousculent le monde et créent des rencontres. Mes atouts ? Une fine capacité d’analyse, une belle plume et une bonne dose de créativité pour concevoir des récits de marque en phase avec les publics cibles et les problématiques contemporaines."
            :en "My story is one of a culture-aficionado, trained in humanities research who turned communication strategist. Passionate about theater and writing, I honed my skills in strategic communication and event management within cultural institutions. After earning my master's degree in communication from Sorbonne University, I pursued a post-master's year to specialize in brand strategy.\n\nI want to do brand strategy because it’s the perfect intersection between my curiosity to understand people and my passion for storytelling. Creating brand narratives resonates with my reason for starting communication: to tell stories that question the world and foster connections. My strengths? A sharp analytical mind, a strong writing style and a keen creative flair to craft brand narratives that resonate with target audiences and address contemporary challenges."}]
   [hard-skills nil]
   [soft-skills nil]
   [cv-download]
   [c/page-end-buffer]])


(def intro-small
  {:anchor        :intro
   :detail-anchor :intro-detail
   :title         title
   :detail        [intro-detail]
   :view          letter})


(def intro-large-1
  {:view large-letter-1})


(def intro-large-2
  {:view large-letter-2})


(defn intro
  [context]
  (let [[x y] (projects/max-project-fit context)]
    (if (< (* x y) 3)
      [projects/project-page
       [intro-small]
       "translate(0%,100%)"
       "translate(0%,-100%)"
       context]
      [projects/project-page
       [intro-large-1 intro-large-2]
       "translate(0%,100%)"
       "translate(0%,-100%)"
       (assoc context :anchor :intro)])))
