package com.example.easyship.data.basic;

import com.example.easyship.models.Adresse;

import java.util.ArrayList;

public class Adresses implements IBasicData<Adresse> {
    ArrayList<Adresse> mList;

    public Adresses() {
        mList = new ArrayList<>();
        fill();
    }

    @Override
    public void fill() {
        mList.add(new Adresse("ES-ADDRESS-0001", "Cameroun", "Douala", "Logpom"));
        mList.add(new Adresse("ES-ADDRESS-0002", "Cameroun", "Douala", "Yassa"));
        mList.add(new Adresse("ES-ADDRESS-0003", "Cameroun", "Douala", "Logbessou"));
        mList.add(new Adresse("ES-ADDRESS-0004", "Cameroun", "Douala", "Makepe"));
    }

    @Override
    public ArrayList<Adresse> getAll() {
        return mList;
    }

    public Adresse getAdresseParLeQuartier(String nomQuartier){
        for(Adresse adresse : mList){
            if(adresse.getQuartier().toLowerCase().equals(nomQuartier.toLowerCase())){
                return adresse;
            }
        }
        return null;
    }
}
