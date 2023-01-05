package validForm;

import java.io.IOException;
import java.sql.SQLException;

import DAO.AdministrateurDAO;
import de.mkammerer.argon2.Argon2Factory;
import modele.Administrateur;
import utility.DatabaseConnection;
import utility.UserAuth;

/**
 * Permet d'authentifier un administrateur.
 * Ce formulaire a comme particularité d'authentifier l'utilisateur dans l'objet session UserAuth.
 * @see UserAuth
 * @see controllers.AdminConnCtrl
 */
public class FormAdminConnValidator extends FormValidator {
    /**
     * Vérifie que le mot de passe fourni correspond bien au mot de passe en base,
     * Ce mot de passe est hashé avec Argon2.
     * @param login le pseudonyme de l'admin
     * @param password le mot de passe
     */
    public FormAdminConnValidator(String login, String password) {
        AdministrateurDAO aDAO;

        try {
            aDAO = new AdministrateurDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid(e.toString());
            return;
        }

        Administrateur adm = aDAO.get(login);

        if (adm == null) {
            setInvalid("Utilisateur non inscrit.");
            return;
        }

        if (!Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64).verify(adm.getMdpAdmin(),
                password.toCharArray())) {
            setInvalid("Mot de passe invalide.");
            return;
        }

        UserAuth.setAdministrateur(adm);
    }
}
