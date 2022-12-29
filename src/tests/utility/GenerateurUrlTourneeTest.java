package tests.utility;

import DAO.*;
import modele.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.DatabaseConnection;
import utility.GenerateurUrl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

/**
 * Tests de la classe utility.GenerateurUrlTournee.
 */
public class GenerateurUrlTourneeTest {

    static private Connection conn;
    static private CommandeDAO commandeDAO;
    static private ClientDAO clientDAO;
    static private TourneeDAO tourneeDAO;
    static private VehiculeDAO vehiculeDAO;
    static private ProducteurDAO producteurDAO;
    static private GenerateurUrl gut;

    /**
     * Simule un TRUNCATE de la table table.
     * D'abord on supprime tout, puis on RESET l'auto_increment.
     * <p>
     *
     * @throws SQLException
     * @param table Le nom de la table que l'on souhaite TRUNCATE.
     */
    private static void truncateTable(String table) throws SQLException {
        Statement st = conn.createStatement();
        st.execute("DELETE FROM " + table);
        st.execute("ALTER TABLE " + table + " AUTO_INCREMENT=1");
    }

    /**
     * On crée une Connection puis instancie les DAO.
     * On vide ensuite la table Client pour prédire les résultats.
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @BeforeAll
    public static void setup() throws SQLException, IOException, ClassNotFoundException {
        conn = DatabaseConnection.getInstance("testing");
        commandeDAO = new CommandeDAO(conn);
        clientDAO = new ClientDAO(conn);
        tourneeDAO = new TourneeDAO(conn);
        vehiculeDAO = new VehiculeDAO(conn);
        producteurDAO = new ProducteurDAO(conn);

        gut = new GenerateurUrl();

        truncateTable("Commande");
        truncateTable("Client");
        truncateTable("Tournee");
        truncateTable("Vehicule");
        truncateTable("Producteur");
    }

    /**
     * On crée une Tournée et on vérifie que l'URL généré est bien celui attendu
     */
    @Test
    @DisplayName("Test du générateur d'URL")
    public void AffichageTourneeUrlTest() {

        // Coordonnées GPS arrondies pour correspondre à OpenRouteService (imprécisions donc)
        Producteur producteur = new Producteur(
            "siret",
            "proprietaire",
            "Tour Eiffel",
            "numTelProd",
            "48.858585,2.2945564",
            "mdpProd");
        Vehicule vehicule = new Vehicule(
            "numImm",
            70F,
            "VROUM",
            producteur);
        Client client1 = new Client(
            "nomClient",
            "Invalides",
            "48.856693,2.3127310",
            "numTelClient"
        );
        Client client2 = new Client(
            "nomClient",
            "Gare Montparnasse",
            "48.840864,2.3193185",
            "numTelClient"
        );
        Client client3 = new Client(
            "nomClient",
            "Jardin du Luxembourg",
            "48.846331,2.3371190",
            "numTelClient"
        );
        Tournee tournee = new Tournee(
            new Timestamp(70000),
            new Timestamp(95000),
            69F,
            "Livraison de goodies parisiens",
            vehicule
        );
        Commande commande1 = new Commande(
            "Commande Invalides",
            14F,
            new Timestamp(72000),
            new Timestamp(73000),
            producteur,
            client1
            );
        Commande commande2 = new Commande(
          "Commande Montparnasse",
          25F,
            new Timestamp(80000),
            new Timestamp(82000),
            producteur,
            client2
        );
        Commande commande3 = new Commande(
            "Commande Jardin du Luxembourg",
            30F,
            new Timestamp(85000),
            new Timestamp(88000),
            producteur,
            client3
        );

        producteurDAO.add(producteur);
        vehiculeDAO.add(vehicule);
        clientDAO.add(client1);
        clientDAO.add(client2);
        clientDAO.add(client3);
        tourneeDAO.add(tournee);
        commandeDAO.add(commande1);
        commandeDAO.add(commande2);
        commandeDAO.add(commande3);
        tournee.addCommande(commande1);
        tournee.addCommande(commande2);
        tournee.addCommande(commande3);
        commandeDAO.update(commande1);
        commandeDAO.update(commande2);
        commandeDAO.update(commande3);

        assertEquals(
            gut.AffichageTourneeUrl(tournee),
            "https://www.google.com/maps/dir/?api=1&origin=48.858585,2.2945564"
                + "&destination=48.858585,2.2945564&waypoints=48.856693,2.3127310%7C"
                + "48.840864,2.3193185%7C48.846331,2.3371190");
    }
}
