package validForm;

import DAO.TourneeDAO;
import modele.Vehicule;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Validateur de formulaire pour DeleteVehicleCtrl.
 * Utilisé lors qu'on supprime un véhicule.
 * Si un véhicule est lié à une tournée, on ne peut le supprimer.
 *
 * @see controllers.DeleteVehicleCtrl
 */
public class FormDeleteVehicule extends FormValidator {

    /**
     * Constructeur de FormDeleteVehicule.
     * @param vehicule Le Véhicule à supprimer.
     */
    public FormDeleteVehicule(Vehicule vehicule) {
        TourneeDAO tDAO;

        try {
            tDAO = new TourneeDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de données.");
            return;
        } // try/catch
        
        // Il faut qu'elle ne soit associée à aucune Tournée.
        try {
            if (!tDAO.getTourneesByVehicule(vehicule).isEmpty()) {
                setInvalid("Impossible de supprimer un Véhicule associé à une Tournée");
            }
        } catch (SQLException e) {
            setInvalid("Problème, veuillez contacter l'Administrateur: " + e.getErrorCode());
        } // try/catch
    } // constructeur

} // FormDeleteVehicule
