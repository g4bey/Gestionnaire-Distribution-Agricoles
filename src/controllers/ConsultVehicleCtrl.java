package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import src.modele.Tournee;
import src.utility.ControllersUtils;

/**
* Contrôleur permettant l'aperçu d'un Vehicule.
*/
public class ConsultVehicleCtrl implements Initializable {

    @FXML
    private Text vehicleLabelText;
    
    @FXML
    private Text vehicleImmatText;
    
    @FXML
    private Text vehicleCapacityText;
    
    @FXML
    private ListView<Tournee> tourListView;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    }
	
	/**
    * Méthode qui ferme la vue de consultation
    * des informations d'un véhicule.
    * @param event ActionEvent
    */
    public void closeConsultVehicle(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}