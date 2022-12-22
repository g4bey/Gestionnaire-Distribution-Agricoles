package GDA.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import GDA.utility.ControllersUtils;

/**
* Contrôleur permettant la modification d'un Producteur.
*/
public class ModifyProdCtrl implements Initializable {
	
    @FXML
	private TextField prodSiretField;
	
    @FXML
	private TextField propNameField;
	
    @FXML
   	private TextField addressNumField;
       
    @FXML
    private ChoiceBox<String> pathTypeChoiceBox;
       
    @FXML
    private TextField pathNameField;
       
    @FXML
    private TextField townNameField;
       
    @FXML
    private TextField postcodeField;
	
    @FXML
	private TextField prodPhoneField;
	
    @FXML
	private TextField prodPasswordField;
	
    @FXML
	private TextField confirmPasswordField;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    }
    
	/**
	* Méthode qui valide la modification d'un producteur.
	* @param event ActionEvent
	*/
    public void validateModifyProd(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }
	
	/**
    * Méthode qui permet de fermer la vue de modification d'un producteur.
    * @param event ActionEvent
    */
    public void cancelModifyProd(ActionEvent event) {
    	ControllersUtils.closePopup(event);
    }
}