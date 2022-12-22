package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

/**
* Contrôleur permettant la suppression d'un Vehicule.
*/
public class DeleteVehicleCtrl implements Initializable {

    @FXML
    private Text vehicleLabelText;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    }
    
    /**
    * Méthode qui valide la suppression du véhicule.
    * @param event ActionEvent
    */
    public void validateDeleteVehicle(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
    
    /**
    * Méthode qui annule la suppression du véhicule.
    * @param event ActionEvent
    */
    public void cancelDeleteVehicle(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}