package validForm;

import DAO.TourneeDAO;
import modele.Commande;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class FormDeleteCommand extends FormValidator {
    public FormDeleteCommand(Commande commande) {
        TourneeDAO tDAO;

        try {
            tDAO = new TourneeDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de donnée.");
            return;
        }

        // Il faut qu'elle soit associée à aucune commande.
        if (tDAO.getAll().stream().anyMatch(
                tournee -> tournee.getCommandes().contains(commande))) {
            setInvalid("Cette commande est associée à une tournée");
        }
    }
}
