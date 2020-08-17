package com.example.easyship.models;

public class Adresse {
    String id;
    String pays;
    String ville;
    String quartier;

    public Adresse() {

    }

    public Adresse(String id, String pays, String ville, String quartier) {
        this.id = id;
        this.pays = pays;
        this.ville = ville;
        this.quartier = quartier;
    }

    @Override
    public String toString() {
        return quartier + ", " + ville;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }
}
