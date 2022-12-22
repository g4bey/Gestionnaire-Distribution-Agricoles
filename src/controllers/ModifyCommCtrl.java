package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.Client;
import utility.ControllersUtils;

/**
* Contrôleur permettant la modification d'une Commande.
*/
public class ModifyCommCtrl implements Initializable {

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

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    }
	
	/**
	* Méthode qui valide la modification de la commande.
	* @param event ActionEvent
	*/
    public void validateModifyComm(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
    
    /**
	* Méthode qui ferme la vue de modification de la commande.
	* @param event ActionEvent
	*/
    public void cancelModifyComm(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
}