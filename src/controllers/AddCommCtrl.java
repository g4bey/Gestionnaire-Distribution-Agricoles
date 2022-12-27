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
import utility.ControllersUtils;

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
    	ControllersUtils.closePopup(event);
    }
    
    /**
    * Méthode qui permet de fermer la vue d'ajout d'une commande.
    * @param event ActionEvent
    */
    public void cancelAddComm(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}