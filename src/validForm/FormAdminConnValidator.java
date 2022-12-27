package validForm;

import java.io.IOException;
import java.sql.SQLException;

import DAO.AdministrateurDAO;
import de.mkammerer.argon2.Argon2Factory;
import modele.Administrateur;
import utility.DatabaseConnection;
import utility.UserAuth;

public class FormAdminConnValidator extends FormValidator {
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
