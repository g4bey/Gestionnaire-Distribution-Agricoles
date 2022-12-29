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
 * Formulaire d'ajout de tournée.
 * 
 * @see controllers.AddTourCtrl
 */
public class FormTourValidator extends FormValidator {
    private Timestamp[] horaires;

    /**
     *
     * @param libelle    le libellé pour identifier la tournée.
     * @param producteur le producteur associé à la tournée.
     * @param vehicule   le vehicle associé à la tournée.
     * @param commandes  la liste de commande composant la tournée.
     * @param poids      le poids de la tournée
     */
    public FormTourValidator(String libelle, Producteur producteur, Vehicule vehicule, ArrayList<Commande> commandes,
            String poids) {
        if (libelle.isEmpty()) {
            setInvalid("Veuillez écrire un libellé !");
            return;
        }
        if (vehicule == null) {
            setInvalid("Veuillez choisir un véhicule !");
            return;
        }
        if (commandes.isEmpty()) {
            setInvalid("Veuillez choisir une commande !");
            return;
        }
        if (!ValidateurTournee.validePoids(vehicule.getPoidsMax(), commandes)) {
            setInvalid("La capacité du véhicule est dépassé !");
        }

        try {
            horaires = ValidateurTournee.calculTournee(commandes, producteur.getGpsProd());
        } catch (IOException | InterruptedException | InvalidRouteException e) {
            setInvalid("La suite de commandes est incorrecte !");
            return;
        }
    }

    public Timestamp getHeureDebut() {
        return horaires[0];
    }

    public Timestamp getHeureFin() {
        return horaires[1];
    }
}