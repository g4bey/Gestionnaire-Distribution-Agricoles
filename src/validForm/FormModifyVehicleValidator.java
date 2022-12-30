package validForm;

import modele.Vehicule;

/**
 * Formulaire utilisé lorsqu'on ajoute ou modifie un véhicule.
 *
 * @see controllers.ModifyVehicleCtrl
 */
public class FormModifyVehicleValidator extends FormVehicleValidator {
    /**
     * Vérifie le format des données correspondant à un véhicule.
     *
     * @param numImmat   la plaque d'immatriculation avec des tirets entre les zones.
     * @param poids      le poids max du véhicule.
     * @param libelle    un libellé utilisé pour l'identifier facilement.
     * @param vehicule   le vehicule concerné.
     */
    public FormModifyVehicleValidator(String numImmat, String poids, String libelle, Vehicule vehicule) {
        super(numImmat, poids, libelle);

        // Si on veut changer le poids.
        if (!vehicule.getTournees().isEmpty() && Float.parseFloat(poids) != vehicule.getPoidsMax()) {
            setInvalid("Modifcation poids impossible car ce vehicule est concerné par une tournée.");
        }
    }
}
