package tests.dao;

import DAO.ProducteurDAO;
import DAO.TourneeDAO;
import DAO.VehiculeDAO;
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
 * Tests de la classe DAO.VehiculeDAO.
 */
public class VehiculeDAOTest {

    static private Connection conn;
    static private VehiculeDAO vehiculeDAO;
    static private ProducteurDAO prodDAO;
    static private Vehicule VEHICULE_A;
    static private Vehicule VEHICULE_B;
    static private Producteur EXEMPLE_PROD;

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
     * On vide ensuite la table producteur pour prédire les résultats.
     * <p>
     * Avant d'exécuter les tests, on crée un producteur puis on l'insère en base.
     * Évidemment, l'auto_increment.
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @BeforeAll
    public static void setup() throws SQLException, IOException, ClassNotFoundException {
        conn = DatabaseConnection.getInstance("testing");
        vehiculeDAO = new VehiculeDAO(conn);
        prodDAO = new ProducteurDAO(conn);

        truncateTable("Vehicule");
        truncateTable("Producteur");

        EXEMPLE_PROD = new Producteur(
                "siret",
                "proprietaire",
                "adresseProd",
                "numTel",
                "gps",
                "mdp"
        );
        prodDAO.add(EXEMPLE_PROD);
    }

    /**
     * On crée deux véhicules.
     * On pense aussi à reset l'auto-increment de la table Vehicule apres l'avoir vidée.
     * <p>
     * @throws SQLException
     */
    @BeforeEach
    public void init() throws SQLException {
        VEHICULE_A = new Vehicule("AAAA", 30.5F, "Libelle",  EXEMPLE_PROD);
        VEHICULE_B = new Vehicule("BBBB", 32.5F, "Libelle 2",  EXEMPLE_PROD);

        truncateTable("Vehicule");
    }

    /**
     * Vérifions que l'ID de VEHICULE_A: 0 avant insertion,
     * est bien mis à jour suite à l'ajout en base.
     * <p>
     * Si l'ajout est effectif, cet ID sera 1.
     */
    @Test
    @DisplayName("Test de la methode add")
    public void addTest() {
        // Apres ajout, l'ID doit devenir 1 et non 0.
        assertEquals(0, VEHICULE_A.getIdVehicule());
        vehiculeDAO.add(VEHICULE_A);
        assertEquals(1, VEHICULE_A.getIdVehicule());
    }

    /**
     * On insère VEHICULE_A en base.
     * <p>
     * Ensuite, on demande un vehicule ayant pour ID l'iD du VEHICULE_A.
     * <p>
     * Puis, on vérifie que l'attribut producteur est bien le producteur dans VEHICULE_A.
     * <p>
     * Enfin, on assure qu'un ID inexistant renvoie bien null.
     */
    @Test
    @DisplayName("Test de la methode get")
    public void getTest() {
        vehiculeDAO.add(VEHICULE_A);

        // Demander un véhicule d'ID associé au VEHICULE_A
        // Doit nécessairement aboutir à une égalité d'attributs
        Vehicule vehiculeRetour = vehiculeDAO.get(VEHICULE_A.getIdVehicule());
        assertTrue(VEHICULE_A.equals(vehiculeRetour));

        // L'ID du producteur associé au véhicule correspondre au producteur associé initialement.
        Producteur producteur = vehiculeRetour.getProducteur();
        assertEquals(producteur.getIdProducteur(), EXEMPLE_PROD.getIdProducteur());

        // Cet ID n'existe pas.
        Vehicule vehiculeNull = vehiculeDAO.get(5);
        assertNull(vehiculeNull);
    }

    /**
     * Insérons VEHICULE_A et VEHICULE_B en base.
     * Ces derniers auront comme ID 1 et 2.
     * <p>
     * On vérifie qu'il y a bien 2 elements dans le tableau retourné.
     * Puis on vérifie que ces elements ont les bons ID.
     */
    @Test
    @DisplayName("Test la methode getAll")
    public void getAllTest() {
        vehiculeDAO.add(VEHICULE_A);
        vehiculeDAO.add(VEHICULE_B);

        // On devrait avoir deux véhicules d'ID 1 et 2 dans le tableau.
        List<Vehicule> vehiculeList = vehiculeDAO.getAll();
        assertEquals(2, vehiculeList.size());
        assertEquals(1, vehiculeList.get(0).getIdVehicule());
        assertEquals(2, vehiculeList.get(1).getIdVehicule());
    }

    /**
     * On insère le VEHICULE_A en base.
     * Ensuite, on récupère son ID.
     * <p>
     * Enfin, on supprime le véhicule correspondant à cet ID.
     * Puis on vérifie que demander cet ID renvoie bien null.
     */
    @Test
    @DisplayName("Test la methode delete")
    public void deleteTest() {
        vehiculeDAO.add(VEHICULE_A);
        int idVehicule = VEHICULE_A.getIdVehicule();

        // Apres suppression, l'ID devrait être null.
        vehiculeDAO.delete(VEHICULE_A);
        assertNull(vehiculeDAO.get(idVehicule));
    }

