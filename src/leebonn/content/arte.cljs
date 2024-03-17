(ns leebonn.content.arte
  (:require
    [leebonn.content.common :as c]))


(def title
  {:fr "Arte : Génération Ecologie"})


(def comp-1
  {:fr "Création de guide d’entretien"
   :en "Created interview guidance"})


(def comp-2
  {:fr "Synthèse des résultats"
   :en "Synthesis of results"})


(def comp-3
  {:fr "Identification d’insights"
   :en "Identification of insights"})


(def brief-header
  {:fr "Brief"
   :en "Brief"})


(def brief
  {:fr "Réaliser une étude qualitative et quantitative pour comprendre comment les différentes générations entrevoient la notion d’écologie.\nRéaliser une recommandation stratégique sur l’évolution des programmes d’Arte traitant de l’écologie."
   :en "Conduct a qualitative and quantitative study to understand how different generations perceive the notion of ecology.\nProvide a strategic recommendation for the evolution of Arte's programs dealing with ecology."})


(def context-header
  {:fr "Contexte"
   :en "Context"})


(def context
  {:fr "Arte est une chaîne publique de télévision franco-allemande qui propose des programmes culturels, artistiques et éducatifs. Des baby Boomers à la GenZ, Arte touche un large public grâce à sa présence sur d’autres plateformes dont YouTube et Twitch et en explorant de nouveaux formats narratifs, notamment le podcast. \n\nGuidée par son rôle de chaîne du service public, Arte souhaite sensibiliser le public à des thématiques de société, notamment la question écologique. L'objectif principal est de faire évoluer les contenus et les récits afin d'accorder une place plus significative à la thématique écologique sous toutes ses formes."
   :en "Arte is a French-German public television channel that offers cultural, artistic, and educational programs. From Baby Boomers to Gen Z, Arte extends its reach through platforms like YouTube and Twitch, exploring innovative narrative formats, including podcasts.\n\nDriven by its commitment to public service, Arte aims to raise awareness about societal issues, with a primary focus on ecological concerns. The main objective is to transform content and storytelling to give a more significant place to the ecological theme in all its forms."})


(def challenge-header
  {:fr "Challenge"
   :en "Challenge"})


(def challenge
  {:fr "Comment les différentes générations perçoivent-elles la notion d'écologie ?\nComment décliner cette notion d’écologie et lui donner de la lisibilité et du sens dans les nombreux programmes sans tomber dans les stéréotypes ?"
   :en "How do diverse generations interpret the concept of ecology?\nHow do you address the subject of ecology in diverse content without resorting to stereotypes?"})


(def objective-header
  {:fr "Objectifs"
   :en "Goals"})


(def objective
  {:fr "Faire évoluer les programmes de fiction d’Arte afin de donner une place plus grande à la thématique écologique, tout en collant aux attentes des téléspectateurs selon leur génération."
   :en "Adjust Arte's fiction programs to focus more on ecological themes, aligning with viewer expectations according to their generational preferences."})


(def steps-header
  {:fr "Démarche"
   :en "Analysis"})


(def steps-1
  {:fr "La réalisation d’une étude qualitative comptant 10 entretiens semi-directifs menés auprès de 10 personnes sélectionnées en fonction de leur critère d'âge (appartenance à une génération) et leur lieu d’habitation (en ville/ en milieu rural). \n5 générations ont été interrogées selon les catégories marketing préexistantes qui qualifient les stéréotypes générationnels : les baby boomers, la GenX, les Millenials, la GenZ et les Alpha. \n\nCette étude qualitative a été complétée par une étude quantitative via un formulaire Google doc. L'étude quantitative comptabilise 60 répondants. \n\nL’angle stratégique adopté pour réaliser cette étude qualitative ambitieuse : Interroger les sociotype générationnels par l’angle des moments de vie qui ont eu un impact sur leur imaginaire, leur engagement à propos de l’écologie. Les dix moments de vie identifiés sont les suivants :\n• Entrée dans la vie active\n• Entrée dans les études supérieures\n• Parentalité, naissance d’un enfant\n• Vote aux élections présidentielles\n• Les confinements ou un autre « moment » de la période Covid\n• Les étés très chauds\n• Le départ du foyer familial\n• L’installation/colocation/concubinage avec quelqu’un de sensible à l'écologie/engagé\n• La retraite\n• Le déménagement"
   :en "The realization  of a qualitative study involving 10 guided interviews. The individuals were selected on their age criteria (belonging to a specific generation) and their living environment (urban/rural). Five generations were interviewed according to pre-existing marketing categories that characterize generational stereotypes: Baby Boomers, GenX, Millennials, GenZ, and Alpha.\n\nThe strategic focus of this qualitative study is to question the generational sociotype on their life moments  that have shaped their \nThe ten identified life moments are as follows:\n• Entry into the workforce\n• Enrollment in higher education\n• Parenthood, birth of a child\n• Voting in presidential elections\n• Periods of lockdown or another notable event during the acute phase of the Covid pandemic\n• Very hot summers\n• Leaving the parental home\n• Moving in/cohabiting with someone environmentally conscious or engaged\n• Retirement\n• Relocation"})


(def download-questionnaire
  {:en "Quality and quantity maintenance guide"
   :fr "Guide entretien quali et quanti téléchargeable"})


(def recommendation-header
  {:fr "Résultats et recommandations stratégiques"
   :en "Results and strategic recommendations"})


(defn project-detail
  []
  [:div.space-y-4.text-pink-400
   [c/title title]
   [c/competencies
    comp-1
    comp-2
    comp-3]

   [c/header brief-header]
   [c/body brief]

   [c/header context-header]
   [c/body context]

   [c/header challenge-header]
   [c/body challenge]

   [c/header objective-header]
   [c/body objective]

   [c/header steps-header]
   [c/body steps-1]
   [c/download download-questionnaire "assets/arte/QUESTIONNAIRES QUALI ET QUANTI ETUDE ARTE.pdf"]

   [c/header recommendation-header]
   [c/inline-img "arte/body_1.jpg"]
   [c/inline-img "arte/body_2.jpg"]
   [c/inline-img "arte/body_3.jpg"]
   [c/inline-img "arte/body_4.jpg"]
   #_[c/carousel
    "seazon/cara_1.jpg"
    "seazon/cara_2.jpg"
    "seazon/cara_3.jpg"
    "seazon/cara_4.jpg"]])
