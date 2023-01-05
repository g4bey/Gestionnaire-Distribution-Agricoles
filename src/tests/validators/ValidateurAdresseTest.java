package tests.validators;

import exceptions.AdresseInvalideException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import validator.ValidateurAdresse;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateurAdresseTest {

    /**
     * Permet de vérifier la création d'une adresse.
     */
    @Test
    @DisplayName("Validation de la création d'une adresse valide")
    public void adresseCreationTest() throws AdresseInvalideException {

        assertThrows(AdresseInvalideException.class, () -> {
            ValidateurAdresse.create(
                "8",
                "Boulevard",
                "machin",
                "Amiens",
                "80000"
            ); // create
        }); // assertThrows
        ValidateurAdresse adresse = ValidateurAdresse.create(
            "8",
            "Boulevard",
            "du Port",
            "Amiens",
            "80000"
            ); // create
        assertEquals(adresse.getCoordX(), "49.897442");
        assertEquals(adresse.getCoordY(), "2.290084");
        assertEquals(adresse.getCoordXY(), "2.290084,49.897442");
    } // adresseCreationTest

    /**
     * Permet de vérifier que demander une adresse déjà vérifiée renvoie le même objet.
     */
    @Test
    @DisplayName("Renvoi d'une adresse existante")
    public void adresseExistanteTest() throws AdresseInvalideException {
        ValidateurAdresse adresse = ValidateurAdresse.create(
            "8",
            "Boulevard",
            "du Port",
            "Amiens",
            "80000"
            ); // create
        ValidateurAdresse adresseExistante = ValidateurAdresse.create(
            "8",
            "Boulevard",
            "du Port",
            "Amiens",
            "80000"
            ); // create
        ValidateurAdresse adressePresqueIdentique = ValidateurAdresse.create(
            "3",
            "Boulevard",
            "du Port",
            "Amiens",
            "80000"
            ); // create
        assertEquals(adresse.getCoordX(), "49.897442");
        assertEquals(adresse.getCoordY(), "2.290084");
        assertEquals(adresse.getCoordXY(), "2.290084,49.897442");
        assertEquals(adresse, adresseExistante);
        assertNotEquals(adressePresqueIdentique, adresseExistante);
    } // adresseExistanteTest

    /**
     * Permet de vérifier le format d'une adresse.
     */
    @Test
    @DisplayName("Validation du format d'une adresse")
    public void formatTest() throws AdresseInvalideException {
        ValidateurAdresse adresse = ValidateurAdresse.create(
            "8",
            "Boulevard",
            "du Port",
            "Amiens",
            "80000"
            ); // create
        assertEquals(adresse.format(), "8 Boulevard du Port Amiens 80000");
    } // formatTest

} // ValidateurAdresseTest