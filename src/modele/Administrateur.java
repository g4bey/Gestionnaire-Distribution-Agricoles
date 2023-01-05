package modele;

/**
 * Objet métier représentant un Administrateur.
 */
public class Administrateur {
    private int idAdministrateur;

    private String pseudo;

    private String mdpAdmin;

    public int getIdAdministrateur() {
        return idAdministrateur;
    } // getIdAdministrateur

    public String getPseudo() {
        return pseudo;
    } // getPseudo

    public String getMdpAdmin() {
        return mdpAdmin;
    } // getMdpAdmin

    public void setIdAdministrateur(int idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    } // setIdAdministrateur

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    } // setPseudo

    public void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    } // setMdpAdmin

    @Override
    public String toString() {
        return ("Information Administrateur :\nId : " + idAdministrateur)
                .concat("\nPseudo : ").concat(pseudo)
                .concat("\nHash du mdp : ").concat(mdpAdmin);
    } // toString

    /**
     * Compare au niveau des attributs l'égalité entre 2 instances d'Administrateur
     * 
     * @param adm L'Administrateur à comparer
     * @return Un booléen représentant l'égalité entre les 2 instances
     */
    public boolean equals(Administrateur adm) {
        return idAdministrateur == adm.idAdministrateur
                && pseudo.equals(adm.pseudo)
                && mdpAdmin.equals(adm.mdpAdmin);
    } // equals

    /**
     * Constructeur d'Administrateur.
     * 
     * @param idAdministrateur Int représentant l'Administrateur.
     * @param pseudo           String représentant l'Administrateur.
     * @param mdpAdmin         String représentant l'Administrateur.
     */
    public Administrateur(int idAdministrateur, String pseudo, String mdpAdmin) {
        this.idAdministrateur = idAdministrateur;
        this.pseudo = pseudo;
        this.mdpAdmin = mdpAdmin;
    } // constructeur

    /**
     * Constructeur d'Administrateur.
     * 
     * @param pseudo   String représentant l'Administrateur.
     * @param mdpAdmin String représentant l'Administrateur.
     */
    public Administrateur(String pseudo, String mdpAdmin) {
        this.pseudo = pseudo;
        this.mdpAdmin = mdpAdmin;
    } // constructeur

} // Administrateur