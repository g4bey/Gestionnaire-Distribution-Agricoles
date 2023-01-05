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
    } // getIdVehicule

    public String getNumImmat() {
        return numImmat;
    } // getNumImmat

    public float getPoidsMax() {
        return poidsMax;
    } // getPoidsMax

    public String getLibelle() {
        return libelle;
    } // getLibelle

    public ArrayList<Tournee> getTournees() {
        return tournees;
    } // getTournees

    public Producteur getProducteur() {
        return producteur;
    } // getProducteur

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    } // setIdVehicule

    public void setNumImmat(String numImmat) {
        this.numImmat = numImmat;
    } // setNumImmat

    public void setPoidsMax(float poidsMax) {
        this.poidsMax = poidsMax;
    } // setPoidsMax

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    } // setLibelle

    public void setProducteur(Producteur producteur) {
        this.producteur = producteur;
    } // setProducteur

    @Override
    public String toString() {
        return ("Information Vehicule :\nId : " + idVehicule)
                .concat("\nImmatriculation : ").concat(numImmat)
                .concat("\nPoids max : " + poidsMax)
                .concat("\nLibellé : ").concat(libelle)
                .concat("\nProducteur : ").concat(producteur.getProprietaire());
    } // toString

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
    } // equals

    /**
     * Constructeur de Véhicule.
     * 
     * @param idVehicule Int représentant l'id du Véhicule.
     * @param numImmat   String représentant le numéro d'immatriculation du
     *                   Véhicule.
     * @param libelle    String représentant le libellé du Véhicule.
     * @param poidsMax   Float représentant le poids maximum que le Véhicule peut
     *                   accepter.
     * @param producteur Producteur représentant le Producteur utilisant le
     *                   Véhicule.
     */
    public Vehicule(int idVehicule, String numImmat, float poidsMax, String libelle, Producteur producteur) {
        this.tournees = new ArrayList<>();
        this.idVehicule = idVehicule;
        this.numImmat = numImmat;
        this.libelle = libelle;
        this.poidsMax = poidsMax;
        this.producteur = producteur;
    } // constructeur

    /**
     * Constructeur de Véhicule.
     * 
     * @param numImmat   String représentant le numéro d'immatriculation du
     *                   Véhicule.
     * @param libelle    String représentant le libellé du Véhicule.
     * @param poidsMax   Float représentant le poids maximum que le Véhicule peut
     *                   accepter.
     * @param producteur Producteur représentant le Producteur utilisant le
     *                   Véhicule.
     */
    public Vehicule(String numImmat, float poidsMax, String libelle, Producteur producteur) {
        this.tournees = new ArrayList<>();
        this.numImmat = numImmat;
        this.libelle = libelle;
        this.poidsMax = poidsMax;
        this.producteur = producteur;
    } // constructeur

    /**
     * Permet d'ajouter une Tournée à la liste de Tournées du Véhicule
     * 
     * @param tournee L'objet Tournée à ajouter
     */
    public void addTournee(Tournee tournee) {
        this.tournees.add(tournee);
    } // addTournee

    /**
     * Permet de supprimer une Tournée de la liste de Tournées du Véhicule
     * 
     * @param tournee L'objet Tournée à supprimer
     */
    public void removeTournee(Tournee tournee) {
        this.tournees.remove(tournee);
    } // removeTournee

} // Vehicule