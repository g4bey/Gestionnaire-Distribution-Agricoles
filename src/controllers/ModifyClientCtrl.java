package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import src.utility.ControllersUtils;

/**
* Contrôleur permettant la modification d'un Client.
*/
public class ModifyClientCtrl implements Initializable {

    @FXML
    private TextField clientNameField;
    
    @FXML
    private TextField clientAddressField;
    
    @FXML
    private TextField clientPhoneField;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    }
    
    /**
    * Méthode qui valide la modification d'un client.
    * @param event ActionEvent
    */
    public void validateModifyClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
    
    /**
    * Méthode qui ferme la vue de modification d'un client.
    * @param event ActionEvent
    */
    public void cancelModifyClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}