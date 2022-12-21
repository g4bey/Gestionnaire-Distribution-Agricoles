package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

/**
* Contrôleur permettant la suppression d'une Commande.
*/
public class DeleteCommCtrl implements Initializable {
	
    @FXML
    private Text commLabelText;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    }
    
    /**
    * Méthode qui valide la suppression de la commande.
    * @param event ActionEvent
    */
    public void validateDeleteComm(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
    
    /**
    * Méthode qui annule la suppression de la commande.
    * @param event ActionEvent
    */
    public void cancelDeleteComm(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}