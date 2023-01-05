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
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import modele.Client;
import modele.Commande;
import utility.ControllersUtils;
import utility.DateManager;
import validForm.FormCommModifyValidator;
import validForm.FormCommValidator;

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
    @FXML
    private Text formErrorText;

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

        clientChoiceBox.setConverter(new StringConverter<Client>() {

            @Override
            public Client fromString(String arg0) {
                return null;
            } // fromString
        
            @Override
            public String toString(Client arg0) {
                if (arg0 == null) {
                    return "";
                } // if
                return arg0.getNomClient();
            } // toString
        } // StringConverter<Client>
        ); // setConverter
    } // initialize

    /**
     * Méthode qui valide la modification de la Commande.
     *
     * @param event ActionEvent
     */
    public void validateModifyComm(ActionEvent event) {
        FormCommValidator fmcv = new FormCommModifyValidator(commande.getIdCommande(), commLabelField.getText(), commWeightField.getText(),
                commDateField.getValue(), commStartField.getText(), commEndField.getText(), clientChoiceBox.getValue());

        if (fmcv.isValid()) {
            commande.setLibelle(commLabelField.getText());
            commande.setPoids(Float.parseFloat(commWeightField.getText()));
            commande.setHoraireDebut(
                    DateManager.convertToTimestamp(commDateField.getValue(), commStartField.getText()));
            commande.setHoraireFin(DateManager.convertToTimestamp(commDateField.getValue(), commEndField.getText()));
            commande.setClient(clientChoiceBox.getValue());

            commDAO.update(commande);
            ControllersUtils.closePopupAndUpdateParent(event);
        } // if
        else {
            formErrorText.setVisible(true);
            formErrorText.setText(fmcv.getErrors());
        } // else
    } // validateModifyComm

    /**
     * Méthode qui ferme la vue de modification de la Commande.
     *
     * @param event ActionEvent
     */
    public void cancelModifyComm(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    } // cancelModifyComm

    /**
     * Méthode qui récupère la Commande sélectionnée dans la listView
     * de la vue précédente (prodSelectMenu)
     *
     * @param comm Commande
     */
    public static void setCommande(Commande comm) {
        commande = comm;
    } // set Commande

} // ModifyCommCtrl