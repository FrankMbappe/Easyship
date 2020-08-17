package com.example.easyship.models.uidesign;

public class SpinnerItem {
    private int mName;
    private int mImage;

    public SpinnerItem(int name, int image) {
        mName = name;
        mImage = image;
    }

    public int getName() {
        return mName;
    }

    public int getImage() {
        return mImage;
    }
}
