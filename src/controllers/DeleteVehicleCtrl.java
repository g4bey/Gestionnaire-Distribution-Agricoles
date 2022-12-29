package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modele.Vehicule;
import utility.ControllersUtils;
import validForm.FormDeleteVehicule;
import validForm.FormValidator;

/**
 * Contrôleur permettant la suppression d'un Vehicule.
 */
public class DeleteVehicleCtrl extends AbstractConnCtrl implements Initializable {

    @FXML
    private Text vehicleLabelText;
    @FXML
    private Text deleteErrorText;

    private static Vehicule vehicule;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        vehicleLabelText.setText(vehicule.getLibelle());
    }

    /**
     * Méthode qui valide la suppression du véhicule.
     * 
     * @param event ActionEvent
     */
    public void validateDeleteVehicle(ActionEvent event) {
        FormValidator formulaire = new FormDeleteVehicule(vehicule);

        if (formulaire.isValid()) {
            vDAO.delete(vehicule);
        } else {
            deleteErrorText.setVisible(true);
            deleteErrorText.setText(formulaire.getErrors());
        }

        ControllersUtils.closePopupAndUpdateParent(event);
    }

    /**
     * Méthode qui annule la suppression du véhicule.
     * 
     * @param event ActionEvent
     */
    public void cancelDeleteVehicle(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    }

    /**
     * Méthode qui récupère le véhicule sélectionné dans la listView
     * de la vue précédente (prodSelectMenu)
     * 
     * @param vehi Vehicule
     */
    public static void setVehicule(Vehicule vehi) {
        vehicule = vehi;
    }
}