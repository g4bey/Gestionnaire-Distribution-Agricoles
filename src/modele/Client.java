package modele;

/**
 * Objet métier représentant un Client.
 */
public class Client {
    private int idClient;

    private String nomClient;

    private String adresseClient;

    private String gpsClient;

    private String numTelClient;

    public int getIdClient() {
        return idClient;
    } // getIdClient

    public String getNomClient() {
        return nomClient;
    } // getNomClient

    public String getAdresseClient() {
        return adresseClient;
    } // getAdresseClient

    public String getGpsClient() {
        return gpsClient;
    } // getGpsClient

    public String getNumTelClient() {
        return numTelClient;
    } // getNumTelClient

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    } // setIdClient

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    } // setNomClient

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    } // setAdresseClient

    public void setGpsClient(String gpsClient) {
        this.gpsClient = gpsClient;
    } // setGpsClient

    public void setNumTelClient(String numTelClient) {
        this.numTelClient = numTelClient;
    } // setNumTelClient

    @Override
    public String toString() {
        return ("Information Client :\nId : " + idClient)
                .concat("\nNom : ").concat(nomClient)
                .concat("\nAdresse : ").concat(adresseClient.replace(",", " "))
                .concat("\nGPS : ").concat(gpsClient)
                .concat("\nNuméro téléphone : ").concat(numTelClient);
    } // toString

    /**
     * Compare au niveau des attributs l'égalité entre 2 instances Client
     * 
     * @param clt Le Client à comparer
     * @return Un booléen représentant l'égalité entre les 2 instances
     */
    public boolean equals(Client clt) {
        return idClient == clt.idClient
                && nomClient.equals(clt.nomClient)
                && adresseClient.equals(clt.adresseClient)
                && gpsClient.equals(clt.gpsClient)
                && numTelClient.equals(clt.numTelClient);
    } // equals

    /**
     * Constructeur de Client.
     * 
     * @param idClient      Int représentant l'id du Client.
     * @param nomClient     String représentant le nom du Client.
     * @param adresseClient String représentant l'adresse du Client.
     * @param gpsClient     String représentant les coordonnées GPS du Client.
     * @param numTelClient  String représentant le numéro de téléphone du Client.
     */
    public Client(int idClient, String nomClient, String adresseClient, String gpsClient, String numTelClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
        this.gpsClient = gpsClient;
        this.numTelClient = numTelClient;
    } // constructeur

    /**
     * Constructeur de Client.
     * 
     * @param nomClient     String représentant le nom du Client.
     * @param adresseClient String représentant l'adresse du Client.
     * @param gpsClient     String représentant les coordonnées GPS du Client.
     * @param numTelClient  String représentant le numéro de téléphone du Client.
     */
    public Client(String nomClient, String adresseClient, String gpsClient, String numTelClient) {
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
        this.gpsClient = gpsClient;
        this.numTelClient = numTelClient;
    } // constructeur

} // Client