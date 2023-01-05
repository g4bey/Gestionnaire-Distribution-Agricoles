package validForm;

import modele.Commande;
import modele.Producteur;
import modele.Tournee;
import modele.Vehicule;
import validator.ValidateurTournee;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Validateur de formulaire pour ModifyTourCtrl.
 * 
 * @see controllers.ModifyTourCtrl
 */
public class FormModifyTourValidator extends FormTourValidator {

        /**
         * Constructeur de FormModifyTourValidator.
         *
         * @param libelle    Le libellé pour identifier la Tournée.
         * @param producteur Le Producteur associé à la Tournée.
         * @param vehicule   Le Véhicule associé à la Tournée.
         * @param commandes  La liste de Commandes composant la tournée.
         * @param poids      Le poids de la Tournée.
         */
        public FormModifyTourValidator(String libelle, Producteur producteur, Vehicule vehicule,
                                       ArrayList<Commande> commandes,
                                       String poids, Tournee tournee) {
                super(libelle, producteur, vehicule, commandes, poids);

                // On affiche les erreurs avant de faire d'autres verifications.
                if (!isValid()) {
                        return;
                } // if

                // Si le Véhicule n'a pas changé, il ne faut pas revérifier.
                if (!tournee.getVehicule().equals(vehicule) && !ValidateurTournee.valideVehicule(vehicule, horaires[0], horaires[1])) {
                        setInvalid("Le véhicule n'est pas disponible pour ce créneau horaire !");
                } // if

                // On vérifie que la Commande n'est pas déjà dans une Tournée autre que celle-ci
                Optional<Commande> cmd = commandes.stream().filter(commande -> commande.getTournee() != null
                                && commande.getTournee().getIdTournee() != tournee.getIdTournee()).findFirst();
                cmd.ifPresent(
                                commande -> setInvalid("La commande " + commande.getLibelle()
                                                + " est déja dans une tournée."));
        } // constructeur

} // FormModifyTourValidator