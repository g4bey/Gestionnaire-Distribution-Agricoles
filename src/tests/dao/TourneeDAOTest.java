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
     * On vide ensuite les tables Tournee, Vehicule et Producteur pour prédire les résultats.
     * <p>
     * Avant d'exécuter les tests, on crée un producteur et un véhicule puis on les insère en base.
     * Évidemment, l'auto_increment
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
            "mdp");

        producteurDAO.add(PRODUCTEUR);

        VEHICULE = new Vehicule(
            1,
            "AAAA",
            30.5F,
            "Libelle",
            PRODUCTEUR);

        vehiculeDAO.add(VEHICULE);
    }

    /**
     * On crée deux tournées.
     * On pense aussi à reset l'auto-increment de la table Tournee apres l'avoir vidée.
     * <p>
     * @throws SQLException
     */
    @BeforeEach
    public void init() throws SQLException {
        TOURNEE_A = new Tournee(new Timestamp(30000), new Timestamp(40000), 31.5F, "Livarisno de corercteurs", VEHICULE);
        TOURNEE_B = new Tournee(new Timestamp(450000), new Timestamp(8000000), 22F, "Livraison de flûtes québecoises", VEHICULE);

        truncateTable("Tournee");
    }

    /**
     * Vérifions que l'ID de TOURNEE_A: 0 avant insertion,
     * est bien mise à jour suite à l'ajout en base.
     * <p>
     * Si l'ajout est effectif, cet ID sera 1.
     */
    @Test
    @DisplayName("Test de la methode add")
    public void addTest() {
        // Apres ajout, l'ID doit devenir 1 et non 0.
        assertEquals(0, TOURNEE_A.getIdTournee());
        tourneeDAO.add(TOURNEE_A);
        assertEquals(1, TOURNEE_A.getIdTournee());
    }

    /**
     * On insère TOURNEE_A en base.
     * <p>
     * Ensuite, on demande une tournée ayant pour ID l'iD de TOURNEE_A.
     * <p>
     * Puis l'on vérifie que l'attribut véhicule est bien le véhicule dans TOURNEE_A.
     * <p>
     * Enfin,on s'assure qu'un ID inexistant renvoie bien null.
     */
    @Test
    @DisplayName("Test de la methode get")
    public void getTest() {
        tourneeDAO.add(TOURNEE_A);

        // Demander une tournée d'ID associé à TOURNEE_A
        // Doit nécessairement aboutir à une égalité d'attributs
        Tournee tourneeRetour = tourneeDAO.get(TOURNEE_A.getIdTournee());
        assertTrue(TOURNEE_A.equals(tourneeRetour));

        // L'ID du véhicule associé à la tournée doit correspondre au véhicule associé initialement.
        Vehicule vehicule = tourneeRetour.getVehicule();
        assertEquals(vehicule.getIdVehicule(), VEHICULE.getIdVehicule());

        // Cet ID n'existe pas.
        Tournee tourneeNull = tourneeDAO.get(5);
        assertNull(tourneeNull);
    }

    /**
     * Insérons TOURNEE_A et TOURNEE_B en base.
     * Ces dernières auront comme ID 1 et 2.
     * <p>
     * L'on vérifie qu'il y a bien 2 elements dans le tableau retourné.
     * Puis l'on vérifie que ces éléments ont les bons ID.
     */
    @Test
    @DisplayName("Test la methode getAll")
    public void getAllTest() {
        tourneeDAO.add(TOURNEE_A);
        tourneeDAO.add(TOURNEE_B);

        // L'on devrait avoir deux tournées d'ID 1 et 2 dans le tableau.
        List<Tournee> tourneeList = tourneeDAO.getAll();
        assertEquals(2, tourneeList.size());
        assertEquals(1, tourneeList.get(0).getIdTournee());
        assertEquals(2, tourneeList.get(1).getIdTournee());
    }

    /**
     * On insère la TOURNEE_A en base.
     * Ensuite, on récupère son ID.
     * <p>
     * Enfin, on supprime la tournée correspondant à cet ID.
     * Puis, on vérifie que demander cet ID renvoie bien null.
     */
    @Test
    @DisplayName("Test la methode delete")
    public void deleteTest() {
        tourneeDAO.add(TOURNEE_A);
        int idTournee = TOURNEE_A.getIdTournee();

        // Apres suppression, l'ID devrait être null.
        tourneeDAO.delete(TOURNEE_A);
        assertNull(tourneeDAO.get(idTournee));
    }

    /**
     * On commence par créer et ajouter un nouveau véhicule en base.
     * Ensuite, on ajoute TOURNEE_A en base et modifie tous ses attributs,
     * puis on update cette tournée, y compris le véhicule.
     * <p>
     * Enfin, on crée un autre objet avec le même ID pour s'assurer
     * que les attributs sont égaux.
     */
    @Test
    @DisplayName("Test la methode update")
    public void updateTest() {
        // On ajoute un nouveau véhicule
        Vehicule vehiculeB = new Vehicule("1337", 50F, "VOITURE", PRODUCTEUR);
        vehiculeDAO.add(vehiculeB);

        // On ajoute puis met à jour la TOURNEE_A
        // On change aussi le producteur
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
    }

    /**
     * On s'assure que les commandes sont bien propagées.
     * D'abord dans les objets existants puis dans les objets nouvellement créés.
     */
    @Test
    @DisplayName("Test propagation update commande")
    public void propagationCommande() {
        tourneeDAO.add(TOURNEE_A);

        // On crée un client
        Client client = new Client("Pedro", "31 rue NullPointerException 37000 TOURS", "", "0634117279");

        // On crée deux commandes
        Commande commande1 = new Commande("commande1", 31F, new Timestamp(30000), new Timestamp(370000), PRODUCTEUR, client);
        Commande commande2 = new Commande("commande2", 12F, new Timestamp(12000), new Timestamp(12000), PRODUCTEUR, client);

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

        // On verifie que les commandes ne sont plus associes a la tournee dans les objets
        tourneeDAO.delete(TOURNEE_A);
        assertNull(commande1.getTournee());
        assertNull(commande2.getTournee());

        // On verifie que les commandes ne sont plus associes a la tournee en base.
        assertNull(commandeDAO.get(commande1.getIdCommande()).getTournee());
        assertNull(commandeDAO.get(commande2.getIdCommande()).getTournee());
    }

    /**
     * On s'assure que les tournées concernées par un véhicule peuvent bien être retrouvées à partir de celui-ci
     * @throws SQLException
     */
    @Test
    @DisplayName("Test de la méthode getTourneesByVehicule")
    public void getTourneesByVehiculeTest() throws SQLException {
        Producteur producteurC = new Producteur("Dédé", "Jean-Louis", "23 rue de la grosse grange", "", "", "");
        Vehicule vehiculeC = new Vehicule("450677", 57F, "PELLETEUSE", producteurC);
        Tournee tourneeC = new Tournee(new Timestamp(78000), new Timestamp(97000), 30F, "Courroies de transmission", vehiculeC);

        producteurDAO.add(producteurC);
        vehiculeDAO.add(vehiculeC);
        tourneeDAO.add(TOURNEE_A);
        tourneeDAO.add(TOURNEE_B);
        tourneeDAO.add(tourneeC);

        // On vérifie que seules les tournées liées au véhicule sont retournées
        ArrayList<Tournee> tournees = tourneeDAO.getTourneesByVehicule(VEHICULE);
        assertEquals(2, tournees.size());

        // On vérifie les valeurs des tournées
        assertTrue(TOURNEE_A.equals(tournees.get(0)));
        assertTrue(TOURNEE_B.equals(tournees.get(1)));
    }

    /**
     * On s'assure que les tournées concernées par une liste de véhicules peuvent bien être
     * retrouvées à partir de celle-ci
     * @throws SQLException
     */
    @Test
    @DisplayName("Test de la méthode getTourneesByVehicules")
    public void getTourneesByVehiculesTest() throws SQLException {
        Producteur producteurC = new Producteur("DDDDDDD", "Jean-Louis", "23 rue de la grosse grange", "", "", "");
        Vehicule vehiculeC = new Vehicule("4506", 57F, "PELLETEUSE", producteurC);
        Tournee tourneeC = new Tournee(new Timestamp(78000), new Timestamp(97000), 30F, "Courroies de transmission", vehiculeC);
        Vehicule vehiculeD = new Vehicule("23814", 45F, "TRACTEUR", PRODUCTEUR);
        Tournee tourneeD = new Tournee(new Timestamp(13000), new Timestamp(47000), 20F, "Transfert de lingitos", VEHICULE);

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

        // On vérifie que seules les tournées liées à la liste de véhicules sont retournées
        ArrayList<Tournee> tournees = tourneeDAO.getTourneesByVehicules(vehicules);
        assertEquals(3, tournees.size());

        // On vérifie les valeurs des tournées
        assertTrue(TOURNEE_A.equals(tournees.get(0)));
        assertTrue(TOURNEE_B.equals(tournees.get(1)));
        assertTrue(tourneeD.equals(tournees.get(2)));
    }
}
