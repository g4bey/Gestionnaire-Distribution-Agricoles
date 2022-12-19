package DAO;

import modele.Commande;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente le DAO des véhicules.
 */
public class CommandeDAO extends DAO<Commande> {
    /**
     * Récupère dans la base de données l'instance de Commande demandée.
     * 
     * @param id id de type int, représente l'id de l'objet Commande demandé.
     * @return Une instance de Commande.
     */
    @Override
    public Commande get(int id) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Commande WHERE idCommande = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                TourneeDAO tDAO = new TourneeDAO(conn);
                ProducteurDAO pDAO = new ProducteurDAO(conn);
                ClientDAO clDAO = new ClientDAO(conn);

                return new Commande(id, 
                    rs.getString("libelle"), 
                    rs.getFloat("poids"), 
                    rs.getTimestamp("horaireDebut"),
                    rs.getTimestamp("horaireFin"), 
                    tDAO.get(rs.getInt("idTournee")),
                    pDAO.get(rs.getInt("idProducteur")), 
                    clDAO.get(rs.getInt("idClient"))
                );
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances de Commande.
     * 
     * @return Une liste d'instances de Commande.
     */
    @Override
    public List<Commande> getAll() {
        ArrayList<Commande> commandes = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Commande");

            TourneeDAO tDAO = new TourneeDAO(conn);
            ProducteurDAO pDAO = new ProducteurDAO(conn);
            ClientDAO clDAO = new ClientDAO(conn);

            while (rs.next()) {
                commandes.add(new Commande(
                    rs.getInt("idCommande"), 
                    rs.getString("libelle"), 
                    rs.getFloat("poids"),
                    rs.getTimestamp("horaireDebut"), 
                    rs.getTimestamp("horaireFin"),
                    tDAO.get(rs.getInt("idTournee")), 
                    pDAO.get(rs.getInt("idProducteur")),
                    clDAO.get(rs.getInt("idClient")))
                );
            }

            return commandes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances de Commande appartenant
     * à la Tournee correspond à l'id.
     * 
     * @param idTournee de type int, représente l'id de la Tournee auquelle appartient
     *           la Commande.
     * @return Une liste d'instances de Commande.
     */
    public List<Commande> getAllByIdTournee(int idTournee) {
        ArrayList<Commande> commandes = new ArrayList<>();

        for (Commande commande : getAll()) {
            if (commande.getTournee().getIdTournee() == idTournee) {
                commandes.add(commande);
            }
        }

        return commandes;
    }

    /**
     * Ajoute dans la base de données une instance de Commande.
     * 
     * @param t l'instance Commande de l'objet à ajouter.
     */
    @Override
    public void add(Commande t) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Commande VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, t.getLibelle());
            pstmt.setFloat(2, t.getPoids());
            pstmt.setTimestamp(3, t.getHoraireDebut());
            pstmt.setTimestamp(4, t.getHoraireFin());
            pstmt.setInt(4, t.getTournee().getIdTournee());
            pstmt.setInt(5, t.getProducteur().getIdProducteur());
            pstmt.setInt(6, t.getClient().getIdClient());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger)rs.getObject(1)).longValue();
                t.setIdCommande((int)id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour dans la base de données une instance de Commande.
     * 
     * @param t l'instance Commande de l'objet à mettre à jour.
     */
    @Override
    public void update(Commande t) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Commande SET libelle = ?, poids = ?, horaireDebut = ?, horaireFin = ?, idTournee = ?, idProducteur = ?, idClient = ? WHERE idCommande = ?");
            pstmt.setString(1, t.getLibelle());
            pstmt.setFloat(2, t.getPoids());
            pstmt.setTimestamp(3, t.getHoraireDebut());
            pstmt.setTimestamp(4, t.getHoraireFin());
            pstmt.setInt(5, t.getTournee().getIdTournee());
            pstmt.setInt(6, t.getProducteur().getIdProducteur());
            pstmt.setInt(7, t.getClient().getIdClient());
            pstmt.setInt(8, t.getIdCommande());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance de Commande associée à l'id.
     * 
     * @param id int représentant l'id de Commande à supprimer.
     */
    @Override
    public void delete(int id) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Commande WHERE idCommande = ?");
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructeur de CommandeDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */
    public CommandeDAO(Connection conn) {
        super(conn);
    }
}