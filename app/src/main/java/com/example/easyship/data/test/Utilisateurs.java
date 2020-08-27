package com.example.easyship.data.test;

import com.example.easyship.R;
import com.example.easyship.data.basic.Adresses;
import com.example.easyship.models.Adresse;
import com.example.easyship.models.Colis;
import com.example.easyship.models.Livraison;
import com.example.easyship.models.Notification;
import com.example.easyship.models.Personne;
import com.example.easyship.models.Utilisateur;

import java.util.ArrayList;
import java.util.Calendar;

public class Utilisateurs {
    ArrayList<Notification> notifications;
    ArrayList<Livraison> livraisons;
    ArrayList<Colis> colis;

    public Utilisateurs(){
        Adresse doualaMakepe = new Adresses().getAdresseParLeQuartier("Makepe");

        colis = new ArrayList<>();
        colis.add(new Colis(
                "ES-COLIS-USER001-001",
                Calendar.getInstance(),
                "Spaghettis et bouteille de miel",
                0,
                1,
                2,
                null,
                "testCommentaires"
        ));
        colis.add(new Colis(
                "ES-COLIS-USER001-002",
                Calendar.getInstance(),
                "Bouteille de vin",
                2,
                1,
                0,
                null,
                "testCommentaires"
        ));

        livraisons = new ArrayList<>();
        livraisons.add(new Livraison(
                "ES-SHIPMENT-USER001-001",
                1,
                Calendar.getInstance(),
                null,
                null,
                -1,
                0,
                new Adresses().getAdresseParLeQuartier("Makepe"),
                null,
                getUtilisateurTest().getId(),
                colis,
                null,
                new Personne("Paul","Kobe", (long) 656895349, doualaMakepe),
                Calendar.getInstance(),
                0,
                "Test"
        ));

    }
    public Utilisateur getUtilisateurTest(){
        return new Utilisateur(
                "KOM II",
                "Samuel",
                (long) 656895348,
                new Adresses().getAdresseParLeQuartier("Makepe"),
                null,
                "ES-USER-0001",
                Calendar.getInstance(),
                "samuelkomii@gmail.com",
                "samkom2020",
                -1,
                0,
                notifications,
                livraisons
        );
    }
}
