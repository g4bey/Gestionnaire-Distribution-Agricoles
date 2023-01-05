package validForm;

import exceptions.AdresseInvalideException;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

/**
 * Validateur de formulaire pour AddClientCtrl.
 *
 * @see controllers.AddClientCtrl
 */
public class FormClientValidator extends FormValidator {

    private String adresseCsv;
    private String coordsXY;

    /**
     * Constructeur de FormClientAddValidator,
     * Permet de changer les champs d'adresse en une adresse au format CSV
     * et génère les coordonnées GPS
     * 
     * @param clientName      Nom du Client récupéré dans la vue
     * @param addressNumField Numéro de voie du Client récupéré dans la vue
     * @param addressPathType Type de voie du Client récupéré dans la vue
     * @param addressPathName Nom de voie du Client récupéré dans la vue
     * @param addressTownName Nom de ville du Client récupéré dans la vue
     * @param addressPostCode Code postal du Client récupéré dans la vue
     * @param clientPhone     Numéro de téléphone du Client récupéré dans la vue
     */
    public FormClientValidator(String clientName, String addressNumField, String addressPathType,
            String addressPathName, String addressTownName, String addressPostCode, String clientPhone) {
        if (clientName.equals("")) {
            setInvalid("Veuillez entrer le nom du Client");
        } // if
        if (!ValidateurDonnee.valideNom(clientName, 50)) {
            setInvalid("Ce nom est invalide et doit faire au plus 50 caractères.");
        } // if
        if (!ValidateurDonnee.valideTelephone(clientPhone)) {
            setInvalid("Le numéro de téléphone n'est pas valide");
        } // if
        if (addressPathType == null) {
            setInvalid("Veuillez sélectionner un type de voie");
            return;
        } // if
        if (addressNumField.equals("") && (!addressPathType.equals("Lieu Dit"))) {
            setInvalid("Veuillez entrer un numéro de voie");
            return;
        } // if
        if (addressPathName.equals("")) {
            setInvalid("Veuillez entrer une voie");
            return;
        } // if
        if (addressPostCode.equals("")) {
            setInvalid("Veuillez entrer un code postal");
            return;
        } // if
        if (!ValidateurDonnee.valideCodePostal(addressPostCode)) {
            setInvalid("Le code postal n'est pas valide");
            return;
        } // if
        if (addressTownName.equals("")) {
            setInvalid("Veuillez entrer le nom de la ville");
            return;
        } // if

        ValidateurAdresse validadresse;
        try {
            validadresse = ValidateurAdresse.create(addressNumField, addressPathType, addressPathName, addressTownName,
                    addressPostCode);
        } catch (NumberFormatException | AdresseInvalideException e) {
            setInvalid("L'adresse n'est pas valide");
            return;
        } // try/catch

        adresseCsv = validadresse.csv();
        coordsXY = validadresse.getCoordXY();
    } // constructeur

    public String getAdresseCSV() {
        return adresseCsv;
    } // getAdresseCSV

    public String getCoordsXY() {
        return coordsXY;
    } // getCoordsXY

} // FormClientValidator
