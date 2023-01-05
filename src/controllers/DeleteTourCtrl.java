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
 * Contrôleur permettant la suppression d'une Tournée.
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
  } // initialize

  /**
   * Méthode qui valide la suppression de la Tournée.
   * 
   * @param event ActionEvent
   */
  public void validateDeleteTour(ActionEvent event) {
    tDAO.delete(tournee);
    ControllersUtils.closePopupAndUpdateParent(event);
  } // validateDeleteTour

  /**
   * Méthode qui annule la suppression de la Tournée.
   * 
   * @param event ActionEvent
   */
  public void cancelDeleteTour(ActionEvent event) {
    ControllersUtils.closePopupAndUpdateParent(event);
  } // cancelDeleteTour

  /**
   * Méthode qui récupère la Tournée sélectionnée dans la listView
   * de la vue précédente (prodSelectMenu)
   * 
   * @param tour Tournee
   */
  public static void setTournee(Tournee tour) {
    tournee = tour;
  } // setTournee

} // DeleteTourCtrl