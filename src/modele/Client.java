package modele;

import java.util.*;



/**
* Métier représentant un client.
*/

public class Client extends ModeleConcret {
    private int idClient;

    private String nomClient;

    private String adresseClient;

    private String gpsClient;

    private String numTelClient;

    private List<Commande> commandes = new ArrayList<>();

    private int getIdClient() {
        return idClient;
    }

    private String getNomClient() {
        return nomClient;
    }

    private String getAdresseClient() {
        return adresseClient;
    }

    private String getGpsClient() {
        return gpsClient;
    }

    private String getNumTelClient() {
        return numTelClient;
    }

    private List<Commande> getCommandes() {
        return commandes;
    }

    private void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    private void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    private void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    private void setGpsClient(String gpsClient) {
        this.gpsClient = gpsClient;
    }

    private void setNumTelClient(String numTelClient) {
        this.numTelClient = numTelClient;
    }

    /**
    * Constructeur de Client.
    * @param idClient int représentant l'id du Client.
    * @param nomClient String représentant le nom du Client.
    * @param adresseClient String représentant l'adresse du Client.
    * @param gpsClient String représentant les coordonnées GPS du Client.
    * @param numTelClient String représentant le numéro de téléphone du Client.
    * @param commandes ArrayList<Commande> représentant les commandes du Client.
    */

    public Client(int idClient, String nomClient, String adresseClient, String gpsClient, String numTelClient, List<Commande> commandes) {
    }
}