package GDA.controllers;

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
import GDA.modele.Administrateur;
import GDA.modele.Producteur;
import GDA.utility.ControllersUtils;
import GDA.modele.Client;

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
    
    private ControllersUtils util;
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub 
    	this.util = new ControllersUtils();
    	
    	modifyProdBtn.setDisable(false);
    	modifyClientBtn.setDisable(false);
    	deleteProdBtn.setDisable(false);
    	deleteClientBtn.setDisable(false);
    }
    
    /**
    * Méthode qui ouvre une popup affichant le profile de l'utilisateur.
    * @param event MouseEvent
    */
    public void userProfile(MouseEvent event) {
    	util.loadPopup(event, "/GDA/views/adminProfile.fxml");
    }
	
    /**
    * Méthode qui ouvre une popup pour l'ajout d'un administrateur.
    * @param event ActionEvent
    */
    public void popupAddAdmin(ActionEvent event) {
    	util.loadPopup(event, "/GDA/views/addAdmin.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour l'ajout d'un producteur.
    * @param event ActionEvent
    */
    public void popupAddProd(ActionEvent event) {
    	util.loadPopup(event, "/GDA/views/addProd.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour la modification d'un producteur.
    * @param event ActionEvent
    */
    public void popupModifyProd(ActionEvent event) {
    	util.loadPopup(event, "/GDA/views/modifyProd.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour demander la confirmation
    * de la suppression d'un producteur.
    * @param event ActionEvent
    */
    public void popupDeleteProd(ActionEvent event) {
    	util.loadPopup(event, "/GDA/views/deleteProd.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter un producteur double-cliqué dans la ListView prodListView.
    * @param event MouseEvent
    */
    public void popupConsultProd(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            util.loadPopup(event, "/GDA/views/consultProd.fxml");
        }
    }
    
    /**
    * Méthode qui ouvre une popup pour l'ajout d'un client.
    * @param event ActionEvent
    */
    public void popupAddClient(ActionEvent event) {
    	util.loadPopup(event, "/GDA/views/addClient.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour la modification d'un client.
    * @param event ActionEvent
    */
    public void popupModifyClient(ActionEvent event) {
    	util.loadPopup(event, "/GDA/views/modifyClient.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour demander la confirmation
    * de la suppression d'un client.
    * @param event ActionEvent
    */
    public void popupDeleteClient(ActionEvent event) {
    	util.loadPopup(event, "/GDA/views/deleteClient.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter un client double-cliqué dans la ListView clientListView.
    * @param event MouseEvent
    */
    public void popupConsultClient(MouseEvent event) {
    	if (event.getClickCount() >= 2) {
            util.loadPopup(event, "/GDA/views/consultClient.fxml");
        }
    }
}