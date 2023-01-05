package modele;

import java.util.ArrayList;

/**
 * Objet métier représentant un Producteur.
 */
public class Producteur {
    private int idProducteur;

    private String siret;

    private String proprietaire;

    private String adresseProd;

    private String numTelProd;

    private String gpsProd;

    private String mdpProd;

    private ArrayList<Vehicule> vehicules = new ArrayList<>();

    private ArrayList<Commande> commandes = new ArrayList<>();

    private ArrayList<Tournee> tournees = new ArrayList<>();

    public int getIdProducteur() {
        return idProducteur;
    } // getIdProducteur

    public String getSiret() {
        return siret;
    } // getSiret

    public String getProprietaire() {
        return proprietaire;
    } // getProprietaire

    public String getAdresseProd() {
        return adresseProd;
    } // getAdresseProd

    public String getNumTelProd() {
        return numTelProd;
    } // getNumTelProd

    public String getGpsProd() {
        return gpsProd;
    } // getGpsProd

    public String getMdpProd() {
        return mdpProd;
    } // getMdpProd

    public ArrayList<Vehicule> getVehicules() {
        return vehicules;
    } // getVehicules

    public ArrayList<Commande> getCommandes() {
        return commandes;
    } // getCommandes

    public ArrayList<Tournee> getTournees() {
        return tournees;
    } // getTournees

    public void setIdProducteur(int idProducteur) {
        this.idProducteur = idProducteur;
    } // setIdProducteur

    public void setSiret(String siret) {
        this.siret = siret;
    } // setSiret

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    } // setProprietaire

    public void setAdresseProd(String adresseProd) {
        this.adresseProd = adresseProd;
    } // setAdresseProd

    public void setNumTelProd(String numTelProd) {
        this.numTelProd = numTelProd;
    } // setNumTelProd

    public void setGpsProd(String gpsProd) {
        this.gpsProd = gpsProd;
    } // setGpsProd

    public void setMdpProd(String mdpProd) {
        this.mdpProd = mdpProd;
    } // setMdpProd

    @Override
    public String toString() {
        return ("Information Producteur :\nId : " + idProducteur)
                .concat("SIRET : ").concat(siret)
                .concat("Propriétaire : ").concat(proprietaire)
                .concat("Adresse : ").concat(adresseProd.replace(",", " "))
                .concat("Numéro téléphone : ").concat(numTelProd)
                .concat("GPS : ").concat(gpsProd)
                .concat("Hash du mdp : ").concat(mdpProd);
    } // toString

    /**
     * Compare au niveau des attributs l'égalité entre 2 instances de Producteur
     * 
     * @param prd Le Producteur à comparer
     * @return Un booléen représentant l'égalité entre les 2 instances
     */
    public boolean equals(Producteur prd) {
        return idProducteur == prd.idProducteur
                && siret.equals(prd.siret)
                && proprietaire.equals(prd.proprietaire)
                && adresseProd.equals(prd.adresseProd)
                && numTelProd.equals(prd.numTelProd)
                && gpsProd.equals(prd.gpsProd)
                && mdpProd.equals(prd.mdpProd);
    } // equals

    /**
     * Constructeur de Producteur.
     * 
     * @param idProducteur Int représentant l'id du Producteur.
     * @param siret        String représentant le siret du Producteur.
     * @param proprietaire String représentant le nom et prénom du propriétaire.
     * @param adresseProd  String représentant l'adresse du Producteur.
     * @param numTelProd   String représentant le numéro de téléphone du Producteur.
     * @param gpsProd      String représentant les coordonnées GPS du Producteur.
     * @param mdpProd      String représentant le hash du mot de passe du
     *                     Producteur.
     */
    public Producteur(int idProducteur, String siret, String proprietaire, String adresseProd, String numTelProd,
            String gpsProd, String mdpProd) {
        this.idProducteur = idProducteur;
        this.siret = siret;
        this.proprietaire = proprietaire;
        this.adresseProd = adresseProd;
        this.numTelProd = numTelProd;
        this.gpsProd = gpsProd;
        this.mdpProd = mdpProd;
    } // constructeur

    /**
     * Constructeur de Producteur.
     * 
     * @param siret        String représentant le siret du Producteur.
     * @param proprietaire String représentant le nom et prénom du propriétaire.
     * @param adresseProd  String représentant l'adresse du Producteur.
     * @param numTelProd   String représentant le numéro de téléphone du Producteur.
     * @param gpsProd      String représentant les coordonnées GPS du Producteur.
     * @param mdpProd      String représentant le hash du mot de passe du
     *                     Producteur.
     */
    public Producteur(String siret, String proprietaire, String adresseProd, String numTelProd,
            String gpsProd, String mdpProd) {
        this.siret = siret;
        this.proprietaire = proprietaire;
        this.adresseProd = adresseProd;
        this.numTelProd = numTelProd;
        this.gpsProd = gpsProd;
        this.mdpProd = mdpProd;
    } // constructeur

    /**
     * Permet d'ajouter un Véhicule à la liste de Véhicules du Producteur
     * 
     * @param vehicule L'objet Véhicule à ajouter
     */
    public void addVehicule(Vehicule vehicule) {
        this.vehicules.add(vehicule);
    } // addVehicule

    /**
     * Permet de supprimer un Véhicule de la liste de Véhicules du Producteur
     * 
     * @param vehicule L'objet Véhicule à supprimer
     */
    public void removeVehicule(Vehicule vehicule) {
        this.vehicules.remove(vehicule);
    } // removeVehicule

    /**
     * Permet d'ajouter une Commande à la liste de Commandes du Producteur
     * 
     * @param commande L'objet Commande à ajouter
     */
    public void addCommande(Commande commande) {
        this.commandes.add(commande);
    } // addCommande

    /**
     * Permet de supprimer une Commande de la liste de Commandes du Producteur
     * 
     * @param commande L'objet Commande à supprimer
     */
    public void removeCommande(Commande commande) {
        this.commandes.remove(commande);
    } // removeCommande

    /**
     * Permet d'ajouter une Tournée à la liste de Tournées du Producteur
     * 
     * @param tournee L'objet Tournee à ajouter
     */
    public void addTournee(Tournee tournee) {
        this.tournees.add(tournee);
    } // addTournee

    /**
     * Permet de supprimer une Tournée de la liste de Tournées du Producteur
     * 
     * @param tournee L'objet Tournée à supprimer
     */
    public void removeTournee(Tournee tournee) {
        this.tournees.remove(tournee);
    } // removeTournee

} // Producteur