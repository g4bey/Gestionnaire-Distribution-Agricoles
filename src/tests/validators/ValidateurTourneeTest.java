package tests.validators;

import DAO.*;
import exceptions.InvalidRouteException;
import modele.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DatabaseConnection;
import validator.ValidateurTournee;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateurTourneeTest {

        private static ProducteurDAO producteurDAO;
        private static VehiculeDAO vehiculeDAO;
        private static CommandeDAO commandeDAO;
        private static ClientDAO clientDAO;
        private static TourneeDAO tourneeDAO;
        private static Producteur PRODUCTEUR_A;
        static Vehicule VEHICULE_A;
        private static Client CLIENT_A;
        private static Client CLIENT_B;
        private static Commande COMMANDE_A;
        private static Commande COMMANDE_B;
        ArrayList<Commande> listCommandes;

        private static Connection conn;

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
         * Ajoutons les données nécessaires en base.
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
                commandeDAO = new CommandeDAO(conn);
                clientDAO = new ClientDAO(conn);

                truncateTable("Commande");
                truncateTable("Client");
                truncateTable("Tournee");
                truncateTable("Vehicule");
                truncateTable("Producteur");

                // Ajouton un producteur.
                PRODUCTEUR_A = new Producteur(
                                "siret",
                                "La FAC Granmont",
                                "Rue François Bonamy 37200 Tours",
                                "numTel",
                                "0.684000,47.392000",
                                "mdp");
                producteurDAO.add(PRODUCTEUR_A);

                // Ajouton un vehicule;
                VEHICULE_A = new Vehicule(
                                1,
                                "AAAABB",
                                100F,
                                "Libelle",
                                PRODUCTEUR_A);
                vehiculeDAO.add(VEHICULE_A);

                // Ajouton des clients
                CLIENT_A = new Client(
                                "Blaise", "ibis Tours Sud 37200 Tours", "0.7003045,47.3357014", "1001101101");
                COMMANDE_A = new Commande(
                                "Les PC nuls de la fac", 10F,
                        Timestamp.valueOf(LocalDateTime.now().plusHours(2)),
                        Timestamp.valueOf(LocalDateTime.now().plusHours(5)),
                                PRODUCTEUR_A, CLIENT_A);
                clientDAO.add(CLIENT_A);
                commandeDAO.add(COMMANDE_A);
                CLIENT_B = new Client(
                                "Jul", "Les Atlantes Saint Pierre Des Corps 37700", "0.724309,47.3839677", "non");
                COMMANDE_B = new Commande(
                                "Des albums claqués au sol.", 20F,
                        Timestamp.valueOf(LocalDateTime.now().plusHours(3)),
                        Timestamp.valueOf(LocalDateTime.now().plusHours(12)),
                                PRODUCTEUR_A, CLIENT_B);
                clientDAO.add(CLIENT_B);
                commandeDAO.add(COMMANDE_B);
        }

        /**
         * Créons une liste de commandes qui sera
         * toujours vidée puis recréer avant chaque test.
         * Cette suite de commandes sans modification est cohérente.
         */
        @BeforeEach
        public void init() {
                listCommandes = new ArrayList<>();
                listCommandes.add(COMMANDE_A);
                listCommandes.add(COMMANDE_B);
        }

        /**
         * On injecte une liste de commandes et un poids de départ dans le validateur,
         * et on constate que c'est cohérent.
         */
        @Test
        @DisplayName("Test d'une tournée valide.")
        public void tourneeValideTest() {
                try {
                        ValidateurTournee.calculTournee(listCommandes, PRODUCTEUR_A.getGpsProd());
                } catch (IOException | InterruptedException e) {
                        fail("Erreur d'accès lors de la requête.\n".concat(e.toString()));

                } catch (InvalidRouteException e) {
                        e.printStackTrace();
                        fail("Erreur, le trajet est bon et la méthode ne doit pas échouer.");
                }
        }

        /**
         * Désormais ajoutons une commande fictive
         * et impossible à livrer dans le créneau donnée.
         */
        @Test
        @DisplayName("Test d'une tournée impossible, pas assez de temps trop loin.")
        public void tourneeImpossible() {
                // c'est la commande A.

                // Une commande vachement loin.
                Client clientChiant = new Client(
                                "Kim Jong Un",
                                "Residence Ryongsong,  Corée du Nord",
                                "2.2972896,48.8735162",
                                "Rien");

                // La plage horaire ne permet pas de se rendre si loin.
                Commande commandeImpossible = new Commande(
                                "Les PC nuls de la fac", 10F,
                        Timestamp.valueOf(LocalDateTime.now().plusHours(2)),
                        Timestamp.valueOf(LocalDateTime.now().plusHours(3)),
                                PRODUCTEUR_A, clientChiant);
                listCommandes.add(commandeImpossible);

                // Vérifions que le résultat est FALSE.
                try {
                        ValidateurTournee.calculTournee(listCommandes, PRODUCTEUR_A.getGpsProd());
                        fail("Le trajet n'est pas valide et la méthode doit échouer.");
                } catch (IOException | InterruptedException e) {
                        fail("Erreur d'accès lors de la requête.\n".concat(e.toString()));
                } catch (InvalidRouteException e) {
                        assertNotNull(e);
                }
        }

        /**
         * Dans un premier temps, nous ajoutons une tournée en base.
         * Ensuite, nous vérifions que fournir une plage horaire où le véhicule est
         * libre renvoi bien TRUE.
         * Puis nous vérifions qu'une plage horaire où le véhicule est non-libre renvoi
         * false.
         * <p>
         * Il faut prendre en compte que c'est méthode est appelée avant d'avoir créé la
         * tournée.
         */
        @Test
        @DisplayName("Test véhicule non disponible sur une plage donnée.")
        public void vehiculeNonDisponibleTest() {

                Tournee tournee = new Tournee(new Timestamp(1672012800), new Timestamp(1673012800), 50F, "Test",
                                VEHICULE_A);
                tourneeDAO.add(tournee);

                // Deux horaires où le véhicule est libre.
                assertTrue(
                                ValidateurTournee.valideVehicule(VEHICULE_A, new Timestamp(1972012800),
                                                new Timestamp(1973012800)));

                // Deux horaires où le véhicule est non-libre.
                assertFalse(
                                ValidateurTournee.valideVehicule(VEHICULE_A, new Timestamp(1672012800),
                                                new Timestamp(1672012900)));
                tourneeDAO.delete(tournee);
        }

        /**
         * Nous testons si validePoids renvoie FALSE quand le poids max du véhicule est
         * inférieur au poids de la tournée.
         * Nous testons aussi qu'il renvoie true lorsque ce poids est supérieur.
         * Enfin nous testons le validateur lorsque ces poids sont identique.
         */
        @Test
        @DisplayName("Test poids commandes vs poids max véhicule")
        public void validePoidsTest() {
                // Le poids max est inférieur au poids des commandes.
                assertFalse(
                                ValidateurTournee.validePoids(10F, listCommandes));

                // Le poids max est supérieur au poids des commandes.
                assertTrue(
                                ValidateurTournee.validePoids(50F, listCommandes));

                // Le poids max est égal au poids des commandes.
                assertTrue(
                                ValidateurTournee.validePoids(30F, listCommandes));
        }
}
