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

    private List<Vehicule> vehicules;

    private List<Commande> commandes;

    private List<Tournee> tournees;

    private int getIdProducteur() {
        return idProducteur;
    }

    private String getSiret() {
        return siret;
    }

    private String getProprietaire() {
        return proprietaire;
    }

    private String getAdresseProd() {
        return adresseProd;
    }

    private String getNumTelProd() {
        return numTelProd;
    }

    private String getGpsProd() {
        return gpsProd;
    }

    private String getMdpProd() {
        return mdpProd;
    }

    private List<Vehicule> getVehicules() {
        return vehicules;
    }

    private List<Commande> getCommandes() {
        return commandes;
    }

    private List<Tournee> getTournees() {
        return tournees;
    }

    private void setIdProducteur(int idProducteur) {
        this.idProducteur = idProducteur;
    }

    private void setSiret(String siret) {
        this.siret = siret;
    }

    private void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    private void setAdresseProd(String adresseProd) {
        this.adresseProd = adresseProd;
    }

    private void setNumTelProd(String numTelProd) {
        this.numTelProd = numTelProd;
    }

    private void setGpsProd(String gpsProd) {
        this.gpsProd = gpsProd;
    }

    private void setMdpProd(String mdpProd) {
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
        this.vehicules = new ArrayList<>();
        this.commandes = new ArrayList<>();
        this.tournees = new ArrayList<>();
    }

    /**
     * Permet d'ajouter un véhicule à la liste de véhicules du producteur
     * @param vehicule L'objet Vehicule à ajouter
     */
    public void addVehicule(Vehicule vehicule) {
        this.vehicules.add(vehicule);
    }

    /**
     * Permet de supprimer un véhicule de la liste de véhicules du producteur
     * @param vehicule L'objet Vehicule à supprimer
     */
    public void removeVehicule(Vehicule vehicule) {
        this.vehicules.remove(vehicule);
    }

    /**
     * Permet d'ajouter une commande à la liste de commandes du producteur
     * @param commande L'objet Commande à ajouter
     */
    public void addCommande(Commande commande) {
        this.commandes.add(commande);
    }

    /**
     * Permet de supprimer une commande de la liste de commandes du producteur
     * @param commande L'objet Commande à supprimer
     */
    public void removeCommande(Commande commande) {
        this.commandes.remove(commande);
    }

    /**
     * Permet d'ajouter une tournée à la liste de Tournées du producteur
     * @param tournee L'objet Tournee à ajouter
     */
    public void addTournee(Tournee tournee) {
        this.tournees.add(tournee);
    }

    /**
     * Permet de supprimer une tournée de la liste de Tournées du producteur
     * @param tournee L'objet Tournee à supprimer
     */
    public void removeTournee(Tournee tournee) {
        this.tournees.remove(tournee);
    }
}