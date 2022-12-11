/*
* This code has been generated by the Rebel: a code generator for modern Java.
*
* Drop us a line or two at feedback@archetypesoftware.com: we would love to hear from you!
*/
package GDA.DAO;

import java.util.*;
import java.time.*;


import DAO.DAO.T;
import Modele.Producteur;
import java.sql.Connection;
// ----------- << imports@AAAAAAGEbLG6N/JHSAE= >>
// ----------- >>

/**
* Représente le DAO des producteurs.
*/

// ----------- << class.annotations@AAAAAAGEbLG6N/JHSAE= >>
// ----------- >>
public class ProducteurDAO extends DAO<Producteur> {
    /**
    * Récupère dans la base de données l'instance de T demandée.
    * @param id id de type int, représente l'id de l'objet T demandé.
    * @returns Une instance de T.
    */

    @Override
    // ----------- << method.annotations@AAAAAAGEbNLXdvfIIVM= >>
    // ----------- >>
    public T get(int id) {
    // ----------- << method.body@AAAAAAGEbNLXdvfIIVM= >>
    // ----------- >>
    }
    /**
    * Récupère dans la base de données toutes les instances de T.
    * @returns Une liste d'instances de T.
    */

    @Override
    // ----------- << method.annotations@AAAAAAGEesCaleu+4ys= >>
    // ----------- >>
    public List<T> getAll() {
    // ----------- << method.body@AAAAAAGEesCaleu+4ys= >>
    // ----------- >>
    }
    /**
    * Ajoute dans la base de données une instance de T.
    * @param t l'instance T de l'objet à ajouter.
    */

    @Override
    // ----------- << method.annotations@AAAAAAGEbNLv1vfU89g= >>
    // ----------- >>
    public void add(T t) {
    // ----------- << method.body@AAAAAAGEbNLv1vfU89g= >>
    // ----------- >>
    }
    /**
    * Met à jour dans la base de données une instance de T.
    * @param t l'instance T de l'objet à mettre à jour.
    */

    @Override
    // ----------- << method.annotations@AAAAAAGEbNL5svfab9s= >>
    // ----------- >>
    public void update(T t) {
    // ----------- << method.body@AAAAAAGEbNL5svfab9s= >>
    // ----------- >>
    }
    /**
    * Supprime de la base de données  l'instance de T associée à l'id.
    * @param id int représentant l'id de T à supprimer.
    */

    @Override
    // ----------- << method.annotations@AAAAAAGEbNWAKffjF44= >>
    // ----------- >>
    public void delete(int id) {
    // ----------- << method.body@AAAAAAGEbNWAKffjF44= >>
    // ----------- >>
    }
    /**
    * Constructeur de ProducteurDAO.
    * @param conn Une Connection représentant la connexion à la base de données.
    */

    // ----------- << method.annotations@AAAAAAGEesoXSQ4RHu8= >>
    // ----------- >>
    public ProducteurDAO(Connection conn) {
    // ----------- << method.body@AAAAAAGEesoXSQ4RHu8= >>
    // ----------- >>
    }
// ----------- << class.extras@AAAAAAGEbLG6N/JHSAE= >>
// ----------- >>
}