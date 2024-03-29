package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utility.ControllersUtils;
import validForm.FormAdminConnValidator;

/**
 * Contrôleur permettant la connexion d'un Administrateur.
 */
public class AdminConnCtrl implements Initializable {

    @FXML
    private TextField adminLoginField;

    @FXML
    private PasswordField adminPasswordField;

    @FXML
    private Button validateAdminConn;

    @FXML
    private Button cancelAdminConn;

    @FXML
    private Text formErrorText;

    private ControllersUtils util;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        util = new ControllersUtils();
        formErrorText.setVisible(false);
    } // initialize

    /**
     * Méthode pour annuler la connexion de l'utilisateur
     * et revenir sur la page d'accueil.
     * 
     * @param event ActionEvent
     */
    public void cancelAdminConn(ActionEvent event) {
        util.loadView(event, "/views/homePage.fxml");
    } // cancelAdminConn

    /**
     * Méthode pour connecter l'utilisateur.
     * 
     * @param event ActionEvent
     */
    public void validateAdminConn(ActionEvent event) {
        FormAdminConnValidator verif = new FormAdminConnValidator(adminLoginField.getText(),
                adminPasswordField.getText());
        if (verif.isValid()) {
            util.loadView(event, "/views/adminSelectMenu.fxml");
        } // if
        else {
            formErrorText.setText(verif.getErrors());
            formErrorText.setVisible(true);
        } // else
    } // validateAdminConn

} // AdminConnCtrl