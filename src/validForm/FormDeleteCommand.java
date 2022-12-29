package validForm;

import modele.Commande;

/**
 * Utilisé lors qu'on supprime une commande.
 * Si une tournée est associée à une commande, elle ne peut etre supprimée.
 * 
 * @see controllers.DeleteCommCtrl
 */
public class FormDeleteCommand extends FormValidator {
    /**
     * Constructeur de FormDeleteCommand.
     * 
     * @param commande la commande à supprimer.
     */
    public FormDeleteCommand(Commande commande) {
        // Il faut qu'elle soit associée à aucune tournée.
        if (commande.getTournee() != null) {
            setInvalid("Cette commande est associée à une tournée");
        }
    }
}
