package tests.utility;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidateurAdresseTest {
    
    /**
     * Permet de vérifier la création d'une adresse.
     */
    @Test
    @DisplayName("Validation de la création d'une adresse valide")
    public void adresseCreationTest() {

        assertThrows(AdresseInvalide.class, () -> {
            ValidateurAdresse.create("8 Boulevard machin", "80000", "Amiens");
        });
        ValidateurAdresse adresse = ValidateurAdresse.create("8 Boulevard du Port", "80000", "Amiens");
        assertEquals(adresse.getCoordX(), "49.897443");
        assertEquals(adresse.getCoordY(), "2.290084");
        assertEquals(adresse.getCoordXY(), "49.897443,2.290084")
    }

    /**
     * Permet de vérifier le format d'une adresse.
     */
    @Test
    @DisplayName("Validation du format d'une adresse")
    public void formatTest() {
        ValidateurAdresse adresse = ValidateurAdresse.create("8 Boulevard du Port", "80000", "Amiens"); 
        assertEquals(adresse.format(), "8 Boulevard du Port, 80000, Amiens");
    }
}