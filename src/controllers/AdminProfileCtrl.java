package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

/**
* Contrôleur de la page profil de l'Administrateur.
*/
public class AdminProfileCtrl {

    @FXML
    private Text adminLoginLabel;
    
    /**
    * Méthode qui permet de fermer la vue du profile.
    * @param event ActionEvent
    */
    public void closeAdminProfile(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}