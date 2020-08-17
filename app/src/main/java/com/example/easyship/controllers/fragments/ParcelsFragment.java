package com.example.easyship.controllers.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyship.R;
import com.example.easyship.controllers.activities.ShipmentStudioActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ParcelsFragment extends Fragment {
    public static final String TAG="ParcelsFragment";
    private Context mContext;
    private FloatingActionButton mFloatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_parcels, container, false);

        mContext = rootView.getContext();
        mFloatingActionButton = rootView.findViewById(R.id.fragment_parcels_floating_action_button);

        init();

        return rootView;
    }

    void init(){
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ShipmentStudioActivity.class));
            }
        });
    }
}