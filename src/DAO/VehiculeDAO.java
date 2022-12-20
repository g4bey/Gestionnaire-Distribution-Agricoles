package DAO;

import modele.Vehicule;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

                Vehicule vehicule = new Vehicule(id,
                    rs.getString("numImmat"), 
                    rs.getFloat("poidsMax"),
                    rs.getString("libelle"),
                    pDAO.get(rs.getInt("idProducteur"))
                );

                chargeListeTournee(vehicule);

                return vehicule;
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
            rs = stmt.executeQuery("SELECT * FROM Vehicule");

            ProducteurDAO pDAO = new ProducteurDAO(conn);

            while (rs.next()) {
                Vehicule vehicule = new Vehicule(
                        rs.getInt("idVehicule"),
                        rs.getString("numImmat"),
                        rs.getFloat("poidsMax"),
                        rs.getString("libelle"),
                        pDAO.get(rs.getInt("idProducteur"))
                );

                chargeListeTournee(vehicule);
                vehicules.add(vehicule);
            }

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
            pstmt = conn.prepareStatement("INSERT INTO Vehicule VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
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
            updateListeTournee(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance de Vehicule associée à l'id.
     * 
     * @param t Vehicule représentant le Vehicule à supprimer.
     */
    @Override
    public void delete(Vehicule t) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Vehicule WHERE idVehicule = ?");
            pstmt.setInt(1, t.getIdVehicule());

            pstmt.executeUpdate();
            t.getTournees().forEach(tournee -> t.setIdVehicule(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode permettant de charger la liste de tournee associee à un vehicle.
     * @param t Vehicule le Vehicule contenant la liste de tournee.
     */
    public void chargeListeTournee(Vehicule t) throws SQLException {
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        pstmt = conn.prepareStatement(
                "SELECT idTournee FROM Tournee WHERE idVehicule = ?"
        );
        pstmt.setInt(1, t.getIdVehicule());
        ResultSet rsTournee = pstmt.executeQuery();

        // On charge le tableau pour chaaue ID tournee.
        while(rsTournee.next()) {
            t.addTournee(
                    tourneeDAO.get(rsTournee.getInt("idTournee"))
            );
        }
    }

    /**
     * Mise à jour de la liste de tournee associée à un vehicle.
     * Ici, on supprime le tableau et on le recharge.
     * @param t Vehicule le Vehicule contenant la liste de tournee.
     */
    public void updateListeTournee(Vehicule t) throws SQLException {
        t.getTournees().forEach(tournee -> tournee.setIdTournee(0));
        chargeListeTournee(t);
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