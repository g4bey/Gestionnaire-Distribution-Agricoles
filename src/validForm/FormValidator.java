package validForm;

/**
 * Lorsque l'on instancie un enfant de formValidator, on passe en paramètres
 * les champs et on invoque setInvalid(reason) pour chaque champ invalide.
 * <p>
 * Ensuite, on pourra récupérer les erreurs avec getErrors();
 */
public abstract class FormValidator {
    protected boolean isValid = true;
    private String errors = "";

    /**
     * Si le formulaire est invalide, FALSE.
     * Si le formulaire est valide, TRUE.
     * 
     * @return Un booléen attestant de la validité du formulaire.
     */
    public boolean isValid() {
        return isValid;
    } // isValid

    /**
     * Invalide le formulaire et appelle appendError pour
     * y ajouter le message d'erreur.
     * 
     * @param reason String Message d'erreur à afficher.
     */
    public void setInvalid(String reason) {
        isValid = false;
        appendError(reason);
    } // setInvalid

    /**
     * Invalide le formulaire.
     */
    public void setInvalid() {
        isValid = false;
    } // setInvalid

    /**
     * On reset le formulaire.
     *
     * @return Les messages d'erreur.
     */
    public String getErrors() {
        isValid = true;
        return errors;
    }

    /**
     * Permet d'ajouter un message d'erreur et de faire les sauts de lignes
     * nécessaires.
     * 
     * @param error L'erreur que l'on doit ajouter dans le message.
     */
    private void appendError(String error) {
        if (!this.errors.isEmpty()) {
            errors = errors + "\n";
        } // if

        errors = errors + error;
    } // appendError

} // FormValidator
