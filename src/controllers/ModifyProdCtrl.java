package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import de.mkammerer.argon2.Argon2Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import modele.Producteur;
import utility.ControllersUtils;
import validForm.FormModifyProdCtrl;

/**
 * Contrôleur permettant la modification d'un Producteur.
 */
public class ModifyProdCtrl extends AbstractConnCtrl implements Initializable {

    @FXML
    private TextField prodSiretField;

    @FXML
    private TextField propNameField;

    @FXML
    private TextField addressNumField;

    @FXML
    private ChoiceBox<String> pathTypeChoiceBox;

    @FXML
    private TextField pathNameField;

    @FXML
    private TextField townNameField;

    @FXML
    private TextField postcodeField;

    @FXML
    private TextField prodPhoneField;

    @FXML
    private TextField prodPasswordField;

    @FXML
    private TextField confirmPasswordField;

    private static Producteur producteur;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        prodSiretField.setText(producteur.getSiret());
        propNameField.setText(producteur.getProprietaire());

        String[] adresse = producteur.getAdresseProd().split(",");

        addressNumField.setText(adresse[0]);

        ObservableList<String> listePath = FXCollections.observableArrayList();
        listePath.add("Rue");
        listePath.add("Boulevard");
        listePath.add("Avenue");
        listePath.add("Allée");
        listePath.add("Chemin");
        listePath.add("Route");
        listePath.add("Impasse");
        listePath.add("Lieu Dit");
        pathTypeChoiceBox.setItems(listePath);
        pathTypeChoiceBox.setValue(adresse[1]);

        pathNameField.setText(adresse[2]);
        townNameField.setText(adresse[3]);
        postcodeField.setText(adresse[4]);

        prodPhoneField.setText(producteur.getNumTelProd());
    }

    /**
     * Méthode qui valide la modification d'un producteur.
     * 
     * @param event ActionEvent
     */
    public void validateModifyProd(ActionEvent event) {
        FormModifyProdCtrl fmpc = new FormModifyProdCtrl(prodSiretField.getText(), propNameField.getText(),
                addressNumField.getText(), pathTypeChoiceBox.getValue(), pathNameField.getText(),
                townNameField.getText(), postcodeField.getText(), prodPhoneField.getText(), prodPasswordField.getText(),
                confirmPasswordField.getText());

        if (fmpc.isValid()) {
            producteur.setSiret(prodSiretField.getText());
            producteur.setProprietaire(propNameField.getText());
            producteur.setNumTelProd(prodPhoneField.getText());
            producteur.setAdresseProd(fmpc.getAdresseCSV());
            producteur.setGpsProd(fmpc.getCoordsXY());
            producteur.setMdpProd(Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64).hash(2, 15 * 1024, 1,
                    prodPasswordField.getText().toCharArray()));

            pDAO.update(producteur);
            ControllersUtils.closePopup(event);
        }
    }

    /**
     * Méthode qui permet de fermer la vue de modification d'un producteur.
     * 
     * @param event ActionEvent
     */
    public void cancelModifyProd(ActionEvent event) {
        ControllersUtils.closePopup(event);
    }

    /**
     * Méthode qui récupère le producteur sélectionné dans la listView
     * de la vue précédente (adminSelectMenu)
     * 
     * @param prod Producteur
     */
    public static void setProd(Producteur prod) {
        producteur = prod;
    }
}