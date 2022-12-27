package controllers;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Administrateur;
import utility.ControllersUtils;
import validForm.FormAdminAddValidator;

import java.net.URL;
import java.util.ResourceBundle;

/**
* Contrôleur permettant l'ajout d'un Administrateur.
*/
public class AddAdminCtrl extends AbstractConnCtrl implements Initializable {
	
    @FXML
	private TextField adminLoginField;
	
    @FXML
	private TextField adminPasswordField;
	
    @FXML
	private TextField confirmPasswordField;
    
    @FXML
    private Text formErrorText;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        formErrorText.setVisible(false);
    }

	/**
	* Méthode qui valide l'ajout d'un administrateur.
	* @param event ActionEvent
	*/
    public void validateAddAdmin(ActionEvent event) {
        FormAdminAddValidator faav = new FormAdminAddValidator(
            adminLoginField.getText(),
            adminPasswordField.getText(),
            confirmPasswordField.getText());
        if (faav.isValid()) {
            formErrorText.setVisible(false);
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