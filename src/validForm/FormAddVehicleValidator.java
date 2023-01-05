package validForm;

import validator.ValidateurDonnee;

/**
 * Validateur du formulaire pour AddVehicleCtrl.
 *
 * @see controllers.AddVehicleCtrl
 */
public class FormAddVehicleValidator extends FormVehicleValidator {

    /**
     * Constructeur de FormAddVehicleValidator.
     *
     * @param numImmat La plaque d'immatriculation du Véhicule avec des tirets entre les zones.
     * @param poids    Le poids max du Véhicule.
     * @param libelle  Le libellé du Véhicule.
     */
    public FormAddVehicleValidator(String numImmat, String poids, String libelle) {
        super(numImmat, poids, libelle);
    } // constructeur

} // FormAddVehicleValidator
