package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import src.utility.ControllersUtils;

/**
* Contrôleur permettant la connexion d'un Administrateur.
*/
public class AdminConnCtrl implements Initializable {
	
    @FXML
    private TextField adminLoginField;
	
    @FXML
    private TextField adminPasswordField;
	
    @FXML
    private Button validateAdminConn;
	
    @FXML
    private Button cancelAdminConn;
    
    @FXML
    private Text formErrorText;
    
    private ControllersUtils util;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        util = new ControllersUtils();
    }
    
    /**
     * Méthode pour annuler la connexion de l'utilisateur
     * et revenir sur la page d'accueil.
     * @param event ActionEvent
     */
    public void cancelAdminConn(ActionEvent event) {
    	util.loadView(event, "/src/views/homePage.fxml");
    }
    
	/**
     * Méthode pour connecter l'utilisateur.
     * @param event ActionEvent
     */
    public void validateAdminConn(ActionEvent event) {
    	util.loadView(event, "/src/views/adminSelectMenu.fxml");
    }
}