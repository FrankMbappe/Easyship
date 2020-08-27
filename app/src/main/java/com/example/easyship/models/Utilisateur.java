package com.example.easyship.models;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Calendar;

public class Utilisateur extends Personne {
    private String id;
    private Calendar date;
    private String email;
    private String motDePasse;
    private int noteGenerale; // Moyenne des notes attribuées
    private int caps; // Le nombre de livraisons effectuées à partir de l'application
    private ArrayList<Notification> notifications;
    private ArrayList<Livraison> operations;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, Long telephone, Adresse adresse, String id, Calendar date, String email, String motDePasse, int noteGenerale, int caps, ArrayList<Notification> notifications, ArrayList<Livraison> operations) {
        super(nom, prenom, telephone, adresse);
        this.id = id;
        this.date = date;
        this.email = email;
        this.motDePasse = motDePasse;
        this.noteGenerale = noteGenerale;
        this.caps = caps;
        this.notifications = notifications;
        this.operations = operations;
    }

    public Utilisateur(String nom, String prenom, Long telephone, Adresse adresse, Uri photo, String id, Calendar date, String email, String motDePasse, int noteGenerale, int caps, ArrayList<Notification> notifications, ArrayList<Livraison> operations) {
        super(nom, prenom, telephone, adresse, photo);
        this.id = id;
        this.date = date;
        this.email = email;
        this.motDePasse = motDePasse;
        this.noteGenerale = noteGenerale;
        this.caps = caps;
        this.notifications = notifications;
        this.operations = operations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getNoteGenerale() {
        return noteGenerale;
    }

    public void setNoteGenerale(int noteGenerale) {
        this.noteGenerale = noteGenerale;
    }

    public int getCaps() {
        return caps;
    }

    public void setCaps(int caps) {
        this.caps = caps;
    }
}
