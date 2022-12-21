package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

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
    
    @FXML
    private Text formErrorText;
	
	/**
	 * Méthode qui valide l'ajout d'un administrateur.
	 * @param event ActionEvent
	 */
    public void validateAddAdmin(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
    
    /**
     * Méthode qui permet de fermer la vue d'ajout d'un administrateur.
     * @param event ActionEvent
     */
    public void cancelAddAdmin(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}