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

/**
* Contrôleur permettant la consultation d'un Producteur.
*/

public class ProdSelectMenuCtrl implements Initializable {
    @FXML
	private Label prodNameLabel;
    
    @FXML
	private Rectangle prodProfileBtn;
	
    @FXML
	private Button addCommBtn;
    
    @FXML
    private Button modifyCommBtn;
    
    @FXML
    private Button deleteCommBtn;
	
    @FXML
	private ListView<String> commListView;
	
    @FXML
	private Button addTourBtn;
	
    @FXML
	private Button modifyTourBtn;
	
    @FXML
	private Button deleteTourBtn;
	
    @FXML
	private ListView<String> tourListView;
	
    @FXML
	private Button addVehicleBtn;
	
    @FXML
	private Button modifyVehicleBtn;
	
    @FXML
	private Button deleteVehicleBtn;
	
    @FXML
	private ListView<String> vehicleListView;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
        modifyCommBtn.setDisable(false);
        deleteCommBtn.setDisable(false);
        modifyTourBtn.setDisable(false);
    	deleteTourBtn.setDisable(false);
        modifyVehicleBtn.setDisable(false);
        deleteVehicleBtn.setDisable(false);
        
        commListView.getItems().add("truc");
        tourListView.getItems().add("machin");
        vehicleListView.getItems().add("muche");
        
        
    }
    
    /**
     * Méthode qui ouvre une popup affichant le profile de l'utilisateur.
     * @param event
     */
    public void userProfile(MouseEvent event) {
    	loadView(event, "/src/views/prodProfile.fxml");
    }
	
    /**
     * Méthode qui ouvre une popup pour l'ajout d'une commande.
     * @param event ActionEvent
     */
    
    public void addComm(ActionEvent event) {
    	loadView(event, "/src/views/addComm.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour la modification d'une commande
     * sélectionnée dans la ListView commListView.
     * @param event ActionEvent
     */
    
    public void modifyComm(ActionEvent event) {
    	loadView(event, "/src/views/modifyComm.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'une commande sélectionnée dans la ListView commListView.
     * @param event ActionEvent
     */
    
    public void deleteComm(ActionEvent event) {
    	loadView(event, "/src/views/deleteComm.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup permettant de 
     * consulter une commande double-cliquée dans la ListView commListView.
     * @param event MouseEvent
     */
    
    public void consultComm(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            loadView(event, "/src/views/consultCommV1.fxml");
        }
    }
    
    /**
     * Méthode qui ouvre une popup pour l'ajout d'une tournée.
     * @param event ActionEvent
     */
    
    public void addTour(ActionEvent event) {
    	loadView(event, "/src/views/addTour.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour la modification d'une tournée
     * sélectionnée dans la ListView tourListView.
     * @param event ActionEvent
     */
    
    public void modifyTour(ActionEvent event) {
    	loadView(event, "/src/views/modifyTour.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'une tournée sélectionnée dans la ListView commListView.
     * @param event ActionEvent
     */
    
    public void deleteTour(ActionEvent event) {
    	loadView(event, "/src/views/deleteTour.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup permettant de 
     * consulter une tournée double-cliquée dans la ListView tourListView.
     * @param event MouseEvent
     */
    
    public void consultTour(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            loadView(event, "/src/views/consultTour.fxml");
        }
    }
    
    /**
     * Méthode qui ouvre une popup pour l'ajout d'un véhicule.
     * @param event ActionEvent
     */
    
    public void addVehicle(ActionEvent event) {
    	loadView(event, "/src/views/addVehicle.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour la modification d'un véhicule
     * sélectionné dans la ListView vehicleListView.
     * @param event ActionEvent
     */
    
    public void modifyVehicle(ActionEvent event) {
    	loadView(event, "/src/views/modifyVehicle.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'un véhicule sélectionné dans la ListView vehicleListView.
     * @param event ActionEvent
     */
    
    public void deleteVehicle(ActionEvent event) {
    	loadView(event, "/src/views/deleteVehicle.fxml");
    }
    
    /**
     * Méthode qui ouvre une popup permettant de 
     * consulter un véhicule double-cliqué dans la ListView vehicleListView.
     * @param event
     */
    
    public void consultVehicle(MouseEvent event) {
    	if (event.getClickCount() >= 2) {
            loadView(event, "/src/views/consultVehicle.fxml");
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