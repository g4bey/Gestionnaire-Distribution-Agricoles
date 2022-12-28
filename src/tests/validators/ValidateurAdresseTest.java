package tests.validators;

import exceptions.AdresseInvalideException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import validator.ValidateurAdresse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateurAdresseTest {

    /**
     * Permet de vérifier la création d'une adresse.
     */
    @Test
    @DisplayName("Validation de la création d'une adresse valide")
    public void adresseCreationTest() throws AdresseInvalideException {

        assertThrows(AdresseInvalideException.class, () -> {
            ValidateurAdresse.create("8", "Boulevard", "machin", "80000", "Amiens");
        });
        ValidateurAdresse adresse = ValidateurAdresse.create("8", "Boulevard", "du Port", "80000", "Amiens");
        assertEquals(adresse.getCoordX(), "49.897442");
        assertEquals(adresse.getCoordY(), "2.290084");
        assertEquals(adresse.getCoordXY(), "2.290084,49.897442");
    }

    /**
     * Permet de vérifier le format d'une adresse.
     */
    @Test
    @DisplayName("Validation du format d'une adresse")
    public void formatTest() throws AdresseInvalideException {
        ValidateurAdresse adresse = ValidateurAdresse.create("8", "Boulevard", "du Port", "80000", "Amiens");
        assertEquals(adresse.format(), "8 Boulevard du Port Amiens 80000");
    }
}