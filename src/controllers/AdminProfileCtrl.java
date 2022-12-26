package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import utility.ControllersUtils;

/**
* Contrôleur de la page profil de l'Administrateur.
*/
public class AdminProfileCtrl implements Initializable {

    @FXML
    private Text adminLoginLabel;

    @FXML
    private Hyperlink changePasswordLink;

    private ControllersUtils util;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        util = new ControllersUtils();
    }

    /**
     * 
     * @param event
     */
    public void changePassword(ActionEvent event) {
        util.loadPopup(event, "/views/passwordChange.fxml");
    }
    /**
    * Méthode qui permet de fermer la vue du profile.
    * @param event ActionEvent
    */
    public void closeAdminProfile(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}