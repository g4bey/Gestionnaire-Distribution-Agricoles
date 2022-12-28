package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import modele.Commande;
import utility.ControllersUtils;

/**
 * Contrôleur permettant l'aperçu Commande.
 */
public class ConsultCommCtrl implements Initializable {

    @FXML
    private Text commLabelText;

    @FXML
    private Text commWeightText;

    @FXML
    private Text commDateText;

    @FXML
    private Text commStartText;

    @FXML
    private Text commEndText;

    @FXML
    private Text clientAddressText;

    @FXML
    private Text clientNameText;

    @FXML
    private Text tourLabelText;

    @FXML
    private WebView commMapWebView;

    private static Commande commande;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        commLabelText.setText(commande.getLibelle());
        commWeightText.setText(String.valueOf(commande.getPoids()));
        commDateText.setText(AbstractDateCtrl.getJourFormat().format((commande.getHoraireDebut())));
        commStartText.setText(AbstractDateCtrl.getHeureFormat().format(commande.getHoraireDebut()));
        commEndText.setText(AbstractDateCtrl.getHeureFormat().format(commande.getHoraireFin()));
        clientAddressText.setText(commande.getClient().getAdresseClient());
        clientNameText.setText(commande.getClient().getNomClient());
        tourLabelText.setText(commande.getTournee().getLibelle());

        // TODO map
    }

    /**
     * Méthode qui permet de fermer la vue de consultation
     * des informations d'une commande.
     * 
     * @param event ActionEvent
     */
    public void closeConsultComm(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }

    /**
     * Méthode qui récupère la commande sélectionnée dans la listView
     * de la vue précédente (prodSelectMenu)
     * 
     * @param comm Commande
     */
    public static void setCommande(Commande comm) {
        commande = comm;
    }
}