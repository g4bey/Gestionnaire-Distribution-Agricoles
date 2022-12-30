package validForm;

import DAO.TourneeDAO;
import modele.Vehicule;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Utilisé lors qu'on supprime un véhicule.
 * Si un véhicule est lié à une tournée, on ne peut le supprimer.
 * @see controllers.DeleteVehicleCtrl
 */
public class FormDeleteVehicule extends FormValidator {

    /**
     * Constructeur de FormDeleteVehicule.
     * @param vehicule le vehicule à supprimer.
     */
    public FormDeleteVehicule(Vehicule vehicule) {
        TourneeDAO tDAO;

        try {
            tDAO = new TourneeDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de donnée.");
            return;
        }
        
        // Il faut qu'elle soit associée à aucune tournée.
        try {
            if (!tDAO.getTourneesByVehicule(vehicule).isEmpty()) {
                setInvalid("Ce véhicule est associé à une tournée");
            }
        } catch (SQLException e) {
            setInvalid("Probleme, veuillez contacter l'administrateur: " + e.getErrorCode());
        }
    }
}
