package validForm;

import exceptions.AdresseInvalideException;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

/**
 * Validateur du formulaire pour AddProdCtrl.
 *
 * @see controllers.AddProdCtrl
 */
public class FormAddProdValidator extends FormProdValidator {
    /**
     * Constructeur de FormAddProdValidator.
     *
     * @param siret           Le SIRET du Producteur.
     * @param proprietaire    Le nom du Producteur.
     * @param addressNumField Le numéro de rue du Producteur.
     * @param addressPathType Le type de voie dans laquelle travaille le Producteur.
     * @param addressPathName Le type de voie du Producteur récupéré dans la vue
     * @param addressTownName La ville du Producteur.
     * @param addressPostCode Le code postal du Producteur
     * @param numTelProd      Le numéro de téléphone du Producteur.
     * @param password        Le mot de passe du Producteur
     * @param confirmPassword Le mot de passe à confirmer.
     */
    public FormAddProdValidator(String siret, String proprietaire, String addressNumField, String addressPathType,
            String addressPathName, String addressTownName, String addressPostCode, String numTelProd, String password,
            String confirmPassword) {
        super(siret, proprietaire, addressNumField, addressPathType, addressPathName, addressTownName, addressPostCode,
                numTelProd, password, confirmPassword);

        if (!ValidateurDonnee.validePassword(password)) {
            setInvalid(
                    "Le mot de passe ne répond pas aux exigences minimales de sécurité :\n9 caractères\n1 caractère spécial\n1 majuscule\n1 minuscule\n1 chiffre");
        } // if

        if (!isValid()) {
            return;
        } // if

        ValidateurAdresse adresseValide;
        try {
            adresseValide = ValidateurAdresse.create(addressNumField, addressPathType, addressPathName, addressTownName,
                    addressPostCode);
        } catch (NumberFormatException | AdresseInvalideException e) {
            setInvalid("L'adresse n'est pas valide");
            return;
        } // try/catch

        setAdresseCsv(adresseValide.csv());
        setCoordXY(adresseValide.getCoordXY());
    } // constructeur

} // FormAddProdValidator
