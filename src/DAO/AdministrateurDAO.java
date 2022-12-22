package DAO;

import modele.Administrateur;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Représente le DAO des Administrateurs.
 */
public class AdministrateurDAO extends DAO<Administrateur> {
    /**
     * Récupère dans la base de données l'instance d'Administrateur demandée.
     * 
     * @param id Id de type int, représente l'id de l'objet Administrateur demandé.
     * @return Une instance d'Administrateur.
     */
    @Override
    public Administrateur get(int id) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Administrateur WHERE idAdministrateur = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Administrateur(id, rs.getString("pseudo"), rs.getString("mdpAdmin"));
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances d'Administrateur.
     * 
     * @return Une liste d'instances d'Administrateur.
     */
    @Override
    public ArrayList<Administrateur> getAll() {
        ArrayList<Administrateur> administrateurs = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Administrateur");

            while (rs.next()) {
                administrateurs.add(new Administrateur(
                        rs.getInt("idAdministrateur"),
                        rs.getString("pseudo"),
                        rs.getString("mdpAdmin")));
            }

            return administrateurs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute dans la base de données une instance d'Administrateur.
     * 
     * @param adm L'instance Administrateur de l'objet à ajouter.
     */
    @Override
    public void add(Administrateur adm) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Administrateur VALUES (NULL, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, adm.getMdpAdmin());
            pstmt.setString(2, adm.getPseudo());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger) rs.getObject(1)).longValue();
                adm.setIdAdministrateur((int) id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour dans la base de données une instance d'Administrateur.
     * 
     * @param adm L'instance Administrateur de l'objet à mettre à jour.
     */
    @Override
    public void update(Administrateur adm) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Administrateur SET pseudo = ?, mdpAdmin = ? WHERE idAdministrateur = ?");
            pstmt.setString(1, adm.getPseudo());
            pstmt.setString(2, adm.getMdpAdmin());
            pstmt.setInt(3, adm.getIdAdministrateur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance d'Administrateur associée à l'id.
     * 
     * @param adm Administrateur représentant l'Administrateur à supprimer.
     */
    @Override
    public void delete(Administrateur adm) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Administrateur WHERE idAdministrateur = ?");
            pstmt.setInt(1, adm.getIdAdministrateur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructeur d'AdministrateurDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */
    public AdministrateurDAO(Connection conn) {
        super(conn);
    }
}