package validator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import exceptions.AdresseInvalideException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * Wrapper vers l'API de géocodage https://api-adresse.data.gouv.fr
 * Ici l'on utilise l'endpoint search pour récupérer des coords GSP à partir
 * d'une adresse.
 * <p>
 * Si l'adresse est valide, on pourra récupérer les différentes informations via
 * les getter.
 */
public class ValidateurAdresse {
    private final String BASE_URL = "https://api-adresse.data.gouv.fr";
    private final String ENDPOINT = "/search/";

    private final double SCORE_MIN = 0.85;
    private String codePostale;
    private String ville;
    private String label;
    private String csv;
    private double score;
    private String coordX;
    private String coordY;
    private String coordXY;

    /**
     * Vérifie la validité d'une adresse en fonction d'un score de pertinence.
     * On vérifie aussi que la ville et le code postal récupéré dans la réponse sont
     * identiques au
     * résultat fourni.
     * Au minima, la ville et le code postal est précis.
     * <p>
     * Si les résultats sont cohérents, on pourra récupérer les résultats via les
     * getters.
     * 
     * @param rue         la rue fourni
     * @param codePostale le code postal fourni
     * @param ville       la ville fourni
     * @throws AdresseInvalideException
     */
    private ValidateurAdresse(String numeroRue, String typeRue, String nomRue, String ville, String codePostale)
            throws AdresseInvalideException {
        // Récupérons le résultat de la requête
        JsonObject objetJson = makeRequest(makeURI(
                numeroRue.concat(" ").concat(typeRue).concat(" ").concat(nomRue), codePostale, ville));

        // Si le résultat est nul, l'adresse est invalide.
        if (objetJson.isJsonNull()) {
            throw new AdresseInvalideException("Impossible de récupérer cette adresse");
        }

        // Remplissions les attributs.
        fillAttributs(numeroRue, typeRue, nomRue, objetJson);

        // Si le score est trop faible, le résultat n'est pas pertinent.
        if (this.score < SCORE_MIN) {
            throw new AdresseInvalideException("Résultats non pertinent.");
        }

        // Si la ville ou le code postale récupéré ne sont pas ceux fournis, il y a
        // incohérence.
        if (!this.ville.equalsIgnoreCase(ville) || !this.codePostale.equals(codePostale)) {
            throw new AdresseInvalideException("La ville ne correspond pas.");
        }
    }

    /**
     * Wrapper Requete HTTP GET dont la réponse est sous format json.
     *
     * @param uri un objet URI représentant l'URL de la requête HTTP.
     * @return un JsonObject permettant de manipuler le contenu de la réponse.
     */
    private JsonObject makeRequest(URI uri) {
        // Créons une requête HTTP.
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header("accept", "application/json")
                .build();

        // Récupérons la réponse sous forme de String.
        HttpResponse<String> response = null;
        try {
            response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // On crée un JsonObject manipulable.
        return new Gson().fromJson(response.body(), JsonObject.class);
    }

    /**
     * Rempli les attributs lors de la création de l'objet.
     */
    private void fillAttributs(String numeroRue, String typeRue, String nomRue, JsonObject objetJson) {
        // Récupérons les coordonnées.
        JsonArray coords = objetJson.get("features")
                .getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject()
                .get("coordinates").getAsJsonArray();
        this.coordX = coords.get(1).getAsString();
        this.coordY = coords.get(0).getAsString();
        this.coordXY = coordY + "," + coordX;

        // Récupérons les informations sur le lieu
        JsonObject infos = objetJson.get("features")
                .getAsJsonArray().get(0).getAsJsonObject().get("properties").getAsJsonObject();
        this.codePostale = infos.get("postcode").getAsString();
        this.ville = infos.get("city").getAsString();

        label = numeroRue.concat(" ").concat(typeRue).concat(" ").concat(nomRue).concat(" ").concat(ville).concat(" ")
                .concat(codePostale);

        csv = numeroRue.concat(",").concat(typeRue).concat(",").concat(nomRue).concat(",").concat(ville).concat(",")
                .concat(codePostale);

        this.score = infos.get("score").getAsDouble();
    }

    /**
     * Initie l'instanciation d'un objet adresse valide et
     * provoque une erreur s'il est impossible de récupérer l'adresse.
     *
     * @param address l'adresse complète au format csv
     * @return un objet AdresseValide
     * @throws AdresseInvalideException l'adresse est invalide.
     */
    public static ValidateurAdresse unpack(String address) throws AdresseInvalideException {
        String[] addressElement = address.split(",");

        return new ValidateurAdresse(addressElement[0], addressElement[1], addressElement[2], addressElement[3],
                addressElement[4]);
    }

    /**
     * Initie l'instanciation d'un objet adresse valide et
     * provoque une erreur s'il est impossible de récupérer l'adresse.
     *
     * @param address l'adresse complète au format csv
     * @return un objet AdresseValide
     * @throws AdresseInvalideException l'adresse est invalide.
     */
    public static ValidateurAdresse create(String numeroRue, String typeRue, String nomRue, String ville,
            String codePostale) throws AdresseInvalideException {

        return new ValidateurAdresse(numeroRue, typeRue, nomRue, ville, codePostale);
    }

    /**
     * Encode les paramètres rue, codePostale et ville selon les spécifications de
     * l'API.
     * <p>
     * 
     * @param rue         la rue
     * @param codePostale le code postal
     * @param ville       la ville
     * @return URI une url encodé dans le bon format.
     */
    private URI makeURI(String rue, String codePostale, String ville) {
        // On formate l'URL
        String query = "?q="
                + URLEncoder.encode(rue, StandardCharsets.UTF_8)
                + "&ville=" + URLEncoder.encode(ville, StandardCharsets.UTF_8)
                + "&postcode=" + URLEncoder.encode(codePostale, StandardCharsets.UTF_8);

        return URI.create(BASE_URL + ENDPOINT + query);
    }

    /**
     * Renvoie le coordonné X de l'adresse.
     * 
     * @return le coordonné x.
     */
    public String getCoordX() {
        return coordX;
    }

    /**
     * Renvoie le coordonné Y de l'adresse.
     * 
     * @return le coordonné y.
     */
    public String getCoordY() {
        return coordY;
    }

    /**
     * Renvoie les coordonnés X et Y séparés par une virgule.
     * 
     * @return le couple de coordonnés X, Y au format x,y sous forme de string.
     */
    public String getCoordXY() {
        return coordXY;
    }

    /**
     * Renvoie l'adresse au format RUE, Code Postale, Ville
     * 
     * @return l'adresse bien formattée.
     */
    public String format() {
        return label;
    }

    /**
     * Renvoie l'adresse au format CSV
     * 
     * @return l'adresse au format CSV.
     */
    public String csv() {
        return csv;
    }
}
