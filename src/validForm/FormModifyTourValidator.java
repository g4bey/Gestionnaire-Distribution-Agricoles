package validForm;

import exceptions.InvalidRouteException;
import modele.Commande;
import modele.Producteur;
import modele.Vehicule;
import validator.ValidateurTournee;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Formulaire d'ajout de tournée.
 * 
 * @see controllers.ModifyTourCtrl
 */
public class FormModifyTourValidator extends FormTourValidator {
    private Timestamp[] horaires;

    /**
     *
     * @param libelle      le libellé pour identifier la tournée.
     * @param producteur   le producteur associé à la tournée.
     * @param vehicule     le vehicle associé à la tournée.
     * @param commandes    la liste de commande composant la tournée.
     * @param poids        le poids de la tournée
     */
    public FormModifyTourValidator(String libelle, Producteur producteur, Vehicule vehicule, ArrayList<Commande> commandes,
                                   String poids, int tourneeId) {
        super(libelle, producteur, vehicule, commandes, poids);

        // On vérifie que la commande n'est pas deja dans une tournée autre que celle dans laquelle elle est deja.
        Optional<Commande> cmd = commandes.stream().filter(commande -> commande.getTournee()
                != null && commande.getTournee().getIdTournee() != tourneeId
        ).findFirst();
        cmd.ifPresent(
                commande -> setInvalid("La commande " + commande.getLibelle() + " est déja dans une tournée.")
        );
    }
}