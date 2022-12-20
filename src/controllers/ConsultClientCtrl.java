package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
* Contrôleur permettant l'aperçu Client.
*/

public class ConsultClientCtrl {
	
    @FXML
    private Text clientNameText;
    
    @FXML
    private Text clientAddressText;
    
    @FXML
    private Text clientPhoneText;
    
    private Stage stage;
    
    /**
     * Méthode qui permet de fermer la vue popup.
     * @param event ActionEvent
     */
    public void closePopup(ActionEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
    
    
}