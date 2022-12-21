package DAO;

import modele.Commande;
import modele.Tournee;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
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
                        clDAO.get(rs.getInt("idClient")));
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
                        clDAO.get(rs.getInt("idClient"))));
            }

            return commandes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute dans la base de données une instance de Commande.
     * 
     * @param t l'instance Commande de l'objet à ajouter.
     */
    @Override
    public void add(Commande t) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Commande VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, t.getLibelle());
            pstmt.setFloat(2, t.getPoids());
            pstmt.setTimestamp(3, t.getHoraireDebut());
            pstmt.setTimestamp(4, t.getHoraireFin());
            pstmt.setInt(5, t.getTournee().getIdTournee());
            pstmt.setInt(6, t.getProducteur().getIdProducteur());
            pstmt.setInt(7, t.getClient().getIdClient());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger) rs.getObject(1)).longValue();
                t.setIdCommande((int) id);
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
     * @param t Commande représentant la Commande à supprimer.
     */
    @Override
    public void delete(Commande t) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Commande WHERE idCommande = ?");
            pstmt.setInt(1, t.getIdCommande());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retour une liste de commandes associée à une tournée.
     *
     * @param t le Tournee qui doit etre associe à la commande.
     * @return tournees un ArrayList<> contenant les tournees associés au vehicle
     * @throws SQLException
     */
    public ArrayList<Commande> getCommandeByTournee(Tournee t) throws SQLException {
        ArrayList<Commande> commandes = new ArrayList<>();

        pstmt = conn.prepareStatement(
                "SELECT * FROM Commande WHERE idTournee = ?"
        );
        pstmt.setInt(1, t.getIdTournee());
        rs = pstmt.executeQuery();

        ProducteurDAO producteurDAO = new ProducteurDAO(conn);
        ClientDAO clientDAO = new ClientDAO(conn);

        while (rs.next()) {
                commandes.add(new Commande(
                            rs.getInt("idCommande"),
                            rs.getString("libelle"),
                            rs.getFloat("poids"),
                            rs.getTimestamp("horaireDebut"),
                            rs.getTimestamp("horaireFin"),
                            t,
                            producteurDAO.getProducteurById(rs.getInt("idProducteur")),
                            clientDAO.getClientById(rs.getInt("idProducteur"))
                        )
                );
        }

        return commandes;
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