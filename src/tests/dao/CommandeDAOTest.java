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
 * Tests de la classe DAO.CommandeDAO.
 */
public class CommandeDAOTest {

        static private Connection conn;
        static private CommandeDAO commandeDAO;
        static private Commande COMMANDE_A;
        static private Commande COMMANDE_B;
        static private Producteur PRODUCTEUR_DEMO;
        static private Client CLIENT_DEMO;

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
         * Instantiation de la Connection avant TOUS les tests.
         * On injecte la Connection dans le DAO commande.
         * <p>
         * On vide toutes les tables afin de prédire les résultats.
         * <p>
         * 
         * @throws SQLException
         * @throws IOException
         * @throws ClassNotFoundException
         */
        @BeforeAll
        public static void setup() throws SQLException, IOException, ClassNotFoundException {
                conn = DatabaseConnection.getInstance("testing");
                commandeDAO = new CommandeDAO(conn);

                truncateTable("Commande");
                truncateTable("Client");
                truncateTable("Tournee");
                truncateTable("Vehicule");
                truncateTable("Producteur");
        } // setup

        /**
         * Avant CHAQUE test, on réinitialise les objets métier Commande, Client et
         * Producteur témoins.
         * <p>
         * 
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
                                ); // COMMANDE_A

                COMMANDE_B = new Commande(
                                "Commande 2",
                                34F,
                                new Timestamp(900000),
                                new Timestamp(1000000),
                                null,
                                PRODUCTEUR_DEMO,
                                CLIENT_DEMO
                                ); // COMMANDE_B
        } // init

        /**
         * Vérifions que l'ID de COMMANDE_A : 0 avant insertion,
         * est bien mis à jour suite à l'ajout en base.
         * <p>
         * Si l'ajout est effectif, cet ID sera 1.
         */
        @Test
        @DisplayName("Test de la méthode add")
        public void addTest() {
                // Après ajout, l'ID doit devenir 1 et non 0.
                assertEquals(0, COMMANDE_A.getIdCommande());
                commandeDAO.add(COMMANDE_A);
                assertEquals(1, COMMANDE_A.getIdCommande());
        } // addTest

        /**
         * On insère COMMANDE_A en base.
         * <p>
         * Ensuite, on demande une commande ayant pour ID l'ID de COMMANDE_A.
         * <p>
         * Puis on vérifie que l'attribut producteur est bien le Producteur dans
         * COMMANDE_A.
         * De même pour l'attribut client.
         * <p>
         * Enfin, on assure qu'un ID inexistant renvoie bien null.
         */
        @Test
        @DisplayName("Test de la méthode get")
        public void getTest() {
                commandeDAO.add(COMMANDE_A);

                // Demander un Véhicule d'ID associé à la COMMANDE_A
                // Doit nécessairement aboutir à une égalité d'attributs
                Commande commandeRetour = commandeDAO.get(COMMANDE_A.getIdCommande());
                assertTrue(COMMANDE_A.equals(commandeRetour));

                // L'ID de la Commande est associé à un Producteur
                Producteur producteur = commandeRetour.getProducteur();
                assertEquals(producteur.getIdProducteur(), COMMANDE_A.getProducteur().getIdProducteur());

                // L'ID de la Commande est associé à un Client
                Client client = commandeRetour.getClient();
                assertTrue(client.equals(COMMANDE_A.getClient()));

                // Cet ID n'existe pas.
                Commande commandeNull = commandeDAO.get(5);
                assertNull(commandeNull);
        } // getTest

        /**
         * Insérons COMMANDE_A et COMMANDE_B en base.
         * Ces derniers auront comme ID 1 et 2.
         * <p>
         * On vérifie qu'il y a bien 2 éléments dans le tableau retourné.
         * Puis l'on vérifie que ces éléments ont les bons ID.
         */
        @Test
        @DisplayName("Test la méthode getAll")
        public void getAllTest() {
                commandeDAO.add(COMMANDE_A);
                commandeDAO.add(COMMANDE_B);

                // On devrait avoir deux Véhicules d'ID 1 et 2 dans le tableau.
                ArrayList<Commande> commandeListe = commandeDAO.getAll();
                assertEquals(2, commandeListe.size());
                assertEquals(1, commandeListe.get(0).getIdCommande());
                assertEquals(2, commandeListe.get(1).getIdCommande());
        } // getAllTest

        /**
         * On insère la COMMANDE_A en base.
         * Ensuite, on récupère son ID.
         * <p>
         * Enfin, on supprime la Commande correspondant à cet ID.
         * Puis on vérifie que demander cet ID renvoie bien null.
         */
        @Test
        @DisplayName("Test la méthode delete")
        public void deleteTest() {
                commandeDAO.add(COMMANDE_A);
                int idCommande = COMMANDE_A.getIdCommande();

                // Après suppression, l'ID devrait renvoyer null.
                commandeDAO.delete(COMMANDE_A);
                assertNull(commandeDAO.get(idCommande));
        } // deleteTest

