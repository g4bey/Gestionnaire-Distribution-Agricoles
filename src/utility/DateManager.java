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

    /**
     * Convertit un timestamp en sa date sous format LocalDate.
     *
     * @param timestamp l'objet Timestamp.
     * @return un objet LocalDate utilisé par JavaFX DatePicker.
     */
    public static LocalDate TimestampToLocalDate(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();
        return LocalDate.of(ldt.getYear(), ldt.getMonth(), ldt.getDayOfMonth());
    }

    /**
     * Convertit un timestamp en son heure sous forme de string.
     *
     * @param timestamp l'objet Timestamp.
     * @return un string représentant une heure du type HH:mm.
     */
    public static String TimestampToHourString(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();
        return ldt.getHour() + ":" + ldt.getMinute();
    }

    /**
     * Convertit un timestamp en sa date sous forme de string.
     *
     * @param timestamp l'objet Timestamp.
     * @return un string représentant une heure sour la forme JJ-mm-AAAA.
     */
    public static String TimestampToDateString(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();
        return ldt.getDayOfMonth() + "-" + ldt.getMonthValue() + "-" + ldt.getYear();
    }

    /**
     * Convertit un timestamp en un objet LocalTime.
     *
     * @param timestamp l'objet Timestamp.
     * @return un objet LocalTime.
     */
    public static LocalTime TimestampToLocalTime(Timestamp timestamp) {
        LocalDateTime ldt = timestamp.toLocalDateTime();
        return LocalTime.of(ldt.getHour(), ldt.getMinute());
    }
}
