package com.example.easyship.data.basic;

import com.example.easyship.R;
import com.example.easyship.models.uidesign.SpinnerItem;

import java.util.ArrayList;

public class ParcelDimensions implements IBasicData<SpinnerItem> {
    ArrayList<SpinnerItem> mList;

    public ParcelDimensions() {
        mList = new ArrayList<>();
        fill();
    }

    @Override
    public ArrayList<SpinnerItem> getAll() {
        return mList;
    }

    @Override
    public void fill() {
        mList.add(new SpinnerItem(R.string.str_small, R.drawable.ic_icons8_resize_vertical));  // Petit
        mList.add(new SpinnerItem(R.string.str_medium, R.drawable.ic_icons8_resize_vertical));  // Moyen
        mList.add(new SpinnerItem(R.string.str_large, R.drawable.ic_icons8_resize_vertical));  // Grand
        mList.add(new SpinnerItem(R.string.str_very_large, R.drawable.ic_icons8_resize_vertical));  // Très grand
    }

}
