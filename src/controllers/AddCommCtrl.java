package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.modele.Client;

/**
* Contrôleur permettant l'ajout d'une Commande.
*/

public class AddCommCtrl {
	
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
    
    private Stage stage;
    
    /**
     * Méthode qui valide l'ajout d'une commande.
     * @param event ActionEvent
     */
    public void addComm(ActionEvent event) {
    	closePopup(event);
    }
    
    /**
     * Méthode qui permet de fermer la vue popup.
     * @param event ActionEvent
     */
    public void closePopup(ActionEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
}