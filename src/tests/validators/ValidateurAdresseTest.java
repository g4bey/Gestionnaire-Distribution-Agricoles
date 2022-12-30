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
            ValidateurAdresse.create("8", "Boulevard", "machin", "Amiens", "80000");
        });
        ValidateurAdresse adresse = ValidateurAdresse.create("8", "Boulevard", "du Port", "Amiens", "80000");
        assertEquals(adresse.getCoordX(), "49.897442");
        assertEquals(adresse.getCoordY(), "2.290084");
        assertEquals(adresse.getCoordXY(), "2.290084,49.897442");
    }

    /**
     * Permet de vérifier que demander une adresse déjà vérifiée renvoie le meme objet.
     */
    @Test
    @DisplayName("Renvoie d'une adresse existante")
    public void adresseExistanteTest() throws AdresseInvalideException {
        ValidateurAdresse adresse = ValidateurAdresse.create("8", "Boulevard", "du Port", "Amiens", "80000");
        ValidateurAdresse adresseExistante = ValidateurAdresse.create("8", "Boulevard", "du Port", "Amiens", "80000");
        ValidateurAdresse adressPresqueIdentique = ValidateurAdresse.create("3", "Boulevard", "du Port", "Amiens", "80000");
        assertEquals(adresse.getCoordX(), "49.897442");
        assertEquals(adresse.getCoordY(), "2.290084");
        assertEquals(adresse.getCoordXY(), "2.290084,49.897442");
        assertEquals(adresse, adresseExistante);
        assertNotEquals(adressPresqueIdentique, adresseExistante);
    }

    /**
     * Permet de vérifier le format d'une adresse.
     */
    @Test
    @DisplayName("Validation du format d'une adresse")
    public void formatTest() throws AdresseInvalideException {
        ValidateurAdresse adresse = ValidateurAdresse.create("8", "Boulevard", "du Port", "Amiens", "80000");
        assertEquals(adresse.format(), "8 Boulevard du Port Amiens 80000");
    }
}