package modele;

/**
 * Objet métier représentant un Administrateur.
 */

public class Administrateur {
    private int idAdministrateur;

    private String pseudo;

    private String mdpAdmin;

    private int getIdAdministrateur() {
        return idAdministrateur;
    }

    private String getPseudo() {
        return pseudo;
    }

    private String getMdpAdmin() {
        return mdpAdmin;
    }

    private void setIdAdministrateur(int idAdministrateur) {
        this.idAdministrateur = idAdministrateur;
    }

    private void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    private void setMdpAdmin(String mdpAdmin) {
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
    }
}