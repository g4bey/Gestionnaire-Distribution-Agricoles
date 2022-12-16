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
     * @param vehicules    ArrayList<Vehicule> représentant les véhicules du
     *                     Producteur.
     * @param commandes    ArrayList<Commande> représentant les commandes du
     *                     Producteur.
     */

    public Producteur(int idProducteur, String siret, String proprietaire, String adresseProd, String numTelProd,
            String gpsProd, String mdpProd, List<Vehicule> vehicules, List<Commande> commandes) {
    }
}