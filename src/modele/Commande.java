package modele;

/**
 * Objet métier représentant une commande.
 */

public class Commande {
    private int idCommande;

    private String libelle;

    private float poids;

    private String horaireDebut;

    private String horaireFin;

    private Producteur producteur;

    private Client client;

    private Tournee tournee;

    private int getIdCommande() {
        return idCommande;
    }

    private String getLibelle() {
        return libelle;
    }

    private float getPoids() {
        return poids;
    }

    private String getHoraireDebut() {
        return horaireDebut;
    }

    private String getHoraireFin() {
        return horaireFin;
    }

    private Producteur getProducteur() {
        return producteur;
    }

    private Client getClient() {
        return client;
    }

    private Tournee getTournee() {
        return tournee;
    }

    private void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    private void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    private void setPoids(float poids) {
        this.poids = poids;
    }

    private void setHoraireDebut(String horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    private void setHoraireFin(String horaireFin) {
        this.horaireFin = horaireFin;
    }

    private void setProducteur(Producteur producteur) {
        this.producteur = producteur;
    }

    private void setClient(Client client) {
        this.client = client;
    }

    private void setTournee(Tournee tournee) {
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

    public Commande(int idCommande, String libelle, float poids, String horaireDebut, String horaireFin,
            Producteur producteur, Client client, Tournee tournee) {
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

    public Commande(String libelle, float poids, String horaireDebut, String horaireFin,
            Producteur producteur, Client client, Tournee tournee) {
    }
}