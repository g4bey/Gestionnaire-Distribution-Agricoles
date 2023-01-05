package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Méthode permettant de récupérer les valeurs associées aux clefs
 * dans config.properties situé dans le dossier ressources.
 */
public class ConfigHelper {
    private static final String PATH_TO_CONFIG = "ressources/config.properties";

    /**
     * Permet de retourner un attribut dans le fichier config.properties
     * <p>
     * @param key La clef de l'attribut recherché
     * @return La valeur de l'attribut.
     * @throws IOException Impossible de trouver le fichier de configuration
     */
    public static String get(String key) throws IOException {
        // Récupérons le classLoader de l'objet
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // Obtenons le fichier de configuration sous forme de stream, à partir de la racine
        InputStream configFile = classLoader.getResourceAsStream(PATH_TO_CONFIG);

        // Déclarons un object Properties que nous nommerons config.
        Properties config = new Properties();

        // Chargeons config selon configFile
        config.load(configFile);

        // Retournons l'attribut
        return config.getProperty(key);
    } // get

} // ConfigHelper
