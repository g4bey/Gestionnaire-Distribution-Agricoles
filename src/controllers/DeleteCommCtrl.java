package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modele.Commande;
import utility.ControllersUtils;

/**
* Contrôleur permettant la suppression d'une Commande.
*/
public class DeleteCommCtrl extends AbstractConnCtrl implements Initializable {
	
    @FXML
    private Text commLabelText;

    private static Commande commande;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        commLabelText.setText(commande.getLibelle());
    }
    
    /**
    * Méthode qui valide la suppression de la commande.
    * @param event ActionEvent
    */
    public void validateDeleteComm(ActionEvent event) {
    	ControllersUtils.closePopupAndUpdateParent(event);
    }
    
    /**
    * Méthode qui annule la suppression de la commande.
    * @param event ActionEvent
    */
    public void cancelDeleteComm(ActionEvent event) {
    	ControllersUtils.closePopupAndUpdateParent(event);
    }

    /**
    * Méthode qui récupère la commande sélectionnée dans la listView
    * de la vue précédente (prodSelectMenu)
    * @param comm Commande
    */
    public static void setCommande(Commande comm) {
        commande = comm;
    }
}