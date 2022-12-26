package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Vehicule;
import modele.Commande;
import utility.ControllersUtils;

/**
* Contrôleur permettant l'ajout d'une Tournee.
*/
public class AddTourneeCtrl {
	
    @FXML
	private TextField tourLabelField;
    
    @FXML
    private ChoiceBox<Vehicule> vehicleChoiceBox;
    
    @FXML
    private ListView<Commande> commListView;
    
    @FXML
    private ChoiceBox<Commande> commChoiceBox;

    @FXML
    private Label maxWeightLabel;
   
    @FXML
    private Label startLabel;
   
    @FXML
    private Label endLabel;
   
    @FXML
    private Label datetimeLabel;
    
    @FXML
    private Text formErrorText;
    
	/**
	* Méthode qui valide l'ajout d'une tournée.
	* @param event ActionEvent
	*/
    public void validateAddTour(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
	
	/**
    * Méthode qui permet de fermer la vue d'ajout d'une tournée.
    * @param event ActionEvent
    */
    public void cancelAddTour(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}