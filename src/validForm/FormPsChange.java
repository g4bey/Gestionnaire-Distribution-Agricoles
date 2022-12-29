package validForm;

import validator.ValidateurDonnee;

/**
 * Permet de vérifier le nouveau mot-de-passe.
 * @see controllers.PasswordChangeCtrl
 */
public class FormPsChange extends  FormValidator {

    /**
     * Constructeur du formulaire de changement de mot de passe.
     * @param ps le nouveau mot de passe.
     * @param comfirmPs le mot de passe à confirmer.
     */
    public FormPsChange(String ps, String comfirmPs) {
        if(!ValidateurDonnee.validePassword(ps)) {
            setInvalid("Votre mots de passe doit faire au moins 8 caracteres.");
        }

        if(!ps.equals(comfirmPs)) {
            setInvalid("Les mots de passe ne sont pas identique.");
        }
    }
}
