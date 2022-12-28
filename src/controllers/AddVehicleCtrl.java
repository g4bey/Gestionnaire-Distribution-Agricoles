package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Vehicule;
import utility.ControllersUtils;
import utility.UserAuth;
import validForm.FormAddVehicleCtrl;

/**
 * Contrôleur permettant l'ajout d'un Vehicule.
 */
public class AddVehicleCtrl extends AbstractConnCtrl {

    @FXML
    private TextField vehicleImmatField;

    @FXML
    private TextField vehicleLabelField;

    @FXML
    private TextField vehicleCapacityField;

    @FXML
    private Text formErrorText;

    /**
     * Méthode qui valide l'ajout d'un véhicule.
     * 
     * @param event ActionEvent
     */
    public void validateAddVehicle(ActionEvent event) {
        FormAddVehicleCtrl favc = new FormAddVehicleCtrl(vehicleImmatField.getText(), vehicleCapacityField.getText(),
                vehicleLabelField.getText());

        if (favc.isValid()) {
            vDAO.add(new Vehicule(vehicleImmatField.getText(), Float.parseFloat(vehicleCapacityField.getText()),
                    vehicleLabelField.getText(), UserAuth.getProd()));
            ControllersUtils.closePopupAndUpdateParent (event);
        } else {
            formErrorText.setText(favc.getErrors());
            formErrorText.setVisible(true);
        }
    }

    /**
     * Méthode qui permet de fermer la vue d'ajout d'un véhicule.
     * 
     * @param event ActionEvent
     */
    public void cancelAddVehicle(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent (event);
    }
}