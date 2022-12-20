package src.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
     * Méthode pour revenir sur la page d'accueil.
     * @param event
	 * @throws IOException 
     */
    public void switchToHomePage(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/src/views/homePage.fxml"));
    	scene = new Scene(root);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
    
    /**
     * Méthode pour connecter l'utilisateur.
     * @param event
     * @throws IOException 
     */
    public void loginProd(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/src/views/prodSelectMenu.fxml"));
    	scene = new Scene(root);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
}