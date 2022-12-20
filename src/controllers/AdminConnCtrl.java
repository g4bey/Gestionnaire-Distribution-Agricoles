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
    	loadView(event, "/src/views/homePage.fxml");
    }
    
	/**
     * Méthode pour connecter l'utilisateur.
     * @param event ActionEvent
     */
    public void loginAdmin(ActionEvent event) {
    	loadView(event, "/src/views/adminSelectMenu.fxml");
    }
    
    /**
     * Méthode qui charge une vue passée en paramètre.
     * @param event MouseEvent
     * @param url String 
     */
    
    public void loadView(ActionEvent event, String url) {
    	try {
            root = FXMLLoader.load(getClass().getResource(url));
        }
    	catch (IOException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
        }
    	scene = new Scene(root);
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.setScene(scene);
    	stage.show();
    }
}