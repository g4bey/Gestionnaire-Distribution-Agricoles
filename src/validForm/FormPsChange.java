package validForm;

import validator.ValidateurDonnee;

/**
 * Validateur de formulaire pour PasswordChangeCtrl.
 * Permet de vérifier le nouveau mot de passe.
 * 
 * @see controllers.PasswordChangeCtrl
 */
public class FormPsChange extends FormValidator {

    /**
     * Constructeur de FormPsChange.
     * 
     * @param ps        Le nouveau mot de passe.
     * @param comfirmPs Le mot de passe à confirmer.
     */
    public FormPsChange(String ps, String comfirmPs) {
        if (!ValidateurDonnee.validePassword(ps)) {
            setInvalid(
                    "Le mot de passe ne répond pas aux exigences minimales de sécurité :\n9 caractères\n1 caractère spécial\n1 majuscule\n1 minuscule\n1 chiffre");
        } // if

        if (!ps.equals(comfirmPs)) {
            setInvalid("Les mots de passe ne correspondent pas.");
        } // if
    } // constructeur

} // FormPsChange
