package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modele.Commande;
import modele.Tournee;
import modele.Vehicule;
import utility.ControllersUtils;

/**
* Contrôleur permettant la modification d'une Tournee.
*/
public class ModifyTourCtrl {

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

    private static Tournee tournee;
    
	/**
	* Méthode qui valide la modification d'une tournée.
	* @param event ActionEvent
	*/
    public void validateModifyTour(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
	
	/**
    * Méthode qui permet de fermer la vue 
    * de modification d'une tournée.
    * @param event ActionEvent
    */
    public void cancelModifyTour(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }

    /**
    * Méthode qui récupère la tournée sélectionnée dans la listView
    * de la vue précédente (prodSelectMenu)
    * @param tour Tournee
    */
    public static void setTournee(Tournee tour) {
        tournee = tour;
    }
}