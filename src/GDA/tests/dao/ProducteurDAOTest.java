package GDA.tests.dao;

import org.junit.jupiter.api.*;

import GDA.DAO.ClientDAO;
import GDA.DAO.CommandeDAO;
import GDA.DAO.ProducteurDAO;
import GDA.DAO.TourneeDAO;
import GDA.DAO.VehiculeDAO;
import GDA.modele.Client;
import GDA.modele.Commande;
import GDA.modele.Producteur;
import GDA.modele.Tournee;
import GDA.modele.Vehicule;
import GDA.utility.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de la classe src.DAO.ProducteurDAO.
 */
public class ProducteurDAOTest {

    static private Connection conn;
    static private ProducteurDAO prodDAO;
    static private Producteur PRODUCTEUR_A;
    static private Producteur PRODUCTEUR_B;
    static private Vehicule VEHICULE_A;

    /**
     * Simule un TRUNCATE de la table table.
     * D'abord on supprime tout, puis on RESET l'auto_increment.
     * <p>
     * 
     * @throws SQLException
     * @param table Le nom de la table que l'on souhaite TRUNCATE.
     */
    private static void truncateTable(String table) throws SQLException {
        Statement st = conn.createStatement();
        st.execute("DELETE FROM " + table);
        st.execute("ALTER TABLE " + table + " AUTO_INCREMENT=1");
    }

    /**
     * On crée une Connection, puis instancie les src.DAO.
     * On vide ensuite toutes les tables pour prédire les résultats.
     * <p>
     * Avant d'exécuter les src.tests, on crée un Producteur puis on l'insère en base.
     * Évidemment, l'auto_increment.
     * <p>
     * Enfin, on ajoute un Producteur en base.
     * 
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @BeforeAll
    public static void setup() throws SQLException, IOException, ClassNotFoundException {
        conn = DatabaseConnection.getInstance("testing");
        prodDAO = new ProducteurDAO(conn);

        truncateTable("Commande");
        truncateTable("Client");
        truncateTable("Tournee");
        truncateTable("Vehicule");
        truncateTable("Producteur");
    }

    /**
     * On crée deux Producteurs puis l'on les insère en base.
     * On pense aussi à reset l'auto-increment de la table Producteur après l'avoir vidée.
     * <p>
     * 
     * @throws SQLException
     */
    @BeforeEach
    public void init() throws SQLException {
        PRODUCTEUR_A = new Producteur("73282932000074", "Jean", "267 rue de Grandmont", "0674384726",
                "47.37760650.8082932", "hashMDP1");
        PRODUCTEUR_B = new Producteur("73282932000075", "Patrick", "13 rue du Poulpe", "0612934563",
                "48.37760650.8082932", "hashMDP2");

        VEHICULE_A = new Vehicule("AA229AA", 32F, "véhicule principal", PRODUCTEUR_A);

        truncateTable("Producteur");
        truncateTable("Vehicule");

        PRODUCTEUR_A.addVehicule(VEHICULE_A);
    }

    /**
     * Vérifions que l'ID de PRODUCTEUR_A : 0 avant insertion,
     * est bien mis à jour suite à l'ajout en base.
     * <p>
     * Si l'ajout est effectif, cet ID sera 1.
     */
    @Test
    @DisplayName("Test de la méthode add")
    public void addTest() {
        // Après ajout, l'ID doit devenir 1 et non 0.
        assertEquals(0, PRODUCTEUR_A.getIdProducteur());
        prodDAO.add(PRODUCTEUR_A);
        assertEquals(1, PRODUCTEUR_A.getIdProducteur());
    }

    /**
     * On insère PRODUCTEUR_A en base.
     * <p>
     * Ensuite, on demande un Producteur ayant pour ID l'iD du PRODUCTEUR_A.
     * <p>
     * Puis on vérifie que l'attribut producteur est bien le Producteur dans
     * PRODUCTEUR_A.
     * <p>
     * Enfin l'on assure qu'un ID inexistant renvoi bien null.
     */
    @Test
    @DisplayName("Test de la méthode get")
    public void getTest() {
        prodDAO.add(PRODUCTEUR_A);

        // Demander un Producteur d'ID associé au PRODUCTEUR_A
        // Doit nécessairement aboutir à une égalité d'attributs
        Producteur ProducteurRetour = prodDAO.get(PRODUCTEUR_A.getIdProducteur());
        assertTrue(PRODUCTEUR_A.equals(ProducteurRetour));

        // Cet ID n'existe pas.
        Producteur ProducteurNull = prodDAO.get(5);
        assertNull(ProducteurNull);
    }

    /**
     * Insérons PRODUCTEUR_A et PRODUCTEUR_B en base.
     * Ces derniers auront comme ID 1 et 2.
     * <p>
     * On vérifie qu'il y a bien 2 éléments dans le tableau retourné.
     * Puis on vérifie que ces éléments ont les bons ID.
     */
    @Test
    @DisplayName("Test la méthode getAll")
    public void getAllTest() {
        prodDAO.add(PRODUCTEUR_A);
        prodDAO.add(PRODUCTEUR_B);

        // On devrait avoir deux Producteurs d'ID 1 et 2 dans le tableau.
        ArrayList<Producteur> ProducteurList = prodDAO.getAll();
        assertEquals(2, ProducteurList.size());
        assertEquals(1, ProducteurList.get(0).getIdProducteur());
        assertEquals(2, ProducteurList.get(1).getIdProducteur());
    }

