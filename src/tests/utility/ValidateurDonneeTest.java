package tests.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import utility.ValidateurDonnee;

public class ValidateurDonneeTest {

    /*
     * Permet d'assurer que le siret respecte le bon format.
     */
    @Test
    @DisplayName("Validation du siret")
    public void valideSiretTest() {
        assertTrue(ValidateurDonnee.valideSiret("12341234123412"));
        assertFalse(ValidateurDonnee.valideSiret("A2341234123412"));
        assertFalse(ValidateurDonnee.valideSiret("1234123412341A"));
        assertFalse(ValidateurDonnee.valideSiret("123412341234122"));
        assertFalse(ValidateurDonnee.valideSiret("aaaabbbbccccdd"));
    }

    /*
     * Permet d'assurer que le numéro de téléphone suit le bon format.
     * Ici, l'on ne prend en compte que les numéros nationaux.
     */
    @Test
    @DisplayName("Validation du numero de telephone au format francais")
    public void valideTelephoneTest() {
        assertTrue(ValidateurDonnee.valideTelephone("0628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("1628050505"));
        assertFalse(ValidateurDonnee.valideTelephone("0628050A05"));
        assertFalse(ValidateurDonnee.valideTelephone("06280505054"));
        assertFalse(ValidateurDonnee.valideTelephone("0L28050A05"));
    }

    /*
     * Permet d'assurer qu'un poids puisse bien être convertit en double.
     */
    @Test
    @DisplayName("Validation d'un poids sous forme de string.")
    public void validePoidsTest() {
        assertFalse(ValidateurDonnee.validePoids("banane"));
        assertFalse(ValidateurDonnee.validePoids("d43"));
        assertFalse(ValidateurDonnee.validePoids(""));
        assertTrue(ValidateurDonnee.validePoids("42"));
        assertTrue(ValidateurDonnee.validePoids("42.1"));
    }

    /*
     * Permet d'assurer qu'un poids fournit est bien strictement inférieur au
     * maximum fournit..
     */
    @Test
    @DisplayName("Validation d'un poids inferieur à un poids max.")
    public void validePoidsMaxTest() {
        assertTrue(ValidateurDonnee.validePoids("42", 50));
        assertTrue(ValidateurDonnee.validePoids("42", 42));
        assertFalse(ValidateurDonnee.validePoids("42", 41));
    }

    @Test
    @DisplayName("Permet de valider un date")
    public void valideDateTest() {
        fail("Not implemented");
    }

    /**
     * Un nom peut contenir tirets, espaces et apostrophes.
     * Cependant, il ne peut finir ainsi. Un nom ne pas être vide.
     * 
     * L'on definit une taille max dans la fonction de validation.
     */
    @Test
    @DisplayName("Vérification du format des noms, et limite de caracteres.")
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
     * On accept tirets underscore et valeurs alphanumériques.
     * Un pseudo ne peut être vide.
     * 
     * L'on definit une taille max dans la fonction de validation.
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
     * 
     * Elle en compte les formats anciens / recents.
     * Néanmoins, ici, on demandera obligatoirement un tiret entre les groupes,
     * par soucis d'homogénéité.
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
