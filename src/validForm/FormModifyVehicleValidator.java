package validForm;

import modele.Vehicule;

/**
 * Validateur de formulaire pour ModifyVehicleCtrl
 *
 * @see controllers.ModifyVehicleCtrl
 */
public class FormModifyVehicleValidator extends FormVehicleValidator {

    /**
     * Constructeur de FormVehicleValidator.
     * Vérifie le format des données correspondant à un véhicule.
     *
     * @param numImmat   La plaque d'immatriculation du Véhicule avec des tirets entre les zones.
     * @param poids      Le poids max du Véhicule.
     * @param libelle    Le libellé du Véhicule.
     * @param vehicule   Le Véhicule concerné.
     */
    public FormModifyVehicleValidator(String numImmat, String poids, String libelle, Vehicule vehicule) {
        super(numImmat, poids, libelle);

        // Si on veut changer le poids.
        if (!vehicule.getTournees().isEmpty() && Float.parseFloat(poids) != vehicule.getPoidsMax()) {
            setInvalid("Modification du poids impossible car ce Véhicule est concerné par une tTurnée.");
        } // if
    } // constructeur

} // FormModifyVehicleValidator
