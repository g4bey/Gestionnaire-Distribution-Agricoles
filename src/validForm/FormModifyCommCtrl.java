package validForm;

import java.time.LocalDate;

import modele.Client;
import validator.ValidateurDonnee;

public class FormModifyCommCtrl extends FormValidator {
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
