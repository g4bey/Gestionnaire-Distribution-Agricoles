package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modele.Tournee;
import utility.ControllersUtils;

/**
 * Contrôleur permettant la suppression d'une Tournee.
 */
public class DeleteTourCtrl extends AbstractConnCtrl implements Initializable {

  @FXML
  private Text tourLabelText;
  @FXML
  private Text deleteErrorText;

  private static Tournee tournee;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    tourLabelText.setText(tournee.getLibelle());
  }

  /**
   * Méthode qui valide la suppression de la tournée.
   * 
   * @param event ActionEvent
   */
  public void validateDeleteTour(ActionEvent event) {
    tDAO.delete(tournee);
    ControllersUtils.closePopupAndUpdateParent(event);
  }

  /**
   * Méthode qui annule la suppression de la tournée.
   * 
   * @param event ActionEvent
   */
  public void cancelDeleteTour(ActionEvent event) {
    ControllersUtils.closePopupAndUpdateParent(event);
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