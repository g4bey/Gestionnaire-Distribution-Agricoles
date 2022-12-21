package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import src.modele.Vehicule;
import src.utility.ControllersUtils;
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
    
    /**
     * Méthode qui permet de fermer la vue de consultation
     * des informations d'un producteur.
     * @param event ActionEvent
     */
    public void closeConsultProd(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}