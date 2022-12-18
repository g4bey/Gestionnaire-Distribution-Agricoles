package DAO;

import modele.Producteur;
import java.sql.Connection;
import java.util.List;

/**
 * Représente le DAO des producteurs.
 */

public class ProducteurDAO extends DAO<Producteur> {
    /**
     * Récupère dans la base de données l'instance de Producteur demandée.
     * 
     * @param id id de type int, représente l'id de l'objet Producteur demandé.
     * @returns Une instance de Producteur.
     */

    @Override
    public Producteur get(int id) {
    }

    /**
     * Récupère dans la base de données toutes les instances de Producteur.
     * 
     * @returns Une liste d'instances de Producteur.
     */

    @Override
    public List<Producteur> getAll() {
    }

    /**
     * Ajoute dans la base de données une instance de Producteur.
     * 
     * @param t l'instance Producteur de l'objet à ajouter.
     */

    @Override
    public void add(Producteur t) {
    }

    /**
     * Met à jour dans la base de données une instance de Producteur.
     * 
     * @param t l'instance Producteur de l'objet à mettre à jour.
     */

    @Override
    public void update(Producteur t) {
    }

    /**
     * Supprime de la base de données l'instance de Producteur associée à l'id.
     * 
     * @param id int représentant l'id de Producteur à supprimer.
     */

    @Override
    public void delete(int id) {
    }

    /**
     * Constructeur de ProducteurDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */

    public ProducteurDAO(Connection conn) {
        super(conn);
    }
}