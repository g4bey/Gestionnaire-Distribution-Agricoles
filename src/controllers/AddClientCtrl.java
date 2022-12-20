package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
* Contrôleur permettant l'ajout d'un Client.
*/

public class AddClientCtrl {
	
    @FXML
	private TextField clientNameField;
	
    @FXML
	private TextField clientAddressField;

    @FXML
	private TextField clientPhoneField;
    
    private Stage stage;
    
    /**
     * Méthode qui valide l'ajout d'un client.
     * @param event ActionEvent
     */
    public void addClient(ActionEvent event) {
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