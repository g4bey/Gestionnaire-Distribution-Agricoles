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

        /* assertThrows(AdresseInvalide.class, () -> {
            ValidateurAdresse.create("103 rue machin", "37000", "Tours");
        });
        */
        // ValidateurAdresse adresse = ValidateurAdresse.create("103 rue Emile Zola", "37000", "Tours");
        // assertEquals(adresse.getCoordX(), 47.3943051);
        // assertEquals(adresse.getCoordY(), 0.6903615);
    }

    /**
     * Permet de vérifier le format d'une adresse.
     */
    @Test
    @DisplayName("Validation du format d'une adresse")
    public void formatTest() {
        // ValidateurAdresse adresse = ValidateurAdresse.create("103 rue Emile Zola", "37000", "Tours"); 
        // assertEquals(adresse.format(), "103 rue Emile Zola, 37000, Tours");
    }
}