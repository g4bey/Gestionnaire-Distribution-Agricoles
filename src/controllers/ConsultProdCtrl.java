package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        prodSiretText.setText(producteur.getSiret());
        prodPropText.setText(producteur.getProprietaire());
        prodAddressText.setText(producteur.getAdresseProd());
        prodPhoneText.setText(producteur.getNumTelProd());
        prodCommListView.getItems().addAll(producteur.getCommandes());
        prodTourListView.getItems().addAll(producteur.getTournees());
        prodVehicleListView.getItems().addAll(producteur.getVehicules());
    }

    /**
     * Méthode qui permet de fermer la vue de consultation
     * des informations d'un producteur.
     * 
     * @param event ActionEvent
     */
    public void closeConsultProd(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }

    /**
     * Méthode qui récupère le producteur sélectionné dans la listView
     * de la vue précédente (adminSelectMenu)
     * 
     * @param prod Producteur
     */
    public static void setProd(Producteur prod) {
        producteur = prod;
    }
}