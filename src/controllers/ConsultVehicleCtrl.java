package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import modele.Tournee;
import modele.Vehicule;
import utility.ControllersUtils;

/**
 * Contrôleur permettant l'aperçu d'un Véhicule.
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

    private List<Tournee> tournees;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        vehicleLabelText.setText(vehicule.getLibelle());
        vehicleImmatText.setText(vehicule.getNumImmat());
        vehicleCapacityText.setText(String.valueOf(vehicule.getPoidsMax()).concat("kg"));
        tournees = new ArrayList<>(vehicule.getTournees());

        Comparator<Tournee> tourneesAsc = (tour1, tour2) -> Long.valueOf(
                tour1.getHoraireDebut().getTime())
                .compareTo(tour2.getHoraireDebut().getTime());
        Collections.sort(tournees, tourneesAsc);
        tourListView.getItems().addAll(tournees);

        // Affichage du libellé uniquement sur le listView.
        tourListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Tournee row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle());
            }
        } // updateItem
        ); // setCellFactory
    } // initialize

    /**
     * Méthode qui ferme la vue de consultation
     * des informations d'un Véhicule.
     * 
     * @param event ActionEvent
     */
    public void closeConsultVehicle(ActionEvent event) {
        ControllersUtils.closePopupAndUpdateParent(event);
    } // closeConsultVehicle

    /**
     * Méthode qui récupère le Véhicule sélectionné dans la listView
     * de la vue précédente (prodSelectMenu)
     * 
     * @param vehi Vehicule
     */
    public static void setVehicule(Vehicule vehi) {
        vehicule = vehi;
    } // setVehicule

} // ConsultVehicleCtrl