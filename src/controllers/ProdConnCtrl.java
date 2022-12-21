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
* Contrôleur permettant la connexion d'un Producteur.
*/
public class ProdConnCtrl implements Initializable {
    @FXML
	private TextField prodSiretField;
	
    @FXML
	private TextField prodPasswordField;

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
    }
    
    /**
    * Méthode pour revenir sur la page d'accueil.
    * @param event ActionEvent
    */
    public void validateProdConn(ActionEvent event) {
    	util.loadView(event, "/src/views/prodSelectMenu.fxml");
    }
    
    /**
    * Méthode pour connecter l'utilisateur.
    * @param event ActionEvent
    */
    public void cancelProdConn(ActionEvent event) {
    	util.loadView(event, "/src/views/homePage.fxml");
    }
}