package validForm;

import java.util.ArrayList;
import java.util.Optional;
import modele.Commande;
import modele.Producteur;
import modele.Vehicule;
import validator.ValidateurTournee;

/**
 * Validateur du formulaire pour AddTourCtrl.
 *
 * @see controllers.AddTourCtrl
 */
public class FormAddTourValidator extends FormTourValidator {

    /**
     * Constructeur de FormAddTourValidator
     *
     * @param libelle    Le libellé de la Tournée.
     * @param producteur Le Producteur associé à la Tournée.
     * @param vehicule   Le Véhicule associé à la Tournée.
     * @param commandes  La liste de Commandes composant la Tournée.
     * @param poids      Le poids de la Tournée
     */
    public FormAddTourValidator(String libelle, Producteur producteur, Vehicule vehicule, ArrayList<Commande> commandes,
            String poids) {

        super(libelle, producteur, vehicule, commandes, poids);

        // On affiche les erreurs avant de faire d'autres verifications.
        if (!isValid()) {
            return;
        } // if

        if ( !ValidateurTournee.valideVehicule(vehicule, horaires[0], horaires[1])) {
            setInvalid("Le Véhicule n'est pas disponible pour ce créneau horaire !");
        } // if

        // On vérifie que la Commande n'est pas deja dans une Tournée.
        Optional<Commande> cmd = commandes.stream().filter(commande -> commande.getTournee() != null).findFirst();
        cmd.ifPresent(
                commande -> setInvalid("La Commande " + commande.getLibelle() + " est déja dans une Tournée."));

    } // constructeur

} // FormAddTourValidator