package com.example.easyship.controllers.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easyship.R;
import com.example.easyship.controllers.adapters.SpinnerItemAdapter;
import com.example.easyship.data.basic.ParcelDimensions;
import com.example.easyship.data.basic.ParcelTypes;
import com.example.easyship.data.basic.ParcelWeights;
import com.example.easyship.models.Colis;
import com.example.easyship.models.uidesign.SpinnerItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ParcelStudioActivity extends AppCompatActivity {
    public static final String TAG = "ParcelStudioActivity";
    public static final String EXTRA_COLIS = "EXTRA_COLIS";
    private Colis extraColis;

    private ImageButton mBtnFermer;
    private ImageButton mBtnMore;
    private ImageButton mBtnAjouterPhoto;
    private RecyclerView mPhotosRecyclerview;
    private TextView mTxtNom;
    private TextView mTxtCommentaires;
    private Spinner mSpinnerType;
    private Spinner mSpinnerWeight;
    private Spinner mSpinnerDimension;
    private Button mBtnEnregistrer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel_studio);

        binding();
        gestionDesExtras();
        init();
    }

    void binding(){
        mBtnFermer = findViewById(R.id.activity_add_parcel_action_bar_close_btn);
        mBtnMore = findViewById(R.id.activity_add_parcel_action_bar_more_btn);
        mBtnAjouterPhoto = findViewById(R.id.activity_add_parcel_add_photo_btn);
        mPhotosRecyclerview = findViewById(R.id.activity_add_parcel_photo_recyclerview);
        mTxtNom = findViewById(R.id.activity_add_parcel_name_txt);
        mTxtCommentaires = findViewById(R.id.activity_add_parcel_comments_txt);
        mSpinnerType = findViewById(R.id.activity_add_parcel_type_spinner);
        mSpinnerWeight = findViewById(R.id.activity_add_parcel_weight_spinner);
        mSpinnerDimension = findViewById(R.id.activity_add_parcel_dimensions_spinner);
        mBtnEnregistrer = findViewById(R.id.activity_add_parcel_save_btn);
    }

    void init(){
        // Bouton fermer
        mBtnFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        // Recyclerview des photos
        mPhotosRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Paramétrage des spinners
        parametrageDesSpinners();

        // Paramétrage du textarea des commentaires
        parametrageDuTextarea();

        // Bouton Enregistrer
        mBtnEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();

                Gson gson = new GsonBuilder().create();
                returnIntent.putExtra(EXTRA_COLIS, gson.toJson(getColis()));
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }

    void remplissageDesChamps(Colis colis){
        if(colis != null){

        }
    }

    void gestionDesExtras(){
        Gson gson = new GsonBuilder().create();
        String jsonExtraColis = getIntent().getStringExtra(EXTRA_COLIS);
        if(jsonExtraColis != null){
            Log.d(TAG, "gestionDesExtras() : Un extra a été inclu.");
            extraColis = gson.fromJson(jsonExtraColis, Colis.class);
        }
        if(extraColis == null){
            Log.d(TAG, "gestionDesExtras() : L'objet colis est nul.");
            extraColis = new Colis();
        }
        remplissageDesChamps(extraColis);
    }

    void parametrageDuTextarea(){
        mTxtCommentaires.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }

    void parametrageDesSpinners(){
        // Spinner des types de colis
        mSpinnerType.setAdapter(new SpinnerItemAdapter(this, new ParcelTypes().getAll()));
        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem clickedItem = (SpinnerItem) parent.getItemAtPosition(position);
                String clickedTypeName = getString(clickedItem.getName());
                Toast.makeText(ParcelStudioActivity.this, clickedTypeName + " selected.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Spinner des poids de colis
        mSpinnerWeight.setAdapter(new SpinnerItemAdapter(this, new ParcelWeights().getAll()));
        mSpinnerWeight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem clickedItem = (SpinnerItem) parent.getItemAtPosition(position);
                String clickedTypeName = getString(clickedItem.getName());
                Toast.makeText(ParcelStudioActivity.this, clickedTypeName + " selected.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Spinner des dimensions de colis
        mSpinnerDimension.setAdapter(new SpinnerItemAdapter(this, new ParcelDimensions().getAll()));
        mSpinnerDimension.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem clickedItem = (SpinnerItem) parent.getItemAtPosition(position);
                String clickedTypeName = getString(clickedItem.getName());
                Toast.makeText(ParcelStudioActivity.this, clickedTypeName + " selected.", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    Colis getColis(){
        return new Colis();
    }

}