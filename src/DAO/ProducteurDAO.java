package DAO;

import modele.Producteur;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Producteur WHERE idProducteur = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next())
                return new Producteur(id, rs.getString("siret"), rs.getString("proprietaire"),
                        rs.getString("adresseProd"),
                        rs.getString("numTelProd"),
                        rs.getString("gpsProd"),
                        rs.getString("mdpProd"));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances de Producteur.
     * 
     * @returns Une liste d'instances de Producteur.
     */

    @Override
    public List<Producteur> getAll() {
        ArrayList<Producteur> producteurs = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Producteur");

            while (rs.next())
                producteurs.add(
                        new Producteur(rs.getInt("idProducteur"), rs.getString("siret"), rs.getString("proprietaire"),
                                rs.getString("adresseProd"),
                                rs.getString("numTelProd"),
                                rs.getString("gpsProd"),
                                rs.getString("mdpProd")));

            return producteurs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute dans la base de données une instance de Producteur.
     * 
     * @param t l'instance Producteur de l'objet à ajouter.
     */

    @Override
    public void add(Producteur t) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Producteur VALUES (NULL, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, t.getSiret());
            pstmt.setString(2, t.getProprietaire());
            pstmt.setString(3, t.getAdresseProd());
            pstmt.setString(4, t.getNumTelProd());
            pstmt.setString(5, t.getGpsProd());
            pstmt.setString(6, t.getMdpProd());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next())
                t.setIdProducteur(rs.getInt("idProducteur"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour dans la base de données une instance de Producteur.
     * 
     * @param t l'instance Producteur de l'objet à mettre à jour.
     */

    @Override
    public void update(Producteur t) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Producteur SET siret = ?, proprietaire = ?, adresseProd = ?, numTelProd = ?, gpsProd = ?, mdpProd = ?)");
            pstmt.setString(1, t.getSiret());
            pstmt.setString(2, t.getProprietaire());
            pstmt.setString(3, t.getAdresseProd());
            pstmt.setString(4, t.getNumTelProd());
            pstmt.setString(5, t.getGpsProd());
            pstmt.setString(6, t.getMdpProd());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance de Producteur associée à l'id.
     * 
     * @param id int représentant l'id de Producteur à supprimer.
     */

    @Override
    public void delete(int id) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Producteur WHERE idProducteur = ?");
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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