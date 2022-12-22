package tests.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import utility.ValidateurDonnee;

public class ValidateurDonneeTest {

    /*
     * Permet d'assurer que le SIRET respecte le bon format.
     */
    @Test
    @DisplayName("Validation du SIRET")
    public void valideSiretTest() {
        assertTrue(ValidateurDonnee.valideSiret("12341234123412"));
        assertFalse(ValidateurDonnee.valideSiret("A2341234123412"));
        assertFalse(ValidateurDonnee.valideSiret("1234123412341A"));
        assertFalse(ValidateurDonnee.valideSiret("123412341234122"));
        assertFalse(ValidateurDonnee.valideSiret("aaaabbbbccccdd"));
    }

    /*
     * Permet d'assurer que le numéro de téléphone suit le bon format.
     * Ici, on ne prend en compte que les numéros nationaux, +33 compris.
     */
    @Test
    @DisplayName("Validation du numéro de téléphone au format français")
    public void valideTelephoneTest() {
        assertTrue(ValidateurDonnee.valideTelephone("0628050505"));
        assertTrue(ValidateurDonnee.valideTelephone("+33628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("+333628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("+3628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("+33A628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("#33628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("33628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("1628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("0628050A05"));
        assertFalse(ValidateurDonnee.valideTelephone("06280505054"));
        assertFalse(ValidateurDonnee.valideTelephone("0L28050A05"));
    }

    /*
     * Permet d'assurer qu'un poids puisse bien être converti en double.
     */
    @Test
    @DisplayName("Validation d'un poids sous forme de String.")
    public void validePoidsTest() {
        assertFalse(ValidateurDonnee.validePoids("banane"));
        assertFalse(ValidateurDonnee.validePoids("d43"));
        assertFalse(ValidateurDonnee.validePoids(""));
        assertTrue(ValidateurDonnee.validePoids("42"));
        assertTrue(ValidateurDonnee.validePoids("42.1"));
    }

    /*
     * Permet d'assurer qu'un poids fourni est bien strictement inférieur au
     * maximum fourni...
     */
    @Test
    @DisplayName("Validation d'un poids inférieur à un poids max.")
    public void validePoidsMaxTest() {
        assertTrue(ValidateurDonnee.validePoids("42", 50));
        assertTrue(ValidateurDonnee.validePoids("42", 42));
        assertFalse(ValidateurDonnee.validePoids("42", 41));
    }

    /**
     * Permet de valider le format d'une heure sous forme de String.
     * L'on fera le choix que minuit est 00:00 et non 24:00.
     */
    @Test
    @DisplayName("Permet de valider une heure")
    public void valideHeureTest() {
        assertTrue(ValidateurDonnee.valideHeure("00:00"));
        assertTrue(ValidateurDonnee.valideHeure("01:00"));
        assertTrue(ValidateurDonnee.valideHeure("11:00"));
        assertTrue(ValidateurDonnee.valideHeure("21:00"));
        assertTrue(ValidateurDonnee.valideHeure("22:00"));
        assertTrue(ValidateurDonnee.valideHeure("22:00"));
        assertTrue(ValidateurDonnee.valideHeure("23:00"));
        assertTrue(ValidateurDonnee.valideHeure("01:59"));
        assertFalse(ValidateurDonnee.valideHeure("01:60"));
        assertFalse(ValidateurDonnee.valideHeure("24:00"));
        assertFalse(ValidateurDonnee.valideHeure("01:5"));
        assertFalse(ValidateurDonnee.valideHeure("1:50"));
        assertFalse(ValidateurDonnee.valideHeure("0150"));
        assertFalse(ValidateurDonnee.valideHeure("0A:50"));
        assertFalse(ValidateurDonnee.valideHeure("0A:5A"));
        assertFalse(ValidateurDonnee.valideHeure("A23:00"));
        assertFalse(ValidateurDonnee.valideHeure("23:00A"));
    }

    /**
     * Un nom peut contenir tirets, espaces et apostrophes.
     * Cependant, il ne peut finir ainsi. Un nom ne pas être vide.
     * <p>
     * On définit une taille maximum dans la fonction de validation.
     */
    @Test
    @DisplayName("Vérification du format des noms, et limite de caractères.")
    public void valideNomTest() {
        assertTrue(ValidateurDonnee.valideNom("O", 15));
        assertTrue(ValidateurDonnee.valideNom("Jéâêîôûàèùan", 50));
        assertTrue(ValidateurDonnee.valideNom("Jean-Euclide", 50));
        assertTrue(ValidateurDonnee.valideNom("Jean'ne'mar", 50));
        assertTrue(ValidateurDonnee.valideNom("Jean-bon-beurre", 50));
        assertTrue(ValidateurDonnee.valideNom("SixSix", 6));
        assertFalse(ValidateurDonnee.valideNom("SixSix-", 15));
        assertFalse(ValidateurDonnee.valideNom("SixSix ", 15));
        assertFalse(ValidateurDonnee.valideNom("SixSix'", 15));
        assertFalse(ValidateurDonnee.valideNom("", 15));
        assertFalse(ValidateurDonnee.valideNom("Jean-bon-beurre", 1));
    }

    /**
     * Validation d'un pseudonyme.
     * On accepte tirets, underscore et valeurs alphanumériques.
     * Un pseudo ne peut être vide.
     * <p>
     * L'on définit une taille maximum dans la fonction de validation.
     */
    @Test
    @DisplayName("Validation d'un pseudonyme")
    public void validePseudonymeTest() {
        assertFalse(ValidateurDonnee.validePseudonyme("Kaleb", 2));
        assertFalse(ValidateurDonnee.validePseudonyme("K@leb", 15));
        assertTrue(ValidateurDonnee.validePseudonyme("K4leb_4-ko", 15));
        assertTrue(ValidateurDonnee.validePseudonyme("Kaleb", 5));
        assertFalse(ValidateurDonnee.validePseudonyme("", 5));
    }

    /**
     * Regex trouvée en ligne.
     * https://regex101.com/r/VGjLx1/1
     * <p>
     * Elle prend en compte les formats anciens / recents.
     * Néanmoins, ici, on demandera obligatoirement un tiret entre les groupes,
     * par souci d'homogénéité.
     */
    @Test
    @DisplayName("Validation Immatricuation")
    public void valideImmatriculationTest() {
        assertFalse(ValidateurDonnee.valideImmatriculation("3123AA11"));
        assertFalse(ValidateurDonnee.valideImmatriculation("3123 AA 11"));
        assertFalse(ValidateurDonnee.valideImmatriculation("AZ-123-BU"));
        assertFalse(ValidateurDonnee.valideImmatriculation("AI-123-BB"));
        assertFalse(ValidateurDonnee.valideImmatriculation("AU-123-BB"));
        assertTrue(ValidateurDonnee.valideImmatriculation("3123-AA-11"));
        assertTrue(ValidateurDonnee.valideImmatriculation("8987-VG-33"));
        assertTrue(ValidateurDonnee.valideImmatriculation("AA-222-BB"));
    }
}
