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
import java.util.HashMap;

/**
 * Wrapper vers l'API de géocodage https://api-adresse.data.gouv.fr
 * Ici l'on utilise l'endpoint search pour récupérer des coords GSP à partir
 * d'une adresse.
 * <p>
 * Si l'adresse est valide, on pourra récupérer les différentes informations via
 * les getters.
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

    // Le paramètre de type String est l'adresse au format CSV par formatCSV()
    private static HashMap<String, ValidateurAdresse> adressesValide = new HashMap<>();

    /**
     * Vérifie la validité d'une adresse en fonction d'un score de pertinence.
     * On vérifie aussi que la ville et le code postal récupérés dans la réponse sont
     * identiques au résultat fourni.
     * A minima, la ville et le code postal est précis.
     * <p>
     * Si les résultats sont cohérents, on pourra récupérer les résultats via les
     * getters.
     * 
     * @param numeroRue   Le numéro de rue fourni
     * @param typeRue     Le type de rue fourni
     * @param nomRue      Le nom rue fourni
     * @param codePostal  Le code postal fourni
     * @param ville       La ville fournie
     * @throws AdresseInvalideException
     */
    private ValidateurAdresse(String numeroRue, String typeRue, String nomRue, String ville, String codePostal)
            throws AdresseInvalideException {
        // Récupérons le résultat de la requête
        JsonObject objetJson = makeRequest(makeURI(
                numeroRue.concat(" ").concat(typeRue).concat(" ").concat(nomRue), codePostal, ville));

        // Si le résultat est null, l'adresse est invalide.
        if (objetJson.isJsonNull() || objetJson.get("features").getAsJsonArray().isEmpty()) {
            throw new AdresseInvalideException("Impossible de récupérer cette adresse");
        } // if

        // Remplissons les attributs.
        fillAttributs(numeroRue, typeRue, nomRue, objetJson);

        // Si le score est trop faible, le résultat n'est pas pertinent.
        if (this.score < SCORE_MIN) {
            throw new AdresseInvalideException("Résultats non pertinent.");
        } // if

        // Si la ville ou le code postal récupéré ne sont pas ceux fournis, il y a
        // incohérence.
        if (!this.ville.equalsIgnoreCase(ville) || !this.codePostale.equals(codePostal)) {
            throw new AdresseInvalideException("La ville ne correspond pas.");
        } // if
    } // ValidateurAdresse

    /**
     * Wrapper requête HTTP GET dont la réponse est sous format Json.
     *
     * @param uri Un objet URI représentant l'URL de la requête HTTP.
     * @return Un JsonObject permettant de manipuler le contenu de la réponse.
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
        } // try/catch

        // On crée un JsonObject manipulable.
        return new Gson().fromJson(response.body(), JsonObject.class);
    } // makeRequest

    /**
     * Remplit les attributs lors de la création de l'objet.
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

        csv = formatCSV(numeroRue, typeRue, nomRue, ville, codePostale);

        this.score = infos.get("score").getAsDouble();
    } // fillAttributs

    /**
     * Formate les paramètres au format CSV.
     * @param numeroRue Le numéro de rue.
     * @param typeRue Le type de rue.
     * @param nomRue Le nom de la rue.
     * @param ville La ville.
     * @param codePostal le code postal.
     * @return L'adresse au format CSV.
     */
    private static String formatCSV(String numeroRue, String typeRue, String nomRue, String ville, String codePostal) {
        return numeroRue.concat(",").concat(typeRue)
                .concat(",").concat(nomRue)
                .concat(",").concat(ville)
                .concat(",").concat(codePostal);
    } // formatCSV

    /**
     * Initie l'instanciation d'un objet adresse valide et
     * provoque une erreur s'il est impossible de récupérer l'adresse.
     * <p>
     * Si l'adresse a deja été calculée, on renvoie l'objet ValidateurAdresse existant.
     *
     * @param numeroRue Le numéro de rue.
     * @param typeRue Le type de rue.
     * @param nomRue Le nom de la rue.
     * @param ville La ville.
     * @param codePostal Le code postal.
     * @return Un objet ValidateurAdresse
     * @throws AdresseInvalideException L'adresse est invalide.
     */
    public static ValidateurAdresse create(String numeroRue, String typeRue, String nomRue, String ville,
            String codePostal) throws AdresseInvalideException {
        String adresseCSV = formatCSV(numeroRue, typeRue, nomRue, ville, codePostal);

        // Si l'adresse a deja été calculée lors de cette session,
        // on retourne l'adresse existante
        if (adressesValide.containsKey(adresseCSV)) {
            return adressesValide.get(adresseCSV);
        } // if

        // Sinon, on insère cet objet ValidateurAdresse dans l'HashMap.
        ValidateurAdresse adresseValide =  new ValidateurAdresse(numeroRue, typeRue, nomRue, ville, codePostal);
        adressesValide.put(adresseCSV, adresseValide);
        return adresseValide;
    } // create

    /**
     * Encode les paramètres rue, codePostal et ville selon les spécifications de
     * l'API.
     * <p>
     * 
     * @param rue         La rue
     * @param codePostal Le code postal
     * @param ville       La ville
     * @return URI Une URL encodée dans le bon format.
     */
    private URI makeURI(String rue, String codePostal, String ville) {
        // On formate l'URL
        String query = "?q="
                + URLEncoder.encode(rue, StandardCharsets.UTF_8)
                + "&ville=" + URLEncoder.encode(ville, StandardCharsets.UTF_8)
                + "&postcode=" + URLEncoder.encode(codePostal, StandardCharsets.UTF_8);

        return URI.create(BASE_URL + ENDPOINT + query);
    } // makeURI

    /**
     * Renvoie la coordonnée X de l'adresse.
     * 
     * @return La coordonnée X.
     */
    public String getCoordX() {
        return coordX;
    } // getCoordX

    /**
     * Renvoie la coordonnée Y de l'adresse.
     * 
     * @return La coordonnée Y.
     */
    public String getCoordY() {
        return coordY;
    } // getCoordY

    /**
     * Renvoie les coordonnées X et Y séparées par une virgule.
     * 
     * @return Le couple de coordonnés X, Y au format X,Y sous forme de String.
     */
    public String getCoordXY() {
        return coordXY;
    } // getCoordXY

    /**
     * Renvoie l'adresse au format Rue, Code Postal, Ville
     * 
     * @return l'adresse bien formatée.
     */
    public String format() {
        return label;
    } // format

    /**
     * Renvoie l'adresse au format CSV
     * 
     * @return L'adresse au format CSV.
     */
    public String csv() {
        return csv;
    } // csv
}
