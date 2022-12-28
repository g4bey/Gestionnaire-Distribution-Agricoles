package controllers;

import java.text.SimpleDateFormat;

public abstract class AbstractDateCtrl {
    protected static SimpleDateFormat jour = new SimpleDateFormat("yyyy-MM-dd");
    protected static SimpleDateFormat heure = new SimpleDateFormat("HH:mm");
}
