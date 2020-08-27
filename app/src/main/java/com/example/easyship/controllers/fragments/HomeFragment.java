package com.example.easyship.controllers.fragments;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.easyship.R;
import com.example.easyship.application.Easyship;
import com.example.easyship.controllers.activities.ShipmentStudioActivity;
import com.example.easyship.data.test.Utilisateurs;
import com.example.easyship.models.Adresse;
import com.example.easyship.models.Utilisateur;

public class HomeFragment extends Fragment {
    Utilisateur user;
    Context context;

    TextView mAdresse;
    TextView mBtnChanger;
    TextView mMsgSalutations;
    Button mBtnExpedier;
    Button mBtnTrouverColis;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        context = root.getContext();
        user = new Easyship((ContextWrapper) context).getActiveUser();

        binding(root);

        init(user);


        return root;
    }

    void init(Utilisateur user){
        remplissageDesChamps(user);

        mBtnChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtnExpedier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ShipmentStudioActivity.class));
            }
        });

        mBtnTrouverColis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void binding(View root){
        mAdresse = root.findViewById(R.id.fragment_home_location);
        mBtnChanger = root.findViewById(R.id.fragment_home_change_location);
        mMsgSalutations = root.findViewById(R.id.fragment_home_greetings);
        mBtnExpedier = root.findViewById(R.id.fragment_home_send_parcel_btn);
        mBtnTrouverColis = root.findViewById(R.id.fragment_home_find_parcel_btn);
    }

    void remplissageDesChamps(Utilisateur user){
        if(user != null){
            if(user.getAdresse() != null){
                mAdresse.setText(user.getAdresse().toString());
            }

            if(user.getPrenom() != null){
                mMsgSalutations.setText(String.format(context.getString(R.string.fmt_what_s_goin_on_today), user.getPrenom()));
            }
        }
    }
}