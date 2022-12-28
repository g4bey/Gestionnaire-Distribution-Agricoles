package DAO;

import modele.Client;
import modele.Commande;
import modele.Tournee;
import modele.Vehicule;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Représente le DAO des Tournées.
 */
public class TourneeDAO extends DAO<Tournee> {
    /**
     * Récupère dans la base de données l'instance de Tournée demandée.
     * 
     * @param id Id de type int, représente l'id de l'objet Tournée demandé.
     * @return Une instance de Tournée.
     */
    @Override
    public Tournee get(int id) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Tournee WHERE idTournee = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Vehicule vh = new VehiculeDAO(conn).get(rs.getInt("idVehicule"));

                Tournee tournee = new Tournee(id, rs.getTimestamp("horaireDebut"), rs.getTimestamp("horaireFin"),
                        rs.getFloat("poids"), rs.getString("libelle"), vh);

                // On charge la liste des Commandes
                for (Commande commande : new CommandeDAO(conn).getCommandesByTournee(vh.getProducteur(), tournee)) {
                    tournee.addCommande(commande);
                }

                return tournee;
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances de Tournée.
     * 
     * @return Une liste d'instances de Tournée.
     */
    @Override
    public ArrayList<Tournee> getAll() {
        ArrayList<Tournee> tournees = new ArrayList<>();

        CommandeDAO cmdDAO = new CommandeDAO(conn);
        try {
            rs = stmt.executeQuery("SELECT * FROM Tournee");

            VehiculeDAO vDAO = new VehiculeDAO(conn);

            while (rs.next()) {
                Vehicule vh = vDAO.get(rs.getInt("idVehicule"));

                Tournee tournee = new Tournee(
                        rs.getInt("idTournee"),
                        rs.getTimestamp("horaireDebut"),
                        rs.getTimestamp("horaireFin"),
                        rs.getFloat("poids"),
                        rs.getString("libelle"),
                        vh);

                // On charge la liste des Commandes
                for (Commande commande : cmdDAO.getCommandesByTournee(vh.getProducteur(), tournee)) {
                    tournee.addCommande(commande);
                }

                tournees.add(tournee);
            }

            return tournees;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute dans la base de données une instance de Tournée.
     * 
     * @param t L'instance Tournée de l'objet à ajouter.
     */
    @Override
    public void add(Tournee t) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Tournee VALUES (NULL, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setTimestamp(1, t.getHoraireDebut());
            pstmt.setTimestamp(2, t.getHoraireFin());
            pstmt.setFloat(3, t.getPoids());
            pstmt.setString(4, t.getLibelle());
            pstmt.setInt(5, t.getVehicule().getIdVehicule());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger) rs.getObject(1)).longValue();
                t.setIdTournee((int) id);

                CommandeDAO coDAO = new CommandeDAO(conn);
                for (Commande commande : t.getCommandes()) {
                    coDAO.add(commande);
                }
                // On met à jour la liste de Tournées dans Véhicule.
                t.getVehicule().addTournee(t);

                // On met à jour la liste de Tournées dans Producteur
                t.getVehicule().getProducteur().addTournee(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour dans la base de données une instance de Tournée.
     * 
     * @param t L'instance Tournée de l'objet à mettre à jour.
     */
    @Override
    public void update(Tournee t) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Tournee SET horaireDebut = ?, horaireFin = ?, poids = ?, libelle = ?, idVehicule = ? WHERE idTournee = ?");
            pstmt.setTimestamp(1, t.getHoraireDebut());
            pstmt.setTimestamp(2, t.getHoraireFin());
            pstmt.setFloat(3, t.getPoids());
            pstmt.setString(4, t.getLibelle());
            pstmt.setInt(5, t.getVehicule().getIdVehicule());
            pstmt.setInt(6, t.getIdTournee());
            pstmt.executeUpdate();

            CommandeDAO coDAO = new CommandeDAO(conn);

            for (Commande commande : t.getCommandes()) {
                coDAO.update(commande);
            }

            // On met à jour la liste de Tournées dans Véhicule.
            if (!t.getVehicule().getTournees().contains(t)) {
                t.getVehicule().addTournee(t);
            }

            // On met à jour la liste de Tournees dans Producteur.
            if (!t.getVehicule().getProducteur().getTournees().contains(t)) {
                t.getVehicule().addTournee(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance de Tournée associée à l'id.
     * 
     * @param t Tournée représentant la Tournée à supprimer.
     */
    @Override
    public void delete(Tournee t) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Tournee WHERE idTournee = ?");
            pstmt.setInt(1, t.getIdTournee());
            pstmt.executeUpdate();

            // On met à jour la liste de Tournées dans Véhicule.
            t.getVehicule().removeTournee(t);

            CommandeDAO commandeDAO = new CommandeDAO(conn);
            // On met les Tournées à null.
            t.getCommandes().forEach(c -> {
                c.setTournee(null);
                commandeDAO.update(c);
            });

            // On supprime de la liste de Tournées dans Producteur
            t.getVehicule().getProducteur().removeTournee(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retour une liste de Tournées associées à un Véhicle.
     *
     * @param vh Le Véhicule qui doit être associé à la Tournée
     * @return Un ArrayList<> contenant les Tournées associées au Véhicule
     * @throws SQLException
     */
    public ArrayList<Tournee> getTourneesByVehicule(Vehicule vh) throws SQLException {
        ArrayList<Tournee> tournees = new ArrayList<>();

        pstmt = conn.prepareStatement(
                "SELECT * FROM Tournee WHERE idVehicule = ?");
        pstmt.setInt(1, vh.getIdVehicule());
        rs = pstmt.executeQuery();

        while (rs.next()) {
            tournees.add(new Tournee(
                    rs.getInt("idTournee"),
                    rs.getTimestamp("horaireDebut"),
                    rs.getTimestamp("horaireFin"),
                    rs.getFloat("poids"),
                    rs.getString("libelle"),
                    vh));
        }

        return tournees;
    }

    /**
     * Retourne une liste de Tournées associées à une liste de Véhicules, pour
     * récupérer à partir de la liste de Véhicules d'un Producteur sa liste de
     * Tournées.
     *
     * @param vehicules Les Véhicules qui doivent être associés à la Tournée
     * @return ArrayList<Tournee> contenant les Tournées associées aux Véhicules
     * @throws SQLException
     */
    public ArrayList<Tournee> getTourneesByVehicules(ArrayList<Vehicule> vehicules)
            throws SQLException {
        ArrayList<Tournee> tournees = new ArrayList<>();

        for (Vehicule vehicule : vehicules) {
            tournees.addAll(getTourneesByVehicule(vehicule));
        }

        return tournees;
    }

    /**
     * Retourne si oui ou non un client est dans une tournée
     *
     * @param cl Le Client qui doit être associé à la Tournée
     * @return Un boolean attestant de la présence d'une client dans une tournée
     * @throws SQLException
     */
    public boolean clientEstDansTournee (Client cl) throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();

        pstmt = conn.prepareStatement(
                "SELECT idTournee FROM Tournee T JOIN Commande USING(idTournee) WHERE idClient = ?"
        );
        pstmt.setInt(1, cl.getIdClient());
        rs = pstmt.executeQuery();

        if (rs.first()) {
            return true;
        }

        return false;
    }


    /**
     * Constructeur de TourneeDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */
    public TourneeDAO(Connection conn) {
        super(conn);
    }
}