package modele;

import java.sql.Timestamp;

/**
 * Objet métier représentant une commande.
 */

public class Commande {
    private int idCommande;

    private String libelle;

    private float poids;

    private Timestamp horaireDebut;

    private Timestamp horaireFin;

    private Tournee tournee;

    private Producteur producteur;

    private Client client;

    public int getIdCommande() {
        return idCommande;
    }

    public String getLibelle() {
        return libelle;
    }

    public float getPoids() {
        return poids;
    }

    public Timestamp getHoraireDebut() {
        return horaireDebut;
    }

    public Timestamp getHoraireFin() {
        return horaireFin;
    }

    public Producteur getProducteur() {
        return producteur;
    }

    public Client getClient() {
        return client;
    }

    public Tournee getTournee() {
        return tournee;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setHoraireDebut(Timestamp horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public void setHoraireFin(Timestamp horaireFin) {
        this.horaireFin = horaireFin;
    }

    public void setProducteur(Producteur producteur) {
        this.producteur = producteur;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setTournee(Tournee tournee) {
        this.tournee = tournee;
    }

    /**
     * Constructeur de Commande.
     * 
     * @param idCommande   int représentant l'id de la Commande.
     * @param libelle      String représentant le libellé de la Commande.
     * @param poids        float représentant le poids de la commande.
     * @param horaireDebut String représentant l'horaire de début de la Commande.
     * @param horaireFin   String représentant l'horaire de fin de la Commande.
     * @param producteur   Producteur représentant le producteur qui effectue la
     *                     Commande.
     * @param client       Client représentant le client qui a passé la Commande.
     * @param tournee      Tournee représentant la tournée à laquelle appartient la
     *                     Commande.
     */
    public Commande(int idCommande, String libelle, float poids, Timestamp horaireDebut, Timestamp horaireFin,
            Tournee tournee, Producteur producteur, Client client) {
            this.idCommande = idCommande;
            this.libelle = libelle;
            this.poids = poids;
            this.horaireDebut = horaireDebut;
            this.horaireFin = horaireFin;
            this.tournee = tournee;
            this.producteur = producteur;
            this.client = client;
    }

    /**
     * Constructeur de Commande.
     * 
     * @param libelle      String représentant le libellé de la Commande.
     * @param poids        float représentant le poids de la commande.
     * @param horaireDebut String représentant l'horaire de début de la Commande.
     * @param horaireFin   String représentant l'horaire de fin de la Commande.
     * @param producteur   Producteur représentant le producteur qui effectue la
     *                     Commande.
     * @param client       Client représentant le client qui a passé la Commande.
     * @param tournee      Tournee représentant la tournée à laquelle appartient la
     *                     Commande.
     */
    public Commande(String libelle, float poids, Timestamp horaireDebut, Timestamp horaireFin, Tournee tournee,
            Producteur producteur, Client client) {
                this.libelle = libelle;
                this.poids = poids;
                this.horaireDebut = horaireDebut;
                this.horaireFin = horaireFin;
                this.tournee = tournee;
                this.producteur = producteur;
                this.client = client;
    }
}