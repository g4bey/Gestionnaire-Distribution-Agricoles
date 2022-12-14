package DAO;

import modele.Administrateur;
import java.sql.Connection;
import java.util.List;

/**
 * Représente le DAO des administrateurs.
 */

public class AdministrateurDAO extends DAO<Administrateur> {
    /**
     * Récupère dans la base de données l'instance d'Administrateur demandée.
     * 
     * @param id id de type int, représente l'id de l'objet Administrateur demandé.
     * @returns Une instance d'Administrateur.
     */

    @Override
    public Administrateur get(int id) {
    }

    /**
     * Récupère dans la base de données toutes les instances d'Administrateur.
     * 
     * @returns Une liste d'instances d'Administrateur.
     */

    @Override
    public List<Administrateur> getAll() {
    }

    /**
     * Ajoute dans la base de données une instance d'Administrateur.
     * 
     * @param t l'instance Administrateur de l'objet à ajouter.
     */

    @Override
    public void add(Administrateur t) {
    }

    /**
     * Met à jour dans la base de données une instance d'Administrateur.
     * 
     * @param t l'instance Administrateur de l'objet à mettre à jour.
     */

    @Override
    public void update(Administrateur t) {
    }

    /**
     * Supprime de la base de données l'instance de Administrateur associée à l'id.
     * 
     * @param id int représentant l'id d'Administrateur à supprimer.
     */

    @Override
    public void delete(int id) {
    }

    /**
     * Constructeur de AdministrateurDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */

    public AdministrateurDAO(Connection conn) {
    }
}