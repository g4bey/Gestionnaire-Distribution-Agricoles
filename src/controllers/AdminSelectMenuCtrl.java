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
import modele.*;
import utility.ControllersUtils;

/**
 * Contrôleur permettant la consultation Administrateur.
 */
public class AdminSelectMenuCtrl extends AbstractConnCtrl implements Initializable {
    @FXML
    private Label adminLoginLabel;

    @FXML
    private Button adminProfileBtn;

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

        // Affichage du libelle uniquement sur le listView.
        adminListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Administrateur row, boolean empty) {
                super.updateItem(row, empty) ;
                setText(empty ? null : row.getPseudo());
            }
        });
        prodListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Producteur row, boolean empty) {
                super.updateItem(row, empty) ;
                setText(empty ? null : row.getProprietaire());
            }
        });
        clientListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Client row, boolean empty) {
                super.updateItem(row, empty) ;
                setText(empty ? null : row.getNomClient());
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
        adminListView.getItems().clear();
        prodListView.getItems().clear();
        clientListView.getItems().clear();
    }

    /**
     * Load les ListViews
     */
    private void loadListViews() {
        adminListView.getItems().addAll(aDAO.getAll());
        prodListView.getItems().addAll(pDAO.getAll());
        clientListView.getItems().addAll(cltDAO.getAll());
    }

    /**
     * Méthode qui ouvre une popup affichant le profile de l'utilisateur.
     * 
     * @param event MouseEvent
     */
    public void userProfile(ActionEvent event) {
        util.loadPopup(event, "/views/adminProfile.fxml");
    }

    /**
     * Méthode qui déconnecte l'utilisateur et redirige vers la première vue.
     * 
     * @param event ActionEvent
     */
    public void deconnection(ActionEvent event) {
        util.loadView(event, "/views/homePage.fxml");
    }

    /**
     * Méthode qui ouvre une popup pour l'ajout d'un administrateur.
     * 
     * @param event ActionEvent
     */
    public void popupAddAdmin(ActionEvent event) {
        util.loadPopup(event, "/views/addAdmin.fxml");
    }

    /**
     * Méthode qui ouvre une popup pour l'ajout d'un producteur.
     * 
     * @param event ActionEvent
     */
    public void popupAddProd(ActionEvent event) {
        util.loadPopup(event, "/views/addProd.fxml");
    }

    /**
     * Méthode qui ouvre une popup pour la modification d'un producteur.
     * 
     * @param event ActionEvent
     */
    public void popupModifyProd(ActionEvent event) {
        if (!prodListView.getSelectionModel().isEmpty()) {
            ModifyProdCtrl.setProd(prodListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/modifyProd.fxml");
        }
    }

    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'un producteur.
     * 
     * @param event ActionEvent
     */
    public void popupDeleteProd(ActionEvent event) {
        if (!prodListView.getSelectionModel().isEmpty()) {
            DeleteProdCtrl.setProd(prodListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/deleteProd.fxml");
        }
    }

    /**
     * Méthode qui ouvre une popup permettant de
     * consulter un producteur double-cliqué dans la ListView prodListView.
     * 
     * @param event MouseEvent
     */
    public void popupConsultProd(MouseEvent event) {
        if (event.getClickCount() >= 2 && !prodListView.getSelectionModel().isEmpty()) {
            ConsultProdCtrl.setProd(prodListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultProd.fxml");
        }
    }

    /**
     * Méthode qui ouvre une popup pour l'ajout d'un client.
     * 
     * @param event ActionEvent
     */
    public void popupAddClient(ActionEvent event) {
        util.loadPopup(event, "/views/addClient.fxml");
    }

    /**
     * Méthode qui ouvre une popup pour la modification d'un client.
     * 
     * @param event ActionEvent
     */
    public void popupModifyClient(ActionEvent event) {
        if (!clientListView.getSelectionModel().isEmpty()) {
            ModifyClientCtrl.setClient(clientListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/modifyClient.fxml");
        }
    }

    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'un client.
     * 
     * @param event ActionEvent
     */
    public void popupDeleteClient(ActionEvent event) {
        if (!clientListView.getSelectionModel().isEmpty()) {
            DeleteClientCtrl.setClient(clientListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/deleteClient.fxml");
        }
    }

    /**
     * Méthode qui ouvre une popup permettant de
     * consulter un client double-cliqué dans la ListView clientListView.
     * 
     * @param event MouseEvent
     */
    public void popupConsultClient(MouseEvent event) {
        if (event.getClickCount() >= 2 && !clientListView.getSelectionModel().isEmpty()) {
            ConsultClientCtrl.setClient(clientListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultClient.fxml");
        }
    }
}