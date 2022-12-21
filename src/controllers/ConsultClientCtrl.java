package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

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
    
    /**
     * Méthode qui permet de fermer la vue de consultation 
     * des informations d'un client.
     * @param event ActionEvent
     */
    public void closeConsultClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}