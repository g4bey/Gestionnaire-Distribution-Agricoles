package utility;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cette classe permet de valider la coherence des entrées fournies.
 * Il s'agit de s'assurer que les données seront exploitables et homogènes.
 */
public class ValidateurDonnee {

    private static Pattern pattern;
    private static Matcher matcher;

    /*
     * Permet de valider le siret, une chaine de 14 chiffres.
     * 
     * @param siret le siret sous forme de string.
     */
    public static boolean valideSiret(String siret) {
        // Une suite de 14 chiffre uniquement.
        pattern = Pattern.compile("^[0-9]{14}$");
        matcher = pattern.matcher(siret);

        // si la regex match, true.
        return matcher.matches();
    }

    /*
     * Assure que le poids sous forme de string puisse être convertit en double.
     * 
     * @param poids le poids sous forme de string.
     */
    public static boolean validePoids(String poids) {

        // On vérifie que le poids fournit puisse être parsé.
        try {
            Double.valueOf(poids);
        } catch (NumberFormatException e) {
            return false;
        } // end try catch

        // Le poids est bien parsable.
        return true;
    }

    /*
     * Permet de valider le poids avec une contrainte de poids max.
     * On utilise validePoids pour vérifer qu'on puisse bien parser.
     *
     * @param poids le poids sous forme de string.
     * 
     * @param max le poids max en double.
     * 
     * @see validePoids
     */
    public static boolean validePoids(String poids, double max) {
        // Si non parsable, ou poids > max: false, sinon true.
        return !validePoids(poids) || Double.parseDouble(poids) > max ? false : true;
    }

    /**
     * Verifie une date.
     * Si elle est sous format LocalDate, on pourra la parser.
     * <p>
     * @param date LocalDate la date saisi.
     * @return bool
     */
    public static boolean valideDate(LocalDate date) {
        return date != null;
    }

    /**
     * Assure qu'une heure est sous le bon format
     * <p>
     * @param heure l'heure saisie.
     * @return bool
     */
    public static boolean valideHeure(String heure) {
        pattern = Pattern.compile("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
        matcher = pattern.matcher(heure);

        return matcher.matches();
    }

    /*
     * Permet de valider un numero de téléphone francais, +33 compris.
     * 
     * @param heure l'heure sous forme de string.
     */
    public static boolean valideTelephone(String telephone) {
        pattern = Pattern.compile("^(0[0-9]|\\+33[0-9])([0-9][0-9]){4}$");
        matcher = pattern.matcher(telephone);

        // Si la regex match
        return matcher.matches();
    }

    /*
     * @param nom le nom sous forme de string.
     * 
     * @param maxChars la taille maximale du nom.
     */
    public static boolean valideNom(String nom, int maxChars) {
        // Jean val Jean, Jam-bom-beurre, Jean, Jean'ne'mar valide.
        // Accents valide, il doit y avoir une suite apres un tiret
        // un espace ou un apostrophe.
        pattern = Pattern.compile("^([a-zA-ZÀ-ÿ]+((-| |')[a-zA-ZÀ-ÿ]+)*)$");
        matcher = pattern.matcher(nom);

        // Si la regex match & et que le nom est de la bonne taille, true.
        return matcher.matches() && nom.length() <= maxChars ? true : false;
    }

    /*
     * @param pseudonyme le pseudonyme sous forme de string.
     * 
     * @param maxChars la taille maximale du pseudonynme.
     */
    public static boolean validePseudonyme(String pseudonyme, int maxChars) {
        // Kaleb, K3l3b, K7, R2-D2, C3_P0 valide.
        // Kal@b invalide.
        pattern = Pattern.compile("^[0-9a-zA-Z_-]{2,}+$");
        matcher = pattern.matcher(pseudonyme);

        // Si la regex match & et que le pseudo est de la bonne taille, true.
        return matcher.matches() && pseudonyme.length() <= maxChars ? true : false;
    }

    /*
     * Regex trouvée en ligne.
     * https://regex101.com/r/VGjLx1/1
     * 
     * Elle en compte les formats anciens / recents.
     * Néanmoins, ici, on demandera obligatoirement un tiret entre les groupes,
     * par soucis d'homogénéité.
     * 
     * @param immatriculation l'immatriculation sous forme de string.
     */
    public static boolean valideImmatriculation(String immatriculation) {
        String regex = "[A-HJ-NP-TV-Z]{2}[-]"
                + "[0-9]{3}[-][A-HJ-NP-TV-Z]{2}"
                + "|[0-9]{2,4}[-][A-Z]{1,3}[-][0-9]{2}";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(immatriculation);

        return matcher.matches();
    }
}