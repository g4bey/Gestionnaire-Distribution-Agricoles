package DAO;

import modele.Administrateur;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Administrateur WHERE idAdministrateur = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next())
                return new Administrateur(id, rs.getString("pseudo"), rs.getString("mdpAdmin"));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances d'Administrateur.
     * 
     * @returns Une liste d'instances d'Administrateur.
     */

    @Override
    public List<Administrateur> getAll() {
        ArrayList<Administrateur> administrateurs = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Administrateur");

            while (rs.next())
                administrateurs.add(new Administrateur(rs.getInt("idAdministrateur"), rs.getString("pseudo"),
                        rs.getString("mdpAdmin")));

            return administrateurs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute dans la base de données une instance d'Administrateur.
     * 
     * @param t l'instance Administrateur de l'objet à ajouter.
     */

    @Override
    public void add(Administrateur t) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Administrateur VALUES (NULL, ?, ?)");
            pstmt.setString(1, t.getPseudo());
            pstmt.setString(2, t.getMdpAdmin());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next())
                t.setIdAdministrateur(rs.getInt("idAdministrateur"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour dans la base de données une instance d'Administrateur.
     * 
     * @param t l'instance Administrateur de l'objet à mettre à jour.
     */

    @Override
    public void update(Administrateur t) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Administrateur SET pseudo = ?, mdpAdmin = ? WHERE idAdministrateur = ?");
            pstmt.setString(1, t.getPseudo());
            pstmt.setString(2, t.getMdpAdmin());
            pstmt.setInt(1, t.getIdAdministrateur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance de Administrateur associée à l'id.
     * 
     * @param id int représentant l'id d'Administrateur à supprimer.
     */

    @Override
    public void delete(int id) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Administrateur WHERE idAdministrateur = ?");
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructeur de AdministrateurDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */

    public AdministrateurDAO(Connection conn) {
        super(conn);
    }
}