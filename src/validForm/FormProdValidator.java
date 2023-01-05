package validForm;

import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

/**
 * Validateur de formulaire pour AddProdCtrl & ModifyProdValidator.
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
     * @param siret           Le SIRET du Producteur récupéré dans la vue.
     * @param proprietaire    Le nom du Producteur récupéré dans la vue.
     * @param addressNumField Le numéro de rue du Producteur récupéré dans la vue.
     * @param addressPathType Le type de voie du Producteur récupéré dans la vue.
     * @param addressPathName Le type de voie du Producteur récupéré dans la vue
     * @param addressTownName La ville du Producteur récupéré dans la vue.
     * @param addressPostCode Le code Postal du Producteur récupéré dans la vue.
     * @param numTelProd      Le numéro de téléphone du Producteur récupéré dans la vue.
     * @param password        Le mot de passe du Producteur récupéré dans la vue.
     * @param confirmPassword Le mot de passe à confirmer récupéré dans la vue.
     */
    public FormProdValidator(String siret, String proprietaire, String addressNumField, String addressPathType,
            String addressPathName, String addressTownName, String addressPostCode, String numTelProd, String password,
            String confirmPassword) {
        if (!ValidateurDonnee.valideSiret(siret)) {
            setInvalid("Le SIRET est invalide");
        } // if
        if (proprietaire.equals("")) {
            setInvalid("Veuillez entrer un propriétaire");
        } // if
        if (!ValidateurDonnee.valideNom(proprietaire, 50)) {
            setInvalid("Ce nom est invalide et doit faire au plus 50 caractères.");
        }
        if (!ValidateurDonnee.valideTelephone(numTelProd)) {
            setInvalid("Le numéro de téléphone est invalide");
        } // if
        if (!password.equals(confirmPassword)) {
            setInvalid("Les mots de passe ne correspondent pas");
        } // if
        if (addressPathType == null) {
            setInvalid("Veuillez sélectionner un type de voie");
            return;
        } // if
        if (addressNumField.equals("") && (!addressPathType.equals("Lieu Dit"))) {
            setInvalid("Veuillez entrer un numéro de voie");
            return;
        }
        if (addressPathName.equals("")) {
            setInvalid("Veuillez entrer une voie");
            return;
        } // if
        if (addressTownName.equals("")) {
            setInvalid("Veuillez entrer le nom de la ville");
            return;
        } // if
        if (addressPostCode.equals("")) {
            setInvalid("Veuillez entrer un code postal");
            return;
        }
        if (!ValidateurDonnee.valideCodePostal(addressPostCode)) {
            setInvalid("Le code postal n'est pas valide.");
            return;
        } // if
    } // constructeur

    public String getAdresseCSV() {
        return adresseCsv;
    } // getAdresseCSV

    public String getCoordsXY() {
        return coordsXY;
    } // getCoordsXY

    public void setAdresseCsv(String adresseCsv) {
        this.adresseCsv = adresseCsv;
    } // setAdresseCsv

    public void setCoordXY(String coordsXY) {
        this.coordsXY = coordsXY;
    } // setCoordXY

} // FormProdValidator
