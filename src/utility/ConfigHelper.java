package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Méthode permettant de récupérer les valeurs associées aux clefs
 * dans config.property situé dans le fichier ressource.
 */
public class ConfigHelper {
    private static final String pathToConfig = "ressources/config.properties";

    /**
     * Permet de retourner un attribut dans le fichier config.properties
     * <p>
     * @param key la clef de l'attribut recherché
     * @return la valeur de l'attribut.
     * @throws IOException impossible de trouver le fichier de configuration
     */
    public static String get(String key) throws IOException {
        // Récupérons le classLoader de l'object
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Obtenons le fichier de configuration sous forme de stream, à partir de la racine
        InputStream configFile = classLoader.getResourceAsStream(pathToConfig);

        // Déclarons un object Properties que nous nommerons config.
        Properties config = new Properties();

        // Chargeons config selon configFile
        config.load(configFile);

        // Retournons l'attribut
        return config.getProperty(key);
    }
}
