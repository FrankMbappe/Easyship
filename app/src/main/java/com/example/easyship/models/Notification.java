package com.example.easyship.models;

import android.net.Uri;

import java.util.Calendar;

public class Notification {
    private String image;
    private String texte;
    private Calendar date;

    private Notification(){

    }

    public Notification(Uri image, String texte, Calendar date) {
        this.image = image.toString();
        this.texte = texte;
        this.date = date;
    }

    public Uri getImage() {
        if(image != null){
            return Uri.parse(image);
        }
        return null;
    }

    public String getTexte() {
        return texte;
    }

    public Calendar getDate() {
        return date;
    }
}
