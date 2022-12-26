package exceptions;

/**
 * Le trajet généré ne respecte pas les horaires
 */
public class InvalidRouteException extends Exception {
    public InvalidRouteException(String error) {
        super(error);
    }
}
