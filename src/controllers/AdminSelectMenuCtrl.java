package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import modele.Administrateur;
import modele.Producteur;
import utility.ControllersUtils;
import modele.Client;

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

        // Remplissions les listView
        adminListView.getItems().addAll(aDAO.getAll());
        prodListView.getItems().addAll(pDAO.getAll());
        clientListView.getItems().addAll(cltDAO.getAll());

        // On désactive la listeView admin si vide.
        if(adminListView.getItems().isEmpty()) {
            adminListView.setDisable(true);
        }

        // On désactive la listeView prod si vide.
        if(prodListView.getItems().isEmpty()) {
            prodListView.setDisable(true);
        }

        // On désactive la listeView client si vide.
        if(clientListView.getItems().isEmpty()) {
            clientListView.setDisable(true);
        }

        // On desactive les boutons si c'est necessaire.
        prodListView.focusedProperty().addListener((s) -> {
            if (prodListView.focusedProperty().get()) {
                modifyProdBtn.setDisable(false);
                deleteProdBtn.setDisable(false);
            }
            else {
                modifyProdBtn.setDisable(true);
                deleteProdBtn.setDisable(true);
            }
        });

        // On desactive les boutons si c'est necessaire.
        clientListView.focusedProperty().addListener((s) -> {
            if (clientListView.focusedProperty().get()) {
                modifyClientBtn.setDisable(false);
                deleteClientBtn.setDisable(false);
            }
            else {
                modifyClientBtn.setDisable(true);
                deleteClientBtn.setDisable(true);
            }
        });
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
        ModifyProdCtrl.setProd(prodListView.getSelectionModel().getSelectedItem());
        util.loadPopup(event, "/views/modifyProd.fxml");
    }

    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'un producteur.
     * 
     * @param event ActionEvent
     */
    public void popupDeleteProd(ActionEvent event) {
        DeleteProdCtrl.setProd(prodListView.getSelectionModel().getSelectedItem());
        util.loadPopup(event, "/views/deleteProd.fxml");
    }

    /**
     * Méthode qui ouvre une popup permettant de
     * consulter un producteur double-cliqué dans la ListView prodListView.
     * 
     * @param event MouseEvent
     */
    public void popupConsultProd(MouseEvent event) {
        if (event.getClickCount() >= 2) {
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
        ModifyClientCtrl.setClient(clientListView.getSelectionModel().getSelectedItem());
        util.loadPopup(event, "/views/modifyClient.fxml");
    }

    /**
     * Méthode qui ouvre une popup pour demander la confirmation
     * de la suppression d'un client.
     * 
     * @param event ActionEvent
     */
    public void popupDeleteClient(ActionEvent event) {
        DeleteClientCtrl.setClient(clientListView.getSelectionModel().getSelectedItem());
        util.loadPopup(event, "/views/deleteClient.fxml");
    }

    /**
     * Méthode qui ouvre une popup permettant de
     * consulter un client double-cliqué dans la ListView clientListView.
     * 
     * @param event MouseEvent
     */
    public void popupConsultClient(MouseEvent event) {
        if (event.getClickCount() >= 2) {
            ConsultClientCtrl.setClient(clientListView.getSelectionModel().getSelectedItem());
            util.loadPopup(event, "/views/consultClient.fxml");
        }
    }
}