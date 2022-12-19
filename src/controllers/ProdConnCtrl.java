package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
* Contrôleur permettant la connexion d'un Producteur.
*/

public class ProdConnCtrl {
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
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    /**
     * Méthode pour connecter l'utilisateur.
     * @param event
     */
    public void loginAdmin(ActionEvent event) {
    	
    }
}