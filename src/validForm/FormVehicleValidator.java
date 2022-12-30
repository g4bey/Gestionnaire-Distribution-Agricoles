package validForm;

import validator.ValidateurDonnee;

/**
 * Formulaire utilisé lorsqu'on ajoute ou modifie un véhicule.
 */
public class FormVehicleValidator extends FormValidator {
    /**
     * Vérifie le format des données correspondant à un véhicule.
     * 
     * @param numImmat la plaque d'immatriculation avec des tirets entre les zones.
     * @param poids    le poids max du véhicule.
     * @param libelle  un libellé utilisé pour l'identifier facilement.
     */
    public FormVehicleValidator(String numImmat, String poids, String libelle) {

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
