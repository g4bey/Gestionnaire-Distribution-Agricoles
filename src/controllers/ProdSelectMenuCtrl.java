package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.CommandeDAO;
import DAO.TourneeDAO;
import DAO.VehiculeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import modele.Vehicule;
import utility.ControllersUtils;
import utility.DatabaseConnection;
import modele.Tournee;
import modele.Commande;

/**
* Contrôleur permettant la consultation d'un Producteur.
*/
public class ProdSelectMenuCtrl extends AbstractConnCtrl implements Initializable {
    @FXML
	private Label prodNameLabel;
    
    @FXML
	private Button prodProfileBtn;
	
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

        commListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Commande row, boolean empty) {
                super.updateItem(row, empty) ;
                setText(empty ? null : row.getLibelle());
            }
        });

        try {
            commDAO = new CommandeDAO(DatabaseConnection.getInstance("production"));
            tDAO = new TourneeDAO(DatabaseConnection.getInstance("production"));
            vDAO = new VehiculeDAO(DatabaseConnection.getInstance("production"));
            commListView.getItems().addAll(commDAO.getAll());
            tourListView.getItems().addAll(tDAO.getAll());
            vehicleListView.getItems().addAll(vDAO.getAll());
        } catch (ClassNotFoundException | SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        commListView.focusedProperty().addListener((s) -> {
            if (commListView.focusedProperty().get()) {
                modifyCommBtn.setDisable(false);
                deleteCommBtn.setDisable(false);
            }
            else {
                modifyCommBtn.setDisable(true);
                deleteCommBtn.setDisable(true);
            }
        });

        tourListView.focusedProperty().addListener((s) -> {
            if (commListView.focusedProperty().get()) {
                modifyTourBtn.setDisable(false);
                deleteTourBtn.setDisable(false);
            }
            else {
                modifyVehicleBtn.setDisable(true);
                deleteVehicleBtn.setDisable(true);
            }
        });
    }
    
    /**
    * Méthode qui ouvre une popup affichant le profile de l'utilisateur.
    * @param event
    */
    public void userProfile(ActionEvent event) {
    	util.loadPopup(event, "/views/prodProfile.fxml");
    }

    /**
    * Méthode qui déconnecte l'utilisateur et redirige vers la première vue.
    * @param event ActionEvent
    */
	public void deconnection(ActionEvent event) {
        util.loadView(event, "/views/homePage.fxml");
    }
	
    /**
    * Méthode qui ouvre une popup pour l'ajout d'une commande.
    * @param event ActionEvent
    */
    public void popupAddComm(ActionEvent event) {
    	util.loadPopup(event, "/views/addComm.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour la modification d'une commande
    * sélectionnée dans la ListView commListView.
    * @param event ActionEvent
    */
    public void popupModifyComm(ActionEvent event) {
        ModifyCommCtrl.setCommande(commListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/modifyComm.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour demander la confirmation
    * de la suppression d'une commande sélectionnée dans la ListView commListView.
    * @param event ActionEvent
    */
    public void popupDeleteComm(ActionEvent event) {
        ModifyCommCtrl.setCommande(commListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/deleteComm.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter une commande double-cliquée dans la ListView commListView.
    * @param event MouseEvent
    */
    public void popupConsultComm(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            ModifyCommCtrl.setCommande(commListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultCommV1.fxml");
        }
    }
    
    /**
    * Méthode qui ouvre une popup pour l'ajout d'une tournée.
    * @param event ActionEvent
    */
    public void popupAddTour(ActionEvent event) {
    	util.loadPopup(event, "/views/addTour.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour la modification d'une tournée
    * sélectionnée dans la ListView tourListView.
    * @param event ActionEvent
    */
    public void popupModifyTour(ActionEvent event) {
        ModifyTourCtrl.setTournee(tourListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/modifyTour.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour demander la confirmation
    * de la suppression d'une tournée sélectionnée dans la ListView commListView.
    * @param event ActionEvent
    */
    public void popupDeleteTour(ActionEvent event) {
        ModifyTourCtrl.setTournee(tourListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/deleteTour.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter une tournée double-cliquée dans la ListView tourListView.
    * @param event MouseEvent
    */
    public void popupConsultTour(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            ModifyTourCtrl.setTournee(tourListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultTour.fxml");
        }
    }
    
    /**
    * Méthode qui ouvre une popup pour l'ajout d'un véhicule.
    * @param event ActionEvent
    */
    public void popupAddVehicle(ActionEvent event) {
    	util.loadPopup(event, "/views/addVehicle.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour la modification d'un véhicule
    * sélectionné dans la ListView vehicleListView.
    * @param event ActionEvent
    */
    public void popupModifyVehicle(ActionEvent event) {
        ModifyVehicleCtrl.setVehicule(vehicleListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/modifyVehicle.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup pour demander la confirmation
    * de la suppression d'un véhicule sélectionné dans la ListView vehicleListView.
    * @param event ActionEvent
    */
    public void popupDeleteVehicle(ActionEvent event) {
        ModifyVehicleCtrl.setVehicule(vehicleListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/deleteVehicle.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter un véhicule double-cliqué dans la ListView vehicleListView.
    * @param event
    */
    public void popupConsultVehicle(MouseEvent event) {
    	if (event.getClickCount() >= 2) {
            ModifyVehicleCtrl.setVehicule(vehicleListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultVehicle.fxml");
        }
    }
}