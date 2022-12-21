package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import src.utility.ControllersUtils;

/**
* Contrôleur permettant la modification d'une Tournee.
*/
public class ModifyTourneeCtrl {

    @FXML
	private TextField tourLabelField;

    @FXML
    private Label maxWeightLabel;
   
    @FXML
    private Label startLabel;
   
    @FXML
    private Label endLabel;
   
    @FXML
    private Label datetimeLabel;
    
	/**
	* Méthode qui valide la modification d'une tournée.
	* @param event ActionEvent
	*/
    public void validateModifyTour(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
	
	/**
    * Méthode qui permet de fermer la vue 
    * de modification d'une tournée.
    * @param event ActionEvent
    */
    public void cancelModifyTour(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}