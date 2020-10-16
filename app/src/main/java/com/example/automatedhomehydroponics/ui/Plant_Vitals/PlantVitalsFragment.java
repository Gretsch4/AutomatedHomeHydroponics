package com.example.automatedhomehydroponics.ui.Plant_Vitals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.ui.User_Profile.UserProfileViewModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class PlantVitalsFragment extends Fragment {

    private PlantVitalsViewModel plantVitalsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plantVitalsViewModel =
                ViewModelProviders.of(this).get(PlantVitalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_vitals, container, false);
        final TextView textView = root.findViewById(R.id.text_plant_vitals);
        plantVitalsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}