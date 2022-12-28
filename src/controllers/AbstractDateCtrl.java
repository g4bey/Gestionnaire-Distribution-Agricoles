package controllers;

import java.text.SimpleDateFormat;

public abstract class AbstractDateCtrl {
    private static SimpleDateFormat jour = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat heure = new SimpleDateFormat("HH:mm");

    public static SimpleDateFormat getJourFormat() {
        return jour;
    }

    public static SimpleDateFormat getHeureFormat() {
        return heure;
    }
}
