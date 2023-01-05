## Pré-requis
### Version de Java:
Ce projet a été développé sous la **JDK 19**.<br>
Cette version est présentement [disponible sur cette page](https://www.oracle.com/java/technologies/downloads/#java19).<br>
<br>
La commande `java --version` permet de vérifier rapidement la version.

### Base de donnée:
La base de donnée utilisé est un serveur **MariaDB version 10.4**.<br>
Par sourci de simplicité, on peut l'installer via [XAMPP](https://www.apachefriends.org/download.html) ce qui nous permettre d'utiliser phpmyadmin.<br>
<br>
La commande `mariadb --version` permet de vérifier la version.<br>
Il est possible de consulter cette information sur la page d'acceuil de phpmyadmin.

### Bibliothèques:

Dans un premier temps, nous procéderons à au téléchargement des librairies utilisées.
Ensuite, nous procéderons à leurs installations.

#### Bibliothèques fournies sur le dépot:
- JUnit 5.8.1: *junit-platform-console-standalone-1.9.1.jar*
- Gson 2.10: *gson-2.10.jar*
- Argon2-jvm 2.11: *Argon2-jvm-2.11.jar, Argon2-jvm-nolibs-2.11.jar*
- JNA version 5.12.1: *jna-5.12.1.jar utilisée par Argon2-jvm 2.11*
- Connecteur MYSQL version 8.0.13: *mysql-connector-java-8.0.13.jar

Pour les retrouver, veuillez consulter le dosser **/librairies** disponible à la racine du dépot.

#### Bibliothèques non-fournies:
JavaFX étant dépendante du systeme d'exploitation, nous avons choisi de ne pas la fournir.<br>
Le projet a été developpé sous la **version 19 de JAVAFX**: [disponible sur cette page](https://gluonhq.com/products/javafx/).