    /**
     * On insère le PRODUCTEUR_A en base.
     * Ensuite, on récupère son ID.
     * <p>
     * Enfin, on supprime le Producteur correspondant à cet ID.
     * Puis on vérifie que demander cet ID renvoie bien null.
     */
    @Test
    @DisplayName("Test la méthode delete")
    public void deleteTest() {
        prodDAO.add(PRODUCTEUR_A);
        int idProducteur = PRODUCTEUR_A.getIdProducteur();

        // Après suppression, l'ID devrait être null.
        prodDAO.delete(PRODUCTEUR_A);
        assertNull(prodDAO.get(idProducteur));
    }

    /**
     * On commence par créer et ajouter un nouveau Producteur en base.
     * Ensuite, on ajoute PRODUCTEUR_A en base pour modifier tous ses attributs,
     * puis on update ce Producteur.
     * <p>
     * Enfin, on crée un autre objet avec le même ID pour s'assurer
     * que les attributs sont égaux.
     */
    @Test
    @DisplayName("Test la méthode update")
    public void updateTest() {
        PRODUCTEUR_A.addVehicule(VEHICULE_A);

        // On ajoute puis met à jour le PRODUCTEUR_A
        // on change aussi le Producteur
        prodDAO.add(PRODUCTEUR_A);
        PRODUCTEUR_A.setAdresseProd("200 rue de Grandmont");
        PRODUCTEUR_A.setGpsProd("46.37760650.8082932");
        PRODUCTEUR_A.setMdpProd("hashMDP3");
        PRODUCTEUR_A.setNumTelProd("0638462846");
        PRODUCTEUR_A.setProprietaire("Pierre");
        PRODUCTEUR_A.setSiret("73282932000076");
        prodDAO.update(PRODUCTEUR_A);

        // On crée un autre objet de même ID pour s'assurer que les attributs
        // sont identiques. Cela induit qu'ils sont modifiés en BDD.
        Producteur ProducteurRetour = prodDAO.get(PRODUCTEUR_A.getIdProducteur());
        assertTrue(ProducteurRetour.equals(PRODUCTEUR_A));
    }

    /**
     * On s'assure que les références vers d'autres objets sont
     * bien mises à jour.
     */
    @Test
    @DisplayName("Test de la propagation")
    public void propagationTest() {
        prodDAO.add(PRODUCTEUR_A);

        // On ajoute un Client en base
        ClientDAO clientDAO = new ClientDAO(conn);
        Client client = new Client("Jean", "120 Rue", "123,987", "229938");
        clientDAO.add(client);

        // On ajoute une Commande en base
        CommandeDAO commandeDAO = new CommandeDAO(conn);
        Commande commande = new Commande(
                "Com", 20F, new Timestamp(10000), new Timestamp(20000),
                PRODUCTEUR_A, client);
        commandeDAO.add(commande);

        // On vérifie que le Producteur possède bien cette Commande.
        assertTrue(PRODUCTEUR_A.getCommandes().get(0).equals(commande));

        // On insère une autre commande pour le Producteur.
        Commande commandeBis = new Commande(
                "Com2", 20F, new Timestamp(100000), new Timestamp(200000),
                PRODUCTEUR_A, client);
        PRODUCTEUR_A.addCommande(commandeBis);
        prodDAO.update(PRODUCTEUR_A);

        // On constate que c'est bien dans l'objet.
        assertTrue(PRODUCTEUR_A.getCommandes().get(1).equals(commandeBis));

        // On crée un nouveau Véhicule
        Vehicule vehicule = new Vehicule("9897-FF", 90F, "Vehi", PRODUCTEUR_A);
        VehiculeDAO vehiDAO = new VehiculeDAO(conn);
        vehiDAO.add(vehicule);

        // On ajoute le Véhicule au Producteur.
        PRODUCTEUR_A.addVehicule(vehicule);
        prodDAO.update(PRODUCTEUR_A);

        // On s'assure qu'il possède bien ce Véhicule.
        assertTrue(PRODUCTEUR_A.getVehicules().contains(vehicule));

        // On crée une nouvelle Tournée
        Tournee tournee = new Tournee(new Timestamp(100000), new Timestamp(100000), 30F, "Tournee", vehicule);
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        tourneeDAO.add(tournee);

        // On vérifie que le Producteur a bien cette Tournée.
        assertTrue(PRODUCTEUR_A.getTournees().contains(tournee));

        // On change de Véhicule pour la Tournée.
        vehiDAO.add(VEHICULE_A);
        tournee.setVehicule(VEHICULE_A);
        tourneeDAO.update(tournee);

        // On constate que c'est update dans le tableau de Tournée.
        assertTrue(PRODUCTEUR_A.getTournees().get(0).getVehicule().equals(VEHICULE_A));

        // On supprime la Tournée et constate qu'elle n'est plus
        // dans le tableau de Tournée du Producteur.
        tourneeDAO.delete(tournee);
        assertFalse(PRODUCTEUR_A.getTournees().contains(tournee));
    }

    /**
     * Fermeture de la Connection apres les src.tests.
     *
     * @throws SQLException
     */
    @AfterAll
    public static void tearDown() throws SQLException {
        DatabaseConnection.close("testing");
    }
}
