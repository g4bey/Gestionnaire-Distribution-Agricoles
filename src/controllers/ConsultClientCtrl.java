package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import modele.Client;
import utility.ControllersUtils;

/**
* Contrôleur permettant l'aperçu Client.
*/
public class ConsultClientCtrl {
	
    @FXML
    private Text clientNameText;
    
    @FXML
    private Text clientAddressText;
    
    @FXML
    private Text clientPhoneText;

    private static Client client;
    
    /**
    * Méthode qui permet de fermer la vue de consultation 
    * des informations d'un client.
    * @param event ActionEvent
    */
    public void closeConsultClient(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }

    /**
    * Méthode qui récupère le client sélectionné dans la listView
    * de la vue précédente (adminSelectMenu)
    * @param cl Client
    */
    public static void setClient(Client cl) {
        client = cl;
    }
}