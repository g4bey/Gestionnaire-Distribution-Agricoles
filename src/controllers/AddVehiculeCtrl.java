package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utility.ControllersUtils;

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
    
    @FXML
    private Text formErrorText;
    
	/**
	* Méthode qui valide l'ajout d'un véhicule.
	* @param event ActionEvent
	*/
    public void validateAddVehicle(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
	
	/**
    * Méthode qui permet de fermer la vue d'ajout d'un véhicule.
    * @param event ActionEvent
    */
    public void cancelAddVehicle(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}