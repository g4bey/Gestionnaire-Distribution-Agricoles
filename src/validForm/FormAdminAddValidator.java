package validForm;

import validator.ValidateurDonnee;

public class FormAdminAddValidator extends FormValidator {

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
