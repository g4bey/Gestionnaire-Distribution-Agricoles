package modele;

import java.util.ArrayList;

/**
 * Objet métier représentant un Véhicule.
 */

public class Vehicule {
    private int idVehicule;

    private String numImmat;

    private float poidsMax;

    private String libelle;

    private ArrayList<Tournee> tournees = new ArrayList<>();

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

    public String getLibelle() {
        return libelle;
    }

    public ArrayList<Tournee> getTournees() {
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

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setProducteur(Producteur producteur) {
        this.producteur = producteur;
    }

    @Override
    public String toString() {
        return ("Information Vehicule :\nId : " + idVehicule)
                .concat("Immatriculation : ").concat(numImmat)
                .concat("Poids max : " + poidsMax)
                .concat("Libellé : ").concat(libelle)
                .concat("Producteur : ").concat(producteur.getProprietaire());
    }

    /**
     * Compare au niveau des attributs l'égalité entre 2 instances de Vechicule
     * 
     * @param vh le Vechiule à comparer
     * @return un booléen représentant l'égalité entre les 2 instances
     */
    public boolean equals(Vehicule vh) {
        return idVehicule == vh.idVehicule
                && numImmat.equals(vh.numImmat)
                && Float.compare(poidsMax, vh.poidsMax) == 0
                && libelle.equals(vh.libelle)
                && producteur.equals(vh.producteur);
    }

    /**
     * Constructeur de Vehicule.
     * 
     * @param idVehicule int représentant l'id du Vehicule.
     * @param numImmat   String représentant le numéro d'immatriculation du
     *                   Vehicule.
     * @param libelle    String représentant le libellé du Vehicule.
     * @param poidsMax   float représentant le poids maximum que le Vehicule peut
     *                   accepter.
     * @param producteur Producteur représentant le producteur utilisant le
     *                   Vehicule.
     */
    public Vehicule(int idVehicule, String numImmat, float poidsMax, String libelle, Producteur producteur) {
        this.tournees = new ArrayList<>();
        this.idVehicule = idVehicule;
        this.numImmat = numImmat;
        this.libelle = libelle;
        this.poidsMax = poidsMax;
        this.producteur = producteur;
    }

    /**
     * Constructeur de Vehicule.
     * 
     * @param numImmat   String représentant le numéro d'immatriculation du
     *                   Vehicule.
     * @param libelle    String représentant le libellé du Vehicule.
     * @param poidsMax   float représentant le poids maximum que le Vehicule peut
     *                   accepter.
     * @param producteur Producteur représentant le producteur utilisant le
     *                   Vehicule.
     */
    public Vehicule(String numImmat, float poidsMax, String libelle, Producteur producteur) {
        this.tournees = new ArrayList<>();
        this.numImmat = numImmat;
        this.libelle = libelle;
        this.poidsMax = poidsMax;
        this.producteur = producteur;
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