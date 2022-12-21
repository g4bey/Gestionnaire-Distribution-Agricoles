package DAO;

import modele.Client;
import modele.Commande;
import modele.Producteur;
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
            pstmt = conn.prepareStatement("INSERT INTO Commande VALUES (NULL, ?, ?, ?, ?, NULL, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, t.getLibelle());
            pstmt.setFloat(2, t.getPoids());
            pstmt.setTimestamp(3, t.getHoraireDebut());
            pstmt.setTimestamp(4, t.getHoraireFin());
            // A l'ajout d'une commande, l'id tournee est null.
            pstmt.setInt(5, t.getProducteur().getIdProducteur());
            pstmt.setInt(6, t.getClient().getIdClient());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger) rs.getObject(1)).longValue();
                t.setIdCommande((int) id);
            }

            // On ajoute la commande au producteur
            t.getProducteur().addCommande(t);

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

            // Si la commande n'est pas dans le tableau
            if (!t.getProducteur().getCommandes().contains(t)) {
                t.getProducteur().addCommande(t);
            }

            // Si la commande n'est pas dans le tableau et que sa tournée n'est pas null
            if (t.getTournee() != null && !t.getTournee().getCommandes().contains(t)) {
                t.getTournee().addCommande(t);
            }

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

            // On supprime la commande
            t.getProducteur().removeCommande(t);

            // Si la commande est dans une tournee, on la supprime.
            if (t.getTournee() != null) {
                t.getTournee().removeCommande(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retour une liste de commandes associée à une tournée.
     * On prend le producteur en parametre pour conserver les références.
     * <p>
     * 
     * @param prd      le Producteur
     * @param tournees ArrayList<Tournee> du producteur.
     * @return ArrayList<Commande> la liste de commande associée à une Tournee.
     * @throws SQLException
     */
    public ArrayList<Commande> getCommandesByTournee(Producteur prd, Tournee tournee) throws SQLException {
        ArrayList<Commande> commandes = new ArrayList<>();

        pstmt = conn.prepareStatement(
                "SELECT * FROM Commande JOIN Client USING(idClient) WHERE idTournee = ?");
        pstmt.setInt(1, tournee.getIdTournee());
        rs = pstmt.executeQuery();

        while (rs.next()) {
            commandes.add(new Commande(
                    rs.getInt("idCommande"),
                    rs.getString("libelle"),
                    rs.getFloat("poids"),
                    rs.getTimestamp("horaireDebut"),
                    rs.getTimestamp("horaireFin"),
                    tournee,
                    prd,
                    new Client(rs.getInt("idClient"), rs.getString("nomClient"), rs.getString("adresseClient"),
                            rs.getString("gpsClient"), rs.getString("numTelClient"))));
        }

        return commandes;
    }

    /**
     * Retour une liste de commandes associée à une tournée.
     * On prend le producteur en parametre pour conserver les références.
     * <p>
     * 
     * @param prd      le Producteur
     * @param tournees ArrayList<Tournee> du producteur.
     * @return ArrayList<Commande> la liste de commande associée à un producteur.
     * @throws SQLException
     */
    public ArrayList<Commande> getCommandesByProducteurTournees(Producteur prd, ArrayList<Tournee> tournees)
            throws SQLException {
        ArrayList<Commande> commandes = new ArrayList<>();

        // On récupère toutes les commandes associées aux tournées
        for (Tournee tournee : tournees) {
            commandes = getCommandesByTournee(prd, tournee);
        }

        // On récupère toutes les commandes qui n'ont pas de tournées mais qui sont
        // associées au Producteur
        pstmt = conn.prepareStatement(
                "SELECT * FROM Commande JOIN Client USING(idClient) WHERE idProducteur = ? AND idTournee IS NULL");
        pstmt.setInt(1, prd.getIdProducteur());
        rs = pstmt.executeQuery();

        while (rs.next()) {
            commandes.add(new Commande(
                    rs.getInt("idCommande"),
                    rs.getString("libelle"),
                    rs.getFloat("poids"),
                    rs.getTimestamp("horaireDebut"),
                    rs.getTimestamp("horaireFin"),
                    null,
                    prd,
                    new Client(rs.getInt("idClient"), rs.getString("nomClient"), rs.getString("adresseClient"),
                            rs.getString("gpsClient"), rs.getString("numTelClient"))));
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