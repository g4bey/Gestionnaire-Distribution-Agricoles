package DAO;

import modele.Vehicule;
import java.sql.Connection;
import java.util.List;

/**
 * Représente le DAO des véhicules.
 */

public class VehiculeDAO extends DAO<Vehicule> {
    /**
     * Récupère dans la base de données l'instance de Vehicule demandée.
     * 
     * @param id id de type int, représente l'id de l'objet Vehicule demandé.
     * @returns Une instance de Vehicule.
     */

    @Override
    public Vehicule get(int id) {
    }

    /**
     * Récupère dans la base de données toutes les instances de Vehicule.
     * 
     * @returns Une liste d'instances de Vehicule.
     */

    @Override
    public List<Vehicule> getAll() {
    }

    /**
     * Ajoute dans la base de données une instance de Vehicule.
     * 
     * @param t l'instance Vehicule de l'objet à ajouter.
     */

    @Override
    public void add(Vehicule t) {
    }

    /**
     * Met à jour dans la base de données une instance de Vehicule.
     * 
     * @param t l'instance Vehicule de l'objet à mettre à jour.
     */

    @Override
    public void update(Vehicule t) {
    }

    /**
     * Supprime de la base de données l'instance de Vehicule associée à l'id.
     * 
     * @param id int représentant l'id de Vehicule à supprimer.
     */

    @Override
    public void delete(int id) {
    }

    /**
     * Constructeur de VehiculeDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */

    public VehiculeDAO(Connection conn) {
        super(conn);
    }
}