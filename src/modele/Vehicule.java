package modele;

import java.util.*;

/**
 * Objet métier représentant un Véhicule.
 */

public class Vehicule extends ModeleConcret {
    private int idVehicule;

    private String numImmat;

    private float poidsMax;

    private List<Tournee> tournees = new ArrayList<>();

    private Producteur producteur;

    private int getIdVehicule() {
        return idVehicule;
    }

    private String getNumImmat() {
        return numImmat;
    }

    private float getPoidsMax() {
        return poidsMax;
    }

    private List<Tournee> getTournees() {
        return tournees;
    }

    private Producteur getProducteur() {
        return producteur;
    }

    private void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    private void setNumImmat(String numImmat) {
        this.numImmat = numImmat;
    }

    private void setPoidsMax(float poidsMax) {
        this.poidsMax = poidsMax;
    }

    private void setProducteur(Producteur producteur) {
        this.producteur = producteur;
    }

    /**
     * Constructeur de Vehicule.
     * 
     * @param idVehicule int représentant l'id du Vehicule.
     * @param numImmat   String représentant le numéro d'immatriculation du
     *                   Vehicule.
     * @param poidsMax   float représentant le poids maximum que le Vehicule peut
     *                   accepter.
     * @param producteur Producteur représentant le producteur utilisant le
     *                   Vehicule.
     * @param tournees   ArrayList<Tournee> représentant les tournées du Vehicule.
     */

    public Vehicule(int idVehicule, String numImmat, float poidsMax, Producteur producteur, List<Tournee> tournees) {
    }
}