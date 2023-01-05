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
 * Contrôleur permettant la suppression d'un Véhicule.
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
    } // initialize

    /**
     * Méthode qui valide la suppression du Véhicule.
     * 
     * @param event ActionEvent
     */
    public void validateDeleteVehicle(ActionEvent event) {
        FormValidator formulaire = new FormDeleteVehicule(vehicule);

        deleteErrorText.setVisible(false);
        if (formulaire.isValid()) {
            vDAO.delete(vehicule);
            ControllersUtils.closePopupAndUpdateParent(event);
        } // if
        else {
            deleteErrorText.setVisible(true);
            deleteErrorText.setText(formulaire.getErrors());
        } // else
    } // validateDeleteVehicle

    /**
     * Méthode qui annule la suppression du Véhicule.
     * 
     * @param event ActionEvent
     */
    public void cancelDeleteVehicle(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    } // cancelDeleteVehicle

    /**
     * Méthode qui récupère le Véhicule sélectionné dans la listView
     * de la vue précédente (prodSelectMenu)
     * 
     * @param vehi Vehicule
     */
    public static void setVehicule(Vehicule vehi) {
        vehicule = vehi;
    } // setVehicule

} // DeleteVehicleCtrl