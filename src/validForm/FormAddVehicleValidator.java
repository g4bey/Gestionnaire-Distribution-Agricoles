package validForm;

import validator.ValidateurDonnee;

/**
 * Formulaire utilisé lorsqu'on ajoute ou modifie un véhicule.
 * 
 * @see controllers.AddVehicleCtrl
 */
public class FormAddVehicleValidator extends FormVehicleValidator {
    /**
     * Vérifie le format des données correspondant à un véhicule.
     *
     * @param numImmat la plaque d'immatriculation avec des tirets entre les zones.
     * @param poids    le poids max du véhicule.
     * @param libelle  un libellé utilisé pour l'identifier facilement.
     */
    public FormAddVehicleValidator(String numImmat, String poids, String libelle) {
        super(numImmat, poids, libelle);
    }
}
