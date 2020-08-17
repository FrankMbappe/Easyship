package com.example.easyship.data.basic;

import com.example.easyship.R;
import com.example.easyship.models.uidesign.SpinnerItem;
import com.example.easyship.models.uidesign.StatusItem;

import java.util.ArrayList;

public class Statuses implements IBasicData<StatusItem> {
    ArrayList<StatusItem> mList;

    public Statuses() {
        mList = new ArrayList<>();
        fill();
    }

    @Override
    public ArrayList<StatusItem> getAll() {
        return mList;
    }

    @Override
    public void fill() {
        mList.add(new StatusItem(R.string.str_not_done, R.color.colorBackShipStatus_0, R.color.colorDefaultText));  // Statut 0 = Initialisée
        mList.add(new StatusItem(R.string.str_in_progress, R.color.colorBackShipStatus_1, R.color.white));  // Statut 1 = En cours
        mList.add(new StatusItem(R.string.str_canceled, R.color.colorBackShipStatus_2, R.color.white));  // Statut 2 = Annulée
        mList.add(new StatusItem(R.string.str_done, R.color.colorBackShipStatus_3, R.color.white));  // Statut 3 = Terminée
    }

}
