package tests.database;

import utility.DatabaseConnection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * L'on veut s'assurer que les preparedStatement protege bien des injections
 * SQL.
 */
public class SQLInjectionTest {

    private Connection conn;

    /**
     * Base de donnée par défaut, utile pour connaitre les valeurs en avance.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @BeforeEach
    public void init() throws SQLException, IOException, ClassNotFoundException {
        conn = DatabaseConnection.getInstance("testing");
        Statement st = conn.createStatement();
        st.execute("TRUNCATE TABLE `users`;");
        st.execute(
                "INSERT INTO `users`"
                        + "(`id`, `username`, `email`, `password`)"
                        + " VALUES (null, 'user1', 'user1@gmail.com', 'password'),"
                        + " (null, 'user2', 'user2@gmail.com', 'AncienPassword');")
                ;
        st.close();
    }

    /**
     * Injection SQL de premier ordre.
     * La requete devient... SELECT * FROM users WHERE username = user2
     * <p>
     * L'on vérifie qu'il n'y a aucun retour.
     *
     * @throws SQLException
     */
    @Test
    @DisplayName("Protection Injection SQL de Premiere Ordre")
    public void executeQueryFirstOrder() throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "users2'-- ");
        pst.setString(2, "no");
        ResultSet res = pst.executeQuery();
        assertFalse(res.first());
        pst.close();
    }

    /**
     * Vérifions que les preparedStatement protègent des injections SQL de second
     * ordre.
     * L'on veut deux utilisateurs en base : user2'-- et user2
     * <p>
     * Lors de l'update, apres WHERE username = ...
     * '-- clos la requete et met le reste en commentaire.
     * <p>
     * Enfin l'on vérifie que le changement n'est pas effectif.
     *
     * @throws SQLException
     */
    @Test
    @DisplayName("Protection Injection SQL de Second Ordre")
    public void secondOrderSQLInjectionPrepared() throws SQLException {
        // On inject "user2'—" em base.
        String requete1 = "INSERT INTO `users` (`id`, `username`, `email`, `password`)"
                + " VALUES (null, ?, 'default@gmail.com', ?)";
        PreparedStatement pst = conn.prepareStatement(requete1);
        pst.setString(1, "user2'-- ");
        pst.setString(2, "123");
        pst.executeUpdate();
        pst.close();

        // Cela devient... "SET password = newPassword WHERE username = user2"
        String requete2 = "UPDATE users SET password = ? WHERE username = ? AND password = ?";
        PreparedStatement pst2 = conn.prepareStatement(requete2);
        pst2.setString(1, "nouveauPassword");
        pst2.setString(2, "user2'--");
        pst2.setString(3, "uneChaine");
        pst2.executeUpdate();
        pst2.close();

        // Verifions que le mot de passe est toujours AncienPassword
        String requete3 = "SELECT password FROM users WHERE username = 'user2'";
        Statement st = conn.createStatement();
        st.executeQuery(requete3);
        ResultSet rs = st.getResultSet();
        if (rs.first()) {
            assertNotEquals("nouveauPassword", rs.getString("password"));
            assertEquals("AncienPassword", rs.getString("password"));
        } // end if
        st.close();
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
