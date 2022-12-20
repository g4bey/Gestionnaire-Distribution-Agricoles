package src.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
* Contrôleur permettant l'aperçu Commande.
*/

public class ConsultCommCtrl implements Initializable {
	
    @FXML
    private Text commLabelText;
    
    @FXML
    private Text commWeightText;
    
    @FXML
    private Text commDateText;
    
    @FXML
    private Text commStartText;
    
    @FXML
    private Text commEndText;
    
    @FXML
    private Text clientAddressText;
    
    @FXML
    private Text clientNameText;
    
    @FXML
    private Text tourLabelText;
    
    @FXML
	private WebView commMapWebView;

    private Stage stage;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
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