package com.example.easyship.models;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Calendar;

public class Colis {
    String id;
    Calendar dateCreation;
    String nom;
    int type;   // 0= Autres, 1= Documents, 2= Vêtements, 3= Clés, 4= Livres, 5= Bijoux, 6= Appareils électroniques
    int poids;   // 0= Inconnu ou approximatif, 1= 0-500g, 2= 500g-1Kg, 3= 1Kg-2Kg, 4= Plus de 2Kg
    int dimension;   //0= Petit, 1= Moyen, 2= Grand, 3= Très grand
    int idImage;
    String commentaires;

    public Colis() {

    }

    public Colis(String id, Calendar dateCreation, String nom, int type, int poids, int dimension, int idImage, String commentaires) {
        this.id = id;
        this.dateCreation = dateCreation;
        this.nom = nom;
        this.type = type;
        this.poids = poids;
        this.dimension = dimension;
        this.idImage = idImage;
        this.commentaires = commentaires;
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

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }
}
