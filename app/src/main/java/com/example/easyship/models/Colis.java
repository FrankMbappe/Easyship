package com.example.easyship.models;

import android.net.Uri;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class Colis {
    String id;
    Calendar dateCreation;
    String nom;
    int type;   // 0= Autres, 1= Documents, 2= Vêtements, 3= Clés, 4= Livres, 5= Bijoux, 6= Appareils électroniques
    int poids;   // 0= Inconnu ou approximatif, 1= 0-500g, 2= 500g-1Kg, 3= 1Kg-2Kg, 4= Plus de 2Kg
    int dimension;   //0= Petit, 1= Moyen, 2= Grand, 3= Très grand
    String photoUri;
    String commentaires;

    public Colis() {

    }

    public Colis(String id, Calendar dateCreation, String nom, int type, int poids, int dimension, Uri photoUri, String commentaires) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.nom = nom;
        this.type = type;
        this.poids = poids;
        this.dimension = dimension;
        if(photoUri != null){
            this.photoUri = photoUri.toString();
        }
        this.commentaires = commentaires;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj != null && obj.getClass() == Colis.class){
            return id.toLowerCase().equals( ((Colis) obj).getId().toLowerCase() );
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
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

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }
}
