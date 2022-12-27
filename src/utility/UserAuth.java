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
        UserAuth.adminUser = null;
    }

    /**
     * Constructeur de la classe UserAuth pour un utilisateur Administrateur.
     * @param admin
     */
    public UserAuth(Administrateur admin) {
        UserAuth.adminUser = admin;
        UserAuth.prodUser = null;
    }

    public static Producteur getProd() {
        return prodUser;
    }

    public static Administrateur getAdmin() {
        return adminUser;
    }
}
