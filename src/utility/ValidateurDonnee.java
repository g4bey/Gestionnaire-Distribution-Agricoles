package utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Cette classe permet de valider la coherente des entrées fournies.
* Il s'agit d'agit de s'assurer que les données seront exploitable et homogene.
* Ainsi, pour les dates, l'on verifie par exemple qu'elles puissent bien etre parsés.
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
     * Permet de valider le poids.
     * 
     * @param poids le poids sous forme de string.
     */
    public static boolean validePoids(String poids) {

        // On verifie que le poids fournit puisse etre parsé.
        try {
            Double.valueOf(poids); 
        } catch (NumberFormatException e) {
            return false;
        }

        // Le poids est bien parsable.
        return true;
    }

    /*
     * Permet de valider le poids avec une contrainte de poids max.
     *   * 
     * @param poids le poids sous forme de string.
     * @param max le poids max en double.
     */
    public static boolean validePoids(String poids, double max) {
        double poidDouble;

        // On verifie que le poids fournit puisse etre parsé.
        try {
            poidDouble = Double.valueOf(poids); 
        } catch (NumberFormatException e) {
            return false;
        }

        // Si le poid est > a max, false. sinon true.
        return poidDouble > max ? false : true;
    }

    /*
     * Permet de s'arrurer que l'heure puisse etre parsée,
     * et que l'heure de fin est bien apres l'heure de debut.
     * 
     * @param heure l'heure sous forme de string.
     */
    public static boolean valideHeure(String heureDebut, String heureFin) {
        LocalDate dateDebut;
        LocalDate dateFin;

        // Verifions que l'heure fournit puisse etre parsée.
        try{
            dateDebut = LocalDate.parse(heureDebut);
            dateFin = LocalDate.parse(heureFin);
        } catch (DateTimeParseException e) {
            return false;
        }

        // Verifions que les deux heures sont coherentes.
        return dateFin.isAfter(dateDebut);
    }

    /*
     * @param nom le nom sous forme de string.
     * @param maxChars la taille maximale du nom.
     */
    public static boolean valideNom(String nom, int maxChars) {
        // Jean val Jean, Jam-bom-beurre, Jean, Jean'ne'mar valide.
        // Accents valide, il doit y avoir une suite 
        // apres un -, un espace ou un apostrophe.
        // Jean-, Jean' invalide.
        pattern = Pattern.compile("^([a-zA-ZÀ-ÿ]+((-| |')[a-zA-ZÀ-ÿ]+)*)$"); 
        matcher = pattern.matcher(nom);

        // Si la regex match & et que le nom est de la bonne taille, true.
        return matcher.matches() && nom.length() < maxChars ? true : false;
    }

    /*
     * @param pseudonyme le pseudonyme sous forme de string.
     * @param maxChars la taille maximale du pseudonynme.
     */
    public static boolean validePseudonyme(String pseudonyme, int maxChars) {
        // Kaleb, K3l3b, K7, R2-D2, C3_P0 valide.
        // Kal@b invalide.
        pattern = Pattern.compile("^[0-9a-zA-Z_-]{2,}+$"); 
        matcher = pattern.matcher(pseudonyme);

        // Si la regex match & et que le pseudo est de la bonne taille, true.
        return matcher.matches() && pseudonyme.length() < maxChars ? true : false;
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