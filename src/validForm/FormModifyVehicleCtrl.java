package validForm;

import validator.ValidateurDonnee;

public class FormModifyVehicleCtrl extends FormValidator {
    public FormModifyVehicleCtrl(String numImmat, String poids, String libelle) {

        if (!ValidateurDonnee.valideImmatriculation(numImmat)) {
            setInvalid("Veuillez entrer une plaque d'immatriculation valide");
        }
        if (!ValidateurDonnee.validePoids(poids)) {
            setInvalid("Veuillez entrer un poids valide");
        }
        if (libelle.equals("")) {
            setInvalid("Le libellé ne peut être null");
        }
    }
}
