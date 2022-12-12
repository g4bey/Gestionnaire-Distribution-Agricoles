package GDA.Testing.Database;

import GDA.Utility.DatabaseConnection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {
    @Test
    @DisplayName("Premiere connection valide")
    void connectionValide() throws IOException, ClassNotFoundException {
        try {
            Connection conn = DatabaseConnection.getInstance("production");
            assertNotNull(conn);
            DatabaseConnection.close(conn);
        } catch (SQLException e) {
            fail("Serveur MySQL indisponible");
        }
    }
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
    @Test
    @DisplayName("Singleton retournant une et une seule connection")
    void singletonFonctionnel() throws IOException, ClassNotFoundException {
        Connection conn;
        Connection conn2;
        try {
            conn = DatabaseConnection.getInstance("production");
            conn2 = DatabaseConnection.getInstance("production");
            assertEquals(conn, conn2);
            DatabaseConnection.close(conn);
            DatabaseConnection.close(conn2);
        } catch (SQLException e) {
            fail("Le singleton ne renvoi pas un unique connection");
        }
    }
}
