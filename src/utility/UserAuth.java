package utility;

import modele.Administrateur;
import modele.Producteur;

/**
 * Classe d'authentification d'un utilisateur.
 */
public class UserAuth {
    
    private static Producteur prodUser;
    private static Administrateur adminUser;

    /**
     * Constructeur de la classe UserAuth pour un utilisateur Producteur.
     * @param prod Producteur
     */
    public UserAuth(Producteur prod) {
        UserAuth.prodUser = prod;
    }

    /**
     * Constructeur de la classe UserAuth pour un utilisateur Administrateur.
     * @param admin
     */
    public UserAuth(Administrateur admin) {
        UserAuth.adminUser = admin;
    }

    public static int getProdId() {
        return prodUser.getIdProducteur();
    }

    public static int getAdminId() {
        return adminUser.getIdAdministrateur();
    }
}
