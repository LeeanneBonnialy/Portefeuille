(ns leebonn.content.sncf
  (:require
    [leebonn.content.common :as c]))


(def title
  {:fr "SNCF"
   :en "SNCF"})


(def comp-1
  {:fr "Gestion de projet"
   :en "Project management"})


(def comp-2
  {:fr "Pilotage des prestataires"
   :en "Negotiation with service providers"})


(def comp-3
  {:fr "Sens de l’organisation"
   :en "Organizational skills"})


(def comp-4
  {:fr "Etude de faisabilité"
   :en "Feasibility study"})


(def comp-5
  {:fr "Gestion du stress"
   :en "Stress management"})


(def comp-6
  {:fr "Coordination d’équipe"
   :en "Team coordination"})


(def context-header
  {:fr "Contexte"
   :en "Context"})


(def context
  {:fr [:span
        "Communicante mais aussi experte en ingénierie culturelle !\nAfin d’affiner ma stratégie de communication événementielle, il m'a semblé important de comprendre le processus de fabrication d’un événement (de l’idée à sa réalisation finale)."
        "\n\nPour ce faire, j’ai réalisé une alternance d’un an au sein du pôle production de la Direction des Affaires Culturelles de SNCF Retail & Connexions."
        [c/small "\nEn collaboration avec des musées, des festivals ou des associations, la Direction a pour mission d’organiser des expositions en gare. Le but ? Faire de la gare un lieu de vie et de découverte."]
        "\n\nLa Direction des Affaires Culturelles n’est rien de moins que la plus grande institution culturelle de France, en transformant 50 gares du territoire français en un espace d’exposition ouvert à tous."]
   :en [:span
        "Communication strategist but also an expert in event organization!"
        "\n\nIn order to reinforce my skills in event communication strategy, it seemed important to me to understand the event production process, from conception to final execution."
        "\n\nTo achieve this goal, I completed a one-year apprenticeship within the cultural affairs department of SNCF Retail & Connexions."
        [c/small "\n\nSNCF is a transport company responsible for train logistics and station management in France. Retail & Connexions is the subsidiary of SNCF that manages retail outlets and events in train stations."]
        [c/small "\n\nThe cultural affairs department is in charge of organizing exhibitions in gare stations in collaboration with French museums, festivals or associations. The mission? To make the train station a vibrant hub of life and discovery."]
        "\n\nThe cultural affairs department is nothing short of the largest cultural institution in France, transforming 50 train stations across the French territory into exhibition spaces open to all."]})


(def project-header
  {:fr "Focus projet"
   :en "Project focus"})


(def project
  {:fr "À son actif, la Direction des Affaires Culturelles compte 250 expositions par an pour 10 millions de visiteurs par jour.\nJ’ai plus particulièrement coordonné le projet 50 ans-50 gares.\n\nPour sa 50e édition, le Festival international de la bande dessinée s’est associé à SNCF Retail & Connexions afin d’installer des parcours d’expositions BD dans 50 gares sur l'ensemble du territoire métropolitain.\n\nLe projet a eu fort écho auprès de la presse locale comme nationale.\n"
   :en "With 250 exhibitions per year and 10 million visitors daily, the cultural affairs department is quite dynamic.\n\nMy main responsibility has been coordinating the \"50 years - 50 stations\" project. \n\nIn its 50th edition, the International Comics Festival partnered with SNCF Retail & Connexions to showcase comic book exhibitions in 50 stations across France.\n\nThe project received significant attention from both local and national press."})


(defn header
  [text]
  [c/header text
   :classes "text-blue-500"
   :rule-classes "bg-blue-500"])


(defn project-detail
  []
  [:div.space-y-4.text-blue-400.leading-relaxed
   [c/title title :classes "text-blue-500"]
   [c/competencies
    [comp-1
     comp-2
     comp-3
     comp-4
     comp-5
     comp-6]
    {:classes "text-blue-500"
     :pill-classes "bg-blue-500 text-white"}]

   [header context-header]
   [c/body context]

   [header project-header]
   [c/body project]

   [:div.h-50
    [c/carousel ["sncf/cara/Communiqué de presse 50 ans - 50 gares page 1 .jpg"
                 "sncf/cara/Communiqué de presse 50 ans - 50 gares page 2 .jpg"
                 "sncf/cara/Communiqué de presse 50 ans - 50 gares page 3.jpg"]
     :classes "text-blue-300"]]

   ;; [c/inline-img "roger/body_1.png" :href "https://www.instagram.com/p/C4I-LBRstjB/?utm_source=ig_web_copy_link"]
   ;; [c/inline-img "roger/body_2.png" :href "https://www.instagram.com/p/C4YeOwDMx0E/?utm_source=ig_web_copy_link"]
   [c/page-end-buffer]])
