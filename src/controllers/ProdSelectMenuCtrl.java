package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import modele.Producteur;
import modele.Vehicule;
import utility.ControllersUtils;
import modele.Tournee;
import modele.Commande;
import utility.UserAuth;

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
                setItem(row);
            }
        });

        tourListView.focusedProperty().addListener((s) -> {
            if (tourListView.focusedProperty().get()) {
                modifyTourBtn.setDisable(false);
                deleteTourBtn.setDisable(false);
            }
            else {
                modifyTourBtn.setDisable(true);
                deleteTourBtn.setDisable(true);
            }
        });

        vehicleListView.focusedProperty().addListener((s) -> {
            if (vehicleListView.focusedProperty().get()) {
                modifyVehicleBtn.setDisable(false);
                deleteVehicleBtn.setDisable(false);
            }
            else {
                modifyVehicleBtn.setDisable(true);
                deleteVehicleBtn.setDisable(true);
            }
        });

        // si une pop-up est close.
        ControllersUtils.getStage().setOnCloseRequest(
                event -> {
                    reloadListViews();
                }
        );

        loadListViews();
    }


    /**
     * Reload les listViews.
     */
    private void reloadListViews() {
        clearListViews();
        loadListViews();
    }

    /**
     * Clear les listViews.
     */
    private void clearListViews() {
        commListView.getItems().clear();
        tourListView.getItems().clear();
        vehicleListView.getItems().clear();
    }

    /**
     * Load les ListViews
     */
    private void loadListViews() {
        commListView.getItems().addAll(UserAuth.getProd().getCommandes());
        tourListView.getItems().addAll(UserAuth.getProd().getTournees());
        vehicleListView.getItems().addAll(UserAuth.getProd().getVehicules());
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
        DeleteCommCtrl.setCommande(commListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/deleteComm.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter une commande double-cliquée dans la ListView commListView.
    * @param event MouseEvent
    */
    public void popupConsultComm(MouseEvent event) {
        if (event.getClickCount() >= 2 && !commListView.getSelectionModel().isEmpty()) {
            ConsultCommCtrl.setCommande(commListView.getSelectionModel().getSelectedItem());
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
        DeleteTourCtrl.setTournee(tourListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/deleteTour.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter une tournée double-cliquée dans la ListView tourListView.
    * @param event MouseEvent
    */
    public void popupConsultTour(MouseEvent event) {
        if (event.getClickCount() >= 2 && !tourListView.getSelectionModel().isEmpty()) {
            ConsultTourCtrl.setTournee(tourListView.getSelectionModel().getSelectedItem());
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
        DeleteVehicleCtrl.setVehicule(vehicleListView.getSelectionModel().getSelectedItem());
    	util.loadPopup(event, "/views/deleteVehicle.fxml");
    }
    
    /**
    * Méthode qui ouvre une popup permettant de 
    * consulter un véhicule double-cliqué dans la ListView vehicleListView.
    * @param event
    */
    public void popupConsultVehicle(MouseEvent event) {
    	if (event.getClickCount() >= 2 && !vehicleListView.getSelectionModel().isEmpty()) {
            ConsultVehicleCtrl.setVehicule(vehicleListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultVehicle.fxml");
        }
    }
}