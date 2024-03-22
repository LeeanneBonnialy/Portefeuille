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
           ", une amoureuse des mots et des questions audacieuses."
           [:b "\n\nJe recherche un stage de 6 mois à l’étranger en stratégie de marque au sein d’une agence de communication."]]
      :en [:span "I am "
           [:b "Leeanne Bonnialy"]
           ", a lover of words and question marks."
           [:b "\n\nI am seeking a 6-month internship abroad in brand strategy within a communication agency."]]}]
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
  [{:keys [header-classes]}]
  [:div.space-y-4
   [c/header {:fr "Hard skills"
              :en "Hard skills"} :classes header-classes]
   [c/body {:fr "✍\uD83C\uDFFEPlume incisive et sensible \n\uD83D\uDD78\uFE0FAttrapeuse d’insights\n\uD83D\uDCDACondenser des rapports en une phrase \n\uD83D\uDD0DAnalyser des stratégies de marque \n\uD83D\uDD75\uD83C\uDFFD\u200D♀\uFE0FInvestiguer et définir des territoires de marque \n\uD83D\uDC40Garder l’oeil ouvert sur la concurrence \n\uD83D\uDDD2\uFE0FConduire une étude qualitative et quantitative \n\uD83E\uDD33\uD83C\uDFFEStratégie de contenu social média ( la manière pro de dire que je passe mon temps sur Instagram, Threads, Tiktok…oups !)\n\n\uD83E\uDD1D\uD83C\uDFFECopine avec Powerpoint & Google slide\n\uD83E\uDD47Ceinture noire en Canva \n\uD83E\uDDD1\uD83C\uDFFD\u200D\uD83D\uDCBBJouer les Big Brother avec Visibrain"
            :en "✍\uD83C\uDFFEStrong writing skills \n\uD83D\uDD78\uFE0FInsights catcher\n\uD83D\uDCDACondensing reports into a single sentence\n\uD83D\uDD0DAnalyzing brand strategies\n\uD83D\uDD75\uD83C\uDFFD\u200D♀\uFE0FInvestigating and defining brand territories\n\uD83D\uDC40Keeping an eye on the competition\n\uD83D\uDDD2\uFE0FConducting qualitative and quantitative studies\n\uD83E\uDD33\uD83C\uDFFESocial media content strategy (the professional way of saying I spend my time on Instagram, Threads, TikTok...oops!)\n\n\uD83E\uDD1DBFFs with PowerPoint & Google Slides\n\uD83E\uDD47Black belt in Canva\n\uD83E\uDDD1\uD83C\uDFFD\u200D\uD83D\uDCBBPlaying Big Brother with Visibrain"}]])


(defn soft-skills
  [{:keys [header-classes]}]
  [:div.space-y-4
   [c/header {:fr "Soft skills"
              :en "Soft skills"} :classes header-classes]
   [c/body {:fr "❤\uFE0FCuriosité\n\uD83D\uDCA1Créativité \n✨Travail en équipe \n\uD83D\uDDE3\uFE0FA l’aise à l’oral \n\uD83E\uDD14Esprit critique"
            :en "❤\uFE0FCuriosity \n\uD83D\uDCA1Creative\n✨Teamwork lover \n\uD83D\uDDE3\uFE0FComfortable speaking in public\n\uD83E\uDD14Critical thinking"}]])


(defn large-letter-1
  [ctx proj]
  [:div.space-y-4
   [c/header title :classes ""]
   [c/body {:fr "“I am more than a communication strategist, I am a storyteller and a bold conversation-starter”.\n\nJe suis Leeanne Bonnialy, une amoureuse des mots et des questions audacieuses. Je recherche un stage de 6 mois à l’étranger en stratégie de marque au sein d’une agence de communication. \n\nMon histoire est celle d’une culture-lover formée à la recherche en science humaine devenue planneur stratégique. Passionnée par le spectacle vivant et l’écriture,j’ai d’abord fait mes armes en communication stratégique et évènementielle auprès d’institutions culturelles. \n(Après l’obtention de mon master en communication au CELSA-Sorbonne Université j’ai décidé de réaliser une année post-master pour me spécialiser en stratégie de marque.)"
            :en "“I am more than a communication strategist, I am a storyteller and a bold conversation-starter”.\n\nI am Leeanne Bonnialy, a lover of words and question marks. I am seeking a 6-month internship abroad in brand strategy within a communication agency.\nMy story is dealing with a culture-aficionado, trained in humanities research who turned into a communication strategist. Passionate about theater and writing, I honed my skills in strategic communication and event management within cultural institutions."}]])


