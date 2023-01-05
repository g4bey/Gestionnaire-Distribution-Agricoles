package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Vehicule;
import utility.ControllersUtils;
import validForm.FormModifyVehicleValidator;
import validForm.FormVehicleValidator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur permettant la modification d'un Véhicule.
 */
public class ModifyVehicleCtrl extends AbstractConnCtrl implements Initializable {
    @FXML
    private TextField vehicleImmatField;

    @FXML
    private TextField vehicleLabelField;

    @FXML
    private TextField vehicleCapacityField;

    @FXML
    private Text formErrorText;

    private static Vehicule vehicule;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehicleImmatField.setText(vehicule.getNumImmat());
        vehicleLabelField.setText(vehicule.getLibelle());
        vehicleCapacityField.setText(Float.toString(vehicule.getPoidsMax()));
    } // initialize

    /**
     * Méthode qui valide la modification d'un Véhicule.
     * 
     * @param event ActionEvent
     */
    public void validateModifyVehicle(ActionEvent event) {
        FormVehicleValidator fvv = new FormModifyVehicleValidator(vehicleImmatField.getText(), vehicleCapacityField.getText(),
                vehicleLabelField.getText(), vehicule);

        if (fvv.isValid()) {
            vehicule.setNumImmat(vehicleImmatField.getText());
            vehicule.setLibelle(vehicleLabelField.getText());
            vehicule.setPoidsMax(Float.parseFloat(vehicleCapacityField.getText()));

            vDAO.update(vehicule);

            ControllersUtils.closePopupAndUpdateParent(event);
        } // if
        else {
            formErrorText.setText(fvv.getErrors());
            formErrorText.setVisible(true);
        } // else
    } // validateModifyVehicle

    /**
     * Méthode qui permet de fermer la vue
     * de modification d'un Véhicule.
     * 
     * @param event ActionEvent
     */
    public void cancelModifyVehicle(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    } // cancelModifyVehicle

    /**
     * Méthode qui récupère le Véhicule sélectionné dans la listView
     * de la vue précédente (prodSelectMenu)
     * 
     * @param vehi Vehicule
     */
    public static void setVehicule(Vehicule vehi) {
        vehicule = vehi;
    } // setVehicule

} // ModifyVehicleCtrl
