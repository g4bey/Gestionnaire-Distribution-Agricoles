package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Client;
import utility.ControllersUtils;
import validForm.FormClientAddValidator;

import java.net.URL;
import java.util.ResourceBundle;

/**
* Contrôleur permettant l'ajout d'un Client.
*/
public class AddClientCtrl extends AbstractConnCtrl implements Initializable {
	
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
    @FXML
    private Text formErrorText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        formErrorText.setVisible(false);
        ObservableList<String> listePath = FXCollections.observableArrayList();
        listePath.add("Rue");
        listePath.add("Boulevard");
        listePath.add("Avenue");
        listePath.add("Allée");
        listePath.add("Chemin");
        listePath.add("Route");
        listePath.add("Impasse");
        listePath.add("Lieu Dit");
        pathTypeChoiceBox.setItems(listePath);
    }

    /**
    * Méthode qui valide l'ajout d'un client.
    * @param event ActionEvent
    */
    public void validateAddClient(ActionEvent event) {

        FormClientAddValidator fcav = new FormClientAddValidator(
            clientNameField.getText(),
            addressNumField.getText(),
            pathTypeChoiceBox.getValue(),
            pathNameField.getText(),
            townNameField.getText(),
            postcodeField.getText(),
            clientPhoneField.getText()
        );

        if (fcav.isValid()) {
            formErrorText.setVisible(false);
            cltDAO.add(new Client(
                clientNameField.getText(),
                fcav.getAdresseCsv(),
                fcav.getCoordsXY(),
                clientPhoneField.getText()
            ));
            ControllersUtils.closePopup(event);
        }
        else {
            formErrorText.setText(fcav.getErrors());
            formErrorText.setVisible(true);
        }

    }
    
    /**
    * Méthode qui permet de fermer la vue d'ajout d'un client.
    * @param event ActionEvent
    */
    public void cancelAddClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }


}
