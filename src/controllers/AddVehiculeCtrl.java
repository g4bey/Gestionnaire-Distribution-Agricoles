package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
* Contrôleur permettant l'ajout d'un Vehicule.
*/

public class AddVehiculeCtrl {

    @FXML
    private TextField vehicleImmatField;
   
    @FXML
    private TextField vehicleLabelField;
    
    @FXML
    private TextField vehicleCapacityField;
    
    private Stage stage;
	
	/**
	 * Méthode qui valide l'ajout d'un véhicule.
	 * @param event ActionEvent
	 */
    public void addVehicle(ActionEvent event) {
        closePopup(event);
    }
	
	/**
     * Méthode qui permet de fermer la vue popup.
     * @param event ActionEvent
     */
    public void closePopup(ActionEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
}