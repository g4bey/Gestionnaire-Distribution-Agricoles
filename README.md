<div align='center'>
  <h1>Patate & Salade</h1>
  <p>Planification, vérification et visualisation de tournées agricoles.</p>
</div>
<br>

<div align='center'>
  <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/Scnenario.md">Scénario Principal</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/tree/main/MCD">MCD</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/impact_maping.png">Impact Map</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/tree/Romain9/Maquette">Maquette</a><br>
  <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/tree/main/Diagramme%20de%20classes">Diagramme de classes</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/tree/main/Diagramme%20cas%20d'utilisation">Diagramme cas d'utilisation</a>
  • <a href="https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/tree/main/Diagramme%20de%20sequence">Diagramme de sequence</a>
</div>

---
**Structure:**

    .
    ├── Genie Logiciel        # Partie liée au génie logiciel      
    ├── Rapports              # Rapports envoyées
    ├── Set-up                # Scripte de mise en route du projet    
    ├── src                   # Racine du projet               
    ├── README.md             # Information sur le projet
    └── resumé-reunions.md    # Résumé des réunions de groupe
    

**Focus sur src:**

    src
    ├── DAO                   # Accesseur aux données    
    ├── controllers           # Controlleurs (lien entre modele et vue)
    ├── modele                # Objets-Metiers
    ├── ressources            # Configurations et fichiers utilisé par le logiciel. 
    ├── exceptions            # les exceptions dérivant d'Exception
    ├── tests                 # Tests des classes, rangés intuitivement.
    ├── views                 # Vues (interface graphique)
    └── utility               # Classes utilitaires et objets-service

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
- JavaFX & FXML
- MySQL & JDBC
- JUnit 5

**Collaborateurs:**
+ LAGASSE Adrian: @Alpha-Lin
+ MSIAH Romain: @Romain9
+ REMY Léo: @KeziahOnuru
+ BERTHEL Gabriel: @PhobosWolf

[Consulter l'historique des réunions](https://github.com/phoboswolf/Gestionnaire-Distribution-Agricoles/blob/main/resum%C3%A9-reunions.md)
