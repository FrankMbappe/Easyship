package com.example.easyship.controllers.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.easyship.R;
import com.example.easyship.application.Easyship;
import com.example.easyship.controllers.fragments.DeliverFragment;
import com.example.easyship.controllers.fragments.HomeFragment;
import com.example.easyship.controllers.fragments.NotificationsFragment;
import com.example.easyship.controllers.fragments.ParcelsFragment;
import com.example.easyship.data.test.Utilisateurs;
import com.example.easyship.models.Utilisateur;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private Utilisateur utilisateurActif;
    private Easyship es;

    private BottomNavigationView mBottomNav;
    private ImageView mUserProfilePhoto;
    private ImageButton mBtnMore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        es = new Easyship(this);
        es.initApp();

        binding();
        init();
    }

    void binding(){
        mBottomNav = findViewById(R.id.activity_main_bottom_navigation);
        mBtnMore = findViewById(R.id.activity_main_more_btn);
        mUserProfilePhoto = findViewById(R.id.activity_main_user_photo);
    }

    void init(){
        initBottomNavBar();

        utilisateurActif = (Utilisateur) es.getObjectFromSharedPreferences(Easyship.EASYSHIP_ACTIVE_USER, Utilisateur.class);

        if(utilisateurActif != null){
            remplissageDesChamps(utilisateurActif);
        }

        mUserProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });
    }

    private void remplissageDesChamps(Utilisateur utilisateurActif) {
        if(utilisateurActif != null){
            if(utilisateurActif.getPhotoUri() != null){
                Picasso.get().load(utilisateurActif.getPhotoUri()).into(mUserProfilePhoto);
            }
            else{
                mUserProfilePhoto.setImageDrawable(null);
            }
        }
    }

    public void initBottomNavBar() {
        /* J'ajoute un OnItemSelectedListener qui au moment d'une sélection, récupère l'Id du MenuItem correspondant et
         * l'insère (ou le remplace) dans mon fragment_container */
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, "onNavigationItemSelected method invoked.");
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.menu_bottom_nav_home:
                        selectedFragment = new HomeFragment();
                        Log.d(TAG, "SelectedFragment is a HomeFragment.");
                        break;
                    case R.id.menu_bottom_nav_parcels:
                        selectedFragment = new ParcelsFragment();
                        Log.d(TAG, "SelectedFragment is a StatisticsFragment.");
                        break;
                    case R.id.menu_bottom_nav_deliver:
                        selectedFragment = new DeliverFragment();
                        Log.d(TAG, "SelectedFragment is a RemindersFragment.");
                        break;
                    case R.id.menu_bottom_nav_notifications:
                        selectedFragment = new NotificationsFragment();
                        Log.d(TAG, "SelectedFragment is a RemindersFragment.");
                        break;
                }

                // Le remplacement s'effectue ici
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment_container, selectedFragment).commit();
                Log.d(TAG, "New fragment loaded successfully.");
                return true;
            }
        });

        //Je définis l'élément de la Bottom Navigation Bar sélectionné par défaut
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_fragment_container, new HomeFragment()).commit();
    }

}