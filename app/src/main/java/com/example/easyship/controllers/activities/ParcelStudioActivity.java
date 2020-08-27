package com.example.easyship.controllers.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easyship.R;
import com.example.easyship.application.Easyship;
import com.example.easyship.controllers.adapters.SpinnerItemAdapter;
import com.example.easyship.data.basic.ParcelDimensions;
import com.example.easyship.data.basic.ParcelTypes;
import com.example.easyship.data.basic.ParcelWeights;
import com.example.easyship.models.Colis;
import com.example.easyship.models.uidesign.SpinnerItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class ParcelStudioActivity extends AppCompatActivity {
    public static final String TAG = "ParcelStudioActivity";
    public static final String EXTRA_COLIS = "EXTRA_COLIS";
    public static final int REQ_PICK_IMAGE = 3;
    private Colis extraColis;
    private Uri mUriPhoto;

    private ImageButton mBtnFermer;
    private ImageButton mBtnMore;
    private RelativeLayout mPhotoSubLayout;
    private ImageView mPhoto;
    private EditText mTxtNom;
    private TextView mTxtCommentaires;
    private Spinner mSpinnerType;
    private Spinner mSpinnerPoids;
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
        mBtnFermer = findViewById(R.id.activity_parcel_studio_action_bar_close_btn);
        mBtnMore = findViewById(R.id.activity_parcel_studio_action_bar_more_btn);
        mPhotoSubLayout = findViewById(R.id.activity_parcel_studio_photo_sub_layout);
        mPhoto = findViewById(R.id.activity_parcel_studio_photo);
        mTxtNom = findViewById(R.id.activity_parcel_studio_name_txt);
        mTxtCommentaires = findViewById(R.id.activity_parcel_studio_comments_txt);
        mSpinnerType = findViewById(R.id.activity_parcel_studio_type_spinner);
        mSpinnerPoids = findViewById(R.id.activity_parcel_studio_weight_spinner);
        mSpinnerDimension = findViewById(R.id.activity_parcel_studio_dimensions_spinner);
        mBtnEnregistrer = findViewById(R.id.activity_parcel_studio_save_btn);
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

        // Photo
        mPhotoSubLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirLeSelecteurImage();
            }
        });

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
                // Si les valeurs entrées sont valides, on retourne le résultat, sinon on demande à
                // l'utilisateur de les entrer à nouveau
                if(getColis() != null){
                    returnIntent.putExtra(EXTRA_COLIS, gson.toJson(getColis()));
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                else{
                    Toast.makeText(ParcelStudioActivity.this, "Sorry ! There's missing parameters", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void remplissageDesChamps(Colis colis){
        if(colis != null){
            // Photo
            if(colis.getPhotoUri() != null){
                Picasso.get().load(colis.getPhotoUri()).into(mPhoto);
            }else {
                mPhoto.setImageDrawable(null);
            }

            // Nom
            if(colis.getNom() != null){
                mTxtNom.setText(colis.getNom());
            }else{
                mTxtNom.setText("");
            }

            // Type
            mSpinnerType.setSelection(colis.getType());

            // Poids
            mSpinnerPoids.setSelection(colis.getPoids());

            // Dimensions
            mSpinnerDimension.setSelection(colis.getDimension());

            // Commentaires
            if(colis.getCommentaires() != null){
                mTxtCommentaires.setText(colis.getCommentaires());
            }else{
                mTxtCommentaires.setText("");
            }
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
        mUriPhoto = extraColis.getPhotoUri();
    }

    void ouvrirLeSelecteurImage(){
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQ_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_PICK_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null && data.getData() != null) {
                    Log.d(TAG, "onActivityResult(): L'image a bien été reçue");
                    mUriPhoto = data.getData();
                    Picasso.get().load(mUriPhoto).into(mPhoto);
                }
            }
        }
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
        mSpinnerPoids.setAdapter(new SpinnerItemAdapter(this, new ParcelWeights().getAll()));
        mSpinnerPoids.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        if(!Easyship.remainingEmptyFields(this, mTxtNom)){
            if(mUriPhoto != null){
                return new Colis(
                        Easyship.getNewParcelId(),
                        Calendar.getInstance(),
                        mTxtNom.getText().toString(),
                        mSpinnerType.getSelectedItemPosition(),
                        mSpinnerPoids.getSelectedItemPosition(),
                        mSpinnerDimension.getSelectedItemPosition(),
                        mUriPhoto,
                        mTxtCommentaires.getText().toString()
                );
            }
        }
        return null;
    }

}