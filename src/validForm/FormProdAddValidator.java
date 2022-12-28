package validForm;

import exceptions.AdresseInvalideException;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

public class FormProdAddValidator extends FormValidator {

    public String adresseCsv;
    public String coordsXY;

    public FormProdAddValidator(String siret,
                                String proprietaire,
                                String addressNumField,
                                String addressPathType,
                                String addressPathName,
                                String addressTownName,
                                String addressPostCode,
                                String numTelProd,
                                String password,
                                String confirmPassword) {
        if(!ValidateurDonnee.valideSiret(siret)) {
            setInvalid("Le SIRET est invalide");
        }
        if (proprietaire.equals("")) {
            setInvalid("Veuillez entrer un propriétaire");
        }
        if (addressNumField.equals("") && addressPathType.equals("Lieu Dit")) {
            setInvalid("Veuillez entrer un numéro de voie");
        }
        if (addressPathType.equals("")) {
            setInvalid("Veuillez sélectionner un type de voie");
        }
        if (addressPathName.equals("")) {
            setInvalid("Veuillez entrer une voie");
        }
        if (addressTownName.equals("")) {
            setInvalid("Veuillez entrer le nom de la ville");
        }
        if (addressPostCode.equals("")) {
            setInvalid("Veuillez entrer un code postal");
        }
        ValidateurAdresse validadresse;
        try {
            validadresse = ValidateurAdresse.create(addressNumField, addressPathType,
                addressPathName, addressTownName, addressPostCode);
            adresseCsv = validadresse.csv();
            coordsXY = validadresse.getCoordXY();
        } catch (NumberFormatException | AdresseInvalideException e) {
            e.printStackTrace();
            setInvalid("L'adresse n'est pas valide");
        }
        if (!ValidateurDonnee.valideTelephone(numTelProd)) {
            setInvalid("Le numéro de téléphone est invalide");
        }
        if (!ValidateurDonnee.validePassword(password)){
            setInvalid("Format du mot de passe incorrect");
        }
        if (!password.equals(confirmPassword)) {
            setInvalid("Les mots de passe ne correspondent pas");
        }
    }

    public String getAdresseCsv() {
        return adresseCsv;
    }

    public String getCoordsXY() {
        return coordsXY;
    }
}
