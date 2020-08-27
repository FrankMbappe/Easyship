package com.example.easyship.controllers.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.easyship.R;
import com.example.easyship.controllers.activities.ShipmentStudioActivity;
import com.example.easyship.controllers.adapters.ParcelRVAdapter;
import com.example.easyship.models.Livraison;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ParcelsFragment extends Fragment {
    public static final String TAG = "ParcelsFragment";
    private Context context;
    private ArrayList<Livraison> livraisons;
    private int rootViewWidth;

    private FloatingActionButton mFloatingActionButton;
    private RecyclerView mRecyclerView;
    private LinearLayout mLayoutPrincipal;
    private LinearLayout mIllustrationVide;
    private ParcelRVAdapter mAdapter;

    public ParcelsFragment(){

    }

    public ParcelsFragment(ArrayList<Livraison> livraisons){
        super();
        this.livraisons = livraisons;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_parcels, container, false);

        context = root.getContext();
        binding(root);

        init(livraisons);

        //rootViewWidth = root.getWidth();

        return root;
    }

    void binding(View root){
        mFloatingActionButton = root.findViewById(R.id.fragment_parcels_floating_action_button);
        mRecyclerView = root.findViewById(R.id.fragment_parcels_recyclerview);
        mIllustrationVide = root.findViewById(R.id.fragment_parcels_empty_layout);
        mLayoutPrincipal = root.findViewById(R.id.fragment_parcels_main_layout);
    }

    void init(ArrayList<Livraison> livraisons){
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ShipmentStudioActivity.class));
            }
        });

        remplissageDesChamps(livraisons);
    }

    void remplissageDesChamps(ArrayList<Livraison> listeLivraisons){
        if(listeLivraisons != null && listeLivraisons.size() > 0){
            mIllustrationVide.setVisibility(View.GONE);
            mLayoutPrincipal.setVisibility(View.VISIBLE);

            mAdapter = new ParcelRVAdapter(context, listeLivraisons);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            //mRecyclerView.setLayoutManager(new GridLayoutManager(context, calculerNbreDeColonnes(context, rootViewWidth)));
        }
        else {
            mIllustrationVide.setVisibility(View.VISIBLE);
            mLayoutPrincipal.setVisibility(View.GONE);
        }
    }

//    int calculerNbreDeColonnes(Context context, float columnWidthDp) { // For example columnWidthdp=180
//        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
//        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
//        return noOfColumns;
//    }
}