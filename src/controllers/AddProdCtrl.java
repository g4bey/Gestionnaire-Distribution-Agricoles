package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

/**
* Contrôleur permettant l'ajout d'un Producteur.
*/
public class AddProdCtrl {
	
    @FXML
	private TextField prodSiretField;
	
    @FXML
	private TextField propNameField;
	
    @FXML
	private TextField prodAddressField;
	
    @FXML
	private TextField prodPhoneField;
	
    @FXML
	private TextField prodPasswordField;
	
    @FXML
	private TextField confirmPasswordField;
    
    @FXML
    private Text formErrorText;
    
	/**
	* Méthode qui valide l'ajout d'un producteur.
	* @param event ActionEvent
	*/
    public void validateAddProd(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
	
	/**
    * Méthode qui permet de fermer la vue d'ajout d'un producteur.
    * @param event ActionEvent
    */
    public void cancelAddProd(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}