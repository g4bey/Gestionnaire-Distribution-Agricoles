package validForm;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modele.Commande;
import modele.Producteur;
import modele.Vehicule;
import utility.DateManager;
import validator.ValidateurTournee;

public class FormAddTourValidator extends FormValidator {
    
    public FormAddTourValidator(String libelle, Producteur producteur, Vehicule vehicule, ArrayList<Commande> commandes, 
    String poids, String horaireDebut, String horaireFin, String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (libelle.isEmpty()) {
            setInvalid("Veuillez écrire un libellé !");
            return;
        }
        if (vehicule == null) {
            setInvalid("Veuillez choisir un véhicule !");
            return;
        }
        if (date.isEmpty()) {
            setInvalid("Veuillez choisir une commande !");
            return;
        }
        if (!ValidateurTournee.valideVehicule(vehicule, 
        DateManager.convertToTimestamp(LocalDate.parse(date, format), horaireDebut), 
        DateManager.convertToTimestamp(LocalDate.parse(date, format), horaireFin))) {
            setInvalid("Le véhicule n'est pas disponible pour ce créneau horaire !");
        }
        if (!ValidateurTournee.validePoids(vehicule.getPoidsMax(), commandes)) {
            setInvalid("La capacité du véhicule est dépassé !");
        }
    }
}