package validForm;

import exceptions.AdresseInvalideException;
import modele.Producteur;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

/**
 * Utilisé lorsqu'on modifie un Producteur.
 * 
 * @see controllers.ModifyProdCtrl
 */
public class FormModifyProdValidator extends FormProdValidator {
    public FormModifyProdValidator(String siret, String proprietaire, String addressNumField, String addressPathType,
                                   String addressPathName, String addressTownName, String addressPostCode, String numTelProd, String password,
                                   String confirmPassword, Producteur producteur) {
        super(siret, proprietaire, addressNumField, addressPathType, addressPathName, addressTownName, addressPostCode, numTelProd, password, confirmPassword);

        if (!isValid()) {
            return;
        }

        // Si le mot de passe est vide, il reste le meme.
        if(!password.isEmpty()) {
            if (!ValidateurDonnee.validePassword(password)) {
                setInvalid("Format du mot de passe incorrect");
            }
        }

        ValidateurAdresse adresseValide;
        try {
            adresseValide = ValidateurAdresse.create(addressNumField, addressPathType, addressPathName, addressTownName,
                    addressPostCode);

            // si on veut changer l'adresse
            if (adresseValide.csv() != producteur.getAdresseProd() && !producteur.getTournees().isEmpty()) {
                setInvalid("Impossible de modifier l'adresse car le producteur est concerne par une tournée.");
                return;
            }

        } catch (NumberFormatException | AdresseInvalideException e) {
            setInvalid("L'adresse n'est pas valide");
            return;
        }

        setAdresseCsv(adresseValide.csv());
        setCoordXY(adresseValide.getCoordXY());

    }
}
