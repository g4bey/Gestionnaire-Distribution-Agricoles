package src.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
* Contrôleur de la page d'accueil.
*/

public class HomePageCtrl {
	
    @FXML
    private Button adminConnBtn;
	
    @FXML
    private Button prodConnBtn;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
	
    /**
     * Méthode qui permet de changer vers la vue de connexion des administrateur.
     * @param ActionEvent event
     * @throws IOException 
     */
    
    public void switchToAdminConn(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/src/views/adminConn.fxml"));
    	scene = new Scene(root);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
    
    /**
     * Méthode qui permet de changer vers la vue de connexion des producteurs.
     * @param ActionEvent event
     * @throws IOException 
     */
    
    public void switchToProdConn(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("/src/views/prodConn.fxml"));
    	scene = new Scene(root);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
}