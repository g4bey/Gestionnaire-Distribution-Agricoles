package utility;

import modele.Tournee;

public class GenerateurUrlTournee {

    /**
     * Permet de générer l'URL nécessaire à la WebView lors de l'affichage d'une Tournée
     * @param tournee La Tournée à afficher
     * @return L'URL de la map à afficher
     */
    public String AffichageTourneeUrl(Tournee tournee) {
        // L'adresse du Producteur est à la fois point de départ et point d'arrivée
        String adresseProd = tournee.getVehicule().getProducteur().getGpsProd();

        StringBuilder wayPoints = new StringBuilder();

        // On récupère pour chaque Commande les coordonnées du Client lié
        for (int i = 0; i < tournee.getCommandes().size(); i++) {
            wayPoints.append(tournee.getCommandes().get(i).getClient().getGpsClient());

            // On ajoute les "espaces" entre chaque coordonnée
            if (i != tournee.getCommandes().size() - 1) {
                wayPoints.append("%7C");
            }
        } // end for

        return "https://www.google.com/maps/dir/?api=1&origin=" + adresseProd + "&destination="
            + adresseProd + "&waypoints=" + wayPoints;
    }
}
