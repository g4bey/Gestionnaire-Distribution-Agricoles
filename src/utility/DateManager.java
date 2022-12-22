package utility;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Assiste la création de timestamp.
 */
public class DateManager {
    /**
     * Convertit une heure et une date en un timestamp exploitable.
     * <p>
     * @param date LocalDate nécessaire pour créer un timestamp cohérent
     * @param heureString String l'heure au format HH:mm
     * @return un objet java.sql.Timestamp
     */
    public static Timestamp convertToTimestamp(LocalDate date, String heureString) {
        // On parse l'heure et les minutes
        int hr = Integer.parseInt(heureString.substring(0, 2));
        int mn = Integer.parseInt(heureString.substring(3, 6));
        // On construit un objet LocalTime
        LocalTime heure = LocalTime.of(hr, mn);
        // Ce qui nous permet de creer un LocalDateTime
        LocalDateTime dateComplete = LocalDateTime.of(date, heure);
        // qui peut devenir un timestamp
        return Timestamp.valueOf(dateComplete);
    }
}
