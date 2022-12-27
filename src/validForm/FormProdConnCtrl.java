package validForm;

import java.io.IOException;
import java.sql.SQLException;

import DAO.ProducteurDAO;
import de.mkammerer.argon2.Argon2Factory;
import modele.Producteur;
import utility.DatabaseConnection;
import utility.UserAuth;

public class FormProdConnCtrl extends FormValidator {
    public FormProdConnCtrl(String login, String password) {
        ProducteurDAO prodDAO;

        try {
            prodDAO = new ProducteurDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid(e.toString());
            return;
        }

        Producteur prod = prodDAO.get(login);

        if (prod == null) {
            setInvalid("SIRET inconnu.");
            return;
        }

        if (!Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64).verify(prod.getMdpProd(),
                password.toCharArray())) {
            setInvalid("Mot de passe invalide.");
            return;
        }

        UserAuth.setProducteur(prod);
    }
}
