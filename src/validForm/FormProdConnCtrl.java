package validForm;

import java.io.IOException;
import java.sql.SQLException;

import DAO.ProducteurDAO;
import de.mkammerer.argon2.Argon2Factory;
import modele.Producteur;
import utility.DatabaseConnection;
import utility.UserAuth;

/**
 * Validateur de formulaire pour ProsConnCtrl.
 * Permet de connecter un Producteur.
 * Ce formulaire a comme particularité d'authentifier l'utilisateur dans l'objet session UserAuth.
 *
 * @see UserAuth
 * @see controllers.ProdConnCtrl
 */
public class FormProdConnCtrl extends FormValidator {

    /**
     * Constructeur de FormProdConnCtrl.
     * Vérifie que le mot de passe fourni corresponde bien au mot de passe en base.
     * Ce mot de passe est hashé avec Argon2.
     * @param login Le SIRET
     * @param password Le mot de passe
     */
    public FormProdConnCtrl(String login, String password) {
        ProducteurDAO prodDAO;

        try {
            prodDAO = new ProducteurDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid(e.toString());
            return;
        } // try/catch

        Producteur prod = prodDAO.get(login);

        if (prod == null) {
            setInvalid("SIRET inconnu.");
            return;
        } // if

        if (!Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64).verify(prod.getMdpProd(),
                password.toCharArray())) {
            setInvalid("Mot de passe invalide.");
            return;
        } // if

        UserAuth.setProducteur(prod);
    } // constructeur

} // FormProdConnCtrl
