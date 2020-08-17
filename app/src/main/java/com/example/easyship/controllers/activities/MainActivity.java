package com.example.easyship.controllers.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.easyship.R;
import com.example.easyship.controllers.fragments.DeliverFragment;
import com.example.easyship.controllers.fragments.HomeFragment;
import com.example.easyship.controllers.fragments.NotificationsFragment;
import com.example.easyship.controllers.fragments.ParcelsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private BottomNavigationView mBottomNav;
    ImageButton mBtnMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding();
        init();
    }

    void binding(){
        mBottomNav = findViewById(R.id.activity_main_bottom_navigation);
    }

    void init(){
        initBottomNavBar();
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