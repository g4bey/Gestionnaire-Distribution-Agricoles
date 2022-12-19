package tests.dao;

import DAO.AdministrateurDAO;
import modele.Administrateur;
import utility.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests de la classe DAO.AdministrateurDAO.
 */
public class administrateurDAOTest {

    static private Connection conn;
    static private AdministrateurDAO adminDAO;
    static private Administrateur ADMIN_A;
    static private Administrateur ADMIN_B;

    /**
     * Instantiation de la connection avant TOUS les tests.
     * On injecte la connection dans le DAO administrateur.
     * <p>
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
     * Avant CHAQUE test, on réinitialise les objects metier administrateurs témoin.
     * Ensuite l'on vide la table Administrateur afin de pouvoir prédire les résultats.
     * <p>
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
     * On vérifie que ADMIN_A a pour ID: 0 (null)
     * Ensuite l'on insert cet administrateur en base.
     * <p>
     * Enfin, l'on vérifie que l'ID est bien update,
     * et que ce dernier est 1.
     */
    @Test
    @DisplayName("Test de la methode add")
    public void addTest() {
        assertEquals(0, ADMIN_A.getIdAdministrateur());

        adminDAO.add(ADMIN_A);

        assertEquals(1, ADMIN_A.getIdAdministrateur());
    }

    /**
     * L'on insert l'ADMIN_A en base.
     * Ensuite l'on demande un admin qui a pour id l'id d'ADMIN_A.
     * <p>
     * Enfin l'on vérifie que les attributs sont strictement identiques.
     * Puis l'on vérifie qu'un ID inexistant renvoi bien un null.
     */
    @Test
    @DisplayName("Test de la methode get")
    public void getTest() {
        adminDAO.add(ADMIN_A);

        Administrateur adminRetour = adminDAO.get(ADMIN_A.getIdAdministrateur());
        assertEquals(ADMIN_A.getIdAdministrateur(), adminRetour.getIdAdministrateur());
        assertEquals(ADMIN_A.getMdpAdmin(), adminRetour.getMdpAdmin());
        assertEquals(ADMIN_A.getPseudo(), adminRetour.getPseudo());

        Administrateur adminNull = adminDAO.get(5);
        assertNull(adminNull);
    }

    /**
     * L'on insert ADMIN_A et ADMIN_B en base.
     * Ainsi, l'on 2 admins en base, d'id 1 et 2 respectivement.
     * <p>
     * Enfin, l'on vérife que la liste contient bien uniquement ces elements.
     */
    @Test
    @DisplayName("Test la methode getAll")
    public void getAllTest() {
        adminDAO.add(ADMIN_A);
        adminDAO.add(ADMIN_B);

        List<Administrateur> adminList = adminDAO.getAll();
        assertEquals(2, adminList.size());
        assertEquals(1, adminList.get(0).getIdAdministrateur());
        assertEquals(2, adminList.get(1).getIdAdministrateur());
    }

    /**
     * L'on insert ADMIN_A en base, il aura l'ID 1.
     * L'on stock son ID avant de le delete.
     * <p>
     * Enfin, l'on vérifie que l'ADMIN ayant pour ID 1 n'existe plus.
     */
    @Test
    @DisplayName("Test la methode delete")
    public void deleteTest() {
        adminDAO.add(ADMIN_A);
        int idAdmin = ADMIN_A.getIdAdministrateur();

        adminDAO.delete(idAdmin);
        assertNull(adminDAO.get(1));
    }

    /**
     * L'on insert ADMIN_A, ensuite l'on modifie mot de passe et pseudo.
     * Nous effectuons l'update de cet object, avant de charger
     * un object avec l'ID d'ADMIN_A.
     * <p>
     * Enfin, nous vérifions que ses attributs sont bien identiques, donc modifiés.
     */
    @Test
    @DisplayName("Test la methode update")
    public void updateTest() {
        adminDAO.add(ADMIN_A);
        ADMIN_A.setPseudo("Update");
        ADMIN_A.setMdpAdmin("UpdateMDP");
        adminDAO.update(ADMIN_A);

        Administrateur adminRetour = adminDAO.get(ADMIN_A.getIdAdministrateur());
        assertEquals(adminRetour.getPseudo(), ADMIN_A.getPseudo());
        assertEquals(adminRetour.getMdpAdmin(), ADMIN_A.getMdpAdmin());
    }

}
