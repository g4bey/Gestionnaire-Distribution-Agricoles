package validForm;

public class FormPsChange extends  FormValidator {
    public FormPsChange(String ps, String comfirmPs) {
        if(!ps.equals(comfirmPs)) {
            setInvalid("Les mots de passe ne sont pas identique.");
        }
    }
}
