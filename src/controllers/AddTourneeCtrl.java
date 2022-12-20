package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
* Contrôleur permettant l'ajout d'une Tournee.
*/

public class AddTourneeCtrl {
	
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
    
    private Stage stage;
	
	/**
	 * Méthode qui valide l'ajout d'une tournée.
	 * @param event ActionEvent
	 */
    public void addTour(ActionEvent event) {
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