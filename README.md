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

---
**Structure:**

    .
    ├── Genie Logiciel        # Partie liée au génie logiciel
    ├── Rapports              # Rapports envoyées
    ├── Set-up                # Script de mise en route du projet
    ├── src                   # Racine du projet           
    ├── README.md             # Informations sur le projet
    └── resumé-reunions.md    # Résumé des réunions de groupe
    

**Focus sur src:**

    src
    ├── src.DAO                   # Accesseur aux données
    ├── src.controllers           # Contrôleurs (lien entre src.modele et vue)
    ├── src.modele                # Objets-Métier
    ├── src.ressources            # Configurations et fichiers utilisés par le logiciel.
    ├── src.exceptions            # Exceptions dérivant d'Exception
    ├── src.tests                 # Tests des classes, rangés intuitivement.
    ├── src.views                 # Vues (interface graphique)
    └── src.utility               # Classes utilitaires et objets-Service

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

La configuration du checkstyle pour les IDE se trouve dans /src/src.ressources/checkstyleConfigurationGDA.xml


**Outils:**
- JDK 19
- JavaFX & FXML
- MySQL 10.2 (preconfiguré par XAMPP)
- JDBC 
- JUnit 5.8.1 & JUnit4
- Scnene Builder

**Collaborateurs:**
+ LAGASSE Adrian: @Alpha-Lin
+ MSIAH Romain: @Romain9
+ REMY Léo: @KeziahOnuru
+ BERTHEL Gabriel: @g4bey

[Consulter l'historique des réunions](https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/resum%C3%A9-reunions.md)

