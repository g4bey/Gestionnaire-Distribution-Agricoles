package validForm;

import validator.ValidateurDonnee;

/**
 * Utilisé lorsqu'on modifie un Producteur.
 * 
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
        if (!ValidateurDonnee.valideNom(proprietaire, 50)) {
            setInvalid("Ce nom est invalide et doit faire au plus 50 caractères.");
        }
        if (!ValidateurDonnee.valideTelephone(numTelProd)) {
            setInvalid("Le numéro de téléphone est invalide");
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
    }

    public String getAdresseCSV() {
        return adresseCsv;
    }

    public String getCoordsXY() {
        return coordsXY;
    }

    public void setAdresseCsv(String adresseCsv) {
        this.adresseCsv = adresseCsv;
    }

    public void setCoordXY(String coordsXY) {
        this.coordsXY = coordsXY;
    }
}
