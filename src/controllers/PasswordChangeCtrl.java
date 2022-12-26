package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utility.ControllersUtils;

public class PasswordChangeCtrl {
    
    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmField;

    /**
     * Méthode qui permet à l'utilisateur de changer son mot de passe.
     * @param event ActionEvent
     */
    public void validatePasswordChange(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }

    /**
     * Méthode qui ferme la fenêtre de changement de mot de passe.
     * @param event ActionEvent
     */
    public void cancelPasswordChange(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
}