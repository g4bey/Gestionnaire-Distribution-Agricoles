package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    
    private Stage stage;
	
	/**
	 * Méthode qui valide l'ajout d'un producteur.
	 * @param event
	 */
    public void addProd(ActionEvent event) {
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