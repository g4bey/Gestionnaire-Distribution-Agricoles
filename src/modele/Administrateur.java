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
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMdpAdmin() {
        return mdpAdmin;
    }
    

    public void setIdAdministrateur(int idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    }

    /**
     * Constructeur d'Administrateur.
     * 
     * @param idAdministrateur int représentant l'Administrateur.
     * @param pseudo           String représentant l'Administrateur.
     * @param mdpAdmin         String représentant l'Administrateur.
     */
    public Administrateur(int idAdministrateur, String pseudo, String mdpAdmin) {
        this.idAdministrateur = idAdministrateur;
        this.pseudo = pseudo;
        this.mdpAdmin = mdpAdmin;
    }

    /**
     * Constructeur d'Administrateur.
     * 
     * @param pseudo   String représentant l'Administrateur.
     * @param mdpAdmin String représentant l'Administrateur.
     */
    public Administrateur(String pseudo, String mdpAdmin) {
        this.pseudo = pseudo;
        this.mdpAdmin = mdpAdmin;
    }
}