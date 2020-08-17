package com.example.easyship.models.uidesign;

public class StatusItem {
    private int mName;
    private int mBackgroundColor;
    private int mForegroundColor;

    public StatusItem(int name, int foregroundColor, int backgroundColor) {
        mName = name;
        mForegroundColor = foregroundColor;
        mBackgroundColor = backgroundColor;
    }

    public int getName() {
        return mName;
    }

    public int getForegroundColor() {
        return mForegroundColor;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }
}
