package com.example.easyship.models;

import java.util.Calendar;

public class Notification {
    private int image;
    private int idTexte;
    private Calendar date;

    private Notification(){

    }

    public Notification(int image, int text, Calendar date) {
        this.image = image;
        idTexte = text;
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public int getIdTexte() {
        return idTexte;
    }

    public Calendar getDate() {
        return date;
    }
}
