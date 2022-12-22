package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import src.modele.Vehicule;
import src.utility.ControllersUtils;
import src.modele.Tournee;
import src.modele.Commande;

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
	private ListView<Commande> commListView;
	
    @FXML
	private Button addTourBtn;
	
    @FXML
	private Button modifyTourBtn;
	
    @FXML
	private Button deleteTourBtn;
	
    @FXML
	private ListView<Tournee> tourListView;
	
    @FXML
	private Button addVehicleBtn;
	
    @FXML
	private Button modifyVehicleBtn;
	
    @FXML
	private Button deleteVehicleBtn;
	
    @FXML
	private ListView<Vehicule> vehicleListView;
    
    private ControllersUtils util;
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub   
    	util = new ControllersUtils();
    	
    	modifyCommBtn.setDisable(false);
    	modifyTourBtn.setDisable(false);
    	modifyVehicleBtn.setDisable(false);
    	deleteCommBtn.setDisable(false);
    	deleteTourBtn.setDisable(false);
    	deleteVehicleBtn.setDisable(false);
    }
    
    /**
    * Méthode qui ouvre une popup affichant le profile de l'utilisateur.
    * @param event
    */
    public void userProfile(MouseEvent event) {
    	util.loadPopup(event, "/src/views/prodProfile.fxml");
    }
	
    /**
    * Méthode qui ouvre une popup pour l'ajout d'une commande.
    * @param event ActionEvent
    */
    public void popupAddComm(ActionEvent event) {
    	util.loadPopup(event, "/src/views/addComm.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour la modification d'une commande
    * sélectionnée dans la ListView commListView.
    * @param event ActionEvent
    */
    public void popupModifyComm(ActionEvent event) {
    	util.loadPopup(event, "/src/views/modifyComm.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour demander la confirmation
    * de la suppression d'une commande sélectionnée dans la ListView commListView.
    * @param event ActionEvent
    */
    public void popupDeleteComm(ActionEvent event) {
    	util.loadPopup(event, "/src/views/deleteComm.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter une commande double-cliquée dans la ListView commListView.
    * @param event MouseEvent
    */
    public void popupConsultComm(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            util.loadPopup(event, "/src/views/consultCommV1.fxml");
        }
    }
    
    /**
    * Méthode qui ouvre une popup pour l'ajout d'une tournée.
    * @param event ActionEvent
    */
    public void popupAddTour(ActionEvent event) {
    	util.loadPopup(event, "/src/views/addTour.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour la modification d'une tournée
    * sélectionnée dans la ListView tourListView.
    * @param event ActionEvent
    */
    public void popupModifyTour(ActionEvent event) {
    	util.loadPopup(event, "/src/views/modifyTour.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour demander la confirmation
    * de la suppression d'une tournée sélectionnée dans la ListView commListView.
    * @param event ActionEvent
    */
    public void popupDeleteTour(ActionEvent event) {
    	util.loadPopup(event, "/src/views/deleteTour.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter une tournée double-cliquée dans la ListView tourListView.
    * @param event MouseEvent
    */
    public void popupConsultTour(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            util.loadPopup(event, "/src/views/consultTour.fxml");
        }
    }
    
    /**
    * Méthode qui ouvre une popup pour l'ajout d'un véhicule.
    * @param event ActionEvent
    */
    public void popupAddVehicle(ActionEvent event) {
    	util.loadPopup(event, "/src/views/addVehicle.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour la modification d'un véhicule
    * sélectionné dans la ListView vehicleListView.
    * @param event ActionEvent
    */
    public void popupModifyVehicle(ActionEvent event) {
    	util.loadPopup(event, "/src/views/modifyVehicle.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour demander la confirmation
    * de la suppression d'un véhicule sélectionné dans la ListView vehicleListView.
    * @param event ActionEvent
    */
    public void popupDeleteVehicle(ActionEvent event) {
    	util.loadPopup(event, "/src/views/deleteVehicle.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter un véhicule double-cliqué dans la ListView vehicleListView.
    * @param event
    */
    public void popupConsultVehicle(MouseEvent event) {
    	if (event.getClickCount() >= 2) {
            util.loadPopup(event, "/src/views/consultVehicle.fxml");
        }
    }
}