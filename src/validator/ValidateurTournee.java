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
     * @param commandes Les commandes de la Tournee, dans l'ordre de livraison
     * @param gpsProd   Les coordonnées GPS du Producteur
     * @return Un booléen qui indique la validité du trajet associé aux commandes
     */
    public static boolean valideSuiteCommandes(ArrayList<Commande> commandes, String gpsProd) {
        ArrayList<String[]> coordsGPS = new ArrayList<>();
        coordsGPS.add(gpsProd.split(","));
        coordsGPS.addAll(commandes.stream().map(clt -> clt.getClient().getGpsClient().split(",")).toList());
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("coordinates", gson.toJsonTree(coordsGPS));

        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openrouteservice.org/v2/directions/driving-car/json"))
                    .header("Authorization", ConfigHelper.get("ORS_KEY"))
                    .header("Accept",
                            "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
                    .header("Content-Type", "application/json; charset=utf-8")
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(jsonObject))).build();
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        Iterator<Commande> itCmd = commandes.iterator();
        Iterator<JsonElement> itSegm = gson.fromJson(response.body(), JsonObject.class).get("routes").getAsJsonArray()
                .get(0).getAsJsonObject().get("segments").getAsJsonArray().iterator();

        // Horaire de départ
        Timestamp horaireTmp = new Timestamp(itCmd.next().getHoraireDebut().getTime()
                - itSegm.next().getAsJsonObject().get("duration").getAsLong());

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

        // Pour quand on factorisera
        // horaireTmp = new Timestamp(horaireTmp.getTime() +
        // itSegm.next().getAsJsonObject().get("duration").getAsLong());

        return true;
    }
}