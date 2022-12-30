package validForm;

import exceptions.AdresseInvalideException;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

/**
 * Utilisé lorsqu'on modifie un Producteur.
 * 
 * @see controllers.AddProdCtrl
 */
public class FormAddProdValidator extends FormProdValidator {
    /**
     * Constructeur de FormAddProdValidator.
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
    public FormAddProdValidator(String siret, String proprietaire, String addressNumField, String addressPathType,
            String addressPathName, String addressTownName, String addressPostCode, String numTelProd, String password,
            String confirmPassword) {
        super(siret, proprietaire, addressNumField, addressPathType, addressPathName, addressTownName, addressPostCode,
                numTelProd, password, confirmPassword);

        if (!ValidateurDonnee.validePassword(password)) {
            setInvalid(
                    "Le mot de passe ne répond pas aux exigences minimales de sécurité :\n9 caractères\n1 caractère spécial\n1 majuscule\n1 minuscule\n1 chiffre");
        }

        if (!isValid()) {
            return;
        }

        ValidateurAdresse adresseValide;
        try {
            adresseValide = ValidateurAdresse.create(addressNumField, addressPathType, addressPathName, addressTownName,
                    addressPostCode);
        } catch (NumberFormatException | AdresseInvalideException e) {
            setInvalid("L'adresse n'est pas valide");
            return;
        }

        setAdresseCsv(adresseValide.csv());
        setCoordXY(adresseValide.getCoordXY());
    }
}
