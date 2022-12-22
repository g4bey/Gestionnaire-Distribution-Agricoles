package src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Classe abstraite des src.DAO.
 */
public abstract class DAO<T> {
    protected Connection conn = null;
    protected Statement stmt = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;

    /**
     * Constructeur de src.DAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */
    public DAO(Connection conn) {
        this.conn = conn;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère dans la base de données l'instance de T demandée.
     * 
     * @param id Id de type int, représente l'id de l'objet T demandé.
     * @return Une instance de T.
     */
    public abstract T get(int id);

    /**
     * Récupère dans la base de données toutes les instances de T.
     * 
     * @return Une liste d'instances de T.
     */
    public abstract ArrayList<T> getAll();

    /**
     * Ajoute dans la base de données une instance de T.
     * 
     * @param t L'instance T de l'objet à ajouter.
     */
    public abstract void add(T t);

    /**
     * Met à jour dans la base de données une instance de T.
     * 
     * @param t L'instance T de l'objet à mettre à jour.
     */
    public abstract void update(T t);

    /**
     * Supprime de la base de données l'instance de T associée à l'id.
     * 
     * @param t L'instance T de l'objet à supprimer.
     */
    public abstract void delete(T t);
}