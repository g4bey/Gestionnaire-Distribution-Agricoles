package validForm;

import DAO.TourneeDAO;
import modele.Client;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Validateur de formulaire pour DeleteClientCtrl.
 * Utilisé lors qu'on supprime un Client.
 * Si un Client est concerné par une Tournée, il ne peut disparaître de la base.
 *
 * @see controllers.DeleteClientCtrl
 */
public class FormDeleteClient extends FormValidator {

    /**
     * Constructeur de FormDeleteClient.
     * @param client Le client que l'on doit supprimer.
     */
    public  FormDeleteClient(Client client) {
        TourneeDAO tDAO;

        try {
            tDAO = new TourneeDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de données.");
            return;
        } // try/catch

        // Il faut que le client soit associé à aucune tournée.
        try {
            if (tDAO.clientEstDansTournee(client.getIdClient())) {
                setInvalid("Impossible de supprimer un Client associé à une Tournée.");
            } // if
        } catch (SQLException e) {
            setInvalid("Probleme, veuillez contacter l'administrateur: " + e.getErrorCode());
        } // try/catch
    } // constructeur

} // FormDeleteValidator
