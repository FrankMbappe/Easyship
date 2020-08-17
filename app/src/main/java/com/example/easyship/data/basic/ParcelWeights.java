package com.example.easyship.data.basic;

import com.example.easyship.R;
import com.example.easyship.models.uidesign.SpinnerItem;

import java.util.ArrayList;

public class ParcelWeights implements IBasicData<SpinnerItem> {
    ArrayList<SpinnerItem> mList;

    public ParcelWeights() {
        mList = new ArrayList<>();
        fill();
    }

    @Override
    public ArrayList<SpinnerItem> getAll() {
        return mList;
    }

    @Override
    public void fill() {
        mList.add(new SpinnerItem(R.string.str_approximate, R.drawable.ic_icons8_weight_kg));  // Approximatif
        mList.add(new SpinnerItem(R.string.str_0_500g, R.drawable.ic_icons8_weight_kg));  // 0-500g
        mList.add(new SpinnerItem(R.string.str_500_1kg, R.drawable.ic_icons8_weight_kg));  // 500g-1Kg
        mList.add(new SpinnerItem(R.string.str_1_5kg, R.drawable.ic_icons8_weight_kg));  // 1Kg-5Kg
        mList.add(new SpinnerItem(R.string.str_5_10kg, R.drawable.ic_icons8_weight_kg));  // 5Kg-10Kg
        mList.add(new SpinnerItem(R.string.str_over_10kg, R.drawable.ic_icons8_weight_kg));  // Plus de 10Kg
    }

}
