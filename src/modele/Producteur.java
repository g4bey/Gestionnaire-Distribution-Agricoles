package modele;

import java.util.*;

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

    private List<Vehicule> vehicules = new ArrayList<>();

    private List<Commande> commandes = new ArrayList<>();

    private List<Tournee> tournees = new ArrayList<>();

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

    public List<Vehicule> getVehicules() {
        return vehicules;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public List<Tournee> getTournees() {
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

    /**
     * Constructeur de Producteur.
     * 
     * @param idProducteur int représentant l'id du Producteur.
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
    }

    /**
     * Permet d'ajouter un véhicule à la liste de véhicules du producteur
     * 
     * @param vehicule L'objet Vehicule à ajouter
     */
    public void addVehicule(Vehicule vehicule) {
        this.vehicules.add(vehicule);
    }

    /**
     * Permet de supprimer un véhicule de la liste de véhicules du producteur
     * 
     * @param vehicule L'objet Vehicule à supprimer
     */
    public void removeVehicule(Vehicule vehicule) {
        this.vehicules.remove(vehicule);
    }

    /**
     * Permet d'ajouter une commande à la liste de commandes du producteur
     * 
     * @param commande L'objet Commande à ajouter
     */
    public void addCommande(Commande commande) {
        this.commandes.add(commande);
    }

    /**
     * Permet de supprimer une commande de la liste de commandes du producteur
     * 
     * @param commande L'objet Commande à supprimer
     */
    public void removeCommande(Commande commande) {
        this.commandes.remove(commande);
    }

    /**
     * Permet d'ajouter une tournée à la liste de Tournées du producteur
     * 
     * @param tournee L'objet Tournee à ajouter
     */
    public void addTournee(Tournee tournee) {
        this.tournees.add(tournee);
    }

    /**
     * Permet de supprimer une tournée de la liste de Tournées du producteur
     * 
     * @param tournee L'objet Tournee à supprimer
     */
    public void removeTournee(Tournee tournee) {
        this.tournees.remove(tournee);
    }
}