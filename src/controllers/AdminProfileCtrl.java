package src.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
* Contrôleur de la page profil de l'Administrateur.
*/

public class AdminProfileCtrl {

    @FXML
    private Text adminLoginLabel;
    
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