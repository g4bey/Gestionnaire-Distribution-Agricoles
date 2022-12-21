package DAO;

import modele.Commande;
import modele.Tournee;
import modele.Vehicule;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Représente le DAO des tournées.
 */
public class TourneeDAO extends DAO<Tournee> {
    /**
     * Récupère dans la base de données l'instance de Tournee demandée.
     * 
     * @param id id de type int, représente l'id de l'objet Tournee demandé.
     * @return Une instance de Tournee.
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
                        rs.getFloat("poids"), rs.getString("libelle"),
                        vh);

                // On charge la liste des commandes
                ArrayList<Commande> commandes = new CommandeDAO(conn).getCommandesByTournee(vh.getProducteur(),
                        tournee);
                for (Commande commande : commandes) {
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
     * Récupère dans la base de données toutes les instances de Tournee.
     * 
     * @return Une liste d'instances de Tournee.
     */
    @Override
    public ArrayList<Tournee> getAll() {
        ArrayList<Tournee> tournees = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Tournee");

            VehiculeDAO vDAO = new VehiculeDAO(conn);

            while (rs.next()) {
                tournees.add(new Tournee(
                        rs.getInt("idTournee"),
                        rs.getTimestamp("horaireDebut"),
                        rs.getTimestamp("horaireFin"),
                        rs.getFloat("poids"),
                        rs.getString("libelle"),
                        vDAO.get(rs.getInt("idVehicule"))));
            }

            return tournees;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute dans la base de données une instance de Tournee.
     * 
     * @param t l'instance Tournee de l'objet à ajouter.
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

                // On met à jour la liste de tournees dans vehicule.
                t.getVehicule().addTournee(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour dans la base de données une instance de Tournee.
     * 
     * @param t l'instance Tournee de l'objet à mettre à jour.
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

            // On met à jour la liste de tournees dans vehicule.
            if (!t.getVehicule().getTournees().contains(t)) {
                t.getVehicule().addTournee(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance de Tournee associée à l'id.
     * 
     * @param t Tournee représentant la Tournee à supprimer.
     */
    @Override
    public void delete(Tournee t) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Tournee WHERE idTournee = ?");
            pstmt.setInt(1, t.getIdTournee());
            pstmt.executeUpdate();

            // On met à jour la liste de tournees dans vehicule.
            t.getVehicule().removeTournee(t);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retour une liste de tournée associée àa un Vehicle.
     *
     * @param t le Vehicule qui doit etre associe à la tournée
     * @return tournees un ArrayList<> contenant les tournées associés au Vehicle
     * @throws SQLException
     */
    public ArrayList<Tournee> getTourneeByVehicule(Vehicule t) throws SQLException {
        ArrayList<Tournee> tournees = new ArrayList<>();

        pstmt = conn.prepareStatement(
                "SELECT * FROM Tournee WHERE idVehicule = ?");
        pstmt.setInt(1, t.getIdVehicule());
        rs = pstmt.executeQuery();

        while (rs.next()) {
            tournees.add(new Tournee(
                    rs.getInt("idTournee"),
                    rs.getTimestamp("horaireDebut"),
                    rs.getTimestamp("horaireFin"),
                    rs.getFloat("poids"),
                    rs.getString("libelle"),
                    t));
        }

        return tournees;
    }

    /**
     * Retourne une liste de tournée associée à une liste de véhicules, pour
     * récupérer à partir
     * de la liste de véhicules d'un producteur sa liste de tournées.
     *
     * @param vehicules les véhicules qui doivent etre associés à la tournée
     * @return ArrayList<Tournee> contenant les tournées associées aux véhicules
     * @throws SQLException
     */
    public ArrayList<Tournee> getTourneeByVehicules(ArrayList<Vehicule> vehicules)
            throws SQLException {
        ArrayList<Tournee> tournees = new ArrayList<>();

        for (Vehicule vehicule : vehicules) {
            pstmt = conn.prepareStatement("SELECT * FROM Tournee WHERE idVehicule = ?");
            pstmt.setInt(1, vehicule.getIdVehicule());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                tournees.add(new Tournee(
                        rs.getInt("idTournee"),
                        rs.getTimestamp("horaireDebut"),
                        rs.getTimestamp("horaireFin"),
                        rs.getFloat("poids"),
                        rs.getString("libelle"),
                        vehicule));
            }
        }

        return tournees;
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