        /**
         * On commence par créer et ajouter une Commande en base.
         * Ensuite, on modifie tous les attributs, y compris les références.
         * puis on update son Véhicule et son Producteur.
         * <p>
         * Enfin, on crée un autre objet avec le même ID pour s'assurer
         * que les attributs sont égaux.
         */
        @Test
        @DisplayName("Test la méthode update")
        public void updateTest() {
                commandeDAO.add(COMMANDE_A);

                // On crée un Client
                ClientDAO clientDAO = new ClientDAO(conn);
                Client client = new Client(
                                "Jean",
                                "Charles",
                                "20,40",
                                "000394985"
                                ); // client
                clientDAO.add(client);

                // On crée un nouveau Producteur
                ProducteurDAO producteurDAO = new ProducteurDAO(conn);
                Producteur producteur = new Producteur(
                                "123123",
                                "Bastien",
                                "12 rue de la vilardiere",
                                "04049484",
                                "30,2",
                                "mdp"
                                ); // producteur
                producteurDAO.add(producteur);

                // On crée un nouveau Véhicule
                VehiculeDAO vehiculeDAO = new VehiculeDAO(conn);
                Vehicule vehicule = new Vehicule(
                                "193",
                                40F,
                                "patrick",
                                producteur
                                ); // vehicule
                vehiculeDAO.add(vehicule);

                // On l'ajoute à une Tournée.
                TourneeDAO tourneeDAO = new TourneeDAO(conn);
                Tournee tournee = new Tournee(
                                new Timestamp(90000),
                                new Timestamp(80000),
                                40F,
                                "text",
                                vehicule
                                ); // tournee
                tourneeDAO.add(tournee);

                // On ajoute puis met à jour la COMMANDE_A
                // On change Producteur, Client et Tournée.
                COMMANDE_A.setProducteur(producteur);
                COMMANDE_A.setLibelle("Lib2");
                COMMANDE_A.setClient(client);
                COMMANDE_A.setTournee(tournee);
                COMMANDE_A.setHoraireFin(new Timestamp(70000));
                COMMANDE_A.setHoraireDebut(new Timestamp(50000));
                COMMANDE_A.setPoids(40F);
                commandeDAO.update(COMMANDE_A);

                // On crée un autre objet de même ID pour s'assurer que les attributs
                // sont identiques. Cela induit qu'ils sont modifiés en BDD.
                Commande commandeRetour = commandeDAO.get(COMMANDE_A.getIdCommande());
                assertTrue(commandeRetour.equals(COMMANDE_A));
        } // updateTest

        /**
         * On essaie de s'assurer que les références sont propagées.
         * Par exemple en ajoutant des Commandes dans les Tournées,
         * les Commandes déjà existantes auront des liens vers les nouvelles Commandes
         * via le tableau de Tournées.
         */
        @Test
        @DisplayName("Test la méthode update et propagation")
        public void propagationTest() {
                commandeDAO.add(COMMANDE_A);
                commandeDAO.add(COMMANDE_B);

                // On crée un nouveau Véhicule
                VehiculeDAO vehiculeDAO = new VehiculeDAO(conn);
                Vehicule vehicule = new Vehicule(
                                "193",
                                40F,
                                "patrick",
                                PRODUCTEUR_DEMO
                                ); // vehicule
                vehiculeDAO.add(vehicule);

                // On l'ajoute à une Tournée.
                TourneeDAO tourneeDAO = new TourneeDAO(conn);
                Tournee tournee = new Tournee(
                                new Timestamp(90000),
                                new Timestamp(80000),
                                40F,
                                "text",
                                vehicule
                                ); // tournee
                tourneeDAO.add(tournee);

                // On ajoute des Commandes dans la Tournée
                tournee.addCommande(COMMANDE_B);
                tournee.addCommande(COMMANDE_A);
                tourneeDAO.update(tournee);

                // On s'assure que les valeurs sont bien propagées
                assertTrue(COMMANDE_A.getTournee().getVehicule().equals(vehicule));
                assertTrue(COMMANDE_A.getTournee().getCommandes().contains(COMMANDE_B));
                assertTrue(COMMANDE_A.getClient().equals(CLIENT_DEMO));
                assertTrue(COMMANDE_A.getTournee().equals(tournee));
                assertTrue(COMMANDE_A.getTournee().getCommandes().equals(tournee.getCommandes()));

                // On supprime une Commande d'une Tournée.
                tournee.removeCommande(COMMANDE_A);
                commandeDAO.update(COMMANDE_A);

                // On s'assure que la Tournée ne contient plus la Commande dans sa liste.
                assertFalse(tournee.getCommandes().contains(COMMANDE_A));

                // Contains se base sur obj.equals
                // On s'assure que la Tournée ne contient plus la Commande en base.
                assertFalse(tourneeDAO.get(tournee.getIdTournee()).getCommandes().contains(COMMANDE_A));
        } // propagationTest

