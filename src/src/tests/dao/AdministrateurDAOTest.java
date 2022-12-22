package src.tests.dao;

import src.DAO.AdministrateurDAO;
import src.modele.Administrateur;
import org.junit.jupiter.api.*;
import src.utility.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de la classe src.DAO.AdministrateurDAO.
 */
public class AdministrateurDAOTest {

    static private Connection conn;
    static private AdministrateurDAO adminDAO;
    static private Administrateur ADMIN_A;
    static private Administrateur ADMIN_B;

    /**
     * Instantiation de la Connection avant TOUS les src.tests.
     * On injecte la Connection dans le src.DAO Administrateur.
     * <p>
     * 
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @BeforeAll
    public static void setup() throws SQLException, IOException, ClassNotFoundException {
        conn = DatabaseConnection.getInstance("testing");
        adminDAO = new AdministrateurDAO(conn);
    }

    /**
     * Avant CHAQUE test, on réinitialise les objets métier Administrateur témoins.
     * Ensuite, on vide la table Administrateur afin de pouvoir prédire les
     * résultats.
     * <p>
     * 
     * @throws SQLException
     */
    @BeforeEach
    public void init() throws SQLException {
        ADMIN_A = new Administrateur("Jean", "123");
        ADMIN_B = new Administrateur("Baptiste", "321123");

        Statement st = conn.createStatement();
        st.execute("TRUNCATE TABLE `Administrateur`;");
    }

    /**
     * On vérifie que ADMIN_A a pour ID : 0 (null)
     * Ensuite, on insère cet Administrateur en base.
     * <p>
     * Enfin, on vérifie que l'ID est bien update
     * et que ce dernier est 1.
     */
    @Test
    @DisplayName("Test de la méthode add")
    public void addTest() {
        // Après ajout, l'ID doit devenir 1 et non 0.
        assertEquals(0, ADMIN_A.getIdAdministrateur());
        adminDAO.add(ADMIN_A);
        assertEquals(1, ADMIN_A.getIdAdministrateur());
    }

    /**
     * On insère l'ADMIN_A en base.
     * Ensuite, on demande un Administrateur qui a pour id l'id d'ADMIN_A.
     * <p>
     * Enfin, on vérifie que les attributs sont strictement identiques.
     * Puis on vérifie qu'un ID inexistant renvoie bien un null.
     */
    @Test
    @DisplayName("Test de la méthode get")
    public void getTest() {
        adminDAO.add(ADMIN_A);

        // Demander un Administrateur d'ID associé à l'ADMIN_A.
        // Doit aboutir à une égalité d'attributs.
        Administrateur adminRetour = adminDAO.get(ADMIN_A.getIdAdministrateur());
        assertTrue(adminRetour.equals(ADMIN_A));

        Administrateur adminNull = adminDAO.get(5);
        assertNull(adminNull);
    }

    /**
     * On insère ADMIN_A et ADMIN_B en base.
     * Ainsi, on 2 Administrateurs en base, d'id 1 et 2 respectivement.
     * <p>
     * Enfin, on vérifie que la liste contient bien uniquement ces éléments.
     */
    @Test
    @DisplayName("Test la méthode getAll")
    public void getAllTest() {
        adminDAO.add(ADMIN_A);
        adminDAO.add(ADMIN_B);

        // Dans la liste, il doit y avoir 2 Administrateurs d'ID 1 et 2.
        ArrayList<Administrateur> adminList = adminDAO.getAll();
        assertEquals(2, adminList.size());
        assertEquals(1, adminList.get(0).getIdAdministrateur());
        assertEquals(2, adminList.get(1).getIdAdministrateur());
    }

    /**
     * On insère ADMIN_A en base, il aura l'ID 1.
     * On stocke son ID avant de le delete.
     * <p>
     * Enfin, on vérifie que l'ADMIN ayant pour ID 1 n'existe plus.
     */
    @Test
    @DisplayName("Test la méthode delete")
    public void deleteTest() {
        adminDAO.add(ADMIN_A);
        int idAdmin = ADMIN_A.getIdAdministrateur();

        // Après suppression, un getIdAdmin doit être null.
        adminDAO.delete(ADMIN_A);
        assertNull(adminDAO.get(idAdmin));
    }

    /**
     * On insère ADMIN_A, ensuite on modifie mot de passe et pseudo.
     * Nous effectuons l'update de cet objet, avant de charger
     * un objet avec l'ID d'ADMIN_A.
     * <p>
     * Enfin, nous vérifions que ses attributs sont bien identiques, donc modifiés.
     */
    @Test
    @DisplayName("Test la méthode update")
    public void updateTest() {
        adminDAO.add(ADMIN_A);
        ADMIN_A.setPseudo("Update");
        ADMIN_A.setMdpAdmin("UpdateMDP");
        adminDAO.update(ADMIN_A);

        // On crée un autre object de meme ID pour s'assurer que les attributs
        // sont identiques. Cela induit qu'ils sont modifiés en BDD.
        Administrateur adminRetour = adminDAO.get(ADMIN_A.getIdAdministrateur());
        assertTrue(adminRetour.equals(ADMIN_A));
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
