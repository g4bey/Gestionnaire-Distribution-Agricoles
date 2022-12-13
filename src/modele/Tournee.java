package modele;

import java.util.*;



/**
* Métier représentant une Tournée.
*/

public class Tournee extends ModeleConcret {
    private int idTournee;

    private String horaireDebut;

    private String horaireFin;

    private float poids;

    private Vehicule vehicule;

    private List<Commande> commandes = new ArrayList<>();

    private int getIdTournee() {
        return idTournee;
    }

    private String getHoraireDebut() {
        return horaireDebut;
    }

    private String getHoraireFin() {
        return horaireFin;
    }

    private float getPoids() {
        return poids;
    }

    private Vehicule getVehicule() {
        return vehicule;
    }

    private List<Commande> getCommandes() {
        return commandes;
    }

    private void setIdTournee(int idTournee) {
        this.idTournee = idTournee;
    }

    private void setHoraireDebut(String horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    private void setHoraireFin(String horaireFin) {
        this.horaireFin = horaireFin;
    }

    private void setPoids(float poids) {
        this.poids = poids;
    }

    private void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    /**
    * Constructeur de Tournee.
    * @param idTournee int représentant l'id de la Tournee.
    * @param horaireDebut String représentant l'horaire de début de la Tournee.
    * @param horaireFin String représentant l'horaire de fin de la Tournee.
    * @param poids float représentant le poids total de la Tournee.
    * @param vehicule Vehicule représentant le véhicule utilisé pour effectuer la Tournee.
    * @param commandes ArrayList<Commande> représentant les commandes composant la Tournee.
    */

    public Tournee(int idTournee, String horaireDebut, String horaireFin, float poids, Vehicule vehicule, List<Commande> commandes) {
    }
}