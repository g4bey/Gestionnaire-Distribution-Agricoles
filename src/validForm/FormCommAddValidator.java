package validForm;

import modele.Client;
import utility.DateManager;
import validator.ValidateurDonnee;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Validateur de formulaire pour AddCommCtrl
 * @see controllers.AddCommCtrl
 */
public class FormCommAddValidator extends FormValidator {

    /**
     * Constructeur de FormCommAddValidator
     * @param libelle Le libellé de la Commande récupéré dans la vue
     * @param poids Le poids de la Commande récupéré dans la vue
     * @param date La date de la Commande récupérée dans la vue
     * @param creneauDebut Le créneau de début de la Commande récupéré dans la vue
     * @param creneauFin Le créneau de fin de la Commande récupéré dans la vue
     * @param client Le client de la Commande récupéré dans la vue
     */
    public FormCommAddValidator(String libelle,
            String poids,
            LocalDate date,
            String creneauDebut,
            String creneauFin,
            Client client) {

        if (libelle.equals("")) {
            setInvalid("Veuillez entrer un libellé");
        }
        if (!ValidateurDonnee.validePoids(poids)) {
            setInvalid("Format du poids incorrect");
        }
        if (client == null) {
            setInvalid("Veuillez sélectionner un Client");
        }
        if (!ValidateurDonnee.valideDate(date)) {
            setInvalid("Veuillez choisir une date");
            return;
        }
        if (!ValidateurDonnee.valideHeure(creneauDebut)) {
            setInvalid("Le créneau de début doit être de la forme HH:MM");
            return;
        }
        if (!ValidateurDonnee.valideHeure(creneauFin)) {
            setInvalid("Le créneau de fin doit être de la forme HH:MM");
            return;
        }
        if (DateManager.convertToTimestamp(date, creneauDebut).before(Timestamp.valueOf(LocalDateTime.now()))) {
            setInvalid("Vous ne pouvez pas ajouter une Commande dans le passé");
        }
        if (DateManager.convertToTimestamp(date, creneauDebut)
                .after(DateManager.convertToTimestamp(date, creneauFin))) {
            setInvalid("Le créneau de fin est avant le créneau de début !");
        }
    }
}
