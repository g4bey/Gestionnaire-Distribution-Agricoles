package DAO;

import modele.Producteur;
import modele.Tournee;
import modele.Vehicule;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Représente le DAO des Véhicules.
 */

public class VehiculeDAO extends DAO<Vehicule> {

    /**
     * Récupère dans la base de données l'instance de Véhicule demandée.
     * 
     * @param id Id de type int, représente l'id de l'objet Véhicule demandé.
     * @return Une instance de Véhicule.
     */
    @Override
    public Vehicule get(int id) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Vehicule WHERE idVehicule = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ProducteurDAO pDAO = new ProducteurDAO(conn);

                Vehicule vehicule = new Vehicule(id,
                        rs.getString("numImmat"),
                        rs.getFloat("poidsMax"),
                        rs.getString("libelle"),
                        pDAO.get(rs.getInt("idProducteur"))
                ); // vehicule

                // On charge la liste de Tournées
                TourneeDAO tourneeDAO = new TourneeDAO(conn);
                for (Tournee tournee : tourneeDAO.getTourneesByVehicule(vehicule)) {
                    vehicule.addTournee(tournee);
                } // for

                return vehicule;
            } // if

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } // try/catch
    } // get

    /**
     * Retourne le Véhicule associé à une Tournée.
     *
     * @param prd       Le Producteur qui doit être associé à la Tournée
     * @param idTournee L'id de la Tournée.
     * @return Le Véhicule associé à une Tournée.
     * @throws SQLException
     */
    public Vehicule getVehiculeByIdTournee(int idTournee, Producteur prd) throws SQLException {
        pstmt = conn.prepareStatement(
                "SELECT idVehicule, numImmat, poidsMax, V.libelle FROM Vehicule V JOIN Tournee USING(idVehicule) WHERE idTournee = ?");
        pstmt.setInt(1, idTournee);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            return new Vehicule(
                    rs.getInt("idVehicule"),
                    rs.getString("numImmat"),
                    rs.getFloat("poidsMax"),
                    rs.getString("V.libelle"),
                    prd
            ); // Vehicule
        } // if

        return null;
    } // getVehiculeByIdTournee

    /**
     * Récupère dans la base de données toutes les instances de Véhicule.
     * 
     * @return Une liste d'instances de Véhicule.
     */
    @Override
    public ArrayList<Vehicule> getAll() {
        ArrayList<Vehicule> vehicules = new ArrayList<>();

        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        try {
            rs = stmt.executeQuery("SELECT * FROM Vehicule");

            ProducteurDAO pDAO = new ProducteurDAO(conn);

            while (rs.next()) {
                Vehicule vehicule = new Vehicule(
                        rs.getInt("idVehicule"),
                        rs.getString("numImmat"),
                        rs.getFloat("poidsMax"),
                        rs.getString("libelle"),
                        pDAO.get(rs.getInt("idProducteur"))
                ); // vehicule

                // On charge la liste de Tournées
                for (Tournee tournee : tourneeDAO.getTourneesByVehicule(vehicule)) {
                    vehicule.addTournee(tournee);
                } // for

                vehicules.add(vehicule);
            } // while

            return vehicules;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } // try/catch
    } // getAll

    /**
     * Retourne une liste de Véhicules associés à un Producteur.
     *
     * @param prd Le Producteur qui doit être associé au Véhicule.
     * @return vehicules - ArrayList<> contenant les véhicules associés au
     *         Producteur
     * @throws SQLException
     */
    public ArrayList<Vehicule> getVehiculesByProducteur(Producteur prd) throws SQLException {
        ArrayList<Vehicule> vehicules = new ArrayList<>();

        pstmt = conn.prepareStatement("SELECT * FROM Vehicule WHERE idProducteur = ?");
        pstmt.setInt(1, prd.getIdProducteur());
        rs = pstmt.executeQuery();

        while (rs.next()) {
            vehicules.add(new Vehicule(
                    rs.getInt("idVehicule"),
                    rs.getString("numImmat"),
                    rs.getFloat("poidsMax"),
                    rs.getString("libelle"),
                    prd
            ) // Vehicule
            ); // add
        } // while

        return vehicules;
    } // getVehiculesByProducteur

    /**
     * Ajoute dans la base de données une instance de Véhicule.
     * 
     * @param vh L'instance Véhicule de l'objet à ajouter.
     */
    @Override
    public void add(Vehicule vh) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Vehicule VALUES (NULL, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setFloat(1, vh.getPoidsMax());
            pstmt.setString(2, vh.getLibelle());
            pstmt.setString(3, vh.getNumImmat());
            pstmt.setInt(4, vh.getProducteur().getIdProducteur());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger) rs.getObject(1)).longValue();
                vh.setIdVehicule((int) id);
            } // if
            vh.getProducteur().addVehicule(vh);

        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // add

    /**
     * Met à jour dans la base de données une instance de Véhicule.
     * 
     * @param vh L'instance Véhicule de l'objet à mettre à jour.
     */
    @Override
    public void update(Vehicule vh) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Vehicule SET poidsMax = ?, libelle = ?, numImmat = ?, idProducteur = ? WHERE idVehicule = ?");
            pstmt.setFloat(1, vh.getPoidsMax());
            pstmt.setString(2, vh.getLibelle());
            pstmt.setString(3, vh.getNumImmat());
            pstmt.setInt(4, vh.getProducteur().getIdProducteur());
            pstmt.setInt(5, vh.getIdVehicule());

            pstmt.executeUpdate();

            if (!vh.getProducteur().getVehicules().contains(vh)) {
                vh.getProducteur().addVehicule(vh);
            } // if
        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // update

    /**
     * Supprime de la base de données l'instance de Véhicule associée à l'id.
     * 
     * @param vh Véhicule représentant le Véhicule à supprimer.
     */
    @Override
    public void delete(Vehicule vh) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Vehicule WHERE idVehicule = ?");
            pstmt.setInt(1, vh.getIdVehicule());

            pstmt.executeUpdate();
            vh.getTournees().forEach(tournee -> vh.setIdVehicule(0));

            vh.getProducteur().removeVehicule(vh); // Ajout pour tester
        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // delete

    /**
     * Constructeur de VehiculeDAO.
     * 
     * @param conn Une Connection représentant la connexion à la base de données.
     */
    public VehiculeDAO(Connection conn) {
        super(conn);
    } // constructeur

} // VehiculeDAO