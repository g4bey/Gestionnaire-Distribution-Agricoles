package tests.dao;

import DAO.*;
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
import java.util.ArrayList;
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
                34F,
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

    /**
     * On insère le COMMANDE_A en base.
     * Ensuite, on récupère son ID.
     * <p>
     * Enfin, on supprime la commande correspondant à cet ID.
     * Puis on vérifie que demander cet ID renvoie bien null.
     */
    @Test
    @DisplayName("Test la methode delete")
    public void deleteTest() {
        commandeDAO.add(COMMANDE_A);
        int idCommande = COMMANDE_A.getIdCommande();

        // Apres suppression, l'ID devrait être null.
        commandeDAO.delete(COMMANDE_A);
        assertNull(commandeDAO.get(idCommande));
    }

    /**
     * On commence par créer et ajouter une commande en base.
     * Ensuite, l'on modifie tous les attributs, y compris les references.
     * puis on update ce véhicule, y compris le producteur.
     * <p>
     * Enfin, on crée un autre objet avec le même ID pour s'assurer
     * que les attributs sont égaux.
     */
    @Test
    @DisplayName("Test la methode update")
    public void updateTest() {
        commandeDAO.add(COMMANDE_A);

        // On créer un client
        ClientDAO clientDAO = new ClientDAO(conn);
        Client client = new Client(
                "Jean", "Charles",
                "20,40", "000394985"
        );
        clientDAO.add(client);

        // On créer un nouveau producteur
        ProducteurDAO producteurDAO = new ProducteurDAO(conn);
        Producteur producteur = new Producteur(
                "123123", "Bastien",
                "12 rue de la vilardiere",
                "04049484", "30,2",
                "mdp"
        );
        producteurDAO.add(producteur);

        // On créer un nouveau vehicule
        VehiculeDAO vehiculeDAO = new VehiculeDAO(conn);
        Vehicule vehicule = new Vehicule(
                "193", 40F,
                "patrick", producteur
        );
        vehiculeDAO.add(vehicule);

        // On l'ajoute à une tournee.
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        Tournee tournee = new Tournee(
                new Timestamp(90000), new Timestamp(80000),
                40F, "text", vehicule
        );
        tourneeDAO.add(tournee);

        // On ajoute puis met à jour le COMMANDE_A
        // L'on change producteur clients et tournee.
        COMMANDE_A.setProducteur(producteur);
        COMMANDE_A.setLibelle("Lib2");
        COMMANDE_A.setClient(client);
        COMMANDE_A.setTournee(tournee);
        COMMANDE_A.setHoraireFin(new Timestamp(70000));
        COMMANDE_A.setHoraireDebut(new Timestamp(50000));
        COMMANDE_A.setPoids(40F);
        commandeDAO.update(COMMANDE_A);

        // On crée un autre object de meme ID pour s'assurer que les attributs
        // sont identiques. Cela induit qu'ils sont modifés en BDD.
        Commande commandeRetour = commandeDAO.get(COMMANDE_A.getIdCommande());
        assertTrue(commandeRetour.equals(COMMANDE_A));
    }

    /**
     * On essaye de s'assurer que les références sont propagées.
     * Par exemple en ajouter des commandes dans les tournées,
     * les commandes déja existantes auront des liens vers les nouvelles commandes
     * via le tablea de tournée.
     */
    @Test
    @DisplayName("Test la methode update et propgation")
    public void propagationTest() {
        commandeDAO.add(COMMANDE_A);
        commandeDAO.add(COMMANDE_B);

        // On créer un nouveau vehicule
        VehiculeDAO vehiculeDAO = new VehiculeDAO(conn);
        Vehicule vehicule = new Vehicule(
                "193", 40F,
                "patrick", PRODUCTEUR_DEMO
        );
        vehiculeDAO.add(vehicule);

        // On l'ajoute à une tournee.
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        Tournee tournee = new Tournee(
                new Timestamp(90000), new Timestamp(80000),
                40F, "text", vehicule
        );
        tourneeDAO.add(tournee);

        // On ajoute des commandes dans la tournee
        tournee.addCommande(COMMANDE_B);
        tournee.addCommande(COMMANDE_A);
        tourneeDAO.update(tournee);

        // On s'assure que les valeurs sont bien propagées
        assertTrue(COMMANDE_A.getTournee().getVehicule().equals(vehicule));
        assertTrue(COMMANDE_A.getTournee().getCommandes().contains(COMMANDE_B));
        assertTrue(COMMANDE_A.getClient().equals(CLIENT_DEMO));
        assertTrue(COMMANDE_A.getTournee().equals(tournee));
        assertTrue(COMMANDE_A.getTournee().getCommandes().equals(tournee.getCommandes()));
    }

    /**
     * On vérifie que getCommandesByTournee renvoi bien une liste de commande
     * identique à ce qui est insere dans une tourée.
     *
     * @throws SQLException
     */
    @Test
    @DisplayName("Test la methode getCommandesByTournee")
    public void getCommandesByTourneeTest() throws SQLException {
        commandeDAO.add(COMMANDE_A);
        commandeDAO.add(COMMANDE_B);

        // On créer un nouveau vehicule
        VehiculeDAO vehiculeDAO = new VehiculeDAO(conn);
        Vehicule vehicule = new Vehicule(
                "193", 40F,
                "patrick", PRODUCTEUR_DEMO
        );
        vehiculeDAO.add(vehicule);

        // On creer la tournee
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        Tournee tournee = new Tournee(
                new Timestamp(100000), new Timestamp(80000),
                40F, "text", vehicule
        );
        tourneeDAO.add(tournee);

        // On ajoute des commandes à la tournee.
        tournee.addCommande(COMMANDE_A);
        tournee.addCommande(COMMANDE_B);
        tourneeDAO.update(tournee);

        // On creer une liste de commandes temoin
        ArrayList<Commande> commandesTemoin = new ArrayList<>();
        commandesTemoin.add(COMMANDE_A);
        commandesTemoin.add(COMMANDE_B);

        // On recupere la liste de commandes
        ArrayList<Commande> commandes = commandeDAO.getCommandesByTournee(PRODUCTEUR_DEMO, tournee);
        assertTrue(commandes.get(0).equals(COMMANDE_A));
        assertTrue(commandes.get(1).equals(COMMANDE_B));
        assertTrue(commandes.size() == 2);
    }

    /**
     * On vérifie que getCommandesByProducteurTournees renvoi bien une liste de commande
     * identique à ce qui est inséré dans une tournée.
     * <p>
     * Ici, une commande ne peut pas etre dans une tournée.
     *
     * @throws SQLException
     */
    @Test
    @DisplayName("Test la methode getCommandesByTournee")
    public void getCommandesByProducteurTournees() throws SQLException {
        commandeDAO.add(COMMANDE_A);
        commandeDAO.add(COMMANDE_B);

        // On créer un nouveau vehicule
        VehiculeDAO vehiculeDAO = new VehiculeDAO(conn);
        Vehicule vehicule = new Vehicule(
                "193", 40F,
                "patrick", PRODUCTEUR_DEMO
        );
        vehiculeDAO.add(vehicule);

        // On creer la tournee
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        Tournee tournee = new Tournee(
                new Timestamp(100000), new Timestamp(80000),
                40F, "text", vehicule
        );
        tourneeDAO.add(tournee);

        // On ajoute des commandes à la tournee.
        tournee.addCommande(COMMANDE_A);
        tourneeDAO.update(tournee);

        // On creer une autre tournee.
        Tournee tourneeBis = new Tournee(
                new Timestamp(1000000), new Timestamp(9000000),
                420F, "text2", vehicule
        );
        tourneeDAO.add(tourneeBis);

        // On met ajoute les commandes dans cette tournee.
        tourneeBis.addCommande(COMMANDE_B);
        tourneeDAO.update(tourneeBis);

        // On creer une commande qui n'est pas dans une tournee.
        Commande commandeSeule = new Commande(
                "Commande seule",
                12F,
                new Timestamp(300000),
                new Timestamp(500000),
                null,
                PRODUCTEUR_DEMO,
                CLIENT_DEMO
        );
        commandeDAO.add(commandeSeule);

        // On creer une liste de tournees
        ArrayList<Tournee> tourneeListe = new ArrayList<>();
        tourneeListe.add(tournee);
        tourneeListe.add(tourneeBis);

        // On recupere la liste de commandes et on y verifie les valeurs
        ArrayList<Commande> commandes = commandeDAO.getCommandesByProducteursTournees(PRODUCTEUR_DEMO, tourneeListe);
        assertTrue(commandes.get(0).equals(COMMANDE_A));
        assertTrue(commandes.get(1).equals(COMMANDE_B));
        assertTrue(commandes.get(2).equals(commandeSeule));
        assertTrue(commandes.size() == 3);
    }
}
