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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de la classe DAO.ClientDAO.
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

    /**
     * On insère CLIENT_A en base.
     * <p>
     * Ensuite, on demande un client ayant pour ID l'iD de CLIENT_A.
     * <p>
     * Enfin, on s'assure qu'un ID inexistant renvoie bien null.
     */
    @Test
    @DisplayName("Test de la methode get")
    public void getTest() {
        clientDAO.add(CLIENT_A);

        // Demander un client d'ID associé à CLIENT_A
        // Doit nécessairement aboutir à une égalité d'attributs
        Client clientRetour = clientDAO.get(CLIENT_A.getIdClient());
        assertTrue(CLIENT_A.equals(clientRetour));

        // Cet ID n'existe pas.
        Client clientNull = clientDAO.get(5);
        assertNull(clientNull);
    }

    /**
     * Insérons CLIENT_A et CLIENT_B en base.
     * Ces derniers auront comme ID 1 et 2.
     * <p>
     * L'on vérifie qu'il y a bien 2 elements dans le tableau retourné.
     * Puis l'on vérifie que ces éléments ont les bons ID.
     */
    @Test
    @DisplayName("Test la methode getAll")
    public void getAllTest() {
        clientDAO.add(CLIENT_A);
        clientDAO.add(CLIENT_B);

        // L'on devrait avoir deux clients d'ID 1 et 2 dans le tableau.
        List<Client> clientList = clientDAO.getAll();
        assertEquals(2, clientList.size());
        assertEquals(1, clientList.get(0).getIdClient());
        assertEquals(2, clientList.get(1).getIdClient());
    }

    /**
     * On insère le CLIENT_A en base.
     * Ensuite, on récupère son ID.
     * <p>
     * Enfin, on supprime le client correspondant à cet ID.
     * Puis, on vérifie que demander cet ID renvoie bien null.
     */
    @Test
    @DisplayName("Test la methode delete")
    public void deleteTest() {
        clientDAO.add(CLIENT_A);
        int idClient = CLIENT_A.getIdClient();

        // Apres suppression, l'ID devrait être null.
        clientDAO.delete(CLIENT_A);
        assertNull(clientDAO.get(idClient));
    }

    /**
     * On ajoute CLIENT_A en base et modifie tous ses attributs,
     * puis on update ce client.
     * <p>
     * Enfin, on crée un autre objet avec le même ID pour s'assurer
     * que les attributs sont égaux.
     */
    @Test
    @DisplayName("Test la methode update")
    public void updateTest() {
       // On ajoute puis met à jour la TOURNEE_A
        // On change aussi le producteur
        clientDAO.add(CLIENT_A);
        CLIENT_A.setNomClient("Fabrice");
        CLIENT_A.setAdresseClient("8 allée des margin:auto");
        CLIENT_A.setGpsClient("423");
        CLIENT_A.setNumTelClient("0646219187");
        clientDAO.update(CLIENT_A);

        // On crée un autre objet de même ID pour s'assurer que les attributs
        // sont identiques. Cela induit qu'ils sont modifiés en BDD.
        Client clientRetour = clientDAO.get(CLIENT_A.getIdClient());
        assertTrue(clientRetour.equals(CLIENT_A));
    }


}
