package modele;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Objet métier représentant une Tournée.
 */

public class Tournee {
    private int idTournee;

    private Timestamp horaireDebut;

    private Timestamp horaireFin;

    private float poids;

    private String libelle;

    private Vehicule vehicule;

    private ArrayList<Commande> commandes = new ArrayList<>();

    public int getIdTournee() {
        return idTournee;
    } // getIdTournee

    public Timestamp getHoraireDebut() {
        return horaireDebut;
    } // getHoraireDebut

    public Timestamp getHoraireFin() {
        return horaireFin;
    } // getHoraireFin

    public float getPoids() {
        return poids;
    } // getPoids

    public Vehicule getVehicule() {
        return vehicule;
    } // getVehicule

    public ArrayList<Commande> getCommandes() {
        return commandes;
    } // getCommandes

    public void setIdTournee(int idTournee) {
        this.idTournee = idTournee;
    } // setIdTournee

    public void setHoraireDebut(Timestamp horaireDebut) {
        this.horaireDebut = horaireDebut;
    } // setHoraireDebut

    public void setHoraireFin(Timestamp horaireFin) {
        this.horaireFin = horaireFin;
    } // setHoraireFin

    public void setPoids(float poids) {
        this.poids = poids;
    } // setPoids

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    } // setVehicule

    public String getLibelle() {
        return libelle;
    } // getVehicule

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    } // setLibelle

    @Override
    public String toString() {
        return ("Information Tournee :\nId : " + idTournee)
                .concat("\nHoraire début : " + horaireDebut)
                .concat("\nHoraire fin : " + horaireFin)
                .concat("\nPoids : " + poids)
                .concat("\nLibellé : ").concat(libelle)
                .concat("\nVéhicule : ").concat(vehicule.getNumImmat());
    } // toString

    /**
     * Compare au niveau des attributs l'égalité entre 2 instances de Tournée
     * 
     * @param trn La Tournée à comparer
     * @return Un booléen représentant l'égalité entre les 2 instances
     */
    public boolean equals(Tournee trn) {
        return idTournee == trn.idTournee
                && horaireDebut.equals(trn.horaireDebut)
                && horaireFin.equals(trn.horaireFin)
                && Float.compare(poids, trn.poids) == 0
                && libelle.equals(trn.libelle)
                && vehicule.equals(trn.vehicule);
    } // equals

    /**
     * Constructeur de Tournée.
     * 
     * @param idTournee    Int représentant l'id de la Tournée.
     * @param horaireDebut Timestamp représentant l'horaire de début de la Tournée.
     * @param horaireFin   Timestamp représentant l'horaire de fin de la Tournée.
     * @param poids        Float représentant le poids total de la Tournée.
     * @param libelle      String représentant le libellé de la Tournée.
     * @param vehicule     Véhicule représentant le véhicule utilisé pour effectuer
     *                     la Tournée.
     */
    public Tournee(int idTournee, Timestamp horaireDebut, Timestamp horaireFin, float poids, String libelle,
            Vehicule vehicule) {
        this.idTournee = idTournee;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.poids = poids;
        this.libelle = libelle;
        this.vehicule = vehicule;
    } // constructeur

    /**
     * Constructeur de Tournée.
     * 
     * @param horaireDebut Timestamp représentant l'horaire de début de la Tournée.
     * @param horaireFin   Timestamp représentant l'horaire de fin de la Tournée.
     * @param poids        Float représentant le poids total de la Tournée.
     * @param libelle      String représentant le libellé de la Tournée.
     * @param vehicule     Véhicule représentant le véhicule utilisé pour effectuer
     *                     la Tournée.
     */
    public Tournee(Timestamp horaireDebut, Timestamp horaireFin, float poids, String libelle, Vehicule vehicule) {
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.poids = poids;
        this.libelle = libelle;
        this.vehicule = vehicule;
    } // constructeur

    /**
     * Permet d'ajouter une Commande à la liste de Commandes de la Tournée
     * 
     * @param commande L'objet Tournee à ajouter
     */
    public void addCommande(Commande commande) {
        commande.setTournee(this);
        this.commandes.add(commande);
    } // addCommande

    /**
     * Permet de supprimer une Commande de la liste de Commandes de la Tournée
     * 
     * @param commande L'objet Commande à supprimer
     */
    public void removeCommande(Commande commande) {
        commande.setTournee(null);
        this.commandes.remove(commande);
    } // removeCommande

} // Tournee