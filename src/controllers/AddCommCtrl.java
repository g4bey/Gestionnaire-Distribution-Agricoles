package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import src.modele.Client;
import src.utility.ControllersUtils;

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
    
    @FXML
    private Text formErrorText;
    
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