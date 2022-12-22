package tests.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import DAO.ClientDAO;
import DAO.CommandeDAO;
import DAO.ProducteurDAO;
import DAO.TourneeDAO;
import DAO.VehiculeDAO;
import modele.Client;
import modele.Commande;
import modele.Producteur;
import modele.Tournee;
import modele.Vehicule;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de la classe DAO.prodDAO.
 */
public class ProducteurDAOTest {

    static private Connection conn;
    static private ProducteurDAO prodDAO;
    static private Producteur PRODUCTEUR_A;
    static private Producteur PRODUCTEUR_B;
    static private Vehicule VEHICULE_A;

    /**
     * Simule un TRUNCATE de la table table.
     * D'abord l'on supprime tous, puis l'on RESET l'auto_increment.
     * <p>
     * 
     * @throws SQLException
     * @param table la table que l'on souhaite TRUNCATE.
     */
    private static void truncateTable(String table) throws SQLException {
        Statement st = conn.createStatement();
        st.execute("DELETE FROM " + table);
        st.execute("ALTER TABLE " + table + " AUTO_INCREMENT=1");
    }

    /**
     * On créer une connection, puis l'instancie les DAO.
     * L'on vide ensuite toutes les tables pour prédire les résultats.
     * <p>
     * Avant d'exécuter les tests, on crée un producteur, puis on l'insère en base.
     * Évidemment, l'auto_increment.
     * <p>
     * Enfin, on ajoute un producteur en base.
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
     * L'on créer deux producteurs puis l'on les insère en base.
     * L'on pense aussi à reset l'auto-incremente de la table Producteur apres
     * l'avoir
     * vidée.
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
     * Vérifions que l'ID de PRODUCTEUR_A: 0 avant insertion,
     * est bien mise à jour suite à l'ajout en base.
     * <p>
     * Si l'ajout est effectif, cet ID sera 1.
     */
    @Test
    @DisplayName("Test de la methode add")
    public void addTest() {
        // Apres ajout, l'ID doit devenir 1 et non 0.
        assertEquals(0, PRODUCTEUR_A.getIdProducteur());
        prodDAO.add(PRODUCTEUR_A);
        assertEquals(1, PRODUCTEUR_A.getIdProducteur());
    }

    /**
     * L'on insert PRODUCTEUR_A en base.
     * <p>
     * Ensuite le demande un Producteur ayant pour ID l'iD du PRODUCTEUR_A.
     * <p>
     * Puis l'on vérifie que l'attribut producteur est bien le producteur dans
     * PRODUCTEUR_A.
     * <p>
     * Enfin l'on assure qu'un ID inexistant renvoi bien null.
     */
    @Test
    @DisplayName("Test de la methode get")
    public void getTest() {
        prodDAO.add(PRODUCTEUR_A);

        // Demander un producteur d'ID associé au PRODUCTEUR_A
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
     * L'on vérifie qu'il y a bien 2 elements dans le tableau retourné.
     * Puis l'on vérifie que ces elements ont les bons ID.
     */
    @Test
    @DisplayName("Test la methode getAll")
    public void getAllTest() {
        prodDAO.add(PRODUCTEUR_A);
        prodDAO.add(PRODUCTEUR_B);

        // L'on devrait avoir deux Producteurs d'ID 1 et 2 dans le tableau.
        ArrayList<Producteur> ProducteurList = prodDAO.getAll();
        assertEquals(2, ProducteurList.size());
        assertEquals(1, ProducteurList.get(0).getIdProducteur());
        assertEquals(2, ProducteurList.get(1).getIdProducteur());
    }

    /**
     * L'on insert le PRODUCTEUR_A en base.
     * Ensuite l'on récupère son ID.
     * <p>
     * Enfin l'on supprime le producteur correspondant à cet ID.
     * Puis l'on vérifie que demander cet ID renvoi bien null.
     */
    @Test
    @DisplayName("Test la methode delete")
    public void deleteTest() {
        prodDAO.add(PRODUCTEUR_A);
        int idProducteur = PRODUCTEUR_A.getIdProducteur();

        // Apres suppression, l'ID devrait etre null.
        prodDAO.delete(PRODUCTEUR_A);
        assertNull(prodDAO.get(idProducteur));
    }

    /**
     * On commence par créer et ajout un nouveau producteur en base.
     * Ensuite l'on ajout PRODUCTEUR_A en base pour modifier tous ses attributs,
     * puis l'on update ce Producteur, y compris le producteur.
     * <p>
     * Enfin l'on créer un autre objet avec le meme ID pour s'assurer
     * que les attributs sont egaux.
     */
    @Test
    @DisplayName("Test la methode update")
    public void updateTest() {
        PRODUCTEUR_A.addVehicule(VEHICULE_A);

        // On ajoute puis met à jour le PRODUCTEUR_A
        // L'on change aussi le producteur
        prodDAO.add(PRODUCTEUR_A);
        PRODUCTEUR_A.setAdresseProd("200 rue de Grandmont");
        PRODUCTEUR_A.setGpsProd("46.37760650.8082932");
        PRODUCTEUR_A.setMdpProd("hashMDP3");
        PRODUCTEUR_A.setNumTelProd("0638462846");
        PRODUCTEUR_A.setProprietaire("Pierre");
        PRODUCTEUR_A.setSiret("73282932000076");
        prodDAO.update(PRODUCTEUR_A);

        // On créer un autre object de meme ID pour s'assurer que les attributs
        // sont identiques. Cela induit qu'ils sont modifés en BDD.
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

        // On ajoute un client en base
        ClientDAO clientDAO = new ClientDAO(conn);
        Client client = new Client("Jean", "120 Rue", "123,987", "229938");
        clientDAO.add(client);

        // On ajoute une commande en base
        CommandeDAO commandeDAO = new CommandeDAO(conn);
        Commande commande = new Commande(
                "Com", 20F, new Timestamp(10000), new Timestamp(20000),
                PRODUCTEUR_A, client);
        commandeDAO.add(commande);

        // On verifie que le producteur possede bien cette commande.
        assertTrue(PRODUCTEUR_A.getCommandes().get(0).equals(commande));

        // On insère une autre commande par le producteur.
        Commande commandeBis = new Commande(
                "Com2", 20F, new Timestamp(100000), new Timestamp(200000),
                PRODUCTEUR_A, client);
        PRODUCTEUR_A.addCommande(commandeBis);
        prodDAO.update(PRODUCTEUR_A);

        // On constate que c'est bien dans l'objet.
        assertTrue(PRODUCTEUR_A.getCommandes().get(1).equals(commandeBis));

        // On creer un nouveau vehicule
        Vehicule vehicule = new Vehicule("9897-FF", 90F, "Vehi", PRODUCTEUR_A);
        VehiculeDAO vehiDAO = new VehiculeDAO(conn);
        vehiDAO.add(vehicule);

        // On ajoute le vehicule au producteur.
        PRODUCTEUR_A.addVehicule(vehicule);
        prodDAO.update(PRODUCTEUR_A);

        // On s'assure qu'il possède bien ce vehicle.
        assertTrue(PRODUCTEUR_A.getVehicules().contains(vehicule));

        // On creer une nouvelle tournee
        Tournee tournee = new Tournee(new Timestamp(100000), new Timestamp(100000), 30F, "Tournee", vehicule);
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        tourneeDAO.add(tournee);

        // On verifie que le producteur a bien cette tournee.
        assertTrue(PRODUCTEUR_A.getTournees().contains(tournee));

        // On change de vehicule pour la tournee.
        vehiDAO.add(VEHICULE_A);
        tournee.setVehicule(VEHICULE_A);
        tourneeDAO.update(tournee);

        // On constate que c'est update dans le tableau de tournee.
        assertTrue(PRODUCTEUR_A.getTournees().get(0).getVehicule().equals(VEHICULE_A));

        // On supprime la tournee, et constate qu'elle n'est plus
        // dans le tableau de tournee du producteur.
        tourneeDAO.delete(tournee);
        assertFalse(PRODUCTEUR_A.getTournees().contains(tournee));

    }
}
