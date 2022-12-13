
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    /**
     * Nous vérifions que le singleton renvoie bien une instance lors que
     * getInstance est invoqué pour la premiere fois.
     */
    @Test
    @DisplayName("Premiere connection valide")
    void connectionValide() throws IOException, ClassNotFoundException {
        try {
            DatabaseConnection.close(DatabaseConnection.getInstance("testing"));
            Connection conn = DatabaseConnection.getInstance("testing");
            assertNotNull(conn);
            DatabaseConnection.close(conn);
        } catch (SQLException e) {
            fail("Serveur MySQL indisponible");
        }
    }

    /**
     * Nous vérifions qu'un environnement inexistant du fichier de configuration
     * génère bien une erreur.
     */
    @Test
    @DisplayName("Chargeur d'attribut fonctionnel")
    void chargeurAttributFonctionnel() throws IOException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getInstance("existePas");
            fail("Le chargeur d'attribut est, connection qui ne devrait pas avoir lieu");
            DatabaseConnection.close(conn);
        } catch (SQLException e) {
            assertNull(conn);
        }
    }

    /**
     * Nous cherchons à tester l'unicité de la connection.
     * Le singleton doit renvoyer une instance si elle existe deja.
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
            DatabaseConnection.close(conn);
            DatabaseConnection.close(conn2);
        } catch (SQLException e) {
            fail("Le singleton ne renvoi pas un unique connection");
        }
    }
}
