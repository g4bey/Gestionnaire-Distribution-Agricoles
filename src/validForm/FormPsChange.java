package validForm;

import validator.ValidateurDonnee;

public class FormPsChange extends  FormValidator {
    public FormPsChange(String ps, String comfirmPs) {
        if(ValidateurDonnee.validePassword(ps)) {
            setInvalid("Votre mots de passe doit faire au moins 8 caracteres.");
        }

        if(!ps.equals(comfirmPs)) {
            setInvalid("Les mots de passe ne sont pas identique.");
        }
    }
}
