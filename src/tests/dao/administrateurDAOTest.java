package tests.dao;

import DAO.AdministrateurDAO;
import modele.Administrateur;
import utility.DatabaseConnection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests de la classe DAO.AdministrateurDAO
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
        st.execute("TRUNCATE TABLE Administrateur;");
    }

    /**
     * On vérifie que ADMIN_A a pour ID: 0 (null)
     * Ensuite l'on insert cet administrateur en base..
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
     * Enfin l'on vérifie que les attributs sont strictement identique.
     */
    @Test
    @DisplayName("Test de la methode get")
    public void getTest() {
        adminDAO.add(ADMIN_A);
        Administrateur adminRetour = adminDAO.get(ADMIN_A.getIdAdministrateur());
        assertEquals(ADMIN_A.getIdAdministrateur(), adminRetour.getIdAdministrateur());
        assertEquals(ADMIN_A.getMdpAdmin(), adminRetour.getMdpAdmin());
        assertEquals(ADMIN_A.getPseudo(), adminRetour.getPseudo());
    }

    @Test
    @DisplayName("Test la methode getAll")
    public void getAllTest() {
        fail("t Implemented");
    }

    @Test
    @DisplayName("Test la methode delete")
    public void deleteTest() {
        fail("Not Implemented");
    }

    @Test
    @DisplayName("Test la methode update")
    public void updateTest() {
        fail("Not Implemented");
    }

}
