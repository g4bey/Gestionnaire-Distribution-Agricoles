package modele;

import java.sql.Timestamp;
import java.util.*;

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

    private List<Commande> commandes = new ArrayList<>();

    public int getIdTournee() {
        return idTournee;
    }

    public Timestamp getHoraireDebut() {
        return horaireDebut;
    }

    public Timestamp getHoraireFin() {
        return horaireFin;
    }

    public float getPoids() {
        return poids;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setIdTournee(int idTournee) {
        this.idTournee = idTournee;
    }

    public void setHoraireDebut(Timestamp horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public void setHoraireFin(Timestamp horaireFin) {
        this.horaireFin = horaireFin;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Constructeur de Tournee.
     * 
     * @param idTournee    int représentant l'id de la Tournee.
     * @param horaireDebut String représentant l'horaire de début de la Tournee.
     * @param horaireFin   String représentant l'horaire de fin de la Tournee.
     * @param poids        float représentant le poids total de la Tournee.
     * @param libelle      String représentant le libellé de la Tournee.
     * @param vehicule     Vehicule représentant le véhicule utilisé pour effectuer
     *                     la Tournee.
     */
    public Tournee(int idTournee, Timestamp horaireDebut, Timestamp horaireFin, float poids, String libelle,
            Vehicule vehicule, List<Commande> commandes) {
                this.idTournee = idTournee;
                this.horaireDebut = horaireDebut;
                this.horaireFin = horaireFin;
                this.poids = poids;
                this.libelle = libelle;
                this.vehicule = vehicule;
                this.commandes = commandes;
    }

    /**
     * Constructeur de Tournee.
     * 
     * @param horaireDebut String représentant l'horaire de début de la Tournee.
     * @param horaireFin   String représentant l'horaire de fin de la Tournee.
     * @param poids        float représentant le poids total de la Tournee.
     * @param libelle      String représentant le libellé de la Tournee.
     * @param vehicule     Vehicule représentant le véhicule utilisé pour effectuer
     *                     la Tournee.
     */
    public Tournee(Timestamp horaireDebut, Timestamp horaireFin, float poids, String libelle, Vehicule vehicule,
            List<Commande> commandes) {
                this.horaireDebut = horaireDebut;
                this.horaireFin = horaireFin;
                this.poids = poids;
                this.libelle = libelle;
                this.vehicule = vehicule;
                this.commandes = commandes;
    }

    /**
     * Permet d'ajouter une commande à la liste de commandes de la tournée
     * 
     * @param commande L'objet Tournee à ajouter
     */
    public void addCommande(Commande commande) {
        this.commandes.add(commande);
    }

    /**
     * Permet de supprimer une commande de la liste de commandes de la tournée
     * 
     * @param commande L'objet Commande à supprimer
     */
    public void removeCommande(Commande commande) {
        this.commandes.remove(commande);
    }
}