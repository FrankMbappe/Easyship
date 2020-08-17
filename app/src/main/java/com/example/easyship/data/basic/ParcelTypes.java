package com.example.easyship.data.basic;

import com.example.easyship.R;
import com.example.easyship.models.uidesign.SpinnerItem;

import java.util.ArrayList;

public class ParcelTypes implements IBasicData<SpinnerItem> {
    ArrayList<SpinnerItem> mList;

    public ParcelTypes() {
        mList = new ArrayList<>();
        fill();
    }

    @Override
    public ArrayList<SpinnerItem> getAll() {
        return mList;
    }

    @Override
    public void fill() {
        mList.add(new SpinnerItem(R.string.str_others, R.drawable.ic_icons8_more));  // Autres
        mList.add(new SpinnerItem(R.string.str_documents, R.drawable.ic_icons8_document));  // Documents
        mList.add(new SpinnerItem(R.string.str_clothes, R.drawable.ic_icons8_t_shirt));  // Vêtements
        mList.add(new SpinnerItem(R.string.str_books, R.drawable.ic_icons8_books));  // Livres
        mList.add(new SpinnerItem(R.string.str_jewels, R.drawable.ic_icons8_diamond));  // Bijoux
        mList.add(new SpinnerItem(R.string.str_keys, R.drawable.ic_icons8_key));  // Clés
        mList.add(new SpinnerItem(R.string.str_electronic_appliances, R.drawable.ic_icons8_tv));  // Appareils électroniques
    }
}
