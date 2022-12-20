package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.modele.Vehicule;
import src.modele.Tournee;
import src.modele.Commande;

/**
* Contrôleur permettant l'aperçu Producteur.
*/

public class ConsultProdCtrl {

    @FXML
    private Text prodSiretText;
   
    @FXML
    private Text prodPropText;
    
    @FXML
    private Text prodAddressText;
    
    @FXML
    private Text prodPhoneText;
    
    @FXML
    private ListView<Commande> prodCommListView;
    
    @FXML
    private ListView<Tournee> prodTourListView;
    
    @FXML
    private ListView<Vehicule> prodVehicleListView;
    
    private Stage stage;
    
    /**
     * Méthode qui permet de fermer la vue popup.
     * @param event ActionEvent
     */
    public void closePopup(ActionEvent event) {
    	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    }
}