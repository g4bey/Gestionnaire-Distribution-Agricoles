package validForm;

import java.io.IOException;
import java.sql.SQLException;

import DAO.AdministrateurDAO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import modele.Administrateur;
import utility.DatabaseConnection;

public class formAdminConnValidator extends formValidator {
    public formAdminConnValidator(String login, String password) {
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

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);

        if (argon2.verify(argon2.hash(2, 15 * 1024, 1, password.toCharArray()), adm.getMdpAdmin().toCharArray())) {
            setInvalid("Mot de passe invalide.");
        }
    }
}
