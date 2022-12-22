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
     * Compare au niveau des attributs l'égalité entre 2 instances de Véhicule
     * 
     * @param vh Le Véhicule à comparer
     * @return Un booléen représentant l'égalité entre les 2 instances
     */
    public boolean equals(Vehicule vh) {
        return idVehicule == vh.idVehicule
                && numImmat.equals(vh.numImmat)
                && Float.compare(poidsMax, vh.poidsMax) == 0
                && libelle.equals(vh.libelle)
                && producteur.equals(vh.producteur);
    }

    /**
     * Constructeur de Véhicule.
     * 
     * @param idVehicule Int représentant l'id du Véhicule.
     * @param numImmat   String représentant le numéro d'immatriculation du
     *                   Véhicule.
     * @param libelle    String représentant le libellé du Véhicule.
     * @param poidsMax   Float représentant le poids maximum que le Véhicule peut
     *                   accepter.
     * @param producteur Producteur représentant le producteur utilisant le
     *                   Véhicule.
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
     * Constructeur de Véhicule.
     * 
     * @param numImmat   String représentant le numéro d'immatriculation du
     *                   Véhicule.
     * @param libelle    String représentant le libellé du Véhicule.
     * @param poidsMax   Float représentant le poids maximum que le Véhicule peut
     *                   accepter.
     * @param producteur Producteur représentant le producteur utilisant le
     *                   Véhicule.
     */
    public Vehicule(String numImmat, float poidsMax, String libelle, Producteur producteur) {
        this.tournees = new ArrayList<>();
        this.numImmat = numImmat;
        this.libelle = libelle;
        this.poidsMax = poidsMax;
        this.producteur = producteur;
    }

    /**
     * Permet d'ajouter une Tournée à la liste de Tournées du Véhicule
     * 
     * @param tournee L'objet Tournée à ajouter
     */
    public void addTournee(Tournee tournee) {
        this.tournees.add(tournee);
    }

    /**
     * Permet de supprimer une Tournée de la liste de Tournées du Véhicule
     * 
     * @param tournee L'objet Tournée à supprimer
     */
    public void removeTournee(Tournee tournee) {
        this.tournees.remove(tournee);
    }
}