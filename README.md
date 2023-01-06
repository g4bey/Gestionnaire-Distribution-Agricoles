<div align='center'>
  <h1>Patate & Salade</h1>
  <p>Planification, vérification et visualisation de tournées agricoles.</p>
</div>
<br>

<div align='center'>
  <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/Genie%20Logiciel/Scnenario.md">Scénario Principal</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/Genie%20Logiciel/MCD/MCD.mcd">MCD</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/Genie%20Logiciel/impact_maping.png">Impact Map</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/Genie%20Logiciel/Maquette/Maquette_sp-1.pdf">Maquette</a><br>
  <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/Genie%20Logiciel/Diagramme%20de%20classes/diagramme%20de%20classes.svg">Diagramme de classes</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/tree/main/Genie%20Logiciel/Diagramme%20cas%20d'utilisation">Diagramme cas d'utilisation</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/tree/main/Genie%20Logiciel/Diagramme%20de%20sequence">Diagramme de sequence</a>
   • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/Genie%20Logiciel/Requetes.pdf">Requetes</a>
</div>

<br><br>
Pour installer le projet, veuillez consulter la page [lancement.md](/lancement.md)

---
**Structure:**

    .
    ├── Genie Logiciel          # Partie liée au génie logiciel
    ├── Rapports                # Rapports envoyées
    ├── Set-up                  # Script de mise en route du projet
    ├── src                     # Racine du projet           
    ├── libraries               # les librairies cross-platform utilisées     
    ├── README.md               # Informations sur le projet
    ├── lancement.md            # Comment lancer le projet
    ├── identifiants_users.txt   # Identifiants des utilisateurs présents dans le dump.
    └── resumé-reunions.md      # Résumé des réunions de groupe
    

**Focus sur src:**

    src
    ├── DAO                   # Accesseur aux données
    ├── controllers           # Contrôleurs (lien entre modele et vue)
    ├── modele                # Objets-Métier
    ├── ressources            # Configurations et fichiers utilisés par le logiciel.
    ├── exceptions            # Exceptions dérivant d'Exception
    ├── validator             # les différents validateurs sont ici.
    ├── validForm             # les différents validateurs de formulaires sont ici.
    ├── tests                 # Tests des classes, rangés intuitivement.
    ├── views                 # Vues (interface graphique)
    └── utility               # Classes utilitaires et objets-Service

---

**Conventions de codage:**
- Accolades à côté des noms
- Un attribut par ligne
- camelCase
- Majuscules aux noms de classes
- Tab => 4 espaces
- Un saut de ligne entre chaque méthode (on ne colle pas les blocs)
- Commenter la dernière accolade
- Inverser les clauses (if) si possible
- Pas de oneliner sauf si ternaire possible sur le return
- Etendre Exception dans un package dédié pour avoir des Exceptions personnalisées
- Saut de ligne entre bloc déclaration et bloc logique
- Pas de saut de ligne dans les blocs sauf déclaration/logique
- Espace entre valeurs et opérateurs
- Espace après les virgules
- Espace après if, for, else
- Package en minuscule sauf acronyme
- CONSTANTE
- Commentaire JavaDoc
- IdBDD, port, tous les trucs de setup dans un json à part
- Documenter chaque classe

La configuration du checkstyle pour les IDE se trouve dans /src/ressources/checkstyleConfigurationGDA.xml


**Outils:**
- JDK 19
- JavaFX 19 & FXML
- MariaDB 10.4 (preconfiguré par XAMPP)
- JDBC 
- JUnit 5.8.1
- gson 2.10
- Argon2 2.11
- Scnene Builder

Plus de details dans [lancement.md](/lancement.md)

**Collaborateurs:**
+ LAGASSE Adrian: @Alpha-Lin
+ MSIAH Romain: @Romain9
+ REMY Léo: @KeziahOnuru
+ BERTHEL Gabriel: @g4bey

[Consulter l'historique des réunions](https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/resum%C3%A9-reunions.md)

