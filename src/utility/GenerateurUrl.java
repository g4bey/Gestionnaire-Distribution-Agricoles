package utility;

import modele.Commande;
import modele.Tournee;

public class GenerateurUrl {

    /**
     * Permet de générer l'URL nécessaire à la WebView lors de l'affichage d'une Tournée
     * @param tournee La Tournée à afficher
     * @return L'URL de la map à afficher
     */
    public static String AffichageTourneeUrl(Tournee tournee) {
        // L'adresse du Producteur est à la fois point de départ et point d'arrivée
        String gps1Prod = tournee.getVehicule().getProducteur().getGpsProd().split(",")[1];
        String gps2Prod = tournee.getVehicule().getProducteur().getGpsProd().split(",")[0];
        String adresseProd = gps1Prod + "," + gps2Prod;

        StringBuilder wayPoints = new StringBuilder();

        // On récupère pour chaque Commande les coordonnées du Client lié
        for (int i = 0; i < tournee.getCommandes().size(); i++) {
            String gps1 = tournee.getCommandes().get(i).getClient().getGpsClient().split(",")[1];
            String gps2 = tournee.getCommandes().get(i).getClient().getGpsClient().split(",")[0];
            wayPoints.append(gps1).append(",").append(gps2);

            // On ajoute les "espaces" entre chaque coordonnée
            if (i != tournee.getCommandes().size() - 1) {
                wayPoints.append("%7C");
            } // if
        } //for

        return "https://www.google.com/maps/dir/?api=1&origin=" + adresseProd + "&destination="
            + adresseProd + "&waypoints=" + wayPoints;
    } // AffichageTourneeUrl

    public static String AffichageCommandeUrl(Commande commande) {
        String gps1 = commande.getClient().getGpsClient().split(",")[1];
        String gps2 = commande.getClient().getGpsClient().split(",")[0];
        return "https://www.google.com/maps/search/?api=1&query=" + gps1 + "," + gps2;
    } // AffichageCommandeUrl

} // GenerateurUrl
