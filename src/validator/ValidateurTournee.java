package validator;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Time;
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
 * Permet de valider ou non une Tournée.
 */
public class ValidateurTournee {
    /**
     * Vérifie que le Véhicule n'est pas déjà utilisé lors de la livraison.
     * 
     * @param vehicule     Le Véhicule que l'on veut vérifier
     * @param horaireDebut L'horaire de début de la Tournée
     * @param horaireFin   L'horaire de fin de la Tournée
     * @return Un booléen qui indique la disponibilité du Véhicule pour les horaires
     */
    public static boolean valideVehicule(Vehicule vehicule, Timestamp horaireDebut, Timestamp horaireFin) {
        return !vehicule.getTournees().stream()
                .anyMatch(t -> t.getHoraireDebut().before(horaireFin) && t.getHoraireFin().after(horaireDebut));
    } // valideVehicule

    /**
     * Vérifie que le poids maximum du Véhicule est compatible avec les commandes
     * 
     * @param poidsMax  Le poids maximum du Véhicule que l'on veut vérifier
     * @param commandes Les commandes de la Tournée
     * @return Un booléen qui indique si le Véhicule peut accueillir un tel poids
     */
    public static boolean validePoids(float poidsMax, ArrayList<Commande> commandes) {
        return poidsMax >= commandes.stream().map(c -> c.getPoids()).reduce(0.F, Float::sum);
    } // validePoids

    /**
     * Vérifie que l'ordre dans lequel les Commandes sont livrées permet de
     * respecter les horaires de chacune
     * 
     * @param itCmd      Itérateur des Commandes dans l'ordre
     * @param itSegm     Itérateur des segments composant le trajet
     * @param horaireTmp Timestamp représentant l'horaire qui évolue au cours du
     *                   trajet
     * @return Un booléen qui indique la validité du trajet associé aux commandes
     */
    private static Timestamp valideSuiteCommandes(Iterator<Commande> itCmd, Iterator<JsonElement> itSegm,
            Timestamp horaireTmp) {
        while (itCmd.hasNext()) {
            horaireTmp = new Timestamp(
                    horaireTmp.getTime() + itSegm.next().getAsJsonObject().get("duration").getAsLong() * 1000);

            Commande commande = itCmd.next();

            // Vérifie qu'il n'arrive pas après l'heure de fin
            if (horaireTmp.after(commande.getHoraireFin())) {
                return null;
            } // if

            // S'il arrive avant l'heure de début, il attend
            if (horaireTmp.before(commande.getHoraireDebut())) {
                horaireTmp = new Timestamp(commande.getHoraireDebut().getTime());
            } // if
        } // while

        return horaireTmp;
    } // valideSuiteCommandes

    /**
     * Calcule les horaires de départ et d'arrivée du trajet associé à la suite de
     * Commandes
     * 
     * @param commandes Les Commandes de la Tournée, dans l'ordre de livraison
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
        } // try/catch

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw e;
        } // try/catch

        Iterator<Commande> itCmd = commandes.iterator();
        Iterator<JsonElement> itSegm = gson.fromJson(response.body(), JsonObject.class).get("routes").getAsJsonArray()
                .get(0).getAsJsonObject().get("segments").getAsJsonArray().iterator();

        long premiereEtape = itCmd.next().getHoraireDebut().getTime();

        Timestamp horaireDebut = new Timestamp(
                premiereEtape - itSegm.next().getAsJsonObject().get("duration").getAsLong() * 1000);
        Timestamp horaireTmp = new Timestamp(premiereEtape);

        horaireTmp = valideSuiteCommandes(itCmd, itSegm, horaireTmp);
        if (horaireTmp == null) {
            throw new InvalidRouteException("Le trajet généré ne respecte pas les horaires des commandes !");
        } // if

        // On calcule la fin du trajet en ajoutant la durée pour rentrer au dépôt
        horaireTmp = new Timestamp(
                horaireTmp.getTime() + itSegm.next().getAsJsonObject().get("duration").getAsLong() * 1000);

        // Vérifie qu'il n'arrive pas après l'heure de fin, et que ce n'est pas la dernière commande.
        if (horaireTmp.after(commandes.get(commandes.size() - 1).getHoraireFin()) && itCmd.hasNext()) {
            throw new InvalidRouteException("Le trajet généré ne respecte pas les horaires des commandes !");
        } // if



        return new Timestamp[] { horaireDebut, horaireTmp };
    } // calculTournee

} // ValidateurTournee