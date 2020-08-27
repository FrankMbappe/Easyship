package com.example.easyship.models;

import java.util.ArrayList;
import java.util.Calendar;

public class Livraison {
    String uid;
    int id;
    Calendar dateCreation;
    Calendar dateDebut;
    Calendar dateFin;
    int codeValidation;
    int statut; // 0= Initialisée, 1= En cours, 2= Annulée, 3= Terminée
    Adresse origine;
    Adresse destination;
    String uidExpediteur;
    ArrayList<Colis> colis;
    String uidTransporteur;
    Personne destinataire;
    Calendar dateEcheance;
    int prix;
    String details;

    public Livraison() {

    }

    public Livraison(String uid, int id, Calendar dateCreation, Calendar dateDebut, Calendar dateFin, int codeValidation, int statut, Adresse origine, Adresse destination, String uidExpediteur, ArrayList<Colis> colis, String uidTransporteur, Personne destinataire, Calendar dateEcheance, int prix, String details) {
        this.uid = uid;
        this.id = id;
        this.dateCreation = dateCreation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.codeValidation = codeValidation;
        this.statut = statut;
        this.origine = origine;
        this.destination = destination;
        this.uidExpediteur = uidExpediteur;
        this.colis = colis;
        this.uidTransporteur = uidTransporteur;
        this.destinataire = destinataire;
        this.dateEcheance = dateEcheance;
        this.prix = prix;
        this.details = details;
    }

    public void initialiser(){
        statut = 0;
        dateCreation = Calendar.getInstance();
    }
    public void debuter(){
        statut = 1;
        dateDebut = Calendar.getInstance();
    }
    public void annuler(){
        statut = 2;
        dateFin = Calendar.getInstance();
    }
    public void terminer(){
        statut = 3;
        dateFin = Calendar.getInstance();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Calendar getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Calendar getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Calendar getDateFin() {
        return dateFin;
    }

    public void setDateFin(Calendar dateFin) {
        this.dateFin = dateFin;
    }

    public int getCodeValidation() {
        return codeValidation;
    }

    public void setCodeValidation(int codeValidation) {
        this.codeValidation = codeValidation;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public Adresse getOrigine() {
        return origine;
    }

    public void setOrigine(Adresse origine) {
        this.origine = origine;
    }

    public Adresse getDestination() {
        return destination;
    }

    public void setDestination(Adresse destination) {
        this.destination = destination;
    }

    public String getUidExpediteur() {
        return uidExpediteur;
    }

    public void setUidExpediteur(String uidExpediteur) {
        this.uidExpediteur = uidExpediteur;
    }

    public ArrayList<Colis> getColis() {
        return colis;
    }

    public void setColis(ArrayList<Colis> colis) {
        this.colis = colis;
    }

    public String getUidTransporteur() {
        return uidTransporteur;
    }

    public void setUidTransporteur(String uidTransporteur) {
        this.uidTransporteur = uidTransporteur;
    }

    public Personne getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Personne destinataire) {
        this.destinataire = destinataire;
    }

    public Calendar getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Calendar dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
