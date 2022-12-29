package validForm;

import validator.ValidateurDonnee;

/**
 * Utilisé lorsqu'on ajoute un Producteur.
 * 
 * @see controllers.AddProdCtrl
 */
public class FormAddProdCtrl extends FormModifyProdValidator {

    /**
     * Constructeur de FormAddProdCtrl.
     * 
     * @param siret           le siret du producteur.
     * @param proprietaire    le nom du producteur.
     * @param addressNumField le numéro de rue du producteur.
     * @param addressPathType le type de voie dans laquelle travaille le producteur.
     * @param addressPathName le type de voie du Producteur récupéré dans la vue
     * @param addressTownName la ville du Producteur.
     * @param addressPostCode le code Postal du producteur
     * @param numTelProd      le numéro de téléphone du producteur.
     * @param password        le mot de passe du producteur
     * @param confirmPassword le mot de passe à confirmer.
     */
    public FormAddProdCtrl(String siret, String proprietaire, String addressNumField, String addressPathType,
            String addressPathName, String addressTownName, String addressPostCode, String numTelProd, String password,
            String confirmPassword) {
        super(siret, proprietaire, addressNumField, addressPathType, addressPathName, addressTownName, addressPostCode,
                numTelProd, password, confirmPassword);

        if (!ValidateurDonnee.validePassword(password)) {
            setInvalid("Format du mot de passe incorrect");
        }
    }
}
