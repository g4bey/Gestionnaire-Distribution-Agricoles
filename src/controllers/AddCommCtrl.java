package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Client;
import modele.Commande;
import utility.ControllersUtils;
import utility.DateManager;
import utility.UserAuth;
import validForm.FormCommAddValidator;

import java.net.URL;
import java.util.ResourceBundle;

/**
* Contrôleur permettant l'ajout d'une Commande.
*/
public class AddCommCtrl extends AbstractConnCtrl implements Initializable {
	
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        formErrorText.setVisible(false);
        ObservableList<Client> listeClients = FXCollections.observableArrayList();
        listeClients.addAll(cltDAO.getAll());
        clientChoiceBox.setItems(listeClients);
    }

    /**
    * Méthode qui valide l'ajout d'une commande.
    * @param event ActionEvent
    */
    public void validateAddComm(ActionEvent event) {
        FormCommAddValidator fcav = new FormCommAddValidator(
            commLabelField.getText(),
            commWeightField.getText(),
            commDateField.getValue(),
            commStartField.getText(),
            commEndField.getText(),
            clientChoiceBox.getValue()
        );
        if (fcav.isValid()) {
            formErrorText.setVisible(false);
            commDAO.add(new Commande(
                commLabelField.getText(),
                Float.parseFloat(commWeightField.getText()),
                DateManager.convertToTimestamp(commDateField.getValue(), commStartField.getText()),
                DateManager.convertToTimestamp(commDateField.getValue(), commEndField.getText()),
                UserAuth.getProd(),
                clientChoiceBox.getValue()
            ));
            ControllersUtils.closePopupAndUpdateParent(event);
        }
        else {
            formErrorText.setText(fcav.getErrors());
            formErrorText.setVisible(true);
        }

    }
    
    /**
    * Méthode qui permet de fermer la vue d'ajout d'une commande.
    * @param event ActionEvent
    */
    public void cancelAddComm(ActionEvent event) {
    	ControllersUtils.closePopupAndUpdateParent(event);
    }
}