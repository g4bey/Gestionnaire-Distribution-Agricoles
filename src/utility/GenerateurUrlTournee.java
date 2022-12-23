package utility;

import modele.Tournee;

public class GenerateurUrlTournee {

    public String AffichageTourneeUrl(Tournee tournee) {
        String adresseProd = tournee.getVehicule().getProducteur().getGpsProd();

        StringBuilder wayPoints = new StringBuilder();

        for (int i = 0; i < tournee.getCommandes().size(); i++) {
            wayPoints.append(tournee.getCommandes().get(i).getClient().getGpsClient());

            if (i != tournee.getCommandes().size() - 1) {
                wayPoints.append("%7C");
            }
        }

        return "https://www.google.com/maps/dir/?api=1&origin=" + adresseProd + "&destination="
            + adresseProd + "&waypoints=" + wayPoints;
    }
}
