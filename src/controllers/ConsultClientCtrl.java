package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modele.Client;
import utility.ControllersUtils;

/**
 * Contrôleur permettant l'aperçu Client.
 */
public class ConsultClientCtrl implements Initializable {

    @FXML
    private Text clientNameText;

    @FXML
    private Text clientAddressText;

    @FXML
    private Text clientPhoneText;

    private static Client client;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        clientNameText.setText(client.getNomClient());
        clientAddressText.setText(client.getAdresseClient().replace(",", " "));
        clientPhoneText.setText(client.getNumTelClient());
    }

    /**
     * Méthode qui permet de fermer la vue de consultation
     * des informations d'un client.
     * 
     * @param event ActionEvent
     */
    public void closeConsultClient(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    }

    /**
     * Méthode qui récupère le client sélectionné dans la listView
     * de la vue précédente (adminSelectMenu)
     * 
     * @param cl Client
     */
    public static void setClient(Client cl) {
        client = cl;
    }
}