CREATE DATABASE IF NOT EXIST GDADevTest;

--
-- Création de la base de test
--
USE GDADevTest;
CREATE TABLE `users` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(256) NOT NULL,
    `email` varchar(355) NOT NULL,
    `password` varchar(256) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

