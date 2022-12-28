package validForm;

import exceptions.AdresseInvalideException;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

public class FormModifyClientCtrl extends FormValidator {
    private String adresseCSV;
    private String coordsXY;

    public FormModifyClientCtrl(String nom, String numRue, String typeRue, String nomRue, String ville,
            String codePostal, String numTel) {

        if (nom.equals("")) {
            setInvalid("Le nom ne peut être vide");
        }
        if (!ValidateurDonnee.valideTelephone(numTel)) {
            setInvalid("Le numéro de téléphone n'est pas valide");
        }

        ValidateurAdresse adresse = null;

        try {
            adresse = ValidateurAdresse.create(numRue, typeRue, nomRue, ville, codePostal);
        } catch (NumberFormatException | AdresseInvalideException e) {
            setInvalid("L'adresse n'est pas valide");
            return;
        }

        adresseCSV = adresse.csv();
        coordsXY = adresse.getCoordXY();
    }

    public String getAdresseCSV() {
        return adresseCSV;
    }

    public String getCoordsXY() {
        return coordsXY;
    }
}
