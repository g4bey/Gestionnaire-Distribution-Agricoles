## Pré-requis
### Version de Java:
Ce projet a été développé sous la **JDK 19**.<br>
Cette version est présentement [disponible sur cette page](https://www.oracle.com/java/technologies/downloads/#java19).<br>
<br>
La commande `java --version` permet de vérifier rapidement la version.

### Base de donnée:
La base de donnée utilisée est un serveur **MariaDB version 10.4**.<br>
Par souci de simplicité, on peut l'installer via [XAMPP](https://www.apachefriends.org/download.html) ce qui permet d'utiliser phpmyadmin.<br>
<br>
La commande `mariadb --version` permet de vérifier la version.<br>
Il est aussi possible de consulter cette information sur la page d'acceuil de phpmyadmin.

### Bibliothèques:
Dans un premier temps, nous procéderons au téléchargement des librairies.<br>
Ensuite, nous traiterons de leurs installations.

#### Bibliothèques fournies sur le dépot:
- JUnit 5.8.1: *junit-platform-console-standalone-1.9.1.jar*
- Gson 2.10: *gson-2.10.jar*
- Argon2-jvm 2.11: *Argon2-jvm-2.11.jar, Argon2-jvm-nolibs-2.11.jar*
- JNA version 5.12.1: *jna-5.12.1.jar utilisée par Argon2-jvm 2.11*
- Connecteur MYSQL version 8.0.13: *mysql-connector-java-8.0.13.jar*

Pour les retrouver, veuillez consulter le dossier **/librairies** disponible à la racine du dépot.

#### Bibliothèques non-fournies:
JavaFX étant dépendante du système d'exploitation, nous avons choisi de ne pas la fournir.<br>
Notez que le projet a été developpé sous la **version 19 de JAVAFX** [disponible sur cette page](https://gluonhq.com/products/javafx/).

## Installation du projet:
### Clonage du dépot:
- 1/ Selectionnez la SDK 19 comme JRE.
- 2/ Importez le ddépot : `https://github.com/g4bey/Gestionnaire-Distribution-Agricoles.git`

**Attention!** : Si vous souhaitez utiliser Eclipse, **utilisez la branche release-eclipse**.<br>
L'IDE ajoutant un packet src, les chemins dans les fichiers FXML ne seront plus valide autrement.

### Ajout des bibliothèques:
#### Bibliothèques fournies:
- 1/ Copiez les jar fournies dans le dossier `/librairies/` dans un dossier `/lib/` à la racine.
- 2/ Ajoutez ces jars au buildpath.
- 3/ Vérifiez qu'elles apparaissent bien dans le dossier `Referenced Librairies`.

#### Installation de JavaFX 19:
- 1/ Décompressez le dossier `javafx-sdk-19` dans le dossier `/lib/`
- 2/ Ajoutez les jars dans `/lib/javafx-sdk-19/lib/` au buildpath.

## Configuration du projet:
### Lancement du projet JAVAFx:
- 1/ Créez une nouvelle configuration de type **JAVA Application**.
- 2/ Vérifiez que la JRE est bien la **JDK 19**.
- 3/ Selectionnez `src/GDA.java` comme classe principale.
- 4/ Ajoutez les VM arguments suivants:

```--module-path <CHEMIN>/lib/javafx-sdk-19/lib/```<br>
```--add-modules=javafx.base,javafx.graphics,javafx.controls,javafx.fxml,javafx.web,javafx.media```

<CHEMIN\> est le chemin absolu vers le dossier `/lib/javafx-sdk-19/lib/`.

### Lancement des tests JUnit:
- 1/ Créez une nouvelle configuration de type **JUnit**.
- 2/ Vérifiez que la JRE est bien la **JDK 19**.
- 3/ Selectionnez `src/tests/` comme dossier contenant les tests.

## Mise en route des bases de donnee:
- 1/ Lancez MariaDB version 10.4 sur le port 3306.
- 2/ Executez les scripts `deploy-test.sql` et `deploy-prod.sql` afin de créer les bases **GDATest** et **GDAProd**.
- 3/ Executez ensuite le script `dump-production.sql`.

### Configuration par défaut:
Vérifiez que les informations dans `src/ressources/config.properties` correspondent à la configuration suivante.

*GDATest*:
```
db.testing.username=GDATest
db.testing.password=1234
db.testing.url=jdbc:mysql://localhost/GDATest
```
*GDAProd*:
```
db.production.username=GDA
db.production.password=1234
db.production.url=jdbc:mysql://localhost/GDAProd
```
*API OpenRouteService*
```ORS_KEY=5b3ce3597851110001cf6248b0564520d72840508742e806dd03e7a1```
