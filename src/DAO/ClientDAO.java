package DAO;

import modele.Client;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente le DAO des clients.
 */
public class ClientDAO extends DAO<Client> {
    /**
     * Récupère dans la base de données l'instance de Client demandée.
     * 
     * @param id id de type int, représente l'id de l'objet Client demandé.
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
                    );
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Récupère dans la base de données toutes les instances de Client.
     * 
     * @return Une liste d'instances de Client.
     */
    @Override
    public List<Client> getAll() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            rs = stmt.executeQuery("SELECT * FROM Client");

            while (rs.next()) {
                clients.add(new Client(
                    rs.getInt("idClient"), 
                    rs.getString("nomClient"), 
                    rs.getString("adresseClient"),
                    rs.getString("gpsClient"), 
                    rs.getString("numTelClient"))
                );
             }

            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ajoute dans la base de données une instance de Client.
     * 
     * @param t l'instance Client de l'objet à ajouter.
     */
    @Override
    public void add(Client t) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO Client VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, t.getNomClient());
            pstmt.setString(2, t.getAdresseClient());
            pstmt.setString(3, t.getGpsClient());
            pstmt.setString(4, t.getNumTelClient());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long id = ((BigInteger)rs.getObject(1)).longValue();
                t.setIdClient((int)id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour dans la base de données une instance de Client.
     * 
     * @param t l'instance Client de l'objet à mettre à jour.
     */
    @Override
    public void update(Client t) {
        try {
            pstmt = conn.prepareStatement(
                    "UPDATE Client SET nomClient = ?, adresseClient = ?, gpsClient = ?, numTelClient = ? WHERE idClient = ?");
            pstmt.setString(1, t.getNomClient());
            pstmt.setString(2, t.getAdresseClient());
            pstmt.setString(3, t.getGpsClient());
            pstmt.setString(4, t.getNumTelClient());
            pstmt.setInt(5, t.getIdClient());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprime de la base de données l'instance de Client associée à l'id.
     * 
     * @param t Client représentant le Client à supprimer.
     */
    @Override
    public void delete(Client t) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM Client WHERE idClient = ?");
            pstmt.setInt(1, t.getIdClient());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renvoie un client par son ID.
     *
     * @param idClient int l'id du client
     * @return Client le client associé a l'ID fourni.
     */
    public Client getClientById(int idClient) {
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Client WHERE idClient = ?");
            pstmt.setInt(1, idClient);
            pstmt.executeQuery();

            if (rs.first()) {
                return new Client(
                        rs.getInt("idClient"),
                        rs.getString("nomClient"),
                        rs.getString("adresseClient"),
                        rs.getString("gpsClient"),
                        rs.getString("numTelClient")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Constructeur de ClientDAO.
     *
     * @param conn Une Connection représentant la connexion à la base de données.
     */
    public ClientDAO(Connection conn) {
        super(conn);
    }
}