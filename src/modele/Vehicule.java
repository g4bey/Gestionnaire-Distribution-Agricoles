package modele;

import java.util.*;

/**
 * Objet métier représentant un Véhicule.
 */

public class Vehicule {
    private int idVehicule;

    private String numImmat;

    private float poidsMax;

    private List<Tournee> tournees = new ArrayList<>();

    private Producteur producteur;

    public int getIdVehicule() {
        return idVehicule;
    }

    public String getNumImmat() {
        return numImmat;
    }

    public float getPoidsMax() {
        return poidsMax;
    }

    public List<Tournee> getTournees() {
        return tournees;
    }

    public Producteur getProducteur() {
        return producteur;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public void setNumImmat(String numImmat) {
        this.numImmat = numImmat;
    }

    public void setPoidsMax(float poidsMax) {
        this.poidsMax = poidsMax;
    }

    public void setProducteur(Producteur producteur) {
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
     */

    public Vehicule(int idVehicule, String numImmat, float poidsMax, Producteur producteur) {
        this.tournees = new ArrayList<>();
    }

    /**
     * Constructeur de Vehicule.
     * 
     * @param numImmat   String représentant le numéro d'immatriculation du
     *                   Vehicule.
     * @param poidsMax   float représentant le poids maximum que le Vehicule peut
     *                   accepter.
     * @param producteur Producteur représentant le producteur utilisant le
     *                   Vehicule.
     */

    public Vehicule(String numImmat, float poidsMax, Producteur producteur) {
        this.tournees = new ArrayList<>();
    }

    /**
     * Permet d'ajouter une tournée à la liste de tournées du véhicule
     * 
     * @param tournee L'objet Tournee à ajouter
     */
    public void addTournee(Tournee tournee) {
        this.tournees.add(tournee);
    }

    /**
     * Permet de supprimer une tournée de la liste de tournées du véhicule
     * 
     * @param tournee L'objet Tournee à supprimer
     */
    public void removeTournee(Tournee tournee) {
        this.tournees.remove(tournee);
    }
}