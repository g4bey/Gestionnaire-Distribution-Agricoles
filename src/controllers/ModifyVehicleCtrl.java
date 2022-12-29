package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Vehicule;
import utility.ControllersUtils;
import validForm.FormAddVehicleCtrl;
import validForm.FormModifyVehicleCtrl;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contrôleur permettant la modification d'un Vehicule.
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
    }

    /**
     * Méthode qui valide la modification d'un véhicule.
     * 
     * @param event ActionEvent
     */
    public void validateModifyVehicle(ActionEvent event) {
        FormModifyVehicleCtrl fmvc = new FormModifyVehicleCtrl(vehicleImmatField.getText(),
                vehicleCapacityField.getText(), vehicleLabelField.getText());

        if (fmvc.isValid()) {
            vehicule.setNumImmat(vehicleImmatField.getText());
            vehicule.setLibelle(vehicleLabelField.getText());
            vehicule.setPoidsMax(Float.parseFloat(vehicleCapacityField.getText()));

            vDAO.update(vehicule);

            ControllersUtils.closePopupAndUpdateParent(event);
        } else {
            System.out.println(vehicleImmatField.getText());
            System.out.println(vehicleLabelField.getText());
            System.out.println(vehicleCapacityField.getText());
            formErrorText.setText(fmvc.getErrors());
            formErrorText.setVisible(true);
        }
    }

    /**
     * Méthode qui permet de fermer la vue
     * de modification d'un véhicule.
     * 
     * @param event ActionEvent
     */
    public void cancelModifyVehicle(ActionEvent event) {
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
