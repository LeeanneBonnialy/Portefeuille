(ns leebonn.content.seazon
  (:require
    [leebonn.content.common :as c]))


(def seazon-title
  {:en  "Seazon"
   :fr "Seazon"})


(def seazon-type-header {:fr "Type"})
(def seazon-type {:fr "Création publicitaire"})
(def seazon-comp-1 {:fr "Analyse concurrentielle"})
(def seazon-comp-2 {:fr "Identification d’insights"})
(def seazon-comp-3 {:fr "Création graphique"})
(def seazon-comp-4 {:fr "Stratégie de communication d’influence"})
(def seazon-comp-5 {:fr "Esprit d’équipe"})
(def seazon-comp-6 {:fr "Rhétorique et force de conviction en présentation client"})

(def seazon-brief-header {:fr "Brief"})
(def seazon-brief {:fr "Réaliser une campagne publicitaire pour le lancement de la nouvelle gamme proposée par Seazon,  Seazon Équilibre."})

(def seazon-context-header {:fr "Contexte"})
(def seazon-context {:fr "Créé en 2018, Seazon est un abonnement de plats\nfraîchement cuisinés par des chefs livrés dans toute la France. Cette formule sans engagement propose des plats équilibrés concoctés avec des produits locaux. La marque lance en 2021 la gamme Equilibre destiné au rééquilibrage alimentaire. La philosophie de la gamme est de faire du rééquilibrage alimentaire une habitude à adopter pour se sentir mieux dans sa peau."})

(def seazon-challenge-header {:fr "Challenge"})
(def seazon-challenge {:fr "Comment toucher une cible déçue des régimes afin de les convertir au rééquilibrage alimentaire ?"})

(def seazon-objective-header {:fr "Objectifs"})
(def seazon-objective-1 {:fr "La notoriété de l’offre Seazon Équilibre"})
(def seazon-objective-2 {:fr "Susciter l’intérêt pour la démarche de rééquilibrage alimentaire"})
(def seazon-objective-3 {:fr "Déclencher un test du produit pour convertir à l’abonnement"})

(def seazon-steps-header {:fr "Démarche"})
(def seazon-steps-1 {:fr "Afin de promouvoir la gamme Seazon Équilibre il est essentiel de tenir un discours non culpabilisant auprès d’une audience cible majoritairement féminine qui cherche à concilier pragmatisme et envie de manger sainement. L’objectif est de faire la distinction entre le rééquilibrage alimentaire synonyme de plaisir et de bien-être et le régime plutôt associé à l’échec et à la privation."})
(def seazon-steps-2 {:fr "Outre ce changement de valeur symbolique, il s’agit de se démarquer des offres multiples du secteur et détourner les adaptes des marques les plus installées. D’après le benchmark concurrentiel , Seazon Équilibre se démarque grâce à trois caractéristiques :  la facilité de préparation, l’accent mis sur les recettes préparés avec des produits locaux de saison et l’objectif de rééquilibrage alimentaire. Ces trois éléments différenciants doivent figurer dans le message de communication finale."})


(defn project-detail
  []
  [:div.space-y-4.text-pink-400
   [c/title seazon-title]
   [c/competencies
    seazon-comp-1
    seazon-comp-2
    seazon-comp-3
    seazon-comp-4
    seazon-comp-5
    seazon-comp-6]

   [c/header seazon-brief-header]
   [c/body seazon-brief]

   [c/header seazon-context-header]
   [c/body seazon-context]

   [c/header seazon-challenge-header]
   [c/body seazon-challenge]

   [c/carousel "seazon/seazon.jpg" "seazon/seazon.jpg"]])
