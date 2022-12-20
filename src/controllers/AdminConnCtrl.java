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
import javafx.stage.Stage;

/**
* Contrôleur permettant la connexion d'un Administrateur.
*/

public class AdminConnCtrl {
	
    @FXML
    private TextField adminLoginField;
	
    @FXML
    private TextField adminPasswordField;
	
    @FXML
    private Button validateAdminConn;
	
    @FXML
    private Button cancelAdminConn;
    
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
    public void loginAdmin(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/src/views/adminSelectMenu.fxml"));
    	scene = new Scene(root);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
}