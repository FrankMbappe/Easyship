package com.example.easyship.controllers.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.easyship.R;
import com.example.easyship.application.Easyship;
import com.example.easyship.models.Colis;
import com.example.easyship.models.Livraison;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShipmentStudioActivity extends AppCompatActivity {
    public static final String TAG = "ShipmentStudioActivity";
    public static final String EXTRA_LIVRAISON = "EXTRA_LIVRAISON";
    private static final int REQ_LAUNCH_PARCELS_ACTIVITY = 1;
    private Livraison extraLivraison;

    private ImageButton mBtnFermer;
    private LinearLayout mParcelLayout;
    private TextView mNbreColisEnrg;
    private ImageButton mBtnAjouterColis;
    private TextView mNomDestination;
    private ImageButton mModifierDestination;
    private TextView mNomDestinataire;
    private ImageButton mBtnAjouterDestinataire;
    private TextView mDelai;
    private EditText mTxtPrix;
    private EditText mTextareaDetails;
    private Button mBtnTrouverUnLivreur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate(): La méhode a été appelée");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_studio);


        binding();
        gestionDesExtras();
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult(): La méhode a été appelée");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_LAUNCH_PARCELS_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK){
                if(data != null){
                    Log.d(TAG, "onActivityResult(): L'activité a reçu un résultat non nul");

                    Gson gson = new GsonBuilder().create();
                    String jsonResult = data.getStringExtra(IncludedParcelsActivity.EXTRA_LISTE_COLIS);
                    Type typeListeColis = new TypeToken<ArrayList<Colis>>(){}.getType();

                    // Une fois la chaîne en Json convertie en ArrayList<Colis>, je met à jour l'interface
                    ArrayList<Colis> listeColis = gson.fromJson(jsonResult, typeListeColis);
                    Log.d(TAG, "onActivityResult(): La liste des colis a " + listeColis.size() + " colis.");

                    extraLivraison.setColis(listeColis);
                    remplissageDesChamps(extraLivraison);
                }
                else{
                    Log.d(TAG, "onActivityResult(): Le résultat est nul");
                }
            }
            else{
                Log.d(TAG, "onActivityResult(): Le code du résultat n'est pas OK");
            }
        }
        else{
            Log.d(TAG, "onActivityResult(): Le code de la requête n'est pas LAUNCH...");
        }
    }

    void init(){
        mBtnFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        parametrageDuTextarea();

        mParcelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipmentStudioActivity.this, IncludedParcelsActivity.class);

                // Je convertis l'objet en String grâce à Gson et je l'envoie à l'autre activité avec un putExtra
                Gson gson = new GsonBuilder().create();
                intent.putExtra(IncludedParcelsActivity.EXTRA_LISTE_COLIS, gson.toJson(extraLivraison.getColis()));
                startActivityForResult(intent, REQ_LAUNCH_PARCELS_ACTIVITY);
            }
        });

        mBtnAjouterColis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShipmentStudioActivity.this, ParcelStudioActivity.class));
            }
        });
    }

    void binding(){
        mTextareaDetails = findViewById(R.id.activity_shipment_details_txt);
        mParcelLayout = findViewById(R.id.activity_shipment_parcels_layout);
        mBtnFermer = findViewById(R.id.activity_shipment_action_bar_close_btn);
        mNbreColisEnrg = findViewById(R.id.activity_shipment_number_of_saved_parcels);
        mBtnAjouterColis = findViewById(R.id.activity_shipment_add_parcel_btn);
        mNomDestination  = findViewById(R.id.activity_shipment_destination_name);
        mNomDestinataire = findViewById(R.id.activity_shipment_recipient_name);
        mBtnFermer = findViewById(R.id.activity_shipment_action_bar_close_btn);
        mTxtPrix  = findViewById(R.id.activity_shipment_price_txt);
        mDelai  = findViewById(R.id.activity_shipment_deadline);
        mBtnTrouverUnLivreur  = findViewById(R.id.activity_shipment_find_carrier_btn);
        mModifierDestination = findViewById(R.id.activity_shipment_add_destination_btn);
        mBtnAjouterDestinataire = findViewById(R.id.activity_shipment_add_recipient_btn);
    }

    void gestionDesExtras(){
        Gson gson = new GsonBuilder().create();
        String JsonExtraLivraison = getIntent().getStringExtra(EXTRA_LIVRAISON);
        if(JsonExtraLivraison != null){
            extraLivraison = gson.fromJson(JsonExtraLivraison, Livraison.class);
        }
        else{
            extraLivraison = new Livraison();
        }
        remplissageDesChamps(extraLivraison);
    }

    void remplissageDesChamps(Livraison livraison){
        if(livraison != null){
            // Colis
           if(livraison.getColis() != null){
               String nbreColisEnrg = String.format(getString(R.string.fmt_registred_packages), livraison.getColis().size());
               mNbreColisEnrg.setText(nbreColisEnrg);
           }else{
               mNbreColisEnrg.setText(String.format(getString(R.string.fmt_registred_packages), 0));
           }

           // Destinataire
           if(livraison.getDestinataire() != null){
               mNomDestinataire.setText(livraison.getDestinataire().getNomComplet());
           }else{
               mNomDestinataire.setText(getString(R.string.str_not_specified));
           }

           // Destination
           if(livraison.getDestination() != null){
               mNomDestination.setText(livraison.getDestination().toString());
           }else{
               mNomDestination.setText(getString(R.string.str_not_specified));
           }

           // Délai
           if(livraison.getDateEcheance() != null){
               String date = String.format(getString(R.string.fmt_date_at_hour), Easyship.getDateFromCalendar(livraison.getDateEcheance()), Easyship.getTimeFromCalendar(livraison.getDateEcheance()));
               mDelai.setText(date);
           }else{
               mDelai.setText(getString(R.string.str_not_specified));
           }

            // Prix
            mTxtPrix.setText(String.format("%d", livraison.getPrix()));

            // Détails
            if(livraison.getDetails() != null){
                mTextareaDetails.setText(livraison.getDetails());
            }else{
                mTextareaDetails.setText("");
            }
        }
    }

    void parametrageDuTextarea(){
        mTextareaDetails.setOnTouchListener(new View.OnTouchListener() {
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
}