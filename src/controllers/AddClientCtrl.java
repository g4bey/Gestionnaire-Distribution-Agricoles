package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

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
    
    @FXML
    private Text formErrorText;
    
    /**
    * Méthode qui valide l'ajout d'un client.
    * @param event ActionEvent
    */
    public void validateAddClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
    
    /**
    * Méthode qui permet de fermer la vue d'ajout d'un client.
    * @param event ActionEvent
    */
    public void cancelAddClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}