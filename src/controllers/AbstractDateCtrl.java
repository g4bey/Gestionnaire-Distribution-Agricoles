package controllers;

import java.text.SimpleDateFormat;

public abstract class AbstractDateCtrl {
    protected SimpleDateFormat jour = new SimpleDateFormat("yyyy-MM-dd");
    protected SimpleDateFormat heure = new SimpleDateFormat("HH:mm");
}
