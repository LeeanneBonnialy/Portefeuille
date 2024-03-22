(ns leebonn.content.arte
  (:require
    [leebonn.content.common :as c]))


(def title
  {:fr "Arte"
   :en "Arte"})


(def long-title
  {:fr "Arte : Génération Ecologie"
   :en "Arte : Génération Ecologie"})


(def comp-1
  {:fr "Création de guide d’entretien"
   :en "Creating interview guidance"})


(def comp-2
  {:fr "Synthèse des résultats"
   :en "Synthesising results"})


(def comp-3
  {:fr "Identification d’insights"
   :en "Identifying insights"})


(def brief-header
  {:fr "Brief"
   :en "Brief"})


(def brief
  {:fr [:b "Réaliser une étude qualitative et quantitative pour comprendre comment les différentes générations entrevoient la notion d’écologie. \n\nRéaliser une recommandation stratégique sur l’évolution des programmes d’Arte traitant de l’écologie."]
   :en [:b "Conduct a qualitative and quantitative study to understand how different generations perceive the notion of ecology.\n\nProvide a strategic recommendation for the evolution of Arte's programs dealing with ecology."]})


(def context-header
  {:fr "Contexte"
   :en "Context"})


(def context
  {:fr [:span
        "Arte est une chaîne publique de télévision franco-allemande qui propose des programmes culturels, artistiques et éducatifs."
        [c/small "\n\nDes baby Boomers à la GenZ, Arte touche un large public grâce à sa présence sur d’autres plateformes dont YouTube et Twitch et en explorant de nouveaux formats narratifs, notamment le podcast. Guidée par son rôle de chaîne du service public, Arte souhaite sensibiliser le public à des thématiques de société, notamment la question écologique."]
        "\n\nL'objectif principal est de faire évoluer les contenus et les récits afin d'accorder une place plus significative à la thématique écologique sous toutes ses formes."]
   :en [:span
        "Arte is a French-German public television channel that offers cultural, artistic, and educational programs."
        [c/small "\n\nFrom Baby Boomers to Gen Z, Arte extends its reach through platforms like YouTube and Twitch, exploring innovative narrative formats, including podcasts. Driven by its commitment to public service, Arte aims to raise awareness about societal issues, with a primary focus on ecological concerns."]
        "\n\nThe main objective is to transform content and storytelling to give a more significant place to the ecological theme in all its forms."]})


(def challenge-header
  {:fr "Challenge"
   :en "Challenge"})


(def challenge
  {:fr [:b "Comment les différentes générations perçoivent-elles la notion d'écologie ?\n\nComment décliner cette notion d’écologie et lui donner de la lisibilité et du sens dans les nombreux programmes sans tomber dans les stéréotypes?"]
   :en [:b "How do diverse generations interpret the concept of ecology?\n\nHow do you address the subject of ecology in diverse content without resorting to stereotypes?"]})


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
  {:fr [:span
        "La réalisation d’une étude qualitative comptant 10 entretiens semi-directifs."
        [c/small "\n\nLes 10 personnes ont été sélectionnées en fonction de leur critère d'âge (appartenance à une génération) et de leur lieu d’habitation (en ville/ en milieu rural). \n5 générations ont été interrogées selon les catégories marketing préexistantes qui qualifient les stéréotypes générationnels : les baby boomers, la GenX, les Millenials, la GenZ et les Alpha."]
        "\n\nL’angle stratégique adopté pour réaliser cette étude qualitative ambitieuse : Interroger les sociotype générationnels par l’angle des moments de vie qui ont eu un impact sur leur imaginaire, leur engagement à propos de l’écologie. \n\nCette étude qualitative a été complétée par une étude quantitative via un formulaire Google doc. L'étude quantitative comptabilise 60 répondants."]
   :en [:span
        "The realization of a qualitative study involving 10 guided interviews."
        [c/small "\n\nThe individuals were selected on their age criteria (belonging to a specific generation) and their living environment (urban/rural).\nFive generations were interviewed according to pre-existing marketing categories that characterize generational stereotypes: Baby Boomers, GenX, Millennials, GenZ, and Alpha."]
        "\n\nThe strategic focus of this qualitative study is to question the generational sociotype on their life moments that have shaped their relationship with ecology. \n\nThis qualitative study has been completed by a quantitative study on Google form. The quantitative study counts 60 participants."]})


(def download-questionnaire
  {:en "Quality and quantity maintenance guide"
   :fr "Guide entretien quali et quanti téléchargeable"})


(def recommendation-header
  {:fr "Résultats et recommandations stratégiques"
   :en "Results and strategic recommendations"})


(def recommendation
  {:fr "Les résultats de l’analyse permettent d’établir un mapping des sujets qui intéressent le plus en fonction des générations. La cause animale arrive en tête.\n\nLa série est le format préféré pour toucher plusieurs générations. Les enjeux écologiques sont appréciés comme toile de fond mais la créativité de l'intrigue reste essentielle pour attirer l’audience."
   :en "The results of the analysis make it possible to establish a mapping of the subjects which interest each generation the most, with animal welfare ranking first.\n\nTV series are the preferred format to reach multiple generations. While environmental issues are appreciated as a backdrop, the creativity of the plot remains essential to attract an audience."})


(defn project-detail
  []
  [:div.space-y-4.text-pink-400.leading-relaxed
   [c/title long-title]
   [c/competencies
    [comp-1
     comp-2
     comp-3]]

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
   [c/body recommendation]
   [c/carousel-dir "arte/cara/ARTE_page-%s.jpg" 32]
   [c/download {:en "Download the presentation"
                :fr "Téléchargez la présentation"}
    "assets/arte/ARTE.pdf"]
   [c/page-end-buffer]])
