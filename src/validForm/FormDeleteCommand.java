package validForm;

import modele.Commande;

/**
 * Validateur de formulaire pour DeleteCommCtrl
 * Utilisé lors qu'on supprime une Commande.
 * Si la Commande est associée à une Tournée, elle ne peut être supprimée.
 * 
 * @see controllers.DeleteCommCtrl
 */
public class FormDeleteCommand extends FormValidator {
    /**
     * Constructeur de FormDeleteCommand.
     * 
     * @param commande La commande à supprimer.
     */
    public FormDeleteCommand(Commande commande) {
        // Il faut qu'elle ne soit associée à aucune tournée.
        if (commande.getTournee() != null) {
            setInvalid("Impossible de supprimer une Commande associée à une Tournée");
        } // if
    } // constructeur

} // FormDeleteCommand
