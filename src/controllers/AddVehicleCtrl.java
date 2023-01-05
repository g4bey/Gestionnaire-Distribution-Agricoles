package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Vehicule;
import utility.ControllersUtils;
import utility.UserAuth;
import validForm.FormAddVehicleValidator;
import validForm.FormVehicleValidator;

/**
 * Contrôleur permettant l'ajout d'un Véhicule.
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
     * Méthode qui valide l'ajout d'un Véhicule.
     * 
     * @param event ActionEvent
     */
    public void validateAddVehicle(ActionEvent event) {
        FormVehicleValidator fvv = new FormAddVehicleValidator(vehicleImmatField.getText(), vehicleCapacityField.getText(),
                vehicleLabelField.getText());

        if (fvv.isValid()) {
            vDAO.add(new Vehicule(vehicleImmatField.getText(), Float.parseFloat(vehicleCapacityField.getText()),
                    vehicleLabelField.getText(), UserAuth.getProd()));
            ControllersUtils.closePopupAndUpdateParent(event);
        } // if
        else {
            formErrorText.setText(fvv.getErrors());
            formErrorText.setVisible(true);
        } // else
    } // validateAddVehicle

    /**
     * Méthode qui permet de fermer la vue d'ajout d'un Véhicule.
     * 
     * @param event ActionEvent
     */
    public void cancelAddVehicle(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    } // cancelAddVehicle

} // AddVehicleCtrl