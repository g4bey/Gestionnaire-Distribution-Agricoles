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
    public FormAddProdValidator(String siret, String proprietaire, String addressNumField, String addressPathType,
                               String addressPathName, String addressTownName, String addressPostCode, String numTelProd, String password,
                               String confirmPassword) {
        super(siret, proprietaire, addressNumField, addressPathType, addressPathName, addressTownName, addressPostCode, numTelProd, password, confirmPassword);

        if (!ValidateurDonnee.validePassword(password)) {
            setInvalid("Format du mot de passe incorrect");
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
