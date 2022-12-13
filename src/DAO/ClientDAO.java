package DAO;

import java.util.*;


import DAO.DAO.T;
import modele.Client;
import java.sql.Connection;

/**
* Représente le DAO des client.
*/

public class ClientDAO extends DAO<Client> {
    /**
    * Récupère dans la base de données l'instance de T demandée.
    * @param id id de type int, représente l'id de l'objet T demandé.
    * @returns Une instance de T.
    */

    @Override
    public T get(int id) {
    }
    /**
    * Récupère dans la base de données toutes les instances de T.
    * @returns Une liste d'instances de T.
    */

    @Override
    public List<T> getAll() {
    }
    /**
    * Ajoute dans la base de données une instance de T.
    * @param t l'instance T de l'objet à ajouter.
    */

    @Override
    public void add(T t) {
    }
    /**
    * Met à jour dans la base de données une instance de T.
    * @param t l'instance T de l'objet à mettre à jour.
    */

    @Override
    public void update(T t) {
    }
    /**
    * Supprime de la base de données  l'instance de T associée à l'id.
    * @param id int représentant l'id de T à supprimer.
    */

    @Override
    public void delete(int id) {
    }
    /**
    * Constructeur de ClientDAO.
    * @param conn Une Connection représentant la connexion à la base de données.
    */

    public ClientDAO(Connection conn) {
    }
}