package controllers;

import DAO.AdministrateurDAO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import modele.Administrateur;
import modele.Producteur;
import utility.ControllersUtils;
import utility.UserAuth;
import validForm.FormPsChange;
import validForm.FormValidator;

public class PasswordChangeCtrl extends AbstractConnCtrl {

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmField;

    @FXML
    private Text modifyErrorText;

    /**
     * Méthode qui permet à l'utilisateur de changer son mot de passe.
     * 
     * @param event ActionEvent
     */
    public void validatePasswordChangeProd(ActionEvent event) {
        FormValidator formulaire = new FormPsChange(passwordField.getText(), confirmField.getText());
        Producteur prod = UserAuth.getProd();

        if (formulaire.isValid()) {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
            String hashedPs = argon2.hash(2, 15 * 1024, 1, passwordField.getText().toCharArray());
            prod.setMdpProd(hashedPs);
            pDAO.update(prod);
            ControllersUtils.closePopup(event);
        } // if
        else {
            modifyErrorText.setVisible(true);
            modifyErrorText.setText(formulaire.getErrors());
        } // else
    } // validatePasswordChangeProd

    /**
     * Méthode qui permet à l'utilisateur de changer son mot de passe.
     * 
     * @param event ActionEvent
     */
    public void validatePasswordChangeAdmin(ActionEvent event) {
        FormValidator formulaire = new FormPsChange(passwordField.getText(), confirmField.getText());
        aDAO = new AdministrateurDAO(conn);
        Administrateur admin = UserAuth.getAdmin();
        if (formulaire.isValid()) {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
            String hashedPs = argon2.hash(2, 15 * 1024, 1, passwordField.getText().toCharArray());
            admin.setMdpAdmin(hashedPs);
            aDAO.update(admin);
            ControllersUtils.closePopup(event);
        } // if
        else {
            modifyErrorText.setVisible(true);
            modifyErrorText.setText(formulaire.getErrors());
        } // else
    } // validatePasswordChangeAdmin

    /**
     * Méthode qui ferme la fenêtre de changement de mot de passe.
     * 
     * @param event ActionEvent
     */
    public void cancelPasswordChange(ActionEvent event) {
        ControllersUtils.closePopup(event);
    } // cancelPasswordChange

} // PasswordChangeCtrl