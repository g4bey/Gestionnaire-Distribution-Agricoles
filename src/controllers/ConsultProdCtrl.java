package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import modele.Vehicule;
import utility.ControllersUtils;
import modele.Tournee;
import modele.Commande;
import modele.Producteur;

/**
 * Contrôleur permettant l'aperçu Producteur.
 */
public class ConsultProdCtrl implements Initializable {

    @FXML
    private Text prodSiretText;

    @FXML
    private Text prodPropText;

    @FXML
    private Text prodAddressText;

    @FXML
    private Text prodPhoneText;

    @FXML
    private ListView<Commande> prodCommListView;

    @FXML
    private ListView<Tournee> prodTourListView;

    @FXML
    private ListView<Vehicule> prodVehicleListView;

    private static Producteur producteur;

    private List<Commande> commandes;

    private List<Tournee> tournees;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        prodCommListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Commande row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle());
            }
        } // updateItem
        ); // setCellFactory

        prodSiretText.setText(producteur.getSiret());
        prodPropText.setText(producteur.getProprietaire());
        prodAddressText.setText(producteur.getAdresseProd().replace(",", " "));
        prodPhoneText.setText(producteur.getNumTelProd());
        prodVehicleListView.getItems().addAll(producteur.getVehicules());
        commandes = new ArrayList<>(producteur.getCommandes());
        tournees = new ArrayList<>(producteur.getTournees());

        Comparator<Commande> commandesDesc = (comm1, comm2) -> Long.valueOf(
                comm2.getHoraireDebut().getTime())
                .compareTo(comm1.getHoraireDebut().getTime());
        Comparator<Tournee> tourneesDesc = (tour1, tour2) -> Long.valueOf(
                tour2.getHoraireDebut().getTime())
                .compareTo(tour1.getHoraireDebut().getTime());
        Collections.sort(commandes, commandesDesc);
        Collections.sort(tournees, tourneesDesc);

        prodCommListView.getItems().addAll(commandes);
        prodTourListView.getItems().addAll(tournees);

        // Affichage du libellé uniquement sur le listView.
        prodCommListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Commande row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle() + " | " + row.getHoraireDebut() + " | " + row.getHoraireFin());
            }
        } // updateItem
        ); // setCellFactory
        prodTourListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Tournee row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle() + " | " + row.getHoraireDebut() + " | " + row.getHoraireFin());
            }
        } // updateItem
        ); // setCellFactory
        prodVehicleListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Vehicule row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle());
            }
        } // updateItem
        ); // setCellFactory
    } // initialize

    /**
     * Méthode qui permet de fermer la vue de consultation
     * des informations d'un Producteur.
     * 
     * @param event ActionEvent
     */
    public void closeConsultProd(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    } // closeConsultProd

    /**
     * Méthode qui récupère le Producteur sélectionné dans la listView
     * de la vue précédente (adminSelectMenu)
     * 
     * @param prod Producteur
     */
    public static void setProd(Producteur prod) {
        producteur = prod;
    } // setProd

} // ConsultProdCtrl