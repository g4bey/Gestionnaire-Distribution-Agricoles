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
    private Text prodLoginText;
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
		// TODO Auto-generated method stub
        user = UserAuth.getProd();
        prodSiretText.setText(user.getSiret());
        prodAddressText.setText(user.getAdresseProd());
        prodPhoneText.setText(user.getNumTelProd());
		util = new ControllersUtils();
    }

    /**
     * Méthode qui permet la modification du profile utilisateur.
     * @param event ActionEvent
     */
    public void modifyInfo(ActionEvent event) {
        util.loadPopup(event, "/views/passwordChange.fxml");
    }
    
    /**
	* Méthode qui permet de fermer la vue du profile.
	* @param event ActionEvent
	*/
    public void closeProdProfile(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}