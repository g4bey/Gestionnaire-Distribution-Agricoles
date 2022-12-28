package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modele.Client;
import utility.ControllersUtils;
import validForm.FormDeleteClient;
import validForm.FormValidator;

/**
* Contrôleur permettant la suppression d'un Client.
*/
public class DeleteClientCtrl extends AbstractConnCtrl implements Initializable {
    
    @FXML
    private Text clientNameText;
    @FXML
    private Text deleteErrorText;

    private static Client client;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        clientNameText.setText(client.getNomClient());
    }
    
    /**
    * Méthode qui valide la suppression du client.
    * @param event ActionEvent
    */
    public void validateDeleteClient(ActionEvent event) {
        FormValidator formulaire = new FormDeleteClient(client);

        if (formulaire.isValid()) {
            cltDAO.delete(client);
        } else {
            deleteErrorText.setVisible(true);
            deleteErrorText.setText(formulaire.getErrors());
        }

    	ControllersUtils.closePopupAndUpdateParent(event);
    }
    
    /**
    * Méthode qui annule la suppression du client.
    * @param event ActionEvent
    */
    public void cancelDeleteClient(ActionEvent event) {
    	ControllersUtils.closePopupAndUpdateParent(event);
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