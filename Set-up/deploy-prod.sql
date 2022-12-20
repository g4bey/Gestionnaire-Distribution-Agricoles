-- Création de la base de données
DROP DATABASE IF EXISTS GDAProd;
CREATE DATABASE IF NOT EXISTS GDAProd;
USE GDAProd;

#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Producteur
#------------------------------------------------------------

CREATE TABLE Producteur(
                           idProducteur Int UNSIGNED  Auto_increment  NOT NULL ,
                           proprietaire Varchar (50) NOT NULL ,
                           adresseProd  Varchar (50) NOT NULL ,
                           numTelProd   Varchar (12) NOT NULL ,
                           gpsProd      Char (19) NOT NULL ,
                           mdpProd      Char (100) NOT NULL ,
                           siret        Varchar (14) NOT NULL
    ,CONSTRAINT Producteur_AK UNIQUE (siret)
    ,CONSTRAINT Producteur_PK PRIMARY KEY (idProducteur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Vehicule
#------------------------------------------------------------

CREATE TABLE Vehicule(
                         idVehicule   Int UNSIGNED  Auto_increment  NOT NULL ,
                         poidsMax     Float UNSIGNED NOT NULL ,
                         libelle      Varchar (50) NOT NULL ,
                         numImmat     Char (7) NOT NULL ,
                         idProducteur Int UNSIGNED NOT NULL
    ,CONSTRAINT Vehicule_AK UNIQUE (numImmat)
    ,CONSTRAINT Vehicule_PK PRIMARY KEY (idVehicule)

    ,CONSTRAINT Vehicule_Producteur_FK FOREIGN KEY (idProducteur) REFERENCES Producteur(idProducteur) ON DELETE CASCADE
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Tournee
#------------------------------------------------------------

CREATE TABLE Tournee(
                        idTournee    Int UNSIGNED  Auto_increment  NOT NULL ,
                        horaireDebut TimeStamp NOT NULL ,
                        horaireFin   TimeStamp NOT NULL ,
                        poids        Float UNSIGNED NOT NULL ,
                        libelle      Varchar (50) NOT NULL ,
                        idVehicule   Int UNSIGNED NOT NULL
    ,CONSTRAINT Tournee_PK PRIMARY KEY (idTournee)

    ,CONSTRAINT Tournee_Vehicule_FK FOREIGN KEY (idVehicule) REFERENCES Vehicule(idVehicule) ON DELETE SET 0
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Administrateur
#------------------------------------------------------------

CREATE TABLE Administrateur(
                               idAdministrateur Int UNSIGNED  Auto_increment  NOT NULL ,
                               mdpAdmin         Char (100) NOT NULL ,
                               pseudo           Varchar (50) NOT NULL
    ,CONSTRAINT Administrateur_AK UNIQUE (pseudo)
    ,CONSTRAINT Administrateur_PK PRIMARY KEY (idAdministrateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
                       idClient      Int UNSIGNED  Auto_increment  NOT NULL ,
                       nomClient     Varchar (50) NOT NULL ,
                       adresseClient Varchar (50) NOT NULL ,
                       gpsClient     Char (19) NOT NULL ,
                       numTelClient  Varchar (12) NOT NULL
    ,CONSTRAINT Client_PK PRIMARY KEY (idClient)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Commande
#------------------------------------------------------------

CREATE TABLE Commande(
                         idCommande   Int UNSIGNED  Auto_increment  NOT NULL ,
                         libelle      Varchar (50) NOT NULL ,
                         poids        Float UNSIGNED NOT NULL ,
                         horaireDebut TimeStamp NOT NULL ,
                         horaireFin   TimeStamp NOT NULL ,
                         idTournee    Int UNSIGNED ,
                         idProducteur Int UNSIGNED NOT NULL ,
                         idClient     Int UNSIGNED NOT NULL
    ,CONSTRAINT Commande_PK PRIMARY KEY (idCommande)

    ,CONSTRAINT Commande_Tournee_FK FOREIGN KEY (idTournee) REFERENCES Tournee(idTournee) ON DELETE SET NULL
    ,CONSTRAINT Commande_Producteur0_FK FOREIGN KEY (idProducteur) REFERENCES Producteur(idProducteur) ON DELETE CASCADE
    ,CONSTRAINT Commande_Client1_FK FOREIGN KEY (idClient) REFERENCES Client(idClient)
)ENGINE=InnoDB;


-- Insertion du véhicule supprimé
INSERT INTO Vehicule VALUES (0, 0, 'Véhicule supprimé', 0000000, 0);

-- Insertion de l'administrateur initial
INSERT INTO Administrateur (mdpAdmin, pseudo) VALUES ('null', 'Admin');

-- Création de l'utilisateur et paramétrage des droits
DROP USER IF EXISTS 'GDA'@'localhost';
CREATE USER 'GDA'@'localhost' IDENTIFIED BY '1234';
GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO 'GDA'@'localhost';
FLUSH PRIVILEGES;