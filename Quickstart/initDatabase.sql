-- Création des différentes base de donnés
CREATE DATABASE IF NOT EXIST gestAgricoleProd;
CREATE DATABASE IF NOT EXIST gestAgricoleDev;
CREATE DATABASE IF NOT EXIST gestAgricoleTest;

--
-- Création de la base de test
--
USE gestAgricoleTest;
CREATE TABLE `users` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(256) NOT NULL,
    `email` varchar(355) NOT NULL,
    `password` varchar(256) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

