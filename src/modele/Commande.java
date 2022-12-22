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

    @Override
    public String toString() {
        return ("Information Commande :\nId : " + idCommande)
                .concat("Poids : " + poids)
                .concat("Horaire début : " + horaireDebut)
                .concat("Horaire fin : " + horaireFin)
                .concat("Tournée : ").concat(tournee.getLibelle())
                .concat("Producteur : ").concat(producteur.getSiret())
                .concat("Client : ").concat(client.getNomClient());
    }

    /**
     * Compare au niveau des attributs l'égalité entre 2 instances de Commande
     * 
     * @param cmd la Commande à comparer
     * @return un booléen représentant l'égalité entre les 2 instances
     */
    public boolean equals(Commande cmd) {
        return (idCommande == cmd.idCommande)
                && libelle.equals(cmd.libelle)
                && Float.compare(poids, cmd.poids) == 0
                && horaireDebut.equals(cmd.horaireDebut)
                && horaireFin.equals(cmd.horaireFin)
                && producteur.equals(cmd.producteur)
                && client.equals(cmd.client)
                && cmd.tournee != null ? tournee.equals(cmd.tournee) : tournee == null;
        // les tournée peuvent etre nul lorsqu'on créer une commande.
        // si l'une est nul et pas l'autre, c'est false par default.
    }

    /**
     * Constructeur de Commande.
     * 
     * @param idCommande   int représentant l'id de la Commande.
     * @param libelle      String représentant le libellé de la Commande.
     * @param poids        float représentant le poids de la commande.
     * @param horaireDebut Timestamp représentant l'horaire de début de la Commande.
     * @param horaireFin   Timestamp représentant l'horaire de fin de la Commande.
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
     * @param horaireDebut Timestamp représentant l'horaire de début de la Commande.
     * @param horaireFin   Timestamp représentant l'horaire de fin de la Commande.
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

    /**
     * Constructeur de Commande lors qu'on la crée,
     * en effet, elle n'est associée à une commande.
     *
     * @param libelle      String représentant le libellé de la Commande.
     * @param poids        float représentant le poids de la commande.
     * @param horaireDebut Timestamp représentant l'horaire de début de la Commande.
     * @param horaireFin   Timestamp représentant l'horaire de fin de la Commande.
     * @param producteur   Producteur représentant le producteur qui effectue la
     *                     Commande.
     * @param client       Client représentant le client qui a passé la Commande.
     */
    public Commande(String libelle, float poids, Timestamp horaireDebut, Timestamp horaireFin, Producteur producteur,
            Client client) {
        this.libelle = libelle;
        this.poids = poids;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.tournee = null;
        this.producteur = producteur;
        this.client = client;
    }
}