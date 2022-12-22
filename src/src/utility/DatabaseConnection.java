package src.utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Fournit un ensemble de connection unique à la base de donnée.
 * Cette dernière est créée si elle n'existe pas déjà.
 * <p>
 * L'on identifie chaque connection par son attribut environnement,
 * dans l'Hasmap conn.
 * <p>
 * L'on présuppose l'existence d'un fichier config.properties
 * Dans ce dernier seront déclarées
 * + db.environment.url : l'url de la base.
 * + db.environment.login : le login de la base
 * + db.environment.password : le password de la base
 * Ou l'environnement permet d'identifier les tuples.
 * <p>
 * Ainsi, pour les src.tests, l'on aura par exemple:
 * + db.testing.url=root
 * + db.testing.login=root
 * + db.testing.password=jdbc:mysql://localhost/gestAgricoleTest
 * <p>
 * L'aura chargera donc
 * urlMap<testing, root>
 * usernameMap<testing, root>
 * passwordMap<testing, jdbc:mysql://localhost/gestAgricoleTest>
 * <p>
 * Et dans connection l'on aura:
 * conn<testing, <instance@DatabaseConnection>
 */
public final class DatabaseConnection {

    private static HashMap<String, String> urlMap = new HashMap<>();
    private static HashMap<String, String> usernameMap = new HashMap<>();
    private static HashMap<String, String> passwordMap = new HashMap<>();
    private static HashMap<String, Connection> conn = new HashMap<>();

    /**
     * Création de la connection à l'aide de driver manager.
     * L'on commence par charger les attributs url, username et password à
     * partir du fichier config.properties, et de l'environnement fournit
     * en paramètre.
     *
     * Ensuite l'on instancie la connection, puis nous l'inserons dans une HashMap
     * avec comme identifiant l'environnement.
     *
     * @param environment l'environnement (production, testing, development...)
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    private DatabaseConnection(String environment) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        chargerAttribut(environment);
        Connection dbConn = DriverManager.getConnection(
                urlMap.get(environment),
                usernameMap.get(environment),
                passwordMap.get(environment)
            );
        conn.put(environment, dbConn);
    }

    /**
     * Cette methode permet la récupération de l'instance de connection au
     * serveur de base de donnée.
     * <p>
     * Si elle n'existe pas, on initie sa création.
     * Sinon, on retourne l'instance créée précédemment.
     *
     * @param environment l'environnement (production, testing, development...)
     * @return la connection au server sql.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getInstance(String environment) throws ClassNotFoundException, SQLException, IOException {
        if (conn.get(environment) == null) {
            new DatabaseConnection(environment);
        } // end if
        return conn.get(environment);
    }

    /**
     * Cette méthode permet de mettre à jour les attributs selon le fichier
     * de configuration config.properties disponible dans le fichier src.ressources,
     * soit src.ressources/config.properties
     * <p>
     * D'abord nous chargeons le fichier configuration sous forme de stream.
     * Ensuite, nous instancions un object java.utils.Properties que
     * nous chargerons avec ledit fichier configuration.
     * <p>
     * Suite à cela, les attributs (url, username et password) sont mise-à-jour
     * dans leur Hashmap respective (urlMap, usernameMap et passwordMap) en fonction
     * de l'environnement en parametre.
     * <p>
     * Attention ! Cette méthode est invoquée uniquement lors du premier appel
     * du singleton.
     *
     * @param environment l'environnement (production, testing, development...)
     * @throws IOException Impossible de trouver le fichier config.
     */
    private static void chargerAttribut(String environment) throws IOException {
        try {
            // Récupérons le classLoader de l'object
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            // Obtenons le fichier de configuration sous forme de stream, a partir de la
            // racine
            InputStream configFile = classLoader.getResourceAsStream("src/ressources/config.properties");

            // Déclarons un object Properties que nous nommerons config.
            Properties config = new Properties();

            // Chargeons config selon configFile
            config.load(configFile);

            // Chargeons les attributs dans leur hasmap respective
            String url = config.getProperty("db."
                + environment + ".url"
            );
            String username = config.getProperty("db."
                + environment + ".username"
            );
            String password = config.getProperty("db."
                + environment + ".password"
            );

            // Vérifions que ces variables existent bien.
            if (url == null || username == null || password == null) {
                throw new IOException(
                        "Impossible de recuperer la base de donnee de l'environnement "
                        + environment
                );
            } // end if

            // Inserons les dans leur hashmap respective.
            urlMap.put(environment, url);
            usernameMap.put(environment, username);
            passwordMap.put(environment, password);
        } catch (IOException e) {
            throw new IOException(e);
        } // end try/catch
    }

    /**
     * Cette methode permet la fermeture d'une connection identifable par la
     * variable environment.
     * Enfin l'on reset l'attribut connection associé.
     *
     * @param environment la connection qui doit etre close.
     * @throws SQLException la connexion n'existe pas.
     */
    public static void close(String environment) throws SQLException {
        if (conn.get(environment) != null) {
            conn.get(environment).close();
            conn.remove(environment);
        } // end if
    }
}