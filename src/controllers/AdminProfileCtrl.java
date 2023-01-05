package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import modele.Administrateur;
import utility.ControllersUtils;
import utility.UserAuth;

/**
* Contrôleur de la page profil de l'Administrateur.
*/
public class AdminProfileCtrl implements Initializable {

    @FXML
    private Text adminLoginLabel;

    @FXML
    private Hyperlink changePasswordLink;

    private ControllersUtils util;
    private Administrateur admin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin = UserAuth.getAdmin();
        adminLoginLabel.setText(admin.getPseudo());
        util = new ControllersUtils();
    } // initialize

    /**
     * Méthode permettant d'ouvrir la vue de changement de mot de passe
     * @param event
     */
    public void changePassword(ActionEvent event) {
        util.loadPopup(event, "/views/AdminPasswordChange.fxml");
    } // changePassword

    /**
    * Méthode qui permet de fermer la vue du profil.
    * @param event ActionEvent
    */
    public void closeAdminProfile(ActionEvent event) {
    	ControllersUtils.closePopupAndUpdateParent(event);
    } // closeAdminProfile

} // AdminProfileCtrl