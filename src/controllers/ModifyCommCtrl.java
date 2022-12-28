package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.Client;
import modele.Commande;
import utility.ControllersUtils;
import utility.DateManager;
import validForm.FormModifyCommCtrl;

/**
 * Contrôleur permettant la modification d'une Commande.
 */
public class ModifyCommCtrl extends AbstractConnCtrl implements Initializable {

    @FXML
    private TextField commLabelField;

    @FXML
    private TextField commWeightField;

    @FXML
    private DatePicker commDateField;

    @FXML
    private TextField commStartField;

    @FXML
    private TextField commEndField;

    @FXML
    private ChoiceBox<Client> clientChoiceBox;

    private static Commande commande;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        commLabelField.setText(commande.getLibelle());
        commWeightField.setText(String.valueOf(commande.getPoids()));
        commDateField.setValue(DateManager.TimestampToLocalDate(commande.getHoraireDebut()));
        commStartField.setText(DateManager.TimestampToHourString(commande.getHoraireDebut()));
        commEndField.setText(DateManager.TimestampToHourString(commande.getHoraireFin()));

        clientChoiceBox.setItems(FXCollections.observableArrayList(cltDAO.getAll()));
        clientChoiceBox.setValue(clientChoiceBox.getItems().stream()
                .filter(clt -> clt.getIdClient() == commande.getClient().getIdClient()).findFirst().get());
    }

    /**
     * Méthode qui valide la modification de la commande.
     * 
     * @param event ActionEvent
     */
    public void validateModifyComm(ActionEvent event) {
        FormModifyCommCtrl fmcc = new FormModifyCommCtrl(commLabelField.getText(), commWeightField.getText(),
                commDateField.getValue(), commStartField.getText(), commEndField.getText(), clientChoiceBox.getValue());

        if (fmcc.isValid()) {
            commande.setLibelle(commLabelField.getText());
            commande.setPoids(Float.parseFloat(commWeightField.getText()));
            commande.setHoraireDebut(
                    DateManager.convertToTimestamp(commDateField.getValue(), commStartField.getText()));
            commande.setHoraireFin(DateManager.convertToTimestamp(commDateField.getValue(), commEndField.getText()));
            commande.setClient(clientChoiceBox.getValue());

            commDAO.update(commande);
        }
        ControllersUtils.closePopupAndUpdateParent(event);
    }

    /**
     * Méthode qui ferme la vue de modification de la commande.
     * 
     * @param event ActionEvent
     */
    public void cancelModifyComm(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
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