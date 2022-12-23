package tests.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import utility.DateManager;

public class DateManagerTest {
    
    static private LocalDate date;
    static private DateTimeFormatter formatter;

    /**
    * Permet de vérifier que l'heure et la date sont correctement convertis en Timestamp
    */
    @Test
    @DisplayName("Validation de la conversion d'une heure et d'une date en Timestamp")
    public void convertToTimestampTest() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date = LocalDate.parse("01-01-2023", formatter);
        Timestamp ts = generateTimestamp("2023-01-01 15:30:00");
        assertEquals(DateManager.convertToTimestamp(date, "15:30"), ts);
        date = LocalDate.parse("2023-01-01");
        assertEquals(DateManager.convertToTimestamp(date, "15:30"), ts);
    }

    /**
    * Permet de vérifier qu'un Timestamp est correctement converti en date LocalDate
    */
    @Test
    @DisplayName("Validation de la conversion d'un Timestamp en sa date LocalDate")
    public void TimestampToLocalDateTest() {
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date = LocalDate.parse("15-11-2023", formatter);
        LocalDate dateComplete = LocalDate.of(2023, 11, 15);
        Timestamp ts = generateTimestamp("2023-11-15 01:00:00");
        assertEquals(DateManager.TimestampToLocalDate(ts), dateComplete);
    }

    /**
    * Permet de vérifier qu'un Timestamp est correctement converti en string de format HH:mm (heures:minutes)
    */
    @Test
    @DisplayName("Validation de la conversion d'un Timestamp en String de format HH:mm")
    public void TimestampToHourString() {
        Timestamp ts = generateTimestamp("2023-11-15 13:46:00");
        assertNotEquals(DateManager.TimestampToHourString(ts), "00:00");
        assertEquals(DateManager.TimestampToHourString(ts), "13:46");
    }

    /**
     * Permet de vérifier qu'un Timestamp est correctement converti en string de format dd:mm:yyyy (jour:mois:année)
     */
    @Test
    @DisplayName("Validation de la conversion d'un Timestamp en String de format dd:mm:yyyy")
    public void TimestampToDateString() {
        Timestamp ts = generateTimestamp("2041-02-05 00:00:00");
        assertNotEquals(DateManager.TimestampToDateString(ts), "5-2-2041");
        assertNotEquals(DateManager.TimestampToDateString(ts), "05-2-2041");
        assertNotEquals(DateManager.TimestampToDateString(ts), "5-02-2041");
        assertEquals(DateManager.TimestampToDateString(ts), "05-02-2041");
    }

    /**
     * Permet de vérifier qu'un Timestamp est correctement converti en LocalTime
     */
    @Test
    @DisplayName("Validation de la conversion d'un Timestamp en LocalTime")
    public void TimestampToLocalTime() {
        Timestamp ts = generateTimestamp("2041-02-05 17:05:00");
        LocalTime time = LocalTime.of(17, 5);
        assertEquals(DateManager.TimestampToLocalTime(ts), time);
        time = LocalTime.of(17, 05);
        assertEquals(DateManager.TimestampToLocalTime(ts), time);
    }

    /**
    * Permet de generer un Timestamp
    * @param dateString String la date et l'heure en chaîne de caractère.
    * @return Timestamp
    */
    public Timestamp generateTimestamp(String dateString) {
        return Timestamp.valueOf(dateString);
    }
}