        /**
         * On vérifie que getCommandesByTournee renvoie bien une liste de Commandes
         * identique à ce qui est inséré dans une Tournée.
         *
         * @throws SQLException
         */
        @Test
        @DisplayName("Test la méthode getCommandesByTournee")
        public void getCommandesByTourneeTest() throws SQLException {
                commandeDAO.add(COMMANDE_A);
                commandeDAO.add(COMMANDE_B);

                // On crée un nouveau Véhicule
                VehiculeDAO vehiculeDAO = new VehiculeDAO(conn);
                Vehicule vehicule = new Vehicule(
                                "193",
                                40F,
                                "patrick",
                                PRODUCTEUR_DEMO
                                ); // vehicule
                vehiculeDAO.add(vehicule);

                // On crée la Tournée
                TourneeDAO tourneeDAO = new TourneeDAO(conn);
                Tournee tournee = new Tournee(
                                new Timestamp(100000),
                                new Timestamp(80000),
                                40F,
                                "text",
                                vehicule
                                ); // tournee
                tourneeDAO.add(tournee);

                // On ajoute des Commandes à la Tournée.
                tournee.addCommande(COMMANDE_A);
                tournee.addCommande(COMMANDE_B);
                tourneeDAO.update(tournee);

                // On crée une liste de Commandes témoins
                ArrayList<Commande> commandesTemoin = new ArrayList<>();
                commandesTemoin.add(COMMANDE_A);
                commandesTemoin.add(COMMANDE_B);

                // On récupère la liste de Commandes
                ArrayList<Commande> commandes = commandeDAO.getCommandesByTournee(PRODUCTEUR_DEMO, tournee);
                assertTrue(commandes.get(0).equals(COMMANDE_A));
                assertTrue(commandes.get(1).equals(COMMANDE_B));
                assertTrue(commandes.size() == 2);
        } // getCommandesByTourneeTest

        /**
         * On vérifie que getCommandesByProducteurWithoutTournee renvoie bien la liste de
         * Commandes du Producteur n'appartenant à aucune Tournée
         *
         * @throws SQLException
         */
        @Test
        @DisplayName("Test la méthode getCommandesByProducteurWithoutTournee")
        public void getCommandesByProducteurWithoutTourneeTest() throws SQLException {
                commandeDAO.add(COMMANDE_A);

                // On crée un nouveau Véhicule
                VehiculeDAO vehiculeDAO = new VehiculeDAO(conn);
                Vehicule vehicule = new Vehicule(
                                "193",
                                40F,
                                "patrick",
                                PRODUCTEUR_DEMO
                                ); // vehicule
                vehiculeDAO.add(vehicule);

                // On crée la Tournée
                TourneeDAO tourneeDAO = new TourneeDAO(conn);
                Tournee tournee = new Tournee(
                                new Timestamp(100000),
                                new Timestamp(80000),
                                40F,
                                "text",
                                vehicule
                                ); // tournee
                tourneeDAO.add(tournee);

                // On ajoute des Commandes à la Tournée.
                tournee.addCommande(COMMANDE_A);
                tourneeDAO.update(tournee);

                // On crée une Commande qui n'est pas dans une Tournée.
                Commande commandeSeule = new Commande(
                                "Commande seule",
                                12F,
                                new Timestamp(300000),
                                new Timestamp(500000),
                                null,
                                PRODUCTEUR_DEMO,
                                CLIENT_DEMO
                                ); // commandeSeule
                commandeDAO.add(commandeSeule);

                // On récupère la liste de Commandes et on y vérifie les valeurs
                ArrayList<Commande> commandes = commandeDAO.getCommandesByProducteurWithoutTournee(PRODUCTEUR_DEMO);
                assertTrue(commandeSeule.equals(commandes.get(0)));
                assertTrue(commandes.size() == 1);
        } // getCommandesByProducteurWithoutTourneeTest

        /**
         * Fermeture de la Connection apres les tests.
         *
         * @throws SQLException
         */
        @AfterAll
        public static void tearDown() throws SQLException {
                DatabaseConnection.close("testing");
        } // tearDown

} // CommandeDAOTest
