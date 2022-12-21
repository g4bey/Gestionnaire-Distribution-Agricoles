package tests.dao;

import DAO.ClientDAO;
import DAO.ProducteurDAO;
import DAO.TourneeDAO;
import DAO.VehiculeDAO;
import modele.Client;
import modele.Producteur;
import modele.Tournee;
import modele.Vehicule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests de la classe DAO.ClientDAOTest.
 */
public class ClientDAOTest {

    static private Connection conn;
    static private ClientDAO clientDAO;
    static private Client CLIENT_A;
    static private Client CLIENT_B;

    /**
     * Simule un TRUNCATE de la table table.
     * D'abord on supprime tout, puis on RESET l'auto_increment.
     * <p>
     * @throws SQLException
     * @param table la table que l'on souhaite TRUNCATE.
     */
    private static void truncateTable(String table) throws SQLException {
        Statement st = conn.createStatement();
        st.execute("DELETE FROM " + table);
        st.execute("ALTER TABLE " + table + " AUTO_INCREMENT=1");
    }

    /**
     * On crée une connection puis instancie les DAO.
     * On vide ensuite la table Client pour prédire les résultats.
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @BeforeAll
    public static void setup() throws SQLException, IOException, ClassNotFoundException {
        conn = DatabaseConnection.getInstance("testing");
        clientDAO = new ClientDAO(conn);

        truncateTable("Client");
    }

    /**
     * On crée deux clients.
     * On pense aussi à reset l'auto-increment de la table Client apres l'avoir vidée.
     * <p>
     * @throws SQLException
     */
    @BeforeEach
    public void init() throws SQLException {
        CLIENT_A = new Client("Yann", "27 rue du rhododendron", "", "");
        CLIENT_B = new Client ("Thierry", "97 avenue MySQL", "", "");

        truncateTable("Client");
    }

    /**
     * Vérifions que l'ID de CLIENT_A: 0 avant insertion,
     * est bien mise à jour suite à l'ajout en base.
     * <p>
     * Si l'ajout est effectif, cet ID sera 1.
     */
    @Test
    @DisplayName("Test de la methode add")
    public void addTest() {
        // Apres ajout, l'ID doit devenir 1 et non 0.
        assertEquals(0, CLIENT_A.getIdClient());
        clientDAO.add(CLIENT_A);
        assertEquals(1, CLIENT_A.getIdClient());
    }
}
