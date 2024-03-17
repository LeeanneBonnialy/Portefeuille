(ns leebonn.content.seazon
  (:require
    [leebonn.content.common :as c]))


(def seazon-title
  {:en "Seazon"
   :fr "Seazon"})


(def seazon-comp-1
  {:fr "Analyse concurrentielle"
   :en "Benchmarking"})


(def seazon-comp-2
  {:fr "Identification d’insights"
   :en "Identification of Insights"})


(def seazon-comp-3
  {:fr "Création graphique"
   :en "Graphic Design"})


(def seazon-comp-4
  {:fr "Stratégie d’influence"
   :en "Influencer communication strategy"})


(def seazon-brief-header
  {:fr "Brief"
   :en "Brief"})


(def seazon-brief
  {:fr [:b "Réaliser une campagne publicitaire pour le lancement de la nouvelle gamme proposée par Seazon, Seazon Équilibre."]
   :en [:b "Conduct an advertising campaign for the launch of the new offer created by Seazon, Seazon Equilibre."]})


(def seazon-context-header
  {:fr "Contexte"
   :en "Context"})


(def seazon-context
  {:fr "Créé en 2018, Seazon est un abonnement de plats\nfraîchement cuisinés par des chefs livrés dans toute la France. Cette formule sans engagement propose des plats équilibrés concoctés avec des produits locaux. \n\nLa marque lance en 2021 la gamme Equilibre destiné au rééquilibrage alimentaire. La philosophie de la gamme est de faire du rééquilibrage alimentaire une habitude à adopter pour se sentir mieux dans sa peau."
   :en "Created in 2018, Seazon is a subscription service delivering freshly prepared meals by chefs across France. This commitment-free option offers balanced dishes made with local ingredients. \n\nIn 2021, the brand introduced the offer Seazon Equilibre, designed for nutritional rebalancing. The range's philosophy aims to make nutritional rebalancing a daily habit for a better sense of well-being."})


(def seazon-challenge-header
  {:fr "Challenge"
   :en "Challenge"})


(def seazon-challenge
  {:fr [:b "Comment toucher une cible déçue des régimes afin de les convertir au rééquilibrage alimentaire ?"]
   :en [:b "How to engage a target audience disillusioned with diets and convert them to nutritional rebalancing?"]})


(def seazon-objective-header
  {:fr "Objectifs"
   :en "Goals"})


(def seazon-objective
  {:fr "• La notoriété de l’offre Seazon Équilibre\n• Susciter l’intérêt pour la démarche de rééquilibrage alimentaire\n• Déclencher un test du produit pour convertir à l’abonnement"
   :en "• Raise awareness of the Seazon Equilibre offering.\n• Generate interest in the nutritional rebalancing approach.\n• Prompt product trials to convert to a subscription."})


(def seazon-steps-header
  {:fr "Démarche"
   :en "Analysis"})


(def seazon-steps-1
  {:fr "Afin de promouvoir la gamme Seazon Équilibre il est essentiel de tenir un discours non culpabilisant auprès d’une audience cible majoritairement féminine qui cherche à concilier pragmatisme et envie de manger sainement. \n\nL’objectif est de faire la distinction entre le rééquilibrage alimentaire synonyme de plaisir et de bien-être et le régime plutôt associé à l’échec et à la privation."
   :en "To effectively promote the Seazon Equilibre, it is essential to convey a message free of guilt to a predominantly  female audience looking to balance practicality seeking to reconcile pragmatism with a desire for healthy eating. \n\nThe objective is to emphasize the distinction between nutritional rebalancing, associated with enjoyment and well-being, and diets, which are often linked to failure and deprivation."})


