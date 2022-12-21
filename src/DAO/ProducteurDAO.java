package DAO;

import modele.Commande;
import modele.Producteur;
import modele.Tournee;
import modele.Vehicule;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
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
     * @return Une instance de Producteur.
     */
    @Override
    public Producteur get(int id) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Producteur WHERE idProducteur = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Producteur prd = new Producteur(id, rs.getString("siret"), rs.getString("proprietaire"),
                        rs.getString("adresseProd"),
                        rs.getString("numTelProd"),
                        rs.getString("gpsProd"),
                        rs.getString("mdpProd"));

                // On charge la liste de vehicule
                ArrayList<Vehicule> vehicules = new VehiculeDAO(conn).getVehiculeByProducteur(prd);
                for (Vehicule vehicule : vehicules) {
                    prd.addVehicule(vehicule);
                }

                // On charge la liste de tournée
                ArrayList<Tournee> tournees = new TourneeDAO(conn).getTourneeByVehicules(vehicules);
                for (Tournee tournee : tournees) {
                    prd.addTournee(tournee);
                }

                // On charge la liste de commande
                for (Commande commande : new CommandeDAO(conn).getCommandesByProducteurTournees(prd, tournees)) {
                    prd.addCommande(commande);
                }

                return prd;
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances de Producteur.
     * 
     * @return Une liste d'instances de Producteur.
     */
    @Override
    public List<Producteur> getAll() {
        ArrayList<Producteur> producteurs = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Producteur");

            while (rs.next()) {
                producteurs.add(
                        new Producteur(
                                rs.getInt("idProducteur"),
                                rs.getString("siret"),
                                rs.getString("proprietaire"),
                                rs.getString("adresseProd"),
                                rs.getString("numTelProd"),
                                rs.getString("gpsProd"),
                                rs.getString("mdpProd")));
            }

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
            pstmt = conn.prepareStatement("INSERT INTO Producteur VALUES (NULL, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, t.getProprietaire());
            pstmt.setString(2, t.getAdresseProd());
            pstmt.setString(3, t.getNumTelProd());
            pstmt.setString(4, t.getGpsProd());
            pstmt.setString(5, t.getMdpProd());
            pstmt.setString(6, t.getSiret());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger) rs.getObject(1)).longValue();
                t.setIdProducteur((int) id);
            }

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
                    "UPDATE Producteur SET siret = ?, proprietaire = ?, adresseProd = ?, numTelProd = ?, gpsProd = ?, mdpProd = ? WHERE idProducteur = ?");
            pstmt.setString(1, t.getSiret());
            pstmt.setString(2, t.getProprietaire());
            pstmt.setString(3, t.getAdresseProd());
            pstmt.setString(4, t.getNumTelProd());
            pstmt.setString(5, t.getGpsProd());
            pstmt.setString(6, t.getMdpProd());
            pstmt.setInt(7, t.getIdProducteur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données une instance de Producteur.
     * 
     * @param prd Producteur représentant le Producteur à supprimer.
     */
    @Override
    public void delete(Producteur prd) {
        try {
            TourneeDAO tDAO = new TourneeDAO(conn);
            prd.getCommandes().stream().map(t -> t.getTournee()).distinct().forEach(t -> tDAO.delete(t));

            pstmt = conn.prepareStatement("DELETE FROM Producteur WHERE idProducteur = ?");
            pstmt.setInt(1, prd.getIdProducteur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renvoie un producteur par son ID.
     *
     * @param idProducteur int l'id du producteur
     * @return Producteur le Producteur associé a l'ID fourni.
     */
    public Producteur getProducteurById(int idProducteur) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Producteur WHERE idProducteur = ?");
            pstmt.setInt(1, idProducteur);
            pstmt.executeQuery();

            if (rs.first()) {
                return new Producteur(
                        rs.getInt("idProducteur"),
                        rs.getString("siret"),
                        rs.getString("proprietaire"),
                        rs.getString("adresseProd"),
                        rs.getString("numTelProd"),
                        rs.getString("gpsProd"),
                        rs.getString("mdpProd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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