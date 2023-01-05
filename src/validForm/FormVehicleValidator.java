package validForm;

import validator.ValidateurDonnee;

/**
 * Validateur de formulaire pour AddVehicleCtrl & ModifyVehicleCtrl.
 *
 * @see controllers.AddVehicleCtrl
 * @see controllers.ModifyVehicleCtrl
 */
public class FormVehicleValidator extends FormValidator {

    /**
     * Constructeur de FormVehicleValidator
     * Vérifie le format des données correspondant à un Véhicule.
     * 
     * @param numImmat La plaque d'immatriculation du Véhicule avec des tirets entre les zones.
     * @param poids    Le poids max du Véhicule.
     * @param libelle  Le libellé du Véhicule.
     */
    public FormVehicleValidator(String numImmat, String poids, String libelle) {

        if (!ValidateurDonnee.valideImmatriculation(numImmat)) {
            setInvalid("Veuillez entrer une plaque d'immatriculation valide");
        } // if
        if (!ValidateurDonnee.validePoids(poids)) {
            setInvalid("Veuillez entrer un poids valide");
        } // if
        if (libelle.equals("")) {
            setInvalid("Le libellé ne peut être null");
        } // if
    } // constructeur

} // FormVehicleValidator
