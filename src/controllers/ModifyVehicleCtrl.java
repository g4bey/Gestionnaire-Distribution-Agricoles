package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Vehicule;
import utility.ControllersUtils;

/**
* Contrôleur permettant la modification d'un Vehicule.
*/
public class ModifyVehicleCtrl {

    @FXML
    private TextField vehicleImmatField;
   
    @FXML
    private TextField vehicleLabelField;
    
    @FXML
    private TextField vehicleCapacityField;
    
    @FXML
    private Text formErrorText;

    private static Vehicule vehicule;
    
	/**
	* Méthode qui valide la modification d'un véhicule.
	* @param event ActionEvent
	*/
    public void validateModifyVehicle(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
	
	/**
    * Méthode qui permet de fermer la vue
    * de modification d'un véhicule.
    * @param event ActionEvent
    */
    public void cancelModifyVehicle(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }

    /**
    * Méthode qui récupère le véhicule sélectionné dans la listView
    * de la vue précédente (prodSelectMenu)
    * @param vehi Vehicule
    */
    public static void setVehicule(Vehicule vehi) {
        vehicule = vehi;
    }
}
