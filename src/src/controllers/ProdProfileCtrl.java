package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

/**
* Contrôleur de la page de profil du Producteur.
*/
public class ProdProfileCtrl implements Initializable {
	
    @FXML
    private Text prodLoginText;
    
    @FXML
    private Text prodSiretText;
    
    @FXML
    private Text prodAddressText;
    
    @FXML
    private Text prodPhoneText;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    }
    
    /**
	* Méthode qui permet de fermer la vue du profile.
	* @param event ActionEvent
	*/
    public void closeProdProfile(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}