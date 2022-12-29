package validForm;

import java.time.LocalDate;

import modele.Client;
import validator.ValidateurDonnee;

/**
 * Ce validateur est appelé lorsqu'on modifie une commande.
 * @see controllers.ModifyCommCtrl
 */
public class FormModifyCommCtrl extends FormValidator {

    /**
     * Constructeur de FormModifyCommCtrl
     * @param libelle Le libellé de la Commande récupéré dans la vue
     * @param poids Le poids de la Commande récupéré dans la vue
     * @param date La date de la Commande récupérée dans la vue
     * @param heureDebut Le créneau de début de la Commande récupéré dans la vue
     * @param heureFin Le créneau de fin de la Commande récupéré dans la vue
     * @param clt Le client de la Commande récupéré dans la vue
     */
    public FormModifyCommCtrl(String libelle, String poids, LocalDate date, String heureDebut, String heureFin,
            Client clt) {

        if (libelle.equals("")) {
            setInvalid("Le libellé ne peut être vide");
        }
        if (!ValidateurDonnee.validePoids(poids)) {
            setInvalid("Poids invalide");
        }
        if (!ValidateurDonnee.valideDate(date)) {
            setInvalid("Date invalide");
        }
        if (!ValidateurDonnee.valideHeure(heureDebut)) {
            setInvalid("Heure de début invalide)");
        }
        if (!ValidateurDonnee.valideHeure(heureFin)) {
            setInvalid("Heure de fin invalide)");
        }
        if (clt == null) {
            setInvalid("Le Client ne peut être null");
        }
    }
}
