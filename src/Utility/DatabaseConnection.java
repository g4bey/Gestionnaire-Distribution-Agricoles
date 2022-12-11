package GDA.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Fournit une connection unique à la base de donnée.
 * Cette dernière est créée si elle n'existe pas déjà.
 * <p>
 * L'on présuppose l'existence d'un fichier config.properties
 * Dans ce dernier seront déclarées
 * + db.environment.url : l'url de la base.
 * + db.environment.login : le login de la base
 * + db.environment.password : le password de la base
 * Ou l'environnement permet d'identifier les tuples.
 * <p>
 * Ainsi, pour les tests, l'on aura par exemple:
 * + db.testing.url=root
 * + db.testing.login=root
 * + db.testing.password=jdbc:mysql://localhost/gestAgricoleTest
 */
public class DatabaseConnection {
    private static Connection conn;
    private static String url;
    private static String username;
    private static String password;

    /**
     * Création de la connection à l'aide de driver manager.
     * L'on commence par charger les attributs url, username et password à
     * partir du fichier config.properties, et de l'environnement fournit
     * en paramètre.
     *
     * @param environment l'environnement (production, testing, development...)
     */
    private DatabaseConnection(String environment) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        chargerAttribut(environment);
        conn = DriverManager.getConnection(url, username, password);
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
     */
    public static Connection getInstance(String environment) throws ClassNotFoundException, SQLException, IOException {
        if(conn == null){
            new DatabaseConnection(environment);
        }
        return conn;
    }

    /**
     * Cette méthode permet de mettre à jour les attributs selon le fichier
     * de configuration config.properties disponible à la racine du projet,
     * soit src/config.properties
     * <p>
     * D'abord nous chargeons le fichier configuration sous forme de stream.
     * Ensuite, nous instancions un object java.utils.Properties que
     * nous chargerons avec ledit fichier configuration.
     * <p>
     * Suite à cela, les attributs (url, login et password) sont mise-à-jour
     * en fonction de l'environnement passé en paramètres.
     * <p>
     * Attention ! Cette méthode est invoquée uniquement lors du premier appel
     * du singleton.
     *
     * @throws IOException Impossible de trouver le fichier config.
     */
    private static void chargerAttribut(String environment) throws IOException {
        try {
            // Obtenons le fichier de configuration sous forme de stream
            FileInputStream configFile = new FileInputStream("config.properties");

            // Déclarons un object Properties que nous nommerons config.
            Properties config = new Properties();

            // Chargeons config selon configFile
            config.load(configFile);

            // Chargeons les attributs
            url = config.getProperty("db." + environment + ".url");
            username = config.getProperty("db." + environment + ".username");
            password = config.getProperty("db." + environment + ".password");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}