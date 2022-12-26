package validator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import exceptions.InvalidRouteException;
import modele.Commande;
import modele.Vehicule;
import utility.ConfigHelper;

/**
 * Valide ou pas la tournée paramétrée.
 */
public class ValidateurTournee {
    /**
     * Vérifie que le Vehicule n'est pas déjà utilisé lors de la livraison
     * 
     * @param vehicule     Le Vehicule que l'on veut vérifier
     * @param horaireDebut L'horaire de début de la Tournee
     * @param horaireFin   L'horaire de fin de la Tournee
     * @return Un booléen qui indique la disponibilité du Vehicule pour les horaires
     */
    public static boolean valideVehicule(Vehicule vehicule, Timestamp horaireDebut, Timestamp horaireFin) {
        return !vehicule.getTournees().stream()
                .anyMatch(t -> t.getHoraireDebut().before(horaireFin) && t.getHoraireFin().after(horaireDebut));
    }

    /**
     * Vérifie que le poids maximum du Vehicule est compatible avec les commandes
     * 
     * @param poidsMax  Le poids maximum du Vehicule que l'on veut vérifier
     * @param commandes Les commandes de la Tournee
     * @return Un booléen qui indique si le Vehicule peut accueillir un tel poids
     */
    public static boolean validePoids(float poidsMax, ArrayList<Commande> commandes) {
        return poidsMax >= commandes.stream().map(c -> c.getPoids()).reduce(0.F, Float::sum);
    }

    /**
     * Vérifie que l'ordre dans lequel les commandes sont livrées permet de
     * respecter les horaires de chacune
     * 
     * @param itCmd      Itérateur des commandes dans l'ordre
     * @param itSegm     Itérateur des segments composant le trajet
     * @param horaireTmp Timestamp représentant l'horaire qui évolue au cours du
     *                   trajet
     * @return Un booléen qui indique la validité du trajet associé aux commandes
     */
    public static boolean valideSuiteCommandes(Iterator<Commande> itCmd, Iterator<JsonElement> itSegm,
            Timestamp horaireTmp) {
        while (itCmd.hasNext()) {
            horaireTmp = new Timestamp(
                    horaireTmp.getTime() + itSegm.next().getAsJsonObject().get("duration").getAsLong());

            Commande commande = itCmd.next();

            // Vérifie qu'il n'arrive pas après l'heure de fin
            if (horaireTmp.after(commande.getHoraireFin())) {
                return false;
            }

            // S'il arrive avant l'heure de début, il attend
            if (horaireTmp.before(commande.getHoraireDebut())) {
                horaireTmp = commande.getHoraireDebut();
            }
        }

        return true;
    }

    /**
     * Calcul les horaires de départ et d'arrivée du trajet associé à la suite de
     * commandes
     * 
     * @param commandes Les commandes de la Tournee, dans l'ordre de livraison
     * @param gpsProd   Les coordonnées GPS du Producteur
     * @return Un couple de Timestamp représentant l'heure de départ et l'heure
     *         d'arrivée
     * @throws Exception
     */
    public static Timestamp[] calculTournee(ArrayList<Commande> commandes, String gpsProd)
            throws IOException, InterruptedException, InvalidRouteException {
        String[] coordsGPSProd = gpsProd.split(",");

        ArrayList<String[]> coordsGPS = new ArrayList<>();
        coordsGPS.add(coordsGPSProd);
        coordsGPS.addAll(commandes.stream().map(clt -> clt.getClient().getGpsClient().split(",")).toList());
        coordsGPS.add(coordsGPSProd);

        Gson gson = new Gson();

        JsonObject jsonObjectCoordsGPS = new JsonObject();
        jsonObjectCoordsGPS.add("coordinates", gson.toJsonTree(coordsGPS));

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openrouteservice.org/v2/directions/driving-car/json"))
                    .header("Authorization", ConfigHelper.get("ORS_KEY"))
                    .header("Accept",
                            "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
                    .header("Content-Type", "application/json; charset=utf-8")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(jsonObjectCoordsGPS))).build();
        } catch (IOException e1) {
            throw e1;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
        }

        Iterator<Commande> itCmd = commandes.iterator();
        Iterator<JsonElement> itSegm = gson.fromJson(response.body(), JsonObject.class).get("routes").getAsJsonArray()
                .get(0).getAsJsonObject().get("segments").getAsJsonArray().iterator();

        Timestamp horaireDebut = new Timestamp(itCmd.next().getHoraireDebut().getTime()
                - itSegm.next().getAsJsonObject().get("duration").getAsLong());

        Timestamp horaireTmp = new Timestamp(horaireDebut.getTime());

        if (!valideSuiteCommandes(itCmd, itSegm, horaireTmp)) {
            throw new InvalidRouteException("Le trajet généré ne respecte pas les horaires des commandes !");
        }

        // On calcul la fin du trajet en ajoutant la durée pour rentrer au dépôt
        horaireTmp = new Timestamp(horaireTmp.getTime() + itSegm.next().getAsJsonObject().get("duration").getAsLong());

        return new Timestamp[] { horaireDebut, horaireTmp };
    }
}