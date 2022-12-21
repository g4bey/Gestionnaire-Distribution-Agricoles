package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

/**
* Contrôleur permettant la suppression d'un Client.
*/
public class DeleteClientCtrl implements Initializable {
    
    @FXML
    private Text clientNameText;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    }
    
    /**
     * Méthode qui valide la suppression du client.
     * @param event ActionEvent
     */
    public void validateDeleteClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
    
    /**
     * Méthode qui annule la suppression du client.
     * @param event ActionEvent
     */
    public void cancelDeleteClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}