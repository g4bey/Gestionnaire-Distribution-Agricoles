package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modele.Producteur;
import utility.ControllersUtils;

/**
* Contrôleur permettant la suppression d'un Producteur.
*/
public class DeleteProdCtrl extends AbstractConnCtrl implements Initializable {
	
    @FXML
    private Text prodLabelText;
    @FXML
    private Text deleteErrorText;

    private static Producteur producteur;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        prodLabelText.setText(producteur.getSiret());
    }
    
    /**
    * Méthode qui valide la suppression du producteur.
    * @param event ActionEvent
    */
    public void validateDeleteProd(ActionEvent event) {
        pDAO.delete(producteur);
        ControllersUtils.closePopupAndUpdateParent(event);
    }
    
    /**
    * Méthode qui annule la suppression du producteur.
    * @param event ActionEvent
    */
    public void cancelDeleteProd(ActionEvent event) {
    	ControllersUtils.closePopupAndUpdateParent(event);
    }

    /**
    * Méthode qui récupère le producteur sélectionné dans la listView
    * de la vue précédente (adminSelectMenu)
    * @param prod Producteur
    */
    public static void setProd(Producteur prod) {
        producteur = prod;
    }
}