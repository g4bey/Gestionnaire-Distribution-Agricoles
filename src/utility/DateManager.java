package utility;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Assiste la création de Timestamps.
 */
public class DateManager {
    /**
     * Convertit une heure et une date en un Timestamp exploitable.
     * <p>
     * @param date LocalDate nécessaire pour créer un timestamp cohérent
     * @param heureString String L'heure au format HH:mm
     * @return Un objet java.sql.Timestamp
     */
    public static Timestamp convertToTimestamp(LocalDate date, String heureString) {
        // On parse l'heure et les minutes
        int hr = Integer.parseInt(heureString.substring(0, 2));
        int mn = Integer.parseInt(heureString.substring(3, 5));
        // On construit un objet LocalTime
        LocalTime heure = LocalTime.of(hr, mn);
        // Ce qui nous permet de créer un LocalDateTime
        LocalDateTime dateComplete = LocalDateTime.of(date, heure);
        // Qui peut devenir un Timestamp
        return Timestamp.valueOf(dateComplete);
    } // convertToTimestamp

    /**
     * Convertit un Timestamp en sa date sous format LocalDate.
     *
     * @param timestamp L'objet Timestamp.
     * @return Un objet LocalDate utilisé par JavaFX DatePicker.
     */
    public static LocalDate TimestampToLocalDate(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();
        return LocalDate.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth());
    } // TimestampToLocalDate

    /**
     * Convertit un timestamp en son heure sous forme de String.
     *
     * @param timestamp L'objet Timestamp.
     * @return Un string représentant une heure du type HH:mm.
     */
    public static String TimestampToHourString(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("HH:mm");
        return ldt.format(customFormat);
    } // TimestampToHourString

    /**
     * Convertit un Timestamp en sa date sous forme de String.
     *
     * @param timestamp L'objet Timestamp.
     * @return Un string représentant une heure sour la forme JJ-mm-AAAA.
     */
    public static String TimestampToDateString(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return ldt.format(customFormat);
    } // TimestampToDateString

    /**
     * Convertit un Timestamp en un objet LocalTime.
     *
     * @param timestamp L'objet Timestamp.
     * @return Un objet LocalTime.
     */
    public static LocalTime TimestampToLocalTime(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();
        return LocalTime.of(ldt.getHour(), ldt.getMinute());
    } // TimestampToLocalTime

} // DateManager
