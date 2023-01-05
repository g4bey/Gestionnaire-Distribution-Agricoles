package validForm;

import DAO.CommandeDAO;
import modele.Client;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Validateur de formulaire pour ModifyCommCtrl
 *
 * @see controllers.ModifyCommCtrl
 */
public class FormCommModifyValidator extends FormCommValidator {

    /**
     * Constructeur de FormCommAddValidator
     *
     * @param idCommande   L'id de la Commande récupéré dans la vue
     * @param libelle      Le libellé de la Commande récupéré dans la vue
     * @param poids        Le poids de la Commande récupéré dans la vue
     * @param date         La date de la Commande récupérée dans la vue
     * @param creneauDebut Le créneau de début de la Commande récupéré dans la vue
     * @param creneauFin   Le créneau de fin de la Commande récupéré dans la vue
     * @param client       Le Client de la Commande récupéré dans la vue
     */
    public FormCommModifyValidator(int idCommande, String libelle, String poids, LocalDate date, String creneauDebut,
            String creneauFin,
            Client client) {
        super(libelle, poids, date, creneauDebut, creneauFin, client);

        // Si jamais l'appel précédent n'arrive pas à bout.
        if (!this.isValid()) {
            return;
        } // if

        CommandeDAO cDAO;
        try {
            cDAO = new CommandeDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de données.");
            return;
        } // try/catch

        if (cDAO.get(idCommande).getTournee() != null) {
            setInvalid("Impossible de modifier une Commande présente dans une Tournée.");
        } // if
    } // constructeur

} // FormCommModifyValidator
