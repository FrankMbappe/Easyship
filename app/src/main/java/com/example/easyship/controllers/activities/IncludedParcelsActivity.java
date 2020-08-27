package com.example.easyship.controllers.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.easyship.R;
import com.example.easyship.controllers.adapters.ListParcelRVAdapter;
import com.example.easyship.models.Colis;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class IncludedParcelsActivity extends AppCompatActivity {
    public static final String TAG = "IncludedParcelsActivity";
    public static final String EXTRA_LISTE_COLIS = "EXTRA_LISTE_COLIS";
    public static final int REQ_LAUNCH_PARCEL_STUDIO_ACTIVITY = 2;
    private ArrayList<Colis> extraListeColis;

    private ImageButton mBtnFermer;
    private RecyclerView mRecyclerView;
    private LinearLayout mIllustrationVide;
    private FloatingActionButton mBtnAjouterColis;
    private ListParcelRVAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_included_parcels);

        binding();
        gestionDesExtras();
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult(): La méhode a été appelée");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_LAUNCH_PARCEL_STUDIO_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    Log.d(TAG, "onActivityResult(): L'activité a reçu un résultat non nul");

                    Gson gson = new GsonBuilder().create();
                    String jsonResult = data.getStringExtra(ParcelStudioActivity.EXTRA_COLIS);

                    // Une fois la chaîne en Json convertie en colis, je met à jour l'interface
                    Colis colis = gson.fromJson(jsonResult, Colis.class);
                    if(extraListeColis.contains(colis)){
                        extraListeColis.set(extraListeColis.indexOf(colis), colis);
                    }else {
                        extraListeColis.add(colis);
                    }
                    remplissageDesChamps(extraListeColis);
                }
            }
            if(resultCode == Activity.RESULT_CANCELED){
                Log.d(TAG, "onActivityResult(): Le résultat a été annulé.");
            }
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed(): La méthode a été appelée.");
        Intent returnIntent = new Intent();

        Gson gson = new GsonBuilder().create();
        returnIntent.putExtra(EXTRA_LISTE_COLIS, gson.toJson(extraListeColis));
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    void binding(){
        mBtnFermer = findViewById(R.id.activity_included_parcels_action_bar_close_btn);
        mRecyclerView = findViewById(R.id.activity_included_parcels_recyclerview);
        mBtnAjouterColis = findViewById(R.id.activity_included_parcels_floating_action_button);
        mIllustrationVide = findViewById(R.id.activity_included_parcels_empty_layout);
    }

    private void init() {
        mBtnFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBtnAjouterColis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncludedParcelsActivity.this, ParcelStudioActivity.class);
                startActivityForResult(intent, REQ_LAUNCH_PARCEL_STUDIO_ACTIVITY);
            }
        });
    }

    private void remplissageDesChamps(ArrayList<Colis> listeColis){
        if(listeColis.size() > 0){
            mIllustrationVide.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            mAdapter = new ListParcelRVAdapter(this, listeColis);
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else {
            mIllustrationVide.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    private void gestionDesExtras(){
        Gson gson = new GsonBuilder().create();
        String jsonExtraListeColis = getIntent().getStringExtra(EXTRA_LISTE_COLIS);
        if(jsonExtraListeColis != null){
            Log.d(TAG, "gestionDesExtras() : Un extra a été inclu.");
            Type typeListeColis = new TypeToken<ArrayList<Colis>>(){}.getType();
            extraListeColis = gson.fromJson(jsonExtraListeColis, typeListeColis);
        }
        if(extraListeColis == null){
            Log.d(TAG, "gestionDesExtras() : La liste des colis est nulle.");
            extraListeColis = new ArrayList<>();
        }
        remplissageDesChamps(extraListeColis);
    }
}