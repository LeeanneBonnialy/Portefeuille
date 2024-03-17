(ns leebonn.sections.intro
  (:require
    ["react" :as r]
    [leebonn.content.common :as c]
    [leebonn.i18n :as i18n]
    [leebonn.navigation :as nav]
    [leebonn.sections.projects :as projects]
    [leebonn.util :as util]))


(def title {:fr "Faisons connaissance !" :en "Let’s meet !"})


(defn letter
  [ctx proj]
  [:div {:class "w-96 m-auto text-center text-white pointer-events-auto"}
   [i18n/text {:class "text-2xl italic"}
    {:fr "“I am more than a communication strategist, I am a storyteller and a bold conversation-starter”.\n\n"
     :en "“I am more than a communication strategist, I am a storyteller and a bold conversation-starter”.\n\n"}]
   [i18n/text {:class "text-2xl"}
    {:fr "Je suis Leeanne Bonnialy, une amoureuse des mots et des questions audacieuses. Je recherche un stage de 6 mois à l’étranger en stratégie de marque au sein d’une agence de communication..."
     :en "I am Leeanne Bonnialy, a lover of words and question marks. I am seeking a 6-month internship abroad in brand strategy within a communication agency..."}]
   [:svg.w-16.h-16.mx-auto.mt-8 {:class       "transition-all hover:scale-90 cursor-pointer"
                                 :on-click    (partial projects/open-project proj)
                                 :aria-hidden "true" :xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :fill "currentColor" :viewBox "0 0 24 24"}
    [:path {:fill-rule "evenodd" :d "M11 4.717c-2.286-.58-4.16-.756-7.045-.71A1.99 1.99 0 0 0 2 6v11c0 1.133.934 2.022 2.044 2.007 2.759-.038 4.5.16 6.956.791V4.717Zm2 15.081c2.456-.631 4.198-.829 6.956-.791A2.013 2.013 0 0 0 22 16.999V6a1.99 1.99 0 0 0-1.955-1.993c-2.885-.046-4.76.13-7.045.71v15.081Z" :clip-rule "evenodd"}]]
   [i18n/text {:en "discover more about me\n\nor\n\ncontinue exploring my projects."}]
   [:svg.w-16.h-16.mx-auto {:class "transition-all hover:scale-90 cursor-pointer"
                            :on-click    (partial nav/go-to-next)
                            :aria-hidden "true" :xmlns "http://www.w3.org/2000/svg" :width "24" :height "24" :fill "none" :viewBox "0 0 24 24"}
    [:path {:stroke "currentColor" :stroke-linecap "round" :stroke-linejoin "round" :stroke-width "2" :d "M12 19V5m0 14-4-4m4 4 4-4"}]]])


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
  [:div.space-y-4.text-pink-400
   [c/title title]
   [c/body {:fr "... TODO"
            :en "... TODO"}]
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