(def seazon-steps-2
  {:fr "Outre ce changement de valeur symbolique, il s’agit de se démarquer des offres multiples du secteur et détourner les adaptes des marques les plus installées. \n\nD’après le benchmark concurrentiel , Seazon Équilibre se démarque grâce à trois caractéristiques :  la facilité de préparation, l’accent mis sur les recettes préparés avec des produits locaux de saison et l’objectif de rééquilibrage alimentaire. Ces trois éléments différenciants doivent figurer dans le message de communication finale."
   :en "In addition to this symbolic value shift, the aim is to stand out from the numerous offerings in the sector and divert followers from more established brands. \n\nAccording to competitive benchmarking, Seazon Équilibre distinguishes itself through three key features: ease of preparation, a focus on recipes made with local seasonal ingredients, and the goal of nutritional rebalancing. These three distinctive elements should be highlighted in the final communication message."})


(def seazon-quote
  {:fr "Avec Seazon Equilibre,\nc'est si facile !\nDe manger sain\nDe manger gourmand\nDe manger équilibré"
   :en "With Seazon Equilibre,\nit’s so easy!\nEasy to eat healthy\nEasy to enjoy gourmet cuisine\nEasy to maintain a balanced diet"})


(def seazon-recommendation-header
  {:fr "Recommandation stratégique : une campagne d’influence sur Instagram avec la créatrice de contenu Marine Lorphelin"
   :en "Strategic recommendation : An influencer campaign on Instagram with the content creator Marine Lorphelin"})


(def seazon-recommendation
  {:fr [:span "Ex-miss France, désormais interne de médecine générale,  Marine Lorphelin est créatrice de contenus et chroniqueuse bien-être. Elle a 1M de followers sur Instagram. Son contenu est axé sur la nutrition et le sport.\n\n" [:b "Pourquoi Marine Lorphelin ?"] "\n\nEn plus d’avoir une communauté en phase avec la cible de la gamme Équilibre, la profession de médecin de Marine Lorphelin renforce son rôle prescripteur auprès de sa communauté. En tant qu’ancienne Miss France, elle jouit aussi d’une notoriété auprès du grand public qui pourrait contribuer à accroître la visibilité de la gamme Seazon.\n\nLa campagne serait lancée après les fêtes de fin d’année, afin de capitaliser sur la période des bonnes résolutions qui s’ensuit, souvent synonyme de changement de régime alimentaire."]
   :en [:span "Marine Lorphelin participated in the French national beauty competition “Miss France”. Now she’s a doctor, an author and a content creator around well-being, nutrition and fitness. She has 1 million followers on Instagram \n\n" [:b "Why choose Marine Lorphelin?"] "\n\nIn addition to having a community aligned with the Seazon Equilibre’s target audience, Marine Lorphelin's medical background enhances her influence within her community. As a former Miss France, she also enjoys public recognition, which could significantly boost the visibility of Seazon Equilibre.\n\nThe campaign is planned to kick off after the year-end holidays, taking advantage of the New Year's resolutions period, often associated with dietary changes."]})


(def tab-context
  [{:fr "Cultural"
    :en "Cultural"}
   nil
   {:fr "2021 TODO"
    :en "2021 TODO"}])


(defn project-detail
  []
  [:div.space-y-4.text-pink-400
   [c/title seazon-title]
   [c/competencies
    seazon-comp-1
    seazon-comp-2
    seazon-comp-3
    seazon-comp-4]

   [c/header seazon-brief-header]
   [c/body seazon-brief]

   [c/header seazon-context-header]
   [c/body seazon-context]

   [c/header seazon-challenge-header]
   [c/body seazon-challenge]

   [c/header seazon-objective-header]
   [c/body seazon-objective]

   [c/header seazon-steps-header]
   [c/body seazon-steps-1]
   [c/inline-img "seazon/body_1.jpg"]
   [c/body seazon-steps-2]

   [c/inline-img "seazon/body_2.jpg"]
   [c/quote seazon-quote]

   [c/header seazon-recommendation-header]
   [c/body seazon-recommendation]

   [c/carousel
    ["seazon/cara_1.jpg"
     "seazon/cara_2.jpg"
     "seazon/cara_3.jpg"
     "seazon/cara_4.jpg"]]
   [c/download {:en "Download the presentation"
                :fr "Téléchargez la présentation"} "assets/seazon/SEAZON.pdf"]
   [c/page-end-buffer]])