    /**
     * On commence par créer et ajouter un nouveau producteur en base.
     * Ensuite, on ajoute VEHICULE_A en base et modifie tous ses attributs,
     * puis on update ce véhicule, y compris le producteur.
     * <p>
     * Enfin, on crée un autre objet avec le même ID pour s'assurer
     * que les attributs sont égaux.
     */
    @Test
    @DisplayName("Test la methode update")
    public void updateTest() {
        // On ajoute un nouveau producteur
        Producteur prodB = new Producteur("A", "A", "A", "A", "A", "A");
        prodDAO.add(prodB);

        // On ajoute puis met à jour le VEHICULE_A
        // L'on change aussi le producteur
        vehiculeDAO.add(VEHICULE_A);
        VEHICULE_A.setLibelle("AAAA");
        VEHICULE_A.setNumImmat("1111");
        VEHICULE_A.setPoidsMax(45.5F);
        VEHICULE_A.setProducteur(prodB);
        vehiculeDAO.update(VEHICULE_A);

        // On crée un autre object de meme ID pour s'assurer que les attributs
        // sont identiques. Cela induit qu'ils sont modifés en BDD.
        Vehicule vehiculeRetour = vehiculeDAO.get(VEHICULE_A.getIdVehicule());
        assertTrue(vehiculeRetour.equals(VEHICULE_A));
    }

    /**
     * On s'assure que les tournées sont bien propagées.
     * D'abord dans les objets existants puis dans les objets nouvellement créés.
     */
    @Test
    @DisplayName("Test propagation ajout tournee")
    public void propagationTournee() {
        vehiculeDAO.add(VEHICULE_A);

        // On crée deux tournees
        Tournee tournee = new Tournee(new Timestamp(1000), new Timestamp(1000), 10F, "D", VEHICULE_A);
        Tournee tournee2 = new Tournee(new Timestamp(1000), new Timestamp(2000), 12F, "F", VEHICULE_A);

        // On les ajoute en base.
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        tourneeDAO.add(tournee);
        tourneeDAO.add(tournee2);

        // On vérifie qu'elles sont bien propagées
        assertEquals(2, VEHICULE_A.getTournees().size());
        assertTrue(tournee.equals(VEHICULE_A.getTournees().get(0)));

        // On s'assure qu'on a les bonnes valeurs dans les objets.
        Vehicule vehiculeRetour = vehiculeDAO.get(VEHICULE_A.getIdVehicule());
        assertTrue(vehiculeRetour.getTournees().get(1).equals(VEHICULE_A.getTournees().get(1)));
        assertTrue(vehiculeRetour.getTournees().get(0).equals(VEHICULE_A.getTournees().get(0)));

    }

    /**
     * Insérons un producteur et un véhicule et vérifions que l'on ne récupère que les véhicules
     * du producteur entré en paramètre
     * @throws SQLException
     */
    @Test
    @DisplayName("Test de la méthode getVehiculeByProducteur")
    public void getVehiculeByProducteurTest() throws SQLException {
        Producteur producteur = new Producteur("siretProd", "José", "33 rue de la pêche à la mouche", "", "", "");
        Vehicule vehiculeC = new Vehicule("3445", 61F, "CAMION", producteur);

        vehiculeDAO.add(VEHICULE_A);
        vehiculeDAO.add(VEHICULE_B);
        prodDAO.add(producteur);
        vehiculeDAO.add(vehiculeC);

        // On vérifie que l'on ne récupère que les véhicules du producteur EXEMPLE_PROD
        List<Vehicule> vehiculeList = vehiculeDAO.getVehiculesByProducteur(EXEMPLE_PROD);
        assertEquals(2, vehiculeList.size());
        assertEquals(1, vehiculeList.get(0).getIdVehicule());
        assertEquals(2, vehiculeList.get(1).getIdVehicule());
    }

    /**
     * Insérons une tournée et vérifions que le véhicule renvoyé par la méthode est bien celui
     * réalisant la tournée entrée en paramètre
     * @throws SQLException
     */
    @Test
    @DisplayName("Test de la méthode getVehiculeByIdTournee")
    public void getVehiculeByIdTourneeTest() throws SQLException {
        truncateTable("Tournee");
        TourneeDAO tourneeDAO = new TourneeDAO(conn);
        Tournee tournee = new Tournee(new Timestamp(32000), new Timestamp(33000), 37F, "Livraison de patates", VEHICULE_A);

        vehiculeDAO.add(VEHICULE_A);
        tourneeDAO.add(tournee);

        // On vérifie que l'on retrouve bien VEHICULE_A
        Vehicule vehiculeRetour = vehiculeDAO.getVehiculeByIdTournee(tournee.getIdTournee(), EXEMPLE_PROD);
        assertTrue(VEHICULE_A.equals(vehiculeRetour));
    }

}
