package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import modele.Commande;
import modele.Tournee;
import utility.ControllersUtils;

/**
 * Contrôleur permettant l'aperçu Tournee.
 */
public class ConsultTourCtrl extends AbstractDateCtrl implements Initializable {

  @FXML
  private Text tourLabelText;

  @FXML
  private Text tourWeightText;

  @FXML
  private Text tourDatetimeText;

  @FXML
  private Text startText;

  @FXML
  private Text endText;

  @FXML
  private Text vehicleImmatText;

  @FXML
  private Text capacityText;

  @FXML
  private ListView<Commande> commListView;

  @FXML
  private WebView tourMapWebView;

  private static Tournee tournee;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    tourLabelText.setText(tournee.getLibelle());
    tourWeightText.setText(String.valueOf(tournee.getPoids()));
    tourDatetimeText.setText(jour.format(tournee.getHoraireDebut()));
    startText.setText(heure.format(tournee.getHoraireDebut()));
    endText.setText(heure.format(tournee.getHoraireFin()));
    vehicleImmatText.setText(tournee.getVehicule().getNumImmat());
    capacityText.setText(String.valueOf(tournee.getPoids()));
    commListView.getItems().addAll(tournee.getCommandes());

    // TODO map
  }

  /**
   * Méthode qui ferme la vue de consultation
   * des informations d'une tournée.
   * 
   * @param event ActionEvent
   */
  public void closeConsultTour(ActionEvent event) {
    ControllersUtils.closePopup(event);
  }

  /**
   * Méthode qui récupère la tournée sélectionnée dans la listView
   * de la vue précédente (prodSelectMenu)
   * 
   * @param tour Tournee
   */
  public static void setTournee(Tournee tour) {
    tournee = tour;
  }
}