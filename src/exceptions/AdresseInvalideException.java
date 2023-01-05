package exceptions;

/**
 * L'adresse postale n'est pas valide.
 */
public class AdresseInvalideException extends Exception {
    public AdresseInvalideException(String error) {
        super(error);
    } // constructeur

} // AdresseInvalideException
