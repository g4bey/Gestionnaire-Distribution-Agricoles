package modele;

/**
 * Objet métier représentant un client.
 */

public class Client {
    private int idClient;

    private String nomClient;

    private String adresseClient;

    private String gpsClient;

    private String numTelClient;

    public int getIdClient() {
        return idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public String getGpsClient() {
        return gpsClient;
    }

    public String getNumTelClient() {
        return numTelClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public void setGpsClient(String gpsClient) {
        this.gpsClient = gpsClient;
    }

    public void setNumTelClient(String numTelClient) {
        this.numTelClient = numTelClient;
    }

    /**
     * Constructeur de Client.
     * 
     * @param idClient      int représentant l'id du Client.
     * @param nomClient     String représentant le nom du Client.
     * @param adresseClient String représentant l'adresse du Client.
     * @param gpsClient     String représentant les coordonnées GPS du Client.
     * @param numTelClient  String représentant le numéro de téléphone du Client.
     */

    public Client(int idClient, String nomClient, String adresseClient, String gpsClient, String numTelClient) {
    }

    /**
     * Constructeur de Client.
     * 
     * @param nomClient     String représentant le nom du Client.
     * @param adresseClient String représentant l'adresse du Client.
     * @param gpsClient     String représentant les coordonnées GPS du Client.
     * @param numTelClient  String représentant le numéro de téléphone du Client.
     */

    public Client(String nomClient, String adresseClient, String gpsClient, String numTelClient) {
    }
}