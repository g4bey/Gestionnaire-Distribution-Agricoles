package validForm;

import java.util.ArrayList;
import java.util.Optional;

import modele.Commande;
import modele.Producteur;
import modele.Vehicule;

/**
 * Formulaire d'ajout de tournée.
 * 
 * @see controllers.AddTourCtrl
 */
public class FormAddTourValidator extends FormTourValidator {
    /**
     *
     * @param libelle    le libellé pour identifier la tournée.
     * @param producteur le producteur associé à la tournée.
     * @param vehicule   le vehicle associé à la tournée.
     * @param commandes  la liste de commande composant la tournée.
     * @param poids      le poids de la tournée
     */
    public FormAddTourValidator(String libelle, Producteur producteur, Vehicule vehicule, ArrayList<Commande> commandes,
            String poids) {

        super(libelle, producteur, vehicule, commandes, poids);

        // On vérifie que la commande n'est pas deja dans une tournee.
        Optional<Commande> cmd = commandes.stream().filter(commande -> commande.getTournee() != null).findFirst();
        cmd.ifPresent(
                commande -> setInvalid("La commande " + commande.getLibelle() + " est déja dans une tournée."));

    }
}