(defn cv-download
  []
  [c/download {:en "Download my CV"
               :fr "Téléchargez mon CV"}
   "assets/CV Leeanne Bonnialy.pdf"])


(defn large-letter-2
  [opts]
  [:div
   [:div {:class "h-[106px] w-full"}]
   [:div.space-y-4
    [c/body {:fr "Je me suis tournée vers la stratégie de marque car c’est le point de rencontre idéal entre la curiosité que je nourris pour comprendre les gens et ma passion pour les histoires. Créer des récits de marque fait écho à ce pourquoi je fais de la communication : pour raconter des histoires qui bousculent le monde et créent des rencontres.\n\nMes atouts ? Une capacité à adopter une approche anthropologique de l’insight pour penser “out of the box”. Ajouté à cela, une belle plume et une bonne dose de créativité pour concevoir des récits de marque en phase avec les publics cibles et les problématiques contemporaines."
             :en "I want to do brand strategy because it’s the perfect intersection between my curiosity to understand people and my passion for storytelling. Creating brand narratives resonates with the reason why I’ve started communication : to tell stories that question the world and foster connections. \n\nMy strengths? A capability to embrace an anthropological approach to insight for thinking \"out of the box\". Additionally, I possess strong writing skills and a keen creative flair to craft brand narratives that resonate with target audiences and address contemporary challenges."}]
    [cv-download]]])


(defn large-letter-3
  [opts]
  [:div.space-y-4
   [hard-skills opts]
   [soft-skills opts]])


(defn intro-detail
  []
  [:div.space-y-4.text-pink-400.leading-relaxed
   [c/title title]
   [c/body {:fr "Mon parcours est celui d'une culture-lover issue d'une formation en sciences humaines, aujourd’hui spécialisée en stratégie de communication.\nPassionnée par le spectacle vivant et d'écriture, j’ai d’abord fait mes armes en communication évènementielle et stratégique auprès d’institutions culturelles. \n\nMon métier aujourd’hui est de construire des récits de marque qui font bouger le monde. \n\nJe suis une metteure en scène qui a la volonté de trouver le meilleur arc narratif, le meilleur rôle et la meilleure scène pour faire briller la personnalité de chaque marque auprès de son public.  \n\nMes atouts ? Une belle plume, une créativité débordante et une capacité à penser en dehors des sentiers battus pour concevoir des identités de marque et des messages percutants.\n\nEt si on passait à l’action pour inventer ensemble le récit dont tout le monde se souviendra ?"
            :en "A culture-lover with a background in human sciences, now specialized in communication strategy.\nPassionate about theater and writing, I honed my skills in strategic communication and event management within cultural institutions.\n\nI choose to do brand strategy because it’s the perfect intersection between my curiosity to understand people and my passion for storytelling. \n\nMy mission today is to craft brand narratives that shake up the world.\nI see myself as a stage director, who is determined to find the most compelling storyline, the perfect role, and the ideal stage to showcase each brand's personality to its audience. \n\nWhat sets me apart? A flair for writing, boundless creativity, and the ability to think outside of the box to shape brand identities and send impactful messages.\n\nLet's embark on the journey together to create the narrative that will be etched in everyone's memory."}]
   [hard-skills {:header-classes "text-pink-500"}]
   [soft-skills {:header-classes "text-pink-500"}]
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


(def intro-large-3
  {:view large-letter-3})


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
       [intro-large-1 intro-large-2 intro-large-3]
       "translate(0%,100%)"
       "translate(0%,-100%)"
       (assoc context :anchor :intro)])))
