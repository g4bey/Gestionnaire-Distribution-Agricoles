package tests.dao;

import DAO.ProducteurDAO;
import DAO.TourneeDAO;
import DAO.VehiculeDAO;
import modele.Producteur;
import org.junit.jupiter.api.BeforeAll;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Tests de la classe DAO.TourneeDAO.
 */
public class TourneeDAOTest {

    static private Connection conn;
    static private TourneeDAO tourneeDAO;

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


    }
}
