## Scenario Principal

**Acteur 1er** : Producteur
**Acteur 2nd** : Administrateur
**Contexte** : Logiciel
**Niveau** : Système 
**Stakeholders & Interests** :
•    Administrateur : assurer le bon fonctionnement du système. 
•    Producteur : pouvoir utiliser le logiciel de gestion de tournée
**Préconditions** : Un admin doit avoir un compte et être connecté
**Résultat minimum** : l’administrateur enregistre un utilisateur
**Résultat maximum** : le producteur planifie une tournée 
**Déclencheur** : demande informelle de l’ajout d’un producteur

---

Scénario nominal :
1. Administrateur rentre les informations du producteur
2. Création nouveau producteur dans la BDD
3. Producteur entre ses informations de connexion
4. Connexion producteur
5. Producteur entre les informations des commandes
6. Enregistrement des commandes
7. Producteur prépare une tournée
8. enregistrement de la tournée 

---

Scénario alternatif :
1a. Des informations sont manquantes ou n’ont pas le bon format
---- 1a1. Logiciel signale l’erreur
---- 1a2. L’administrateur rentre les bonnes informations
2a. L’utilisateur existe déjà (SIRET ou pseudo administrateur)
---- 2a1. Le logiciel signale l’erreur
4a. Les informations sont erronées
---- 4a1. Le logiciel signale l’erreur
---- 4a2. Le user rentre les bonnes données
6a Mauvais format / informations manquantes
---- 6a1. Le logiciel signale l’erreur
---- 6a2. Le producteur rentre les bonnes dates
7a. Le créneau n’est pas bon
---- 7a1. Erreur
---- 7a2. Change l’horaire de la tournées
7b. Le véhicule n’est pas disponible
---- 7b1. Erreur
---- 7b2. Le producteur sélectionne un autre véhicule
-------- 7b2a. Aucun véhicule libre
-------- 7b2b. Erreur