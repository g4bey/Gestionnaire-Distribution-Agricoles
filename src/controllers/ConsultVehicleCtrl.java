package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import modele.Tournee;
import modele.Vehicule;
import utility.ControllersUtils;

/**
 * Contrôleur permettant l'aperçu d'un Vehicule.
 */
public class ConsultVehicleCtrl implements Initializable {

    @FXML
    private Text vehicleLabelText;

    @FXML
    private Text vehicleImmatText;

    @FXML
    private Text vehicleCapacityText;

    @FXML
    private ListView<Tournee> tourListView;

    private static Vehicule vehicule;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        vehicleLabelText.setText(vehicule.getLibelle());
        vehicleImmatText.setText(vehicule.getNumImmat());
        vehicleCapacityText.setText(String.valueOf(vehicule.getPoidsMax()));

        tourListView.getItems().addAll(vehicule.getTournees());
    }

    /**
     * Méthode qui ferme la vue de consultation
     * des informations d'un véhicule.
     * 
     * @param event ActionEvent
     */
    public void closeConsultVehicle(ActionEvent event) {
        ControllersUtils.closePopup(event);
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