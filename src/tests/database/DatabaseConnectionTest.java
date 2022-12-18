package tests.database;

import utility.DatabaseConnection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;

/**
 * Testons DatabaseConnection.
 */
public class DatabaseConnectionTest {

    /**
     * Fermons la connection avant chaque tests.
     *
     * @throws SQLException
     */
    @BeforeEach
    void init() throws SQLException {
        DatabaseConnection.close("testing");
    }

    /**
     * Nous vérifions que le singleton renvoie bien une instance lors que
     * getInstance est invoqué pour la premiere fois.
     *
     *  @throws IOException
     *  @throws ClassNotFoundException
     */
    @Test
    @DisplayName("Premiere connection valide")
    void connectionValide() throws IOException, ClassNotFoundException {
        try {
            Connection conn = DatabaseConnection.getInstance("testing");
            assertNotNull(conn);
        } catch (SQLException e) {
            fail("Serveur MySQL indisponible");
        } // end try catch
    }

    /**
     * Nous vérifions qu'un environnement inexistant du fichier de configuration
     * génère bien une erreur IOException.
     *
     *  @throws IOException
     *  @throws ClassNotFoundException
     */
    @Test
    @DisplayName("Chargeur d'attribut fonctionnel")
    void chargeurAttributFonctionnel() throws IOException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance("existePas");
            fail("Connection qui ne devrait pas exister.");
        } catch (SQLException e) {
            fail("Problem avec le constructeur.");
        } catch (IOException e) {
            assertNull(conn);
        } // end try catch
    }

    /**
     * Nous cherchons à tester l'unicité de la connection.
     * Le singleton doit renvoyer une instance si elle existe deja.
     *
     *  @throws IOException
     *  @throws ClassNotFoundException
     */
    @Test
    @DisplayName("Singleton retournant une et une seule connection")
    void singletonFonctionnel() throws IOException, ClassNotFoundException {
        Connection conn;
        Connection conn2;
        try {
            conn = DatabaseConnection.getInstance("testing");
            conn2 = DatabaseConnection.getInstance("testing");
            assertEquals(conn, conn2);
        } catch (SQLException e) {
            fail("Le singleton ne renvoi pas un unique connection");
        } // end try catch
    }

    /**
     * Fermeture de la connection apres les tests.
     *
     * @throws SQLException
     */
    @AfterAll
    public static void tearDown() throws SQLException {
        DatabaseConnection.close("testing");
    }
}
