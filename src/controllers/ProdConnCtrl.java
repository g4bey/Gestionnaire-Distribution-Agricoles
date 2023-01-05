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
import validForm.FormProdConnCtrl;

/**
 * Contrôleur permettant la connexion d'un Producteur.
 */
public class ProdConnCtrl implements Initializable {
    @FXML
    private TextField prodSiretField;

    @FXML
    private PasswordField prodPasswordField;

    @FXML
    private Button validateProdConn;

    @FXML
    private Button cancelProdConn;

    @FXML
    private Text connErreurText;

    private ControllersUtils util;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        util = new ControllersUtils();
    } // initialize

    /**
     * Méthode qui authentifie l'utilisateur.
     * 
     * @param event ActionEvent
     */
    public void validateProdConn(ActionEvent event) {
        FormProdConnCtrl verif = new FormProdConnCtrl(prodSiretField.getText(), prodPasswordField.getText());
        if (verif.isValid()) {
            util.loadView(event, "/views/prodSelectMenu.fxml");
        } // if
        else {
            connErreurText.setText(verif.getErrors());
            connErreurText.setVisible(true);
        } // else
    } // validateProdConn

    /**
     * Méthode pour revenir sur la page d'accueil.
     * 
     * @param event ActionEvent
     */
    public void cancelProdConn(ActionEvent event) {
        util.loadView(event, "/views/homePage.fxml");
    } // cancelProdConn

} // ProdConnCtrl