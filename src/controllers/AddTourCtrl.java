package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.util.StringConverter;
import modele.Vehicule;
import modele.Commande;
import modele.Producteur;
import modele.Tournee;
import utility.ControllersUtils;
import utility.DateManager;
import utility.UserAuth;
import validForm.FormAddTourValidator;

/**
* Contrôleur permettant l'ajout d'une Tournee.
*/
public class AddTourCtrl extends AbstractConnCtrl implements Initializable {
	
    @FXML
	private TextField tourLabelField;
    
    @FXML
    private ChoiceBox<Vehicule> vehicleChoiceBox;
    
    @FXML
    private ListView<Commande> commListView;
    
    @FXML
    private ChoiceBox<Commande> commChoiceBox;

    @FXML
    private Button addCommBtn;

    @FXML
    private Button remCommBtn;

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

    private LocalDate date;

    private Timestamp start;
    
    private Timestamp end;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        vehicleChoiceBox.setConverter(new StringConverter<Vehicule>() {

            @Override
            public Vehicule fromString(String arg0) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String toString(Vehicule arg0) {
                // TODO Auto-generated method stub
                if (arg0 == null) {
                    return "";
                }
                return arg0.getLibelle();
            }
            
        });

        commChoiceBox.setConverter(new StringConverter<Commande>() {

            @Override
            public Commande fromString(String arg0) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String toString(Commande arg0) {
                // TODO Auto-generated method stub
                if (arg0 == null) {
                    return "";
                }
                return arg0.getLibelle();
            }
        });

        commListView.setCellFactory(lv -> new ListCell<>() {
            @Override
            public void updateItem(Commande row, boolean empty) {
                super.updateItem(row, empty) ;
                setText(empty ? null : row.getLibelle());
            }
        });

        commListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {

            @Override
            public void changed(ObservableValue<? extends Commande> arg0, Commande arg1, Commande arg2) {
                // TODO Auto-generated method stub$
                if (commListView.getItems().size() > 0) {
                    remCommBtn.setDisable(false);
                }
                else {
                    remCommBtn.setDisable(true);
                }
            }
        });

        vehicleChoiceBox.getItems().addAll(UserAuth.getProd().getVehicules());
        commChoiceBox.getItems().addAll(UserAuth.getProd().getCommandes());
        
        commChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Commande>() {

            @Override
            public void changed(ObservableValue<? extends Commande> arg0, Commande arg1, Commande arg2) {
                // TODO Auto-generated method stub
                addCommBtn.setDisable(false);
            }
            
        });
    }

    /**
     * Ajoute une commande, sélectionnée dans commChoiceBox, dans commListView.
     * @param event
     */
    public void addComm(ActionEvent event) {
        Commande comm = commChoiceBox.getSelectionModel().getSelectedItem();
        List<Commande> commsList = commListView.getItems();
        
        commsList.add(comm);
        commChoiceBox.getSelectionModel().clearSelection();
        commChoiceBox.getItems().remove(comm);
        addCommBtn.setDisable(true);
        List<Commande> newComms = commChoiceBox.getItems().stream().filter(c -> c.getHoraireDebut().compareTo(comm.getHoraireDebut()) > 0).toList();
        commChoiceBox.getItems().clear();
        commChoiceBox.getItems().addAll(newComms);

        if (commsList.size() == 1) {
            changeLabel(comm.getPoids(), comm.getHoraireDebut(), comm.getHoraireFin(), comm.getHoraireFin());
            changeTime(comm.getHoraireDebut(), 
            comm.getHoraireFin(), 
            DateManager.TimestampToLocalDate(comm.getHoraireDebut()));
        }

        if (comm.getHoraireFin().compareTo(end) > 0) {
            end = comm.getHoraireFin();
            changeLabel(Float.parseFloat(maxWeightLabel.getText())+ comm.getPoids(), 
            start, 
            end, 
            DateManager.convertToTimestamp(date, DateManager.TimestampToHourString(start)));
        }
    }

    /**
     * Retire une commande, sélectionnée dans commListView, de commChoiceBox.
     * @param event ActionEvent
     */
    public void remComm(ActionEvent event) {
        Commande comm = commListView.getSelectionModel().getSelectedItem(); // commande sélectionnée
        List<Commande> oldComms = commListView.getItems(); // Commandes de la listView (moins celle sélectionnée)
        List<Commande> comms = new ArrayList<>(); // toutes les commandes de l'utilisateur

        commListView.getItems().remove(comm);
        commChoiceBox.getItems().clear();
        comms.addAll(UserAuth.getProd().getCommandes());
        comms.removeAll(oldComms);
    
        Float weight = Float.parseFloat(maxWeightLabel.getText());

        weight -= comm.getPoids();
        
        if (commListView.getItems().size() == 0) {
            startLabel.setText("");
            endLabel.setText("");
            datetimeLabel.setText("");
        }
        else {
            comms = comms.stream().filter(c -> 
            (c.getHoraireFin().toLocalDateTime().toLocalDate().compareTo(date) == 0))
            .toList();
            
        }

        if (comm.getHoraireDebut().compareTo(start) == 0 && commListView.getItems().size() != 0) {
            start = commListView.getItems().get(0).getHoraireDebut();
            for (Commande c : oldComms) {
                if (c.getHoraireDebut().compareTo(start) < 0) {
                    start = c.getHoraireDebut();
                }
            }
        }
        if (comm.getHoraireFin().compareTo(end) == 0 && commListView.getItems().size() != 0) {
            end = commListView.getItems().get(0).getHoraireFin();
            for (Commande c : oldComms) {
                if (c.getHoraireFin().compareTo(end) < 0) {
                    end = c.getHoraireFin();
                }
            }
        }
        
        changeLabel(weight, start, end, DateManager.convertToTimestamp(date, null));

        if (commListView.getItems().size() > 0) {
            comms = comms.stream().filter(c -> 
            c.getHoraireDebut()
            .compareTo(start) >= 0).toList();
        }

        commChoiceBox.getItems().addAll(comms);   
    }

	/**
	* Méthode qui valide l'ajout d'une tournée.
	* @param event ActionEvent
	*/
    public void validateAddTour(ActionEvent event) {
        FormAddTourValidator fatv = new FormAddTourValidator(
            tourLabelField.getText(), 
            UserAuth.getProd(), 
            vehicleChoiceBox.getSelectionModel().getSelectedItem(), 
            new ArrayList<Commande>(commListView.getItems()), 
            maxWeightLabel.getText(), 
            startLabel.getText(), 
            endLabel.getText(),
            date
        );
        if (fatv.isValid()) {
            tDAO.add(new Tournee(
                0,
                start, 
                end, 
                Float.parseFloat(maxWeightLabel.getText()), 
                tourLabelField.getText(), 
                vehicleChoiceBox.getSelectionModel().getSelectedItem()
            ));
            ControllersUtils.closePopupAndUpdateParent(event);
        }
        else {
            formErrorText.setVisible(true);
            formErrorText.setText(fatv.getErrors());
        }
    }
	
	/**
    * Méthode qui permet de fermer la vue d'ajout d'une tournée.
    * @param event ActionEvent
    */
    public void cancelAddTour(ActionEvent event) {
    	ControllersUtils.closePopupAndUpdateParent(event);
    }

    /**
     * Permet de modifier les labels maxWeightLabel, startLabel,
     * endLabel et datetimeLabel en fonction des commandes ajoutées
     * dans la ListView commListView.
     * @see addComm
     * @see remComm
     * @param weight Float
     * @param startTime Timestamp
     * @param endTime Timestamp
     * @param dateTime Timestamp
     */
    public void changeLabel(Float weight, Timestamp startTime, Timestamp endTime, Timestamp dateTime) {
        maxWeightLabel.setText(Float.toString(weight));
        startLabel.setText(DateManager.TimestampToHourString(startTime));
        endLabel.setText(DateManager.TimestampToHourString(endTime));
        datetimeLabel.setText(DateManager.TimestampToDateString(dateTime));
    }

    /**
     * Permet de modifier les variable d'instance start, end et date.
     * @param startTime
     * @param endTime
     * @param dateTime
     */
    public void changeTime(Timestamp startTime, Timestamp endTime, LocalDate dateTime) {
        date = dateTime;
        start = startTime;
        end = endTime;
    }
}