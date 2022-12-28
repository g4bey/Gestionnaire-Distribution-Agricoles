package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import modele.Client;
import utility.ControllersUtils;
import validForm.FormModifyClientCtrl;

/**
 * Contrôleur permettant la modification d'un Client.
 */
public class ModifyClientCtrl extends AbstractConnCtrl implements Initializable {

    @FXML
    private TextField clientNameField;

    @FXML
    private TextField addressNumField;

    @FXML
    private ChoiceBox<String> pathTypeChoiceBox;

    @FXML
    private TextField pathNameField;

    @FXML
    private TextField townNameField;

    @FXML
    private TextField postcodeField;

    @FXML
    private TextField clientPhoneField;

    private static Client client;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        clientNameField.setText(client.getNomClient());
        addressNumField.setText(client.getNumTelClient());

        String[] adresse = client.getAdresseClient().split(",");

        ObservableList<String> listePath = FXCollections.observableArrayList();
        listePath.add("Rue");
        listePath.add("Boulevard");
        listePath.add("Avenue");
        listePath.add("Allée");
        listePath.add("Chemin");
        listePath.add("Route");
        listePath.add("Impasse");
        listePath.add("Lieu-Dit");
        pathTypeChoiceBox.setItems(listePath);
        pathTypeChoiceBox.setValue(adresse[1]);

        pathNameField.setText(adresse[2]);
        townNameField.setText(adresse[3]);
        postcodeField.setText(adresse[4]);

        clientPhoneField.setText(client.getNumTelClient());
    }

    /**
     * Méthode qui valide la modification d'un client.
     * 
     * @param event ActionEvent
     */
    public void validateModifyClient(ActionEvent event) {
        FormModifyClientCtrl fmcc = new FormModifyClientCtrl(clientNameField.getText(), addressNumField.getText(),
                pathTypeChoiceBox.getValue(), pathNameField.getText(), townNameField.getText(), postcodeField.getText(),
                clientPhoneField.getText());

        if (fmcc.isValid()) {
            client.setNomClient(clientNameField.getText());
            client.setAdresseClient(fmcc.getAdresseCSV());
            client.setGpsClient(fmcc.getCoordsXY());
            client.setNumTelClient(clientPhoneField.getText());

            cltDAO.update(client);
            ControllersUtils.closePopupAndUpdateParent(event);
        }
    }

    /**
     * Méthode qui ferme la vue de modification d'un client.
     * 
     * @param event ActionEvent
     */
    public void cancelModifyClient(ActionEvent event) {
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