package DAO;

import modele.Client;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Représente le DAO des Clients.
 */
public class ClientDAO extends DAO<Client> {

    /**
     * Récupère dans la base de données l'instance de Client demandée.
     * 
     * @param id Id de type int, représente l'id de l'objet Client demandé.
     * @return Une instance de Client.
     */
    @Override
    public Client get(int id) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Client WHERE idClient = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Client(id,
                        rs.getString("nomClient"),
                        rs.getString("adresseClient"),
                        rs.getString("gpsClient"),
                        rs.getString("numTelClient")
                ); // Client
            } // if

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } // try/catch
    } // get

    /**
     * Récupère dans la base de données toutes les instances de Client.
     * 
     * @return Une liste d'instances de Client.
     */
    @Override
    public ArrayList<Client> getAll() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Client");

            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("idClient"),
                        rs.getString("nomClient"),
                        rs.getString("adresseClient"),
                        rs.getString("gpsClient"),
                        rs.getString("numTelClient")
                        ) // Client
                ); // add
            } // while

            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } // try/catch
    } // getAll

    /**
     * Ajoute dans la base de données une instance de Client.
     * 
     * @param clt L'instance Client de l'objet à ajouter.
     */
    @Override
    public void add(Client clt) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Client VALUES (NULL, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, clt.getNomClient());
            pstmt.setString(2, clt.getAdresseClient());
            pstmt.setString(3, clt.getGpsClient());
            pstmt.setString(4, clt.getNumTelClient());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger) rs.getObject(1)).longValue();
                clt.setIdClient((int) id);
            } // if

        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // add

    /**
     * Met à jour dans la base de données une instance de Client.
     * 
     * @param clt L'instance Client de l'objet à mettre à jour.
     */
    @Override
    public void update(Client clt) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Client SET nomClient = ?, adresseClient = ?, gpsClient = ?, numTelClient = ? WHERE idClient = ?");
            pstmt.setString(1, clt.getNomClient());
            pstmt.setString(2, clt.getAdresseClient());
            pstmt.setString(3, clt.getGpsClient());
            pstmt.setString(4, clt.getNumTelClient());
            pstmt.setInt(5, clt.getIdClient());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // update

    /**
     * Supprime de la base de données l'instance de Client associée à l'id.
     * 
     * @param clt Client représentant le Client à supprimer.
     */
    @Override
    public void delete(Client clt) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Client WHERE idClient = ?");
            pstmt.setInt(1, clt.getIdClient());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } // try/catch
    } // delete

    /**
     * Constructeur de ClientDAO.
     *
     * @param conn Une Connection représentant la connexion à la base de données.
     */
    public ClientDAO(Connection conn) {
        super(conn);
    } // constructeur

} // ClientDAO