package tests.dao;

import org.junit.jupiter.api.*;

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
 * Tests de la classe DAO.TourneeDAO.
 */
public class TourneeDAOTest {

    static private Connection conn;
    static private TourneeDAO tourneeDAO;
    static private VehiculeDAO vehiculeDAO;
    static private ProducteurDAO producteurDAO;
    static private Producteur PRODUCTEUR;
    static private Vehicule VEHICULE;
    static private Tournee TOURNEE_A;
    static private Tournee TOURNEE_B;

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
    } // truncateTable

    /**
     * On crée une Connection puis instancie les DAO.
     * On vide ensuite les tables Tournee, Vehicule et Producteur pour prédire les
     * résultats.
     * <p>
     * Avant d'exécuter les tests, on crée un Producteur et un Véhicule, puis on les
     * insère en base.
     * Évidemment, l'auto_increment
     * 
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @BeforeAll
    public static void setup() throws SQLException, IOException, ClassNotFoundException {
        conn = DatabaseConnection.getInstance("testing");
        producteurDAO = new ProducteurDAO(conn);
        vehiculeDAO = new VehiculeDAO(conn);
        tourneeDAO = new TourneeDAO(conn);

        truncateTable("Commande");
        truncateTable("Client");
        truncateTable("Tournee");
        truncateTable("Vehicule");
        truncateTable("Producteur");

        PRODUCTEUR = new Producteur(
                "siret",
                "proprietaire",
                "adresseProd",
                "numTel",
                "gps",
                "mdp"
                ); // PRODUCTEUR

        producteurDAO.add(PRODUCTEUR);

        VEHICULE = new Vehicule(
                1,
                "AAAA",
                30.5F,
                "Libelle",
                PRODUCTEUR
                ); // VEHICULE

        vehiculeDAO.add(VEHICULE);
    } // setup

    /**
     * On crée deux Tournées.
     * On pense aussi à reset l'auto-increment de la table Tournée apres l'avoir
     * vidée.
     * <p>
     * 
     * @throws SQLException
     */
    @BeforeEach
    public void init() throws SQLException {
        TOURNEE_A = new Tournee(
            new Timestamp(30000),
            new Timestamp(40000),
            31.5F,
            "Livarisno de corercteurs",
            VEHICULE
            ); // TOURNEE_A
        TOURNEE_B = new Tournee(
            new Timestamp(450000),
            new Timestamp(8000000),
            22F,
            "Livraison de flûtes québecoises",
            VEHICULE
            ); // TOURNEE_B

        truncateTable("Tournee");
    } // init

    /**
     * Vérifions que l'ID de TOURNEE_A : 0 avant insertion,
     * est bien mis à jour suite à l'ajout en base.
     * <p>
     * Si l'ajout est effectif, cet ID sera 1.
     */
    @Test
    @DisplayName("Test de la méthode add")
    public void addTest() {
        // Apres ajout, l'ID doit devenir 1 et non 0.
        assertEquals(0, TOURNEE_A.getIdTournee());
        tourneeDAO.add(TOURNEE_A);
        assertEquals(1, TOURNEE_A.getIdTournee());
    } // addTest

    /**
     * On insère TOURNEE_A en base.
     * <p>
     * Ensuite, on demande une Tournée ayant pour ID l'iD de TOURNEE_A.
     * <p>
     * Puis l'on vérifie que l'attribut vehicule est bien le Véhicule dans
     * TOURNEE_A.
     * <p>
     * Enfin, on s'assure qu'un ID inexistant renvoie bien null.
     */
    @Test
    @DisplayName("Test de la méthode get")
    public void getTest() {
        tourneeDAO.add(TOURNEE_A);

        // Demander une tournée d'ID associé à TOURNEE_A
        // Doit nécessairement aboutir à une égalité d'attributs
        Tournee tourneeRetour = tourneeDAO.get(TOURNEE_A.getIdTournee());
        assertTrue(TOURNEE_A.equals(tourneeRetour));

        // L'ID du Véhicule associé à la Tournée doit correspondre au Véhicule associé
        // initialement.
        Vehicule vehicule = tourneeRetour.getVehicule();
        assertEquals(vehicule.getIdVehicule(), VEHICULE.getIdVehicule());

        // Cet ID n'existe pas.
        Tournee tourneeNull = tourneeDAO.get(5);
        assertNull(tourneeNull);
    } // getTest

    /**
     * Insérons TOURNEE_A et TOURNEE_B en base.
     * Ces dernières auront comme ID 1 et 2.
     * <p>
     * On vérifie qu'il y a bien 2 éléments dans le tableau retourné.
     * Puis on vérifie que ces éléments ont les bons ID.
     */
    @Test
    @DisplayName("Test la méthode getAll")
    public void getAllTest() {
        tourneeDAO.add(TOURNEE_A);
        tourneeDAO.add(TOURNEE_B);

        // On devrait avoir deux Tournées d'ID 1 et 2 dans le tableau.
        ArrayList<Tournee> tourneeList = tourneeDAO.getAll();
        assertEquals(2, tourneeList.size());
        assertEquals(1, tourneeList.get(0).getIdTournee());
        assertEquals(2, tourneeList.get(1).getIdTournee());
    } // getAllTest

    /**
     * On insère la TOURNEE_A en base.
     * Ensuite, on récupère son ID.
     * <p>
     * Enfin, on supprime la Tournée correspondant à cet ID.
     * Puis, on vérifie que demander cet ID renvoie bien null.
     */
    @Test
    @DisplayName("Test la méthode delete")
    public void deleteTest() {
        tourneeDAO.add(TOURNEE_A);
        int idTournee = TOURNEE_A.getIdTournee();

        // Après suppression, l'ID devrait renvoyer null.
        tourneeDAO.delete(TOURNEE_A);
        assertNull(tourneeDAO.get(idTournee));
    } // deleteTest

    /**
     * On commence par créer et ajouter un nouveau Véhicule en base.
     * Ensuite, on ajoute TOURNEE_A en base et modifie tous ses attributs,
     * puis on update cette Tournée, y compris son Véhicule.
     * <p>
     * Enfin, on crée un autre objet avec le même ID pour s'assurer
     * que les attributs sont égaux.
     */
    @Test
    @DisplayName("Test la méthode update")
    public void updateTest() {
        // On ajoute un nouveau Véhicule
        Vehicule vehiculeB = new Vehicule(
            "1337",
            50F,
            "VOITURE",
            PRODUCTEUR
            ); // vehiculeB
        vehiculeDAO.add(vehiculeB);

        // On ajoute puis met à jour la TOURNEE_A
        // On change aussi son Véhicule
        tourneeDAO.add(TOURNEE_A);
        TOURNEE_A.setHoraireDebut(new Timestamp(25000));
        TOURNEE_A.setHoraireFin(new Timestamp(35000));
        TOURNEE_A.setPoids(90F);
        TOURNEE_A.setLibelle("Livraison de correcteurs");
        TOURNEE_A.setVehicule(vehiculeB);
        tourneeDAO.update(TOURNEE_A);

        // On crée un autre objet de même ID pour s'assurer que les attributs
        // sont identiques. Cela induit qu'ils sont modifiés en BDD.
        Tournee tourneeRetour = tourneeDAO.get(TOURNEE_A.getIdTournee());
        assertTrue(tourneeRetour.equals(TOURNEE_A));
    } // updateTest

    /**
     * On s'assure que les Commandes sont bien propagées,
     * d'abord dans les objets existants puis dans les objets nouvellement créés.
     */
    @Test
    @DisplayName("Test propagation update commande")
    public void propagationCommandeTest() {
        tourneeDAO.add(TOURNEE_A);

        // On crée un Client
        Client client = new Client(
            "Pedro",
            "31 rue NullPointerException 37000 TOURS",
            "",
            "0634117279"
            ); // client

        // On crée deux Commandes
        Commande commande1 = new Commande(
            "commande1",
            31F,
            new Timestamp(30000),
            new Timestamp(370000),
            PRODUCTEUR,
            client
            ); // commande1
        Commande commande2 = new Commande(
            "commande2",
            12F,
            new Timestamp(12000),
            new Timestamp(12000),
            PRODUCTEUR,
            client
            ); // commande2

        // On les ajoute en base.
        CommandeDAO commandeDAO = new CommandeDAO(conn);
        ClientDAO clientDAO = new ClientDAO(conn);
        clientDAO.add(client);
        commandeDAO.add(commande1);
        commandeDAO.add(commande2);

        // On les update.
        commande1.setTournee(TOURNEE_A);
        commande2.setTournee(TOURNEE_A);
        commandeDAO.update(commande1);
        commandeDAO.update(commande2);

        // On vérifie qu'elles sont bien propagées
        assertEquals(2, TOURNEE_A.getCommandes().size());
        assertTrue(commande1.equals(TOURNEE_A.getCommandes().get(0)));

        // On s'assure qu'on a les bonnes valeurs dans les objets.
        Tournee tourneeRetour = tourneeDAO.get(TOURNEE_A.getIdTournee());
        assertTrue(tourneeRetour.getCommandes().get(1).equals(TOURNEE_A.getCommandes().get(1)));
        assertTrue(tourneeRetour.getCommandes().get(0).equals(TOURNEE_A.getCommandes().get(0)));

        // On vérifie que les commandes ne sont plus associées à la Tournée dans les
        // objets une fois celle-ci supprimée
        tourneeDAO.delete(TOURNEE_A);
        assertNull(commande1.getTournee());
        assertNull(commande2.getTournee());

        // On vérifie que les Commandes ne sont plus associées à la Tournée en base.
        assertNull(commandeDAO.get(commande1.getIdCommande()).getTournee());
        assertNull(commandeDAO.get(commande2.getIdCommande()).getTournee());
    } // propagationCommandeTest

    /**
     * On s'assure que les Tournées concernées par un Véhicule peuvent bien être
     * retrouvées à partir de celui-ci
     * 
     * @throws SQLException
     */
    @Test
    @DisplayName("Test de la méthode getTourneesByVehicule")
    public void getTourneesByVehiculeTest() throws SQLException {
        Producteur producteurC = new Producteur(
            "Dédé",
            "Jean-Louis",
            "23 rue de la grosse grange",
            "",
            "",
            ""
            ); // producteurC
        Vehicule vehiculeC = new Vehicule(
            "450677",
            57F,
            "PELLETEUSE",
            producteurC
            ); // vehiculeC
        Tournee tourneeC = new Tournee(
            new Timestamp(78000),
            new Timestamp(97000),
            30F,
            "Courroies de transmission",
            vehiculeC
            ); // tourneeC

        producteurDAO.add(producteurC);
        vehiculeDAO.add(vehiculeC);
        tourneeDAO.add(TOURNEE_A);
        tourneeDAO.add(TOURNEE_B);
        tourneeDAO.add(tourneeC);

        // On vérifie que seules les Tournées liées au Véhicule sont retournées
        ArrayList<Tournee> tournees = tourneeDAO.getTourneesByVehicule(VEHICULE);
        assertEquals(2, tournees.size());

        // On vérifie les valeurs des Tournées
        assertTrue(TOURNEE_A.equals(tournees.get(0)));
        assertTrue(TOURNEE_B.equals(tournees.get(1)));
    } // getTourneesByVehiculeTest

    /**
     * On s'assure que les Tournées concernées par une liste de Véhicules peuvent
     * bien être retrouvées à partir de celle-ci
     * 
     * @throws SQLException
     */
    @Test
    @DisplayName("Test de la méthode getTourneesByVehicules")
    public void getTourneesByVehiculesTest() throws SQLException {
        Producteur producteurC = new Producteur(
            "DDDDDDD",
            "Jean-Louis",
            "23 rue de la grosse grange",
            "",
            "",
            ""
            ); // producteurC
        Vehicule vehiculeC = new Vehicule(
            "4506",
            57F,
            "PELLETEUSE",
            producteurC
            ); // vehiculeC
        Tournee tourneeC = new Tournee(
            new Timestamp(78000),
            new Timestamp(97000),
            30F,
            "Courroies de transmission",
            vehiculeC
            ); // tourneeC
        Vehicule vehiculeD = new Vehicule(
            "23814",
            45F,
            "TRACTEUR",
            PRODUCTEUR
            ); // vehiculeD
        Tournee tourneeD = new Tournee(
            new Timestamp(13000),
            new Timestamp(47000),
            20F,
            "Transfert de lingitos",
            VEHICULE
            ); // tourneeD

        producteurDAO.add(producteurC);
        vehiculeDAO.add(vehiculeC);
        vehiculeDAO.add(vehiculeD);
        tourneeDAO.add(TOURNEE_A);
        tourneeDAO.add(TOURNEE_B);
        tourneeDAO.add(tourneeC);
        tourneeDAO.add(tourneeD);

        ArrayList<Vehicule> vehicules = new ArrayList<>();
        vehicules.add(VEHICULE);
        vehicules.add(vehiculeD);

        // On vérifie que seules les Tournées liées à la liste de Véhicules sont
        // retournées
        ArrayList<Tournee> tournees = tourneeDAO.getTourneesByVehicules(vehicules);
        assertEquals(3, tournees.size());

        // On vérifie les valeurs des Tournées
        assertTrue(TOURNEE_A.equals(tournees.get(0)));
        assertTrue(TOURNEE_B.equals(tournees.get(1)));
        assertTrue(tourneeD.equals(tournees.get(2)));
    } // getTourneesByVehiculesTest

    /**
     * Vérifions que clientEstDansTournee rend bien compte de la présence d'un Client dans une Tournée.
     * @throws SQLException
     */
    @Test
    @DisplayName("Test de la méthode clientEstDansTournee")
    public void clientEstDansTourneeTest() throws SQLException {
        tourneeDAO.add(TOURNEE_A);

        // Créons un Client et ajoutons-le en base.
        Client clientMystere = new Client(
            "Raymon",
            "Mon adresse",
            "coordGPS",
            "tel"
            ); // clientMystere
        ClientDAO cDAO = new ClientDAO(conn);
        cDAO.add(clientMystere);

        // Pour l'instant, le Client n'est dans aucune tournée.
        assertFalse(tourneeDAO.clientEstDansTournee(clientMystere.getIdClient()));

        // Désormais ajoutons une Commande et ajoutons-la dans la Tournée.
        // Cette Commande associe le Client à la Tournée.
        Commande commande1 = new Commande(
            "commande1",
            31F,
            new Timestamp(30000),
            new Timestamp(370000),
            PRODUCTEUR,
            clientMystere
            ); // commande1
        CommandeDAO commandeDAO = new CommandeDAO(conn);
        commandeDAO.add(commande1);
        TOURNEE_A.addCommande(commande1);
        tourneeDAO.update(TOURNEE_A);

        // L'assertion est vraie, car le Client est maintenant associé à la Tournée.
        assertTrue(tourneeDAO.clientEstDansTournee(TOURNEE_A.getCommandes().get(0).getClient().getIdClient()));
    } // clientEstDansTourneeTest

    /**
     * Fermeture de la Connection apres les tests.
     *
     * @throws SQLException
     */
    @AfterAll
    public static void tearDown() throws SQLException {
        DatabaseConnection.close("testing");
    } // tearDown

} // TourneeDAOTest
