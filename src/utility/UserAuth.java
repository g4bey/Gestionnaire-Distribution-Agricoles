package utility;

import modele.Administrateur;
import modele.Producteur;

/**
 * Classe d'authentification d'un utilisateur.
 */
public class UserAuth {

    private static Producteur prodUser = null;
    private static Administrateur adminUser = null;

    /**
     * Associe prodUser au Producteur connecté.
     * 
     * @param prod Producteur
     */
    public static void setProducteur(Producteur prod) {
        UserAuth.prodUser = prod;
    }

    /**
     * Associe adminUser à l'Administrateur connecté.
     * 
     * @param admin
     */
    public static void setAdministrateur(Administrateur admin) {
        UserAuth.adminUser = admin;
    }

    public static Producteur getProd() {
        return prodUser;
    }

    public static Administrateur getAdmin() {
        return adminUser;
    }
}
