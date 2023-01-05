package utility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Fournit une Connection unique à la base de données.
 * Cette dernière est créée si elle n'existe pas déjà.
 * <p>
 * On identifie chaque Connection par son attribut environnement,
 * dans l'HasMap conn.
 * <p>
 * On présuppose l'existence d'un fichier config.properties
 * Dans ce dernier seront déclarées
 * + db.environment.url : l'URL de la base.
 * + db.environment.login : Le login de la base
 * + db.environment.password : Le password de la base
 * Où l'environnement permet d'identifier les tuples.
 * <p>
 * Ainsi, pour les tests, on aura par exemple:
 * + db.testing.url=root
 * + db.testing.login=root
 * + db.testing.password=jdbc:mysql://localhost/gestAgricoleTest
 * <p>
 * L'aura chargera donc
 * urlMap<testing, root>
 * usernameMap<testing, root>
 * passwordMap<testing, jdbc:mysql://localhost/gestAgricoleTest>
 * <p>
 * Et dans Connection on aura :
 * conn<testing, <instance@DatabaseConnection>
 */
public final class DatabaseConnection {

    private static HashMap<String, String> urlMap = new HashMap<>();
    private static HashMap<String, String> usernameMap = new HashMap<>();
    private static HashMap<String, String> passwordMap = new HashMap<>();
    private static HashMap<String, Connection> conn = new HashMap<>();

    /**
     * Création de la Connection à l'aide de DriverManager.
     * On commence par charger les attributs URL, username et password à
     * partir du fichier config.properties, et de l'environnement fourni
     * en paramètre.
     *
     * Ensuite, on instancie la Connection, puis on l'insère dans une HashMap
     * avec comme identifiant l'environnement.
     *
     * @param environment L'environnement (production, testing, development...)
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
                passwordMap.get(environment));
        conn.put(environment, dbConn);
    } // DatabaseConnection

    /**
     * Cette méthode permet la récupération de l'Instance de Connection au
     * serveur de base de données.
     * <p>
     * Si elle n'existe pas, on initie sa création.
     * Sinon, on retourne l'Instance créée précédemment.
     *
     * @param environment L'environnement (production, testing, development...)
     * @return La Connection à la base de données.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static Connection getInstance(String environment) throws ClassNotFoundException, SQLException, IOException {
        if (conn.get(environment) == null) {
            new DatabaseConnection(environment);
        } // end if
        return conn.get(environment);
    } // getInstance

    /**
     * Cette méthode permet de mettre à jour les attributs selon le fichier
     * de configuration config.properties disponible dans le fichier ressources,
     * soit ressources/config.properties
     * <p>
     * D'abord on charge le fichier configuration sous forme de stream.
     * Ensuite, on instancie un object java.utils.Properties que
     * l'on chargera avec fichier configuration.
     * <p>
     * Suite à cela, les attributs (URL, username et password) sont mis à jour
     * dans leurs HashMaps respectives (urlMap, usernameMap et passwordMap) en fonction
     * de l'environnement en paramètre.
     * <p>
     * Attention ! Cette méthode est invoquée uniquement lors du premier appel
     * du Singleton.
     *
     * @param environment L'environnement (production, testing, development...)
     * @throws IOException Impossible de trouver le fichier config.
     */
    private static void chargerAttribut(String environment) throws IOException {
        try {
            // Chargeons les attributs dans leur hasmap respective
            String url = ConfigHelper.get("db." + environment + ".url");
            String username = ConfigHelper.get("db." + environment + ".username");
            String password = ConfigHelper.get("db." + environment + ".password");

            // Vérifions que ces variables existent bien.
            if (url == null || username == null || password == null) {
                throw new IOException(
                        "Impossible de récupérer la base de données de l'environnement "
                                + environment);
            } // if

            // Insérons-les dans leurs HashMaps respectives.
            urlMap.put(environment, url);
            usernameMap.put(environment, username);
            passwordMap.put(environment, password);
        } catch (IOException e) {
            throw new IOException(e);
        } // try/catch
    }

    /**
     * Cette méthode permet la fermeture d'une Connection identifiable par la
     * variable environment.
     * Enfin, on reset l'attribut Connection associé.
     *
     * @param environment La connection qui doit être fermée.
     * @throws SQLException La connexion n'existe pas.
     */
    public static void close(String environment) throws SQLException {
        if (conn.get(environment) != null) {
            conn.get(environment).close();
            conn.remove(environment);
        } // if
    } // close

} // DatabaseConnection