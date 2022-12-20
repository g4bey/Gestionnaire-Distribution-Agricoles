package src.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.modele.Administrateur;
import src.modele.Producteur;
import src.modele.Client;

/**
* Contrôleur permettant la consultation Administrateur.
*/

public class AdminSelectMenuCtrl implements Initializable {
    @FXML
	private Label adminLoginLabel;
    
    @FXML
	private Rectangle adminProfileBtn;
	
    @FXML
	private Button addAdminBtn;
	
    @FXML
	private ListView<Administrateur> adminListView;
	
    @FXML
	private Button addProdBtn;
	
    @FXML
	private Button modifyProdBtn;
	
    @FXML
	private Button deleteProdBtn;
	
    @FXML
	private ListView<Producteur> prodListView;
	
    @FXML
	private Button addClientBtn;
	
    @FXML
	private Button modifyClientBtn;
	
    @FXML
	private Button deleteClientBtn;
	
    @FXML
	private ListView<Client> clientListView;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub   
    }
    
    /**
     * Méthode qui ouvre une popup affichant le profile de l'utilisateur.
     * @param event MouseEvent
     */
    public void userProfile(MouseEvent event) {
    	loadView(event, "/src/views/adminProfile.fxml");
    }
	
    /**
     * Méthode qui ouvre une popup pour l'ajout d'un administrateur.
     * @param event ActionEvent
     */
    
    public void addAdmin(ActionEvent event) {
    	loadView(event, "/src/views/addAdmin.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour l'ajout d'un producteur.
     * @param event ActionEvent
     */
    
    public void addProd(ActionEvent event) {
    	loadView(event, "/src/views/addProd.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour la modification d'un producteur.
     * @param event ActionEvent
     */
    
    public void modifyProd(ActionEvent event) {
    	loadView(event, "/src/views/modifyProd.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'un producteur.
     * @param event ActionEvent
     */
    
    public void deleteProd(ActionEvent event) {
    	loadView(event, "/src/views/deleteProd.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup permettant de 
     * consulter un producteur double-cliqué dans la ListView prodListView.
     * @param event MouseEvent
     */
    
    public void consultProd(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            loadView(event, "/src/views/consultProd.fxml");
        }
    }
    
    /**
     * Méthode qui ouvre une popup pour l'ajout d'un client.
     * @param event ActionEvent
     */
    
    public void addClient(ActionEvent event) {
    	loadView(event, "/src/views/addClient.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour la modification d'un client.
     * @param event ActionEvent
     */
    
    public void modifyClient(ActionEvent event) {
    	loadView(event, "/src/views/modifyClient.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'un client.
     * @param event ActionEvent
     */
    
    public void deleteClient(ActionEvent event) {
    	loadView(event, "/src/views/deleteClient.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup permettant de 
     * consulter un client double-cliqué dans la ListView clientListView.
     * @param event MouseEvent
     */
    
    public void consultClient(MouseEvent event) {
    	if (event.getClickCount() >= 2) {
            loadView(event, "/src/views/consultClient.fxml");
        }
    }
    
    /**
     * Méthode qui charge une vue passée en paramètre.
     * @param event ActionEvent
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
    	stage = new Stage();
    	stage.setScene(scene);
    	stage.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
    	stage.initModality(Modality.WINDOW_MODAL);
    	stage.show();
    }
    
    /**
     * Méthode qui charge une vue passée en paramètre.
     * @param event MouseEvent
     * @param url String
     */
    public void loadView(MouseEvent event, String url) {
    	try {
    	    root = FXMLLoader.load(getClass().getResource(url));
        } 
        catch (IOException e) {
    			// TODO Auto-generated catch block
            e.printStackTrace();
        }
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}