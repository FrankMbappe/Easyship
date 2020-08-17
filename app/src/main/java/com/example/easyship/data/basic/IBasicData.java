package com.example.easyship.data.basic;

import java.util.ArrayList;

public interface IBasicData<T> {
    void fill();
    ArrayList<T> getAll();
}
