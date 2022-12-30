package validForm;

/**
 * Lorsque l'on instancie un enfant de formValidator, l'on passe en parameters
 * les champs,
 * et on invoque setInvalid(reason) pour chaque champs invalide.
 * <p>
 * Ensuite, l'on pourra récupérer les erreurs avec getErrors();
 */
public abstract class FormValidator {
    protected boolean isValid = true;
    private String errors = "";

    /**
     * Si le formulaire est invalide, FALSE.
     * Si le formulaire est valide, TRUE.
     * 
     * @return boolean attestant de la validité du formulaire.
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Rend le formulaire invalide et appel appendError pour
     * y mettre le message d'erreur.
     * 
     * @param reason String message d'erreur à afficher.
     */
    public void setInvalid(String reason) {
        isValid = false;
        appendError(reason);
    }

    /**
     * Rend le formulaire invalide.
     */
    public void setInvalid() {
        isValid = false;
    }

    /**
     * @return on reset le formulaire.
     */
    public String getErrors() {
        isValid = true;
        return errors;
    }

    /**
     * Permet d'ajouter un message d'erreur et de faire les sauts de lignes
     * nécessaires.
     * 
     * @param error l'erreur que l'on doit ajouter dans le message.
     */
    private void appendError(String error) {
        if (!this.errors.isEmpty()) {
            errors = errors + "\n";
        }

        errors = errors + error;
    }
}
