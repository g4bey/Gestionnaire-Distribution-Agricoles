package validForm;

import validator.ValidateurDonnee;

/**
 * Validateur de formulaire pour AddAdminCtrl.
 * 
 * @see controllers.AddAdminCtrl
 */
public class FormAdminAddValidator extends FormValidator {

    /**
     * Constructeur de FormAdminAddValidator.
     * 
     * @param login           Le login de l'Administrateur récupéré dans la vue
     * @param password        Le mot de passe de l'Administrateur récupéré dans la
     *                        vue
     * @param confirmPassword La confirmation du mot de passe de l'Administrateur
     *                        récupéré dans la vue
     */
    public FormAdminAddValidator(String login, String password, String confirmPassword) {
        if (!ValidateurDonnee.validePseudonyme(login, 15)) {
            setInvalid("Format du pseudonyme incorrect");
        } // if
        if (!ValidateurDonnee.validePassword(password)) {
            setInvalid(
                    "Le mot de passe ne répond pas aux exigences minimales de sécurité :\n9 caractères\n1 caractère spécial\n1 majuscule\n1 minuscule\n1 chiffre");
        } // if
        if (!password.equals(confirmPassword)) {
            setInvalid("Les mots de passe ne correspondent pas");
        } // if
    } // constructeur

} // FormAdminAddValidator