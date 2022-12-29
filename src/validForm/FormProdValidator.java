package validForm;

import exceptions.AdresseInvalideException;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

/**
 * Utilisé lorsqu'on ajoute ou modifie un producteur.
 * 
 * @see controllers.AddProdCtrl
 * @see controllers.ModifyProdCtrl
 */
public class FormProdValidator extends FormValidator {
    private String adresseCsv;
    private String coordsXY;

    /**
     * Constructeur de FormProdValidator.
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
    public FormProdValidator(String siret, String proprietaire, String addressNumField, String addressPathType,
            String addressPathName, String addressTownName, String addressPostCode, String numTelProd, String password,
            String confirmPassword) {
        if (!ValidateurDonnee.valideSiret(siret)) {
            setInvalid("Le SIRET est invalide");
        }
        if (proprietaire.equals("")) {
            setInvalid("Veuillez entrer un propriétaire");
        }
        if (!ValidateurDonnee.valideTelephone(numTelProd)) {
            setInvalid("Le numéro de téléphone est invalide");
        }
        if (!ValidateurDonnee.validePassword(password)) {
            setInvalid("Format du mot de passe incorrect");
        }
        if (!password.equals(confirmPassword)) {
            setInvalid("Les mots de passe ne correspondent pas");
        }
        if (addressPathType == null) {
            setInvalid("Veuillez sélectionner un type de voie");
            return;
        }
        if (addressNumField.equals("") && (!addressPathType.equals("Lieu Dit"))) {
            setInvalid("Veuillez entrer un numéro de voie");
            return;
        }
        if (addressPathName.equals("")) {
            setInvalid("Veuillez entrer une voie");
            return;
        }
        if (addressTownName.equals("")) {
            setInvalid("Veuillez entrer le nom de la ville");
            return;
        }
        if (addressPostCode.equals("")) {
            setInvalid("Veuillez entrer un code postal");
            return;
        }

        ValidateurAdresse validadresse;
        try {
            validadresse = ValidateurAdresse.create(addressNumField, addressPathType, addressPathName, addressTownName,
                    addressPostCode);
        } catch (NumberFormatException | AdresseInvalideException e) {
            setInvalid("L'adresse n'est pas valide");
            return;
        }

        adresseCsv = validadresse.csv();
        coordsXY = validadresse.getCoordXY();
    }

    public String getAdresseCSV() {
        return adresseCsv;
    }

    public String getCoordsXY() {
        return coordsXY;
    }
}
