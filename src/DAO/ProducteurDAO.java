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

/**
 * Représente le DAO des Producteurs.
 */
public class ProducteurDAO extends DAO<Producteur> {

    /**
     * Récupère dans la base de données l'instance de Producteur demandée.
     * 
     * @param id Id de type int, représente l'id de l'objet Producteur demandé.
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
                        rs.getString("mdpProd")
                ); // prd

                TourneeDAO tDAO = new TourneeDAO(conn);
                CommandeDAO cmdDAO = new CommandeDAO(conn);

                // On charge la liste de Véhicules
                ArrayList<Vehicule> vehicules = new VehiculeDAO(conn).getVehiculesByProducteur(prd);
                for (Vehicule vehicule : vehicules) {
                    prd.addVehicule(vehicule);

                    // On remplit le tableau de Tournées dans Véhicule
                    for (Tournee tournee : tDAO.getTourneesByVehicule(vehicule)) {
                        prd.addTournee(tournee);
                        vehicule.addTournee(tournee);

                        // On charge les Commandes de la Tournée
                        for (Commande commande : cmdDAO.getCommandesByTournee(prd, tournee)) {
                            prd.addCommande(commande);
                            tournee.addCommande(commande);
                        } // for
                    } // for
                } // for

                for (Commande commande : cmdDAO.getCommandesByProducteurWithoutTournee(prd)) {
                    prd.addCommande(commande);
                } // for

                return prd;
            } // if

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } // try/catch
    } // get

    /**
     * Récupère dans la base de données l'instance de Producteur demandée.
     * 
     * @param siret SIRET de type String, représente le SIRET de l'objet Producteur
     *              demandé.
     * @return Une instance de Producteur.
     */
    public Producteur get(String siret) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Producteur WHERE siret = ?");
            pstmt.setString(1, siret);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Producteur prd = new Producteur(rs.getInt("idProducteur"), siret, rs.getString("proprietaire"),
                        rs.getString("adresseProd"),
                        rs.getString("numTelProd"),
                        rs.getString("gpsProd"),
                        rs.getString("mdpProd")
                ); // prd

                TourneeDAO tDAO = new TourneeDAO(conn);
                CommandeDAO cmdDAO = new CommandeDAO(conn);

                // On charge la liste de Véhicules
                ArrayList<Vehicule> vehicules = new VehiculeDAO(conn).getVehiculesByProducteur(prd);
                for (Vehicule vehicule : vehicules) {
                    prd.addVehicule(vehicule);

                    // On remplit le tableau de Tournées dans Véhicule
                    for (Tournee tournee : tDAO.getTourneesByVehicule(vehicule)) {
                        prd.addTournee(tournee);
                        vehicule.addTournee(tournee);

                        // On charge les Commandes de la Tournée
                        for (Commande commande : cmdDAO.getCommandesByTournee(prd, tournee)) {
                            prd.addCommande(commande);
                            tournee.addCommande(commande);
                        } // for
                    } // for
                } // for

                for (Commande commande : cmdDAO.getCommandesByProducteurWithoutTournee(prd)) {
                    prd.addCommande(commande);
                } // for

                return prd;
            } // if

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } // try/catch
    } // get

    /**
     * Récupère dans la base de données toutes les instances de Producteur.
     * 
     * @return Une liste d'instances de Producteur.
     */
    @Override
    public ArrayList<Producteur> getAll() {
        ArrayList<Producteur> producteurs = new ArrayList<>();

        VehiculeDAO vDAO = new VehiculeDAO(conn);
        TourneeDAO tDAO = new TourneeDAO(conn);
        CommandeDAO cmdDAO = new CommandeDAO(conn);

        try {
            rs = stmt.executeQuery("SELECT * FROM Producteur");

            while (rs.next()) {
                Producteur prd = new Producteur(
                        rs.getInt("idProducteur"),
                        rs.getString("siret"),
                        rs.getString("proprietaire"),
                        rs.getString("adresseProd"),
                        rs.getString("numTelProd"),
                        rs.getString("gpsProd"),
                        rs.getString("mdpProd")
                ); // prd

                // On charge la liste de Véhicules
                ArrayList<Vehicule> vehicules = vDAO.getVehiculesByProducteur(prd);
                for (Vehicule vehicule : vehicules) {
                    prd.addVehicule(vehicule);

                    // On remplit le tableau de Tournées dans Véhicule
                    for (Tournee tournee : tDAO.getTourneesByVehicule(vehicule)) {
                        prd.addTournee(tournee);
                        vehicule.addTournee(tournee);

                        // On charge les Commandes de la Tournée
                        for (Commande commande : cmdDAO.getCommandesByTournee(prd, tournee)) {
                            prd.addCommande(commande);
                            tournee.addCommande(commande);
                        } // for
                    } // for
                } // for

                // Les Commandes qui ne sont pas associées à une Tournée
                for (Commande commande : cmdDAO.getCommandesByProducteurWithoutTournee(prd)) {
                    prd.addCommande(commande);
                } // for

                producteurs.add(prd);
            } // while
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } // catch

        return producteurs;
    } // getAll

    /**
     * Ajoute dans la base de données une instance de Producteur.
     * 
     * @param prd l'instance Producteur de l'objet à ajouter.
     */
    @Override
    public void add(Producteur prd) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Producteur VALUES (NULL, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, prd.getProprietaire());
            pstmt.setString(2, prd.getAdresseProd());
            pstmt.setString(3, prd.getNumTelProd());
            pstmt.setString(4, prd.getGpsProd());
            pstmt.setString(5, prd.getMdpProd());
            pstmt.setString(6, prd.getSiret());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger) rs.getObject(1)).longValue();
                prd.setIdProducteur((int) id);
            } // if

        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // add

    /**
     * Met à jour dans la base de données une instance de Producteur.
     * 
     * @param prd L'instance Producteur de l'objet à mettre à jour.
     */
    @Override
    public void update(Producteur prd) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Producteur SET siret = ?, proprietaire = ?, adresseProd = ?, numTelProd = ?, gpsProd = ?, mdpProd = ? WHERE idProducteur = ?");
            pstmt.setString(1, prd.getSiret());
            pstmt.setString(2, prd.getProprietaire());
            pstmt.setString(3, prd.getAdresseProd());
            pstmt.setString(4, prd.getNumTelProd());
            pstmt.setString(5, prd.getGpsProd());
            pstmt.setString(6, prd.getMdpProd());
            pstmt.setInt(7, prd.getIdProducteur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // update

    /**
     * Supprime de la base de données une instance de Producteur.
     * 
     * @param prd Producteur représentant le Producteur à supprimer.
     */
    @Override
    public void delete(Producteur prd) {
        try {
            TourneeDAO tDAO = new TourneeDAO(conn);

            prd.getTournees().stream().forEach(tDAO::delete);

            pstmt = conn.prepareStatement("DELETE FROM Producteur WHERE idProducteur = ?");
            pstmt.setInt(1, prd.getIdProducteur());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // delete

    /**
     * Constructeur de ProducteurDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */
    public ProducteurDAO(Connection conn) {
        super(conn);
    } // constructeur

} // ProducteurDAO