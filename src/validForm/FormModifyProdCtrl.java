package validForm;

import exceptions.AdresseInvalideException;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

public class FormModifyProdCtrl extends FormValidator {
    private String adresseCSV;
    private String coordsXY;

    public FormModifyProdCtrl(String siret, String proprietaire, String numRue, String typeRue, String nomRue,
            String ville, String codePostal, String numTel, String password, String confirmPassword) {

        if (!ValidateurDonnee.valideSiret(siret)) {
            setInvalid("Veuillez entrer un SIRET valide");
        }
        if (proprietaire.equals("")) {
            setInvalid("Veuillez entrer un nom de producteur valide");
        }
        if (!ValidateurDonnee.valideTelephone(numTel)) {
            setInvalid("Veuillez entrer un numéro de téléphone valide");
        }
        if (!ValidateurDonnee.validePassword(password)) {
            setInvalid(
                    "La taille du mot de passe ne convient pas (doit être supérieure à 8 caractères et inférieure à 70)");
        }
        if (!password.equals(confirmPassword)) {
            setInvalid("Les mots de passe ne sont pas identiques");
        }
        if (typeRue.equals("") | nomRue.equals("") | ville.equals("") | codePostal.equals("")) {
            setInvalid("L'adresse ne peut être vide.");
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
