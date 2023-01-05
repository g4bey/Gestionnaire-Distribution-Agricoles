package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import exceptions.InvalidRouteException;
import javafx.util.StringConverter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Commande;
import modele.Tournee;
import modele.Vehicule;
import utility.ControllersUtils;
import utility.DateManager;
import utility.UserAuth;
import validForm.FormModifyTourValidator;
import validator.ValidateurTournee;

/**
 * Contrôleur permettant la modification d'une Tournée.
 */
public class ModifyTourCtrl extends AbstractConnCtrl implements Initializable {

    @FXML
    private TextField tourLabelField;

    @FXML
    private ChoiceBox<Vehicule> vehicleChoiceBox;

    @FXML
    private ListView<Commande> commListView;

    @FXML
    private ChoiceBox<Commande> commChoiceBox;

    @FXML
    private Label maxWeightLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label datetimeLabel;

    @FXML
    private Text formErrorText;

    @FXML
    private Button addCommBtn;

    @FXML
    private Button remCommBtn;

    private static Tournee tournee;

    private ArrayList<Commande> commandesSav = new ArrayList<>();

    private ArrayList<Commande> commandesToDel = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commandesSav = (ArrayList<Commande>) tournee.getCommandes().clone();

        // On récupère les Commandes qui n'ont pas de Tournée et qui sont compatibles
        // avec l'horaire de fin actuelle
        commChoiceBox
                .getItems().addAll(
                        UserAuth.getProd().getCommandes().stream()
                                .filter(commande -> commande.getTournee() == null
                                        && commande.getHoraireFin().compareTo(tournee.getHoraireFin()) > 0)
                                .toList());

        vehicleChoiceBox.setConverter(new StringConverter<Vehicule>() {

            @Override
            public Vehicule fromString(String arg0) {
                return null;
            } // fromString

            @Override
            public String toString(Vehicule arg0) {
                if (arg0 == null) {
                    return "";
                } // if
                return arg0.getLibelle();
            } // toString
        } // StringConverter<Vehicule>
        ); // setConverter

        commChoiceBox.setConverter(new StringConverter<Commande>() {

            @Override
            public Commande fromString(String arg0) {
                return null;
            } // fromString

            @Override
            public String toString(Commande arg0) {
                if (arg0 == null) {
                    return "";
                } // if
                return arg0.getLibelle();
            } // toString
        } // StringConverter<Commande>
        ); // setConverter

        commListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Commande row, boolean empty) {
                super.updateItem(row, empty);
                setText(empty ? null : row.getLibelle() + " | " + row.getHoraireDebut() + " | " + row.getHoraireFin());
            } // updateItem
        } // ListCell
        ); // setCellFactory

        commListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {

            @Override
            public void changed(ObservableValue<? extends Commande> arg0, Commande arg1, Commande arg2) {
                if (commListView.getItems().size() > 0) {
                    remCommBtn.setDisable(false);
                } // if
                else {
                    remCommBtn.setDisable(true);
                } // else
            } // changed
        } // ChangeListener<Commande>
        ); // addListener

        commChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {

            @Override
            public void changed(ObservableValue<? extends Commande> arg0, Commande arg1, Commande arg2) {
                addCommBtn.setDisable(false);
            } // changed
        } // ChangeListener<Commande>
        ); // addListener

        // On récupère tous les Véhicules disponibles
        vehicleChoiceBox.getItems().addAll(UserAuth.getProd().getVehicules().stream().filter(
                vh -> ValidateurTournee.valideVehicule(vh, tournee.getHoraireDebut(), tournee.getHoraireFin()))
                .toList());
        vehicleChoiceBox.setValue(tournee.getVehicule());

        tourLabelField.setText(tournee.getLibelle());

        commListView.getItems().addAll(tournee.getCommandes());
        changeLabel(tournee.getPoids(), tournee.getHoraireDebut(), tournee.getHoraireFin(), tournee.getHoraireFin());
    } // initialize

    /**
     * Ajoute une Commande, sélectionnée dans commChoiceBox, dans commListView.
     * 
     * @param event
     */
    public void addComm(ActionEvent event) {
        Commande comm = commChoiceBox.getSelectionModel().getSelectedItem();
        ArrayList<Commande> commsList = new ArrayList<>(commListView.getItems());
        commsList.add(comm);

        Timestamp[] horaires;

        try {
            horaires = ValidateurTournee.calculTournee(commsList, UserAuth.getProd().getGpsProd());
        } catch (IOException | InterruptedException | InvalidRouteException e) {
            commsList.remove(comm);
            return;
        } // try/catch

        commListView.getItems().add(comm);

        tournee.addCommande(comm);
        commChoiceBox.getSelectionModel().clearSelection();
        commChoiceBox.getItems().remove(comm);
        addCommBtn.setDisable(true);

        // On conserve uniquement les Commandes dont les horaires de fin sont après
        // l'horaire d'arrivée effectif de la dernière Commande
        List<Commande> newComms = commChoiceBox.getItems().stream()
                .filter(c -> c.getHoraireFin().compareTo(horaires[1]) > 0).toList();
        commChoiceBox.getItems().clear();
        commChoiceBox.getItems().addAll(newComms);

        changeLabel(Float.parseFloat(maxWeightLabel.getText()) + comm.getPoids(), horaires[0], horaires[1],
                horaires[0]);
    } // addComm

    /**
     * Retire une Commande, sélectionnée dans commListView, de commChoiceBox.
     * 
     * @param event ActionEvent
     */
    public void remComm(ActionEvent event) {
        Commande commDel = commListView.getSelectionModel().getSelectedItem(); // Commande sélectionnée

        float poids = commDel.getPoids();
        tournee.removeCommande(commDel);
        commandesToDel.add(commDel);
        commListView.getItems().remove(commDel);
        commChoiceBox.getItems().clear();

        List<Commande> commsDispo = new ArrayList<>(
                UserAuth.getProd().getCommandes().stream().filter(commande -> commande.getTournee() == null).toList());
        // Toutes les Commandes disponibles de l'utilisateur.

        if (commListView.getItems().size() == 0) {
            startLabel.setText("");
            endLabel.setText("");
            datetimeLabel.setText("");
            maxWeightLabel.setText("0");
        } // if
        else {
            Timestamp[] horaires;

            try {
                horaires = ValidateurTournee.calculTournee(new ArrayList<>(commListView.getItems()),
                        UserAuth.getProd().getGpsProd());
            } catch (IOException | InterruptedException | InvalidRouteException e) {
                return;
            } // try/catch

            changeLabel(Float.parseFloat(maxWeightLabel.getText()) - poids, horaires[0], horaires[1],
                    horaires[0]);

            commsDispo = commsDispo.stream()
                    .filter(c -> c.getHoraireFin().compareTo(horaires[1]) < 0 && !commListView.getItems().contains(c))
                    .toList();
        } // else

        commChoiceBox.getItems().addAll(commsDispo);
    } // remComm

    /**
     * Méthode qui valide la modification d'une Tournée.
     * 
     * @param event ActionEvent
     */
    public void validateModifyTour(ActionEvent event) {
        FormModifyTourValidator fatv = new FormModifyTourValidator(tourLabelField.getText(), UserAuth.getProd(),
                vehicleChoiceBox.getSelectionModel().getSelectedItem(),
                new ArrayList<Commande>(commListView.getItems()), maxWeightLabel.getText(), tournee);
        formErrorText.setVisible(false);
        if (fatv.isValid()) {
            tournee.setHoraireDebut(fatv.getHeureDebut());
            tournee.setHoraireFin(fatv.getHeureFin());
            tournee.setLibelle(tourLabelField.getText());
            tournee.setPoids(Float.parseFloat(maxWeightLabel.getText()));
            tournee.setVehicule(vehicleChoiceBox.getSelectionModel().getSelectedItem());

            for (Commande commandeToDel : commandesToDel) {
                commDAO.update(commandeToDel);
            } // for

            tDAO.update(tournee);
            ControllersUtils.closePopupAndUpdateParent(event);
        } // if
        else {
            formErrorText.setVisible(true);
            formErrorText.setText(fatv.getErrors());
        } // else
    } // validateModifyTour

    /**
     * Méthode qui permet de fermer la vue
     * de modification d'une Tournée.
     * 
     * @param event ActionEvent
     */
    public void cancelModifyTour(ActionEvent event) {
        for (Commande CommandeDel : tournee.getCommandes().stream().toList()) {
            tournee.removeCommande(CommandeDel);
        } // for
        for (Commande CommandeSav : commandesSav) {
            tournee.addCommande(CommandeSav);
        } // for

        ControllersUtils.closePopupAndUpdateParent(event);
    } // cancelModifyTour

    /**
     * Méthode qui récupère la Tournée sélectionnée dans la listView
     * de la vue précédente (prodSelectMenu)
     * 
     * @param tour Tournee
     */
    public static void setTournee(Tournee tour) {
        tournee = tour;
    } // setTournee

    /**
     * Permet de modifier les labels maxWeightLabel, startLabel,
     * endLabel et datetimeLabel en fonction des Commandes ajoutées
     * dans la ListView commListView.
     * 
     * @see addComm
     * @see remComm
     * @param weight    Float
     * @param startTime Timestamp
     * @param endTime   Timestamp
     * @param dateTime  Timestamp
     */
    public void changeLabel(Float weight, Timestamp startTime, Timestamp endTime, Timestamp dateTime) {
        maxWeightLabel.setText(Float.toString(weight));
        startLabel.setText(DateManager.TimestampToHourString(startTime));
        endLabel.setText(DateManager.TimestampToHourString(endTime));
        datetimeLabel.setText(DateManager.TimestampToDateString(dateTime));
    } // changeLabel

} // ModifyTourCtrl