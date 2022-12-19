package DAO;

import modele.Vehicule;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente le DAO des véhicules.
 */

public class VehiculeDAO extends DAO<Vehicule> {
    /**
     * Récupère dans la base de données l'instance de Vehicule demandée.
     * 
     * @param id id de type int, représente l'id de l'objet Vehicule demandé.
     * @return Une instance de Vehicule.
     */
    @Override
    public Vehicule get(int id) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Vehicule WHERE idVehicule = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ProducteurDAO pDAO = new ProducteurDAO(conn);

                return new Vehicule(id, 
                    rs.getString("numImmat"), 
                    rs.getFloat("poidsMax"),
                    rs.getString("libelle"),
                    pDAO.get(rs.getInt("idProducteur"))
                );
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances de Vehicule.
     * 
     * @return Une liste d'instances de Vehicule.
     */
    @Override
    public List<Vehicule> getAll() {
        ArrayList<Vehicule> vehicules = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Client");

            ProducteurDAO pDAO = new ProducteurDAO(conn);

            while (rs.next())
                vehicules.add(new Vehicule(
                    rs.getInt("idVehicule"), 
                    rs.getString("numImmat"), 
                    rs.getFloat("poidsMax"),
                    rs.getString("libelle"), 
                    pDAO.get(rs.getInt("idProducteur")))
                );

            return vehicules;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute dans la base de données une instance de Vehicule.
     * 
     * @param t l'instance Vehicule de l'objet à ajouter.
     */
    @Override
    public void add(Vehicule t) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Vehicule VALUES (NULL, ?, ?, ?, ?)");
            pstmt.setFloat(1, t.getPoidsMax());
            pstmt.setString(2, t.getLibelle());
            pstmt.setString(3, t.getNumImmat());
            pstmt.setInt(4, t.getProducteur().getIdProducteur());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            
            if (rs.next()) {
                long id = ((BigInteger)rs.getObject(1)).longValue();
                t.setIdVehicule((int)id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour dans la base de données une instance de Vehicule.
     * 
     * @param t l'instance Vehicule de l'objet à mettre à jour.
     */
    @Override
    public void update(Vehicule t) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Vehicule SET poidsMax = ?, libelle = ?, numImmat = ?, idProducteur = ? WHERE idVehicule = ?");
            pstmt.setFloat(1, t.getPoidsMax());
            pstmt.setString(2, t.getLibelle());
            pstmt.setString(3, t.getNumImmat());
            pstmt.setInt(4, t.getProducteur().getIdProducteur());
            pstmt.setInt(5, t.getIdVehicule());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance de Vehicule associée à l'id.
     * 
     * @param id int représentant l'id de Vehicule à supprimer.
     */
    @Override
    public void delete(int id) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Vehicule WHERE idVehicule = ?");
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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