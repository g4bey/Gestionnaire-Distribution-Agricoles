package tests.dao;

import DAO.ClientDAO;
import DAO.CommandeDAO;
import DAO.ProducteurDAO;
import modele.*;
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

public class CommandeDAOTest {

    static private Connection conn;
    static private CommandeDAO commandeDAO;
    static private Commande COMMANDE_A;
    static private Commande COMMANDE_B;
    static private Producteur PRODUCTEUR_DEMO;
    static private Client CLIENT_DEMO;

    /**
     * Simule un TRUNCATE de la table table.
     * D'abord l'on supprime tous, puis l'on RESET l'auto_increment.
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
     * Instantiation de la connection avant TOUS les tests.
     * On injecte la connection dans le DAO commande.
     * <p>
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @BeforeAll
    public static void setup() throws SQLException, IOException, ClassNotFoundException {
        conn = DatabaseConnection.getInstance("testing");
        commandeDAO = new CommandeDAO(conn);
    }

    /**
     * Avant CHAQUE test, on réinitialise les objects metier Commande témoin.
     * Ensuite l'on vide la table Administrateur afin de pouvoir prédire les résultats.
     * <p>
     * @throws SQLException
     */
    @BeforeEach
    public void init() throws SQLException {
        truncateTable("Commande");
        truncateTable("Client");
        truncateTable("Producteur");

        PRODUCTEUR_DEMO = new Producteur("AA", "A", "A", "A", "A", "A");
        CLIENT_DEMO = new Client("A", "A", "A", "A");

        ClientDAO clientDAO = new ClientDAO(conn);
        ProducteurDAO producteurDAO = new ProducteurDAO(conn);
        clientDAO.add(CLIENT_DEMO);
        producteurDAO.add(PRODUCTEUR_DEMO);

        COMMANDE_A = new Commande(
                "Commande",
                32F,
                new Timestamp(300000),
                new Timestamp(500000),
                null,
                PRODUCTEUR_DEMO,
                CLIENT_DEMO
        );

        COMMANDE_B = new Commande(
                "Commande 2",
                32.4F,
                new Timestamp(900000),
                new Timestamp(1000000),
                null,
                PRODUCTEUR_DEMO,
                CLIENT_DEMO
        );
    }

    /**
     * Vérifions que l'ID de COMMANDE_A: 0 avant insertion,
     * est bien mise à jour suite à l'ajout en base.
     * <p>
     * Si l'ajout est effectif, cet ID sera 1.
     */
    @Test
    @DisplayName("Test de la methode add")
    public void addTest() {
        // Apres ajout, l'ID doit devenir 1 et non 0.
        assertEquals(0, COMMANDE_A.getIdCommande());
        commandeDAO.add(COMMANDE_A);
        assertEquals(1, COMMANDE_A.getIdCommande());
    }

    /**
     * L'on insert COMMANDE_A en base.
     * <p>
     * Ensuite le demande une commande ayant pour ID l'ID de COMMANDE_A.
     * <p>
     * Puis l'on vérifie que l'attribut producteur est bien le producteur dans COMMANDE_A.
     * De meme pour l'attribut client.
     * <p>
     * Enfin l'on assure qu'un ID inexistant renvoi bien null.
     */
    @Test
    @DisplayName("Test de la methode get")
    public void getTest() {
        commandeDAO.add(COMMANDE_A);

        // Demander un véhicule d'ID associé à la COMMANDE_A
        // Doit nécessairement aboutir à une égalité d'attributs
        Commande commandeRetour = commandeDAO.get(COMMANDE_A.getIdCommande());
        assertTrue(COMMANDE_A.equals(commandeRetour));

        // L'ID de la commande est associée à un producteur
        Producteur producteur = commandeRetour.getProducteur();
        assertEquals(producteur.getIdProducteur(), COMMANDE_A.getProducteur().getIdProducteur());

        // L'ID de la commande est associée à un client
        Client client = commandeRetour.getClient();
        assertTrue(client.equals(COMMANDE_A.getClient()));

        // Cet ID n'existe pas.
        Commande commandeNull = commandeDAO.get(5);
        assertNull(commandeNull);
    }

    /**
     * Insérons COMMANDE_A et COMMANDE_B en base.
     * Ces derniers auront comme ID 1 et 2.
     * <p>
     * L'on vérifie qu'il y a bien 2 elements dans le tableau retourné.
     * Puis l'on vérifie que ces elements ont les bons ID.
     */
    @Test
    @DisplayName("Test la methode getAll")
    public void getAllTest() {
        commandeDAO.add(COMMANDE_A);
        commandeDAO.add(COMMANDE_B);

        // L'on devrait avoir deux vehicules d'ID 1 et 2 dans le tableau.
        List<Commande> commandeListe = commandeDAO.getAll();
        assertEquals(2, commandeListe.size());
        assertEquals(1, commandeListe.get(0).getIdCommande());
        assertEquals(2, commandeListe.get(1).getIdCommande());
    }



}
