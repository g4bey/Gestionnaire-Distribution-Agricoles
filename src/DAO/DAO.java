package DAO;

import java.sql.Connection;
import java.util.List;

/**
 * Classe abstraite des DAO.
 */

public abstract class DAO<T> {
    private Connection conn;

    protected Connection getConn() {
        return conn;
    }

    /**
     * Constructeur de DAO.
     * 
     * @param conn une Connection représentant la connexion à la base de données.
     */

    public DAO(Connection conn) {
    }

    /**
     * Récupère dans la base de données l'instance de T demandée.
     * 
     * @param id id de type int, représente l'id de l'objet T demandé.
     * @returns Une instance de T.
     */

    public abstract T get(int id);

    /**
     * Récupère dans la base de données toutes les instances de T.
     * 
     * @returns Une liste d'instances de T.
     */

    public abstract List<T> getAll();

    /**
     * Ajoute dans la base de données une instance de T.
     * 
     * @param t l'instance T de l'objet à ajouter.
     */

    public abstract void add(T t);

    /**
     * Met à jour dans la base de données une instance de T.
     * 
     * @param t l'instance T de l'objet à mettre à jour.
     */

    public abstract void update(T t);

    /**
     * Supprime de la base de données l'instance de T associée à l'id.
     * 
     * @param id int représentant l'id de T à supprimer.
     */

    public abstract void delete(int id);
}