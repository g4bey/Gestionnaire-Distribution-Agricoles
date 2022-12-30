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

    /**
     * Constructeur de FormModifyProdValidator.
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
     * @param producteur      le producteur.
     */
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
            if (!adresseValide.csv().equals(producteur.getAdresseProd()) && !producteur.getTournees().isEmpty()) {
                System.out.println(adresseValide.csv());
                System.out.println(producteur.getAdresseProd());
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
