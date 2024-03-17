(ns leebonn.content.ptit-bleds
  (:require
    [leebonn.content.common :as c]))


(def title
  {:fr "Ptit’s Bleds"
   :en "Ptit’s Bleds"})


(def comp-1
  {:fr "Création de logo"
   :en "Logo creation"})


(def comp-2
  {:fr "Création de supports de communication"
   :en "Creation of communication materials"})


(def comp-3
  {:fr "Stratégie de marketing direct"
   :en "Marketing strategy"})


(def comp-4
  {:fr "Communication évènementielle"
   :en "Event communication"})


(def brief-header
  {:fr "Brief"
   :en "Brief"})


(def brief
  {:fr "Créer une association qui défend une cause, en réponse à une ou plusieurs problématiques contemporaines.\nMettre en place une campagne de communication et de marketing directe."
   :en "Establishing an association that advocates for a cause in response to one or several contemporary issues.\nImplementing a communication and direct marketing campaign."})


(def challenge-header
  {:fr "Challenge"
   :en "Challenge"})


(def challenge
  {:fr "En période de crise où différentes causes rivalisent, comment accroître la visibilité et encourager les dons en faveur d'une association ?"
   :en "In times of crisis, where various causes compete, how do you increase visibility and encourage donations to support a specific association ?"})


(def objective-header
  {:fr "Objectifs"
   :en "Goals"})


(def objective
  {:fr "Créer une campagne de communication cohérente et impactante  à l’aide de différents leviers (réseaux sociaux, événementielle, presse...)."
   :en "Creating a coherent and impactful communication campaign using various channels (social media, events, press, etc.)."})


(def steps-header
  {:fr "Démarche"
   :en "Analysis"})


(def steps-1
  {:fr "En Ile de France, le tourisme est trop souvent un tourisme de masse : L’attention est rivée sur la capitale et les lieux de culture prisés (Louvre, Château de Versailles, Disneyland). En 2022, le nombre de touristes français accueillis à en Île-de-France est de 15,9 millions. Les promenades en ville (65%), les visites de musées et monuments (55%) sont les 2 principales activités pratiquées par les touristes français pendant leur séjours.\n\nAutre problème identifié : pour une grande partie des Français, l’écologie est une notion de plus en plus importante à prendre en compte lorsqu’il s’agit de planifier ses voyages. Cependant dans les faits cette prise en compte est minime dans les choix faits par les touristes (mode de transport, mode d'hébergement, activité pratiquées…)."
   :en "In the parisian region of  Île-de-France, tourism often takes the form of mass tourism, with a focus on the capital and popular cultural sites such as the Louvre, Palace of Versailles, and Disneyland. In 2022, the number of French tourists welcomed in Île-de-France was 15.9 million. City strolls (65%) and visits to museums and monuments (55%) are the two main activities practiced by French tourists during their stays.\nAnother identified issue: for a lot of French people , ecology is an increasingly important consideration when they plan trips. However, in practice, this consideration has less impact on the choices made by tourists (mode of transportation, accommodation, activities, etc.)."})


(def steps-quote
  {:fr [:span "La réponse au tourisme de masse = le " [:b "tourisme de proximité. " [:s "Voyager loin,"] " Voyager mieux"]]
   :en [:span "The solution to mass tourism = " [:b "local tourism. " [:s "Travel far,"] " Travel Better."]]})


(def steps-2
  {:fr "Afin de détourner l’attention de ces grands sites touristiques, nous voulons attirer les voyageurs français vers des territoires hors des sentiers battus, à portée de train, en leur donnant l’opportunité de contribuer à la revalorisation de ces P’tits Bleds à fort intérêt culturel.\nDans un second temps, l’enjeu est aussi de rendre la dimension écologique dans le tourisme plus concrète. En effet, 64% des Français ont entendu parler de tourisme durable mais pour 42% d’entre eux cette notion reste floue."
   :en "To divert attention from these major tourist sites, we aim to attract French travelers to off-the-beaten-path areas, within reach by train, and provide them with the opportunity to contribute to the revitalization of these culturally significant hidden gems.\nAdditionally, the challenge is to make the ecological dimension in tourism more concrete. Indeed, 64% of French people have heard of sustainable tourism, but for 42% of them, this concept remains unclear."})


(def recommendation-header
  {:fr "Réponse stratégique : une campagne de communication multicanale & une stratégie de marketing directe"
   :en "Strategic answer : the communication campaign and the marketing strategy of P’tits bleds"})


(def recommendation-1
  {:fr "Cette ambition stratégique s’incarne à travers la création de l’association P’tits Bleds qui agit pour la promotion des villes & villages franciliens isolés, mal connus. L’association finance  des projets de valorisation touristique, culturelle et écologique pour donner une meilleure visibilité à ces lieux méconnus à grand potentiel touristique."
   :en "This strategic ambition is embodied by the creation of the P’tits Bleds association which promotes isolated and lesser-known towns and villages in the Île-de-France region. \nThe association supports projects that improve tourism, culture and ecology to make these unnoticed places with significant tourist potential more visible."})


(def recommendation-2
  {:fr "Deux objectifs :\n• Accroître la notoriété de l’association (+500 donateurs annuels) et mettre en avant un mode de voyage alternatif.\n• Atteindre un plafond de collecte de 5000 euros afin de financer des projets de réhabilitation du patrimoine local.\n\nPour atteindre le plafond de collecte nous visons les donateurs particuliers, et les philanthropes que sont les entreprises privées. Pour ce qui est de la notoriété, les cibles sont les médias et les influenceurs."
   :en "Two objectives:\n• Increase the association's visibility (with over 500 annual donors) and highlight an alternative mode of travel.\n• Achieve a fundraising target of 5000 euros to finance local heritage rehabilitation projects.\nTo reach the fundraising level,, we are targeting individual donors and private businesses as philanthropists. Regarding visibility, the targets include media and influencers."})


(defn project-detail
  []
  [:div.space-y-4.text-pink-400
   [c/title title]
   [c/competencies
    comp-1
    comp-2
    comp-3
    comp-4]

   [c/header brief-header]
   [c/body brief]

   [c/header challenge-header]
   [c/body challenge]

   [c/header objective-header]
   [c/body objective]

   [c/header steps-header]
   [c/body steps-1]
   [c/quote steps-quote]
   [c/body steps-2]

   [c/header recommendation-header]
   [c/body recommendation-1]
   [c/inline-img "ptit-bleds/body_1.jpg"]
   [c/body recommendation-2]
   [c/page-end-buffer]])
