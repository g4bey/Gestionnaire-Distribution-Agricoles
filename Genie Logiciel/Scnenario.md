## Scenario Principal

**Acteur 1er** : Producteur<br>
**Acteur 2nd** : Administrateur<br>
**Contexte** : Logiciel<br>
**Niveau** : Système <br>
**Stakeholders & Interests** :<br>
•    Administrateur : assurer le bon fonctionnement du système.<br>
•    Producteur : pouvoir utiliser le logiciel de gestion de tournée<br>
**Préconditions** : Un admin doit avoir un compte et être connecté<br>
**Résultat minimum** : l’administrateur enregistre un utilisateur<br>
**Résultat maximum** : le producteur planifie une tournée<br>
**Déclencheur** : demande informelle de l’ajout d’un producteur<br>

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

Scénario alternatif :<br>
1a. Des informations sont manquantes ou n’ont pas le bon format<br>
---- 1a1. Logiciel signale l’erreur<br>
---- 1a2. L’administrateur rentre les bonnes informations<br>
2a. L’utilisateur existe déjà (SIRET ou pseudo administrateur)<br>
---- 2a1. Le logiciel signale l’erreur<br>
4a. Les informations sont erronées<br>
---- 4a1. Le logiciel signale l’erreur<br>
---- 4a2. Le user rentre les bonnes données<br>
6a Mauvais format / informations manquantes<br>
---- 6a1. Le logiciel signale l’erreur<br>
---- 6a2. Le producteur rentre les bonnes dates<br>
7a. Le créneau n’est pas bon<br>
---- 7a1. Erreur<br>
---- 7a2. Change l’horaire de la tournées<br>
7b. Le véhicule n’est pas disponible<br>
---- 7b1. Erreur<br>
---- 7b2. Le producteur sélectionne un autre véhicule<br>
-------- 7b2a. Aucun véhicule libre<br>
-------- 7b2b. Erreur<br>
