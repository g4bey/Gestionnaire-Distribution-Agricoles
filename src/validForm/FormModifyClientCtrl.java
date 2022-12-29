package validForm;

import exceptions.AdresseInvalideException;
import validator.ValidateurAdresse;
import validator.ValidateurDonnee;

/**
 * Ce validateur est appelé lorsqu'on modifie un client.
 * @see controllers.ModifyClientCtrl
 */
public class FormModifyClientCtrl extends FormValidator {
    private String adresseCSV;
    private String coordsXY;

    /**
     * Constructeur de FormClientAddValidator,
     * permet de changer les champs d'adresse en une adresse au format CSV
     * et génère les coordonnées GPS
     * @param nom Nom du Client récupéré dans la vue
     * @param numRue Numéro de voie du Client récupéré dans la vue
     * @param typeRue Type de voie du Client récupéré dans la vue
     * @param nomRue Nom de voie du Client récupéré dans la vue
     * @param ville Nom de ville du Client récupéré dans la vue
     * @param codePostal Code postal du Client récupéré dans la vue
     * @param numTel Numéro de téléphone du Client récupéré dans la vue
     */
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
