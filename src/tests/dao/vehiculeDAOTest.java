package tests.dao;

import DAO.ProducteurDAO;
import DAO.VehiculeDAO;
import modele.Producteur;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de la classe DAO.AdministrateurDAO.
 */
public class vehiculeDAOTest {

    static private Connection conn;
    static private VehiculeDAO vehiculeDAO;
    static private ProducteurDAO prodDAO;
    static private Vehicule VEHICULE_A;
    static private Vehicule VEHICULE_B;
    static private Producteur EXEMPLE_PROD;

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
     * On créer une connection, puis l'instancie les DAO.
     * L'on vide ensuite la table producteur pour prédire les résultats.
     * <p>
     * Avant d'exécuter les tests, on crée un producteur, puis on l'insère en base.
     * Évidemment, l'auto_increment.
     * <p>
     * Enfin, on ajoute un producteur en base.
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
     * L'on créer deux véhicules puis l'on les insère en base.
     * L'on pense aussi à reset l'auto-incremente de la table Vehicule apres l'avoir vidée.
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
     * est bien mise à jour suite à l'ajout en base.
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
     * L'on insert VEHICULE_A en base.
     * <p>
     * Ensuite le demande un vehicule ayant pour ID l'iD du VEHICULE_A.
     * <p>
     * Puis l'on vérifie que l'attribut producteur est bien le producteur dans VEHICULE_A.
     * <p>
     * Enfin l'on assure qu'un ID inexistant renvoi bien null.
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
     * L'on vérifie qu'il y a bien 2 elements dans le tableau retourné.
     * Puis l'on vérifie que ces elements ont les bons ID.
     */
    @Test
    @DisplayName("Test la methode getAll")
    public void getAllTest() {
        vehiculeDAO.add(VEHICULE_A);
        vehiculeDAO.add(VEHICULE_B);

        // L'on devrait avoir deux vehicules d'ID 1 et 2 dans le tableau.
        List<Vehicule> vehiculeList = vehiculeDAO.getAll();
        assertEquals(2, vehiculeList.size());
        assertEquals(1, vehiculeList.get(0).getIdVehicule());
        assertEquals(2, vehiculeList.get(1).getIdVehicule());
    }

    /**
     * L'on insert le VEHICULE_A en base.
     * Ensuite l'on récupère son ID.
     * <p>
     * Enfin l'on supprime le véhicule correspondant à cet ID.
     * Puis l'on vérifie que demander cet ID renvoi bien null.
     */
    @Test
    @DisplayName("Test la methode delete")
    public void deleteTest() {
        vehiculeDAO.add(VEHICULE_A);
        int idVehicule = VEHICULE_A.getIdVehicule();

        // Apres suppression, l'ID devrait etre null.
        vehiculeDAO.delete(VEHICULE_A);
        assertNull(vehiculeDAO.get(idVehicule));
    }

    /**
     * On commence par créer et ajout un nouveau producteur en base.
     * Ensuite l'on ajout VEHICULE_A en base pour modifier tous ses attributs,
     * puis l'on update ce vehicule, y compris le producteur.
     * <p>
     * Enfin l'on créer un autre objet avec le meme ID pour s'assurer
     * que les attributs sont egaux.
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

        // On créer un autre object de meme ID pour s'assurer que les attributs
        // sont identiques. Cela induit qu'ils sont modifés en BDD.
        Vehicule vehiculeRetour = vehiculeDAO.get(VEHICULE_A.getIdVehicule());
        assertTrue(vehiculeRetour.equals(VEHICULE_A));
    }

}
