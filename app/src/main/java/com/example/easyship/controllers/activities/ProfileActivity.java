package com.example.easyship.controllers.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easyship.R;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "ProfileActivity";

    private ImageButton mBtnFermer;
    private ImageButton mBtnMore;
    private Button mBtnModiferProfil;
    private ImageView mPhotoProfil;
    private TextView mNomComplet;
    private ImageView mStatutVerifie;
    private TextView mAdresse;
    private TextView mMoyenneGene;
    private TextView mStatCaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        binding();
        init();
    }

    void binding(){
        mBtnFermer = findViewById(R.id.activity_profile_action_bar_close_btn);
        mBtnMore = findViewById(R.id.activity_profile_action_bar_more_btn);
        mBtnModiferProfil = findViewById(R.id.activity_profile_edit_profile_btn);
        mPhotoProfil = findViewById(R.id.activity_profile_user_photo);
        mNomComplet = findViewById(R.id.activity_profile_user_fullname);
        mStatutVerifie = findViewById(R.id.activity_profile_user_verification_status);
        mAdresse = findViewById(R.id.activity_profile_user_address);
        mMoyenneGene = findViewById(R.id.activity_profile_user_overall_rating);
        mStatCaps = findViewById(R.id.activity_profile_user_caps);
    }

    void init(){
        mBtnFermer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}