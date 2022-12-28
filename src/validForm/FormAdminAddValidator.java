package validForm;

import validator.ValidateurDonnee;

/**
 * Validateur de formulaire pour AddAdminCtrl
 * @see controllers.AddAdminCtrl
 */
public class FormAdminAddValidator extends FormValidator {

    /**
     * Constructeur de FormAdminAddValidator
     * @param login Le login de l'Administrateur récupéré dans la vue
     * @param password Le mot de passe de l'Administrateur récupéré dans la vue
     * @param confirmPassword La confirmation du mot de passe de l'Administrateur récupéré dans la vue
     */
    public FormAdminAddValidator(String login, String password, String confirmPassword) {
        if (!ValidateurDonnee.validePseudonyme(login, 15)) {
            setInvalid("Format du pseudonyme incorrect");
        }
        if (!ValidateurDonnee.validePassword(password)){
            setInvalid("Format du mot de passe incorrect");
        }
        if (!password.equals(confirmPassword)) {
            setInvalid("Les mots de passe ne correspondent pas");
        }
    }
}
