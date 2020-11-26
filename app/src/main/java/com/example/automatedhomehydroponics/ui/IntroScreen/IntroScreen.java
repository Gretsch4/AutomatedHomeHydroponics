package com.example.automatedhomehydroponics.ui.IntroScreen;

import android.os.Bundle;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.ui.Plant_Vitals.PlantVitalsViewModel;
import com.example.automatedhomehydroponics.wifi.WifiModule;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class IntroScreen extends AppCompatActivity {

    private WifiModule wifi;
    PlantVitalsViewModel plantVitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.nav_view);
        navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_user_profile, R.id.navigation_plant_vitals, R.id.navigation_plant_search, R.id.navigation_notes, R.id.navigation_logs)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //plantVitals = getSupportFragmentManager().getFragment(savedInstanceState, PlantVitalsFragment.class.toString());
        //plantVitals = new ViewModelProvider(this).get(PlantVitalsViewModel.class);
        wifi = WifiModule.getInstance();
        wifi.placeContext(this);

        //wifi.startPoll();
    }
}