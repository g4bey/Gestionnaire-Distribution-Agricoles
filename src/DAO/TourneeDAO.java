package DAO;

import modele.Tournee;
import java.sql.Connection;
import java.util.List;

/**
 * Représente le DAO des tournées.
 */

public class TourneeDAO extends DAO<Tournee> {
    /**
     * Récupère dans la base de données l'instance de Tournee demandée.
     * 
     * @param id id de type int, représente l'id de l'objet Tournee demandé.
     * @returns Une instance de Tournee.
     */

    @Override
    public Tournee get(int id) {
    }

    /**
     * Récupère dans la base de données toutes les instances de Tournee.
     * 
     * @returns Une liste d'instances de Tournee.
     */

    @Override
    public List<Tournee> getAll() {
    }

    /**
     * Ajoute dans la base de données une instance de Tournee.
     * 
     * @param t l'instance Tournee de l'objet à ajouter.
     */

    @Override
    public void add(Tournee t) {
    }

    /**
     * Met à jour dans la base de données une instance de Tournee.
     * 
     * @param t l'instance Tournee de l'objet à mettre à jour.
     */

    @Override
    public void update(Tournee t) {
    }

    /**
     * Supprime de la base de données l'instance de Tournee associée à l'id.
     * 
     * @param id int représentant l'id de Tournee à supprimer.
     */

    @Override
    public void delete(int id) {
    }

    /**
     * Constructeur de TourneeDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */

    public TourneeDAO(Connection conn) {
        super(conn);
    }
}