package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
* Contrôleur permettant l'ajout d'un Administrateur.
*/

public class AddAdminCtrl {
	
    @FXML
	private TextField adminLoginField;
	
    @FXML
	private TextField adminPasswordField;
	
    @FXML
	private TextField confirmPasswordField;
    
    private Stage stage;
	
	/**
	 * Méthode qui valide l'ajout d'un administrateur.
	 * @param event ActionEvent
	 */
	
    public void addAdmin(ActionEvent event) {
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