package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import src.utility.ControllersUtils;

/**
* Contrôleur de la page d'accueil.
*/
public class HomePageCtrl implements Initializable {
	
    @FXML
    private Button adminConnBtn;
	
    @FXML
    private Button prodConnBtn;
    
    private ControllersUtils util;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        util = new ControllersUtils();
    }
	
    /**
    * Méthode qui permet de changer vers la vue de connexion des administrateur.
    * @param ActionEvent event
    */
    public void switchToAdminConn(ActionEvent event) {
    	util.loadView(event, "/src/views/adminConn.fxml");
    }
    
    /**
    * Méthode qui permet de changer vers la vue de connexion des producteurs.
    * @param ActionEvent event
    */
    public void switchToProdConn(ActionEvent event) {
    	util.loadView(event, "/src/views/prodConn.fxml");
    }
}