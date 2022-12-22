package GDA.modele;

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
    }

    public String getSiret() {
        return siret;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public String getAdresseProd() {
        return adresseProd;
    }

    public String getNumTelProd() {
        return numTelProd;
    }

    public String getGpsProd() {
        return gpsProd;
    }

    public String getMdpProd() {
        return mdpProd;
    }

    public ArrayList<Vehicule> getVehicules() {
        return vehicules;
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public ArrayList<Tournee> getTournees() {
        return tournees;
    }

    public void setIdProducteur(int idProducteur) {
        this.idProducteur = idProducteur;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setAdresseProd(String adresseProd) {
        this.adresseProd = adresseProd;
    }

    public void setNumTelProd(String numTelProd) {
        this.numTelProd = numTelProd;
    }

    public void setGpsProd(String gpsProd) {
        this.gpsProd = gpsProd;
    }

    public void setMdpProd(String mdpProd) {
        this.mdpProd = mdpProd;
    }

    @Override
    public String toString() {
        return ("Information Producteur :\nId : " + idProducteur)
                .concat("SIRET : ").concat(siret)
                .concat("Propriétaire : ").concat(proprietaire)
                .concat("Adresse : ").concat(adresseProd)
                .concat("Numéro téléphone : ").concat(numTelProd)
                .concat("GPS : ").concat(gpsProd)
                .concat("Hash du mdp : ").concat(mdpProd);

    }

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
    }

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
    }

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
    }

    /**
     * Permet d'ajouter un véhicule à la liste de véhicules du producteur
     * 
     * @param vehicule L'objet Véhicule à ajouter
     */
    public void addVehicule(Vehicule vehicule) {
        this.vehicules.add(vehicule);
    }

    /**
     * Permet de supprimer un véhicule de la liste de véhicules du producteur
     * 
     * @param vehicule L'objet Véhicule à supprimer
     */
    public void removeVehicule(Vehicule vehicule) {
        this.vehicules.remove(vehicule);
    }

    /**
     * Permet d'ajouter une Commande à la liste de Commandes du Producteur
     * 
     * @param commande L'objet Commande à ajouter
     */
    public void addCommande(Commande commande) {
        this.commandes.add(commande);
    }

    /**
     * Permet de supprimer une Commande de la liste de Commandes du Producteur
     * 
     * @param commande L'objet Commande à supprimer
     */
    public void removeCommande(Commande commande) {
        this.commandes.remove(commande);
    }

    /**
     * Permet d'ajouter une Tournée à la liste de Tournées du Producteur
     * 
     * @param tournee L'objet Tournee à ajouter
     */
    public void addTournee(Tournee tournee) {
        this.tournees.add(tournee);
    }

    /**
     * Permet de supprimer une Tournée de la liste de Tournées du Producteur
     * 
     * @param tournee L'objet Tournée à supprimer
     */
    public void removeTournee(Tournee tournee) {
        this.tournees.remove(tournee);
    }
}