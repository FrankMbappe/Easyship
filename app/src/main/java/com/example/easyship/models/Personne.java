package com.example.easyship.models;

import android.net.Uri;

public class Personne {
    String nom;
    String prenom;
    Long telephone;
    Adresse adresse;
    String photoUri;

    public Personne() {

    }

    public Personne(String nom, String prenom, Long telephone, Adresse adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public Personne(String nom, String prenom, Long telephone, Adresse adresse, Uri photoUri) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        if(photoUri != null){
            this.photoUri = photoUri.toString();
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomComplet(){
        return prenom + " " + nom;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Uri getPhotoUri() {
        if(photoUri != null){
            return Uri.parse(photoUri);
        }
        return null;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri.toString();
    }
}
