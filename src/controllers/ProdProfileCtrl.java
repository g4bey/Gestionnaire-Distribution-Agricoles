package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import modele.Producteur;
import utility.ControllersUtils;
import utility.UserAuth;

/**
 * Contrôleur de la page de profil du Producteur.
 */
public class ProdProfileCtrl implements Initializable {

    @FXML
    private Text propNameText;
    @FXML
    private Text prodSiretText;
    @FXML
    private Text prodAddressText;
    @FXML
    private Text prodPhoneText;
    private ControllersUtils util;
    @FXML
    private Hyperlink modifyPasswordLink;
    private Producteur user;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        user = UserAuth.getProd();
        propNameText.setText(user.getProprietaire());
        prodSiretText.setText(user.getSiret());
        propNameText.setText(user.getProprietaire());
        prodAddressText.setText(user.getAdresseProd().replace(",", " "));
        prodPhoneText.setText(user.getNumTelProd());
        util = new ControllersUtils();
    } // initialize

    /**
     * Méthode qui permet la modification du profil utilisateur.
     * 
     * @param event ActionEvent
     */
    public void modifyPassword(ActionEvent event) {
        util.loadPopup(event, "/views/ProdpasswordChange.fxml");
    } // modifyPassword

    /**
     * Méthode qui permet de fermer la vue du profil.
     * 
     * @param event ActionEvent
     */
    public void closeProdProfile(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    } // closeProdProfile

} // ProdProfileCtrl