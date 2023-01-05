package controllers;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import modele.Tournee;
import modele.Commande;
import utility.UserAuth;

/**
 * Contrôleur permettant la consultation par un Producteur de ses Commandes, Tournées et Véhicules
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

    private List<Commande> commandes;

    private List<Tournee> tournees;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        util = new ControllersUtils();

        // Affichage du libellé uniquement sur le listView.
        commListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Commande row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle() + " | " + row.getHoraireDebut() + " | " + row.getHoraireFin());
            }
        } // updateItem
        ); // setCellFactory
        vehicleListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Vehicule row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle());
            }
        } // updateItem
        ); // setCellFactory
        tourListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Tournee row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle() + " | " + row.getHoraireDebut() + " | " + row.getHoraireFin());
            }
        } // updateItem
        ); // setCellFactory

        commListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {
            @Override
            public void changed(ObservableValue<? extends Commande> arg0, Commande arg1, Commande arg2) {
                // TODO Auto-generated method stub$
                if (commListView.getItems().size() > 0) {
                    modifyCommBtn.setDisable(false);
                    deleteCommBtn.setDisable(false);
                } // if
                else {
                    modifyCommBtn.setDisable(true);
                    deleteCommBtn.setDisable(true);
                } // else
            } // changed
        } // ChangeListener <Commande>
        ); // // changed

        tourListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tournee>() {
            @Override
            public void changed(ObservableValue<? extends Tournee> arg0, Tournee arg1, Tournee arg2) {
                // TODO Auto-generated method stub$
                if (commListView.getItems().size() > 0) {
                    modifyTourBtn.setDisable(false);
                    deleteTourBtn.setDisable(false);
                } // if
                else {
                    modifyTourBtn.setDisable(true);
                    deleteTourBtn.setDisable(true);
                } // else
            } // changed
        } // ChangeListener
        ); // addListener

        vehicleListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicule>() {
            @Override
            public void changed(ObservableValue<? extends Vehicule> arg0, Vehicule arg1, Vehicule arg2) {
                // TODO Auto-generated method stub$
                if (commListView.getItems().size() > 0) {
                    modifyVehicleBtn.setDisable(false);
                    deleteVehicleBtn.setDisable(false);
                } // if
                else {
                    modifyVehicleBtn.setDisable(true);
                    deleteVehicleBtn.setDisable(true);
                } // else
            } // changed
        } // ChangeListener
        ); // addListener

        // Si une pop-up est close.
        ControllersUtils.getStage().setOnCloseRequest(
                event -> {
                    reloadListViews();
                } // event
        ); // setOnCloseRequest
        loadListViews();
    } // initialize

    /**
     * Reload les listViews.
     */
    private void reloadListViews() {
        clearListViews();
        loadListViews();
    } // reloasListViews

    /**
     * Clear les listViews.
     */
    private void clearListViews() {
        commListView.getItems().clear();
        tourListView.getItems().clear();
        vehicleListView.getItems().clear();
    } // clearListViews

    /**
     * Charge les ListViews
     */
    private void loadListViews() {
        commandes = UserAuth.getProd().getCommandes();
        tournees = UserAuth.getProd().getTournees();

        Comparator<Commande> commandesDesc = (comm1, comm2) -> Long.valueOf(
                comm2.getHoraireDebut().getTime())
                .compareTo(comm1.getHoraireDebut().getTime());

        Comparator<Tournee> tourneesDesc = (tour1, tour2) -> Long.valueOf(
                tour2.getHoraireDebut().getTime())
                .compareTo(tour1.getHoraireDebut().getTime());
        Collections.sort(commandes, commandesDesc);
        Collections.sort(tournees, tourneesDesc);

        commListView.getItems().addAll(commandes);
        tourListView.getItems().addAll(tournees);
        vehicleListView.getItems().addAll(UserAuth.getProd().getVehicules());
    } // loadListViews

    /**
     * Méthode qui ouvre une pop-up affichant le profil de l'utilisateur.
     * 
     * @param event
     */
    public void userProfile(ActionEvent event) {
        util.loadPopup(event, "/views/prodProfile.fxml");
    } // userProfile

    /**
     * Méthode qui déconnecte l'utilisateur et redirige vers la première vue.
     * 
     * @param event ActionEvent
     */
    public void deconnection(ActionEvent event) {
        util.loadView(event, "/views/homePage.fxml");
    } // deconnection

    /**
     * Méthode qui ouvre une pop-up pour l'ajout d'une Commande.
     * 
     * @param event ActionEvent
     */
    public void popupAddComm(ActionEvent event) {
        util.loadPopup(event, "/views/addComm.fxml");
    } // popupAddComm

    /**
     * Méthode qui ouvre une pop-up pour la modification d'une Commande
     * sélectionnée dans la ListView commListView.
     * 
     * @param event ActionEvent
     */
    public void popupModifyComm(ActionEvent event) {
        if (!commListView.getSelectionModel().isEmpty()) {
            ModifyCommCtrl.setCommande(commListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/modifyComm.fxml");
        } // if
    } // popupModifyComm

    /**
     * Méthode qui ouvre une pop-up pour demander la confirmation
     * de la suppression d'une Commande sélectionnée dans la ListView commListView.
     * 
     * @param event ActionEvent
     */
    public void popupDeleteComm(ActionEvent event) {
        if (!commListView.getSelectionModel().isEmpty()) {
            DeleteCommCtrl.setCommande(commListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/deleteComm.fxml");
        } // if
    } // popupDeleteComm

    /**
     * Méthode qui ouvre une pop-up permettant de
     * consulter une Commande double-cliquée dans la ListView commListView.
     * 
     * @param event MouseEvent
     */
    public void popupConsultComm(MouseEvent event) {
        if (event.getClickCount() >= 2 && !commListView.getSelectionModel().isEmpty()) {
            ConsultCommCtrl.setCommande(commListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultComm.fxml");
        } // if
    } // popupConsultComm

    /**
     * Méthode qui ouvre une pop-up pour l'ajout d'une Tournée.
     * 
     * @param event ActionEvent
     */
    public void popupAddTour(ActionEvent event) {
        util.loadPopup(event, "/views/addTour.fxml");
    } // popupAddTour

    /**
     * Méthode qui ouvre une pop-up pour la modification d'une Tournée
     * sélectionnée dans la ListView tourListView.
     * 
     * @param event ActionEvent
     */
    public void popupModifyTour(ActionEvent event) {
        if (!tourListView.getSelectionModel().isEmpty()) {
            ModifyTourCtrl.setTournee(tourListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/modifyTour.fxml");
        } // if
    } // popupModifyTour

    /**
     * Méthode qui ouvre une pop-up pour demander la confirmation
     * de la suppression d'une Tournée sélectionnée dans la ListView commListView.
     * 
     * @param event ActionEvent
     */
    public void popupDeleteTour(ActionEvent event) {
        if (!tourListView.getSelectionModel().isEmpty()) {
            DeleteTourCtrl.setTournee(tourListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/deleteTour.fxml");
        } // if
    } // popupDeleteTour

    /**
     * Méthode qui ouvre une pop-up permettant de
     * consulter une Tournée double-cliquée dans la ListView tourListView.
     * 
     * @param event MouseEvent
     */
    public void popupConsultTour(MouseEvent event) {
        if (event.getClickCount() >= 2 && !tourListView.getSelectionModel().isEmpty()) {
            ConsultTourCtrl.setTournee(tourListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultTour.fxml");
        } // if
    } // popupConsultTour

    /**
     * Méthode qui ouvre une pop-up pour l'ajout d'un Véhicule.
     * 
     * @param event ActionEvent
     */
    public void popupAddVehicle(ActionEvent event) {
        util.loadPopup(event, "/views/addVehicle.fxml");
    } // popupAddVehicle

    /**
     * Méthode qui ouvre une pop-up pour la modification d'un Véhicule
     * sélectionné dans la ListView vehicleListView.
     * 
     * @param event ActionEvent
     */
    public void popupModifyVehicle(ActionEvent event) {
        if (!vehicleListView.getSelectionModel().isEmpty()) {
            ModifyVehicleCtrl.setVehicule(vehicleListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/modifyVehicle.fxml");
        } // if
    } // popupModifyVehicle

    /**
     * Méthode qui ouvre une pop-up pour demander la confirmation
     * de la suppression d'un Véhicule sélectionné dans la ListView vehicleListView.
     * 
     * @param event ActionEvent
     */
    public void popupDeleteVehicle(ActionEvent event) {
        if (!vehicleListView.getSelectionModel().isEmpty()) {
            DeleteVehicleCtrl.setVehicule(vehicleListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/deleteVehicle.fxml");
        } // if
    } // popupDeleteVehicle

    /**
     * Méthode qui ouvre une pop-up permettant de
     * consulter un Véhicule double-cliqué dans la ListView vehicleListView.
     * 
     * @param event
     */
    public void popupConsultVehicle(MouseEvent event) {
        if (event.getClickCount() >= 2 && !vehicleListView.getSelectionModel().isEmpty()) {
            ConsultVehicleCtrl.setVehicule(vehicleListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultVehicle.fxml");
        } // if
    } // popupConsultVehicle

} // ProdSelectMenuCtrl