package validForm;

import exceptions.InvalidRouteException;
import modele.Commande;
import modele.Producteur;
import modele.Vehicule;
import validator.ValidateurTournee;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Validateur de formulaire pour AddTourCtrl & ModifyTourCtrl.
 *
 * @see controllers.AddTourCtrl
 * @see controllers.ModifyTourCtrl
 */
public class FormTourValidator extends FormValidator {
    protected Timestamp[] horaires;

    /**
     * Constructeur de FormTourValidator
     *
     * @param libelle    Le libellé de la Tournée récupéré dans la vue.
     * @param producteur Le Producteur associé à la Tournée récupéré dans la vue.
     * @param vehicule   Le Véhicule associé à la Tournée récupéré dans la vue.
     * @param commandes  La liste de Commandes composant la Tournée récupérée dans la vue.
     * @param poids      Le poids de la Tournée récupéré dans la vue.
     */
    public FormTourValidator(String libelle, Producteur producteur, Vehicule vehicule, ArrayList<Commande> commandes,
            String poids) {
        if (libelle.isEmpty()) {
            setInvalid("Veuillez entrer un libellé");
            return;
        } // if
        if (vehicule == null) {
            setInvalid("Veuillez choisir un Véhicule");
            return;
        } // if
        if (commandes.isEmpty()) {
            setInvalid("Veuillez choisir une Commande minimum");
            return;
        } // if
        if(commandes.size() > 9) {
            setInvalid("Une Tournée ne peut pas contenir plus de 9 Commandes");
            return;
        } // if
        if (!ValidateurTournee.validePoids(vehicule.getPoidsMax(), commandes)) {
            setInvalid("La capacité du Véhicule est dépassée");
        } // if

        try {
            horaires = ValidateurTournee.calculTournee(commandes, producteur.getGpsProd());
        } catch (IOException | InterruptedException | InvalidRouteException e) {
            setInvalid("La Tournée n'est pas valide");
        } // try/catch
    } // constructeur

    public Timestamp getHeureDebut() {
        return horaires[0];
    } // getHeureDebut

    public Timestamp getHeureFin() {
        return horaires[1];
    } // getHeureFin

} // FormTourValidator