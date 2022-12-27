package controllers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Administrateur;
import utility.ControllersUtils;
import validForm.FormAdminAddValidator;

/**
* Contrôleur permettant l'ajout d'un Administrateur.
*/
public class AddAdminCtrl extends AbstractConnCtrl {
	
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
        formErrorText.setVisible(false);
        FormAdminAddValidator faav = new FormAdminAddValidator(
            adminLoginField.getText(),
            adminPasswordField.getText(),
            confirmPasswordField.getText());
        if (faav.isValid()) {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
            String hashedPs = argon2.hash(2,15*1024,1, adminPasswordField.getText().toCharArray());
            aDAO.add(new Administrateur(adminLoginField.getText(), hashedPs));
            ControllersUtils.closePopup(event);
        }
        else {
            formErrorText.setText(faav.getErrors());
            formErrorText.setVisible(true);
        }
    }
    
    /**
    * Méthode qui permet de fermer la vue d'ajout d'un administrateur.
    * @param event ActionEvent
    */
    public void cancelAddAdmin(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}