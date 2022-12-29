package validForm;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import exceptions.InvalidRouteException;
import modele.Commande;
import modele.Producteur;
import modele.Vehicule;
import utility.DateManager;
import validator.ValidateurTournee;

/**
 * Formulaire d'ajout de tournée.
 * 
 * @see controllers.AddTourCtrl
 */
public class FormAddTourValidator extends FormValidator {
    private Timestamp[] horaires;

    /**
     *
     * @param libelle      le libellé pour identifier la tournée.
     * @param producteur   le producteur associé à la tournée.
     * @param vehicule     le vehicle associé à la tournée.
     * @param commandes    la liste de commande composant la tournée.
     * @param poids        le poids de la tournée
     * @param horaireDebut l'horaire de début de la tournée.
     * @param horaireFin   l'horaire de début de la tournée.
     * @param date         la date de la tournée.
     */
    public FormAddTourValidator(String libelle, Producteur producteur, Vehicule vehicule, ArrayList<Commande> commandes,
            String poids) {
        if (libelle.isEmpty()) {
            setInvalid("Veuillez écrire un libellé !");
            return;
        }
        if (vehicule == null) {
            setInvalid("Veuillez choisir un véhicule !");
            return;
        }
        if (commandes.isEmpty()) {
            setInvalid("Veuillez choisir une commande !");
            return;
        }
        if (!ValidateurTournee.validePoids(vehicule.getPoidsMax(), commandes)) {
            setInvalid("La capacité du véhicule est dépassé !");
        }

        // On vérifie que la commande n'est pas deja dans une commande.
        Optional<Commande> cmd = commandes.stream().filter(commande -> commande.getTournee() != null).findFirst();
        cmd.ifPresent(
                commande -> setInvalid("La commande " + commande.getLibelle() + " est déja dans une tournée.")
        );

        try {
            horaires = ValidateurTournee.calculTournee(commandes, producteur.getGpsProd());
        } catch (IOException | InterruptedException | InvalidRouteException e) {
            setInvalid("La suite de commandes est incorrecte !");
            return;
        }
        if (!ValidateurTournee.valideVehicule(vehicule, horaires[0], horaires[1])) {
            setInvalid("Le véhicule n'est pas disponible pour ce créneau horaire !");
        }
    }

    public Timestamp getHeureDebut() {
        return horaires[0];
    }

    public Timestamp getHeureFin() {
        return horaires[1];
    }
}