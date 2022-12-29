package validForm;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modele.Commande;
import modele.Producteur;
import modele.Vehicule;
import utility.DateManager;
import validator.ValidateurTournee;

/**
 * Formulaire d'ajout de tournée.
 * @see controllers.AddTourCtrl
 */
public class FormAddTourValidator extends FormValidator {

    /**
     *
     * @param libelle le libellé pour identifier la tournée.
     * @param producteur le producteur associé à la tournée.
     * @param vehicule le vehicle associé à la tournée.
     * @param commandes la liste de commande composant la tournée.
     * @param poids le poids de la tournée
     * @param horaireDebut l'horaire de début de la tournée.
     * @param horaireFin l'horaire de début de la tournée.
     * @param date la date de la tournée.
     */
    public FormAddTourValidator(String libelle, Producteur producteur, Vehicule vehicule, ArrayList<Commande> commandes, 
    String poids, String horaireDebut, String horaireFin, LocalDate date) {
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
        if (!ValidateurTournee.valideVehicule(vehicule, 
        DateManager.convertToTimestamp(date, horaireDebut),
        DateManager.convertToTimestamp(date, horaireFin))) {
            setInvalid("Le véhicule n'est pas disponible pour ce créneau horaire !");
        }
        if (!ValidateurTournee.validePoids(vehicule.getPoidsMax(), commandes)) {
            setInvalid("La capacité du véhicule est dépassé !");
        }
    }
}