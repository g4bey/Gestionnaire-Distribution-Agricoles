package validForm;

import modele.Commande;
import modele.Producteur;
import modele.Tournee;
import modele.Vehicule;
import validator.ValidateurTournee;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Formulaire d'ajout de tournée.
 * 
 * @see controllers.ModifyTourCtrl
 */
public class FormModifyTourValidator extends FormTourValidator {
        /**
         *
         * @param libelle    le libellé pour identifier la tournée.
         * @param producteur le producteur associé à la tournée.
         * @param vehicule   le vehicle associé à la tournée.
         * @param commandes  la liste de commande composant la tournée.
         * @param poids      le poids de la tournée
         */
        public FormModifyTourValidator(String libelle, Producteur producteur, Vehicule vehicule,
                                       ArrayList<Commande> commandes,
                                       String poids, Tournee tournee) {
                super(libelle, producteur, vehicule, commandes, poids);

                // Si le vehicle n'a pas changé, il ne faut pas revérifier.
                if (!tournee.getVehicule().equals(vehicule) && !ValidateurTournee.valideVehicule(vehicule, horaires[0], horaires[1])) {
                        setInvalid("Le véhicule n'est pas disponible pour ce créneau horaire !");
                }

                // On vérifie que la commande n'est pas deja dans une tournée autre que celle
                // dans laquelle elle est deja.
                Optional<Commande> cmd = commandes.stream().filter(commande -> commande.getTournee() != null
                                && commande.getTournee().getIdTournee() != tournee.getIdTournee()).findFirst();
                cmd.ifPresent(
                                commande -> setInvalid("La commande " + commande.getLibelle()
                                                + " est déja dans une tournée."));
        }
}