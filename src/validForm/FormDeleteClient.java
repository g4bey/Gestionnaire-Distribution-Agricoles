package validForm;

import DAO.TourneeDAO;
import modele.Client;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class FormDeleteClient extends FormValidator {
    public  FormDeleteClient(Client client) {
        TourneeDAO tDAO;

        try {
            tDAO = new TourneeDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de donnée.");
            return;
        }

        // Il faut que le client soit associé à aucune tournée.
        try {
            if (tDAO.clientEstDansTournee(client)) {
                setInvalid("Ce client est associé à une commande.");
            }
        } catch (SQLException e) {
            setInvalid("Probleme, veuillez contacter l'administrateur: " + e.getErrorCode());
        }
    }
}
