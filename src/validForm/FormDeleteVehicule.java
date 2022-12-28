package validForm;

import DAO.TourneeDAO;
import modele.Vehicule;
import utility.DatabaseConnection;

import java.io.IOException;
import java.sql.SQLException;

public class FormDeleteVehicule extends FormValidator {
    public FormDeleteVehicule(Vehicule vehicule) {
        TourneeDAO tDAO;

        try {
            tDAO = new TourneeDAO(DatabaseConnection.getInstance("production"));
        } catch (ClassNotFoundException | SQLException | IOException e) {
            setInvalid("Impossible de se connecter à la base de donnée.");
            return;
        }
        
        // Il faut qu'elle soit associée à aucune commande.
        try {
            if (!tDAO.getTourneesByVehicule(vehicule).isEmpty()) {
                setInvalid("Ce véhicule est associé à une tournée");
            }
        } catch (SQLException e) {
            setInvalid("Probleme, veuillez contacter l'administrateur: " + e.getErrorCode());
        }
    }
}
