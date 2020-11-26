package com.example.automatedhomehydroponics.ui.Plant_Vitals;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView tds = view.findViewById(R.id.editTextNumberTDS);
        final TextView ph = view.findViewById(R.id.editTextNumberPH);
        final TextView humid = view.findViewById(R.id.editTextNumberHUMID);
        final TextView light = view.findViewById(R.id.editTextNumberLIGHT);
        final TextView airTemp = view.findViewById(R.id.editTextNumberAIRTEMP);
        final TextView phUp = view.findViewById(R.id.editTextNumberPHUP);
        final TextView waterLvl = view.findViewById(R.id.editTextNumberWATERLVL);
        final TextView phDown = view.findViewById(R.id.editTextNumberPHDOWN);
        final TextView nutrientLvl = view.findViewById(R.id.editTextNumberNutrientLVL);
        final TextView waterTemp = view.findViewById(R.id.editTextNumberWATERTEMP);
        final TextView dist = view.findViewById(R.id.editTextNumberDIST);

        plantVitalsViewModel.getTextTDS().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                tds.setText(number);
            }
        });

        plantVitalsViewModel.getTextPH().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                ph.setText(number);
            }
        });

        plantVitalsViewModel.getTextHUMID().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                humid.setText(number);
            }
        });

        plantVitalsViewModel.getTextLIGHT().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                light.setText(number);
            }
        });

        plantVitalsViewModel.getTextAIRTEMP().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                airTemp.setText(number);
            }
        });

        plantVitalsViewModel.getTextPHUP().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                phUp.setText(number);
            }
        });

        plantVitalsViewModel.getTextPHDOWN().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                phDown.setText(number);
            }
        });

        plantVitalsViewModel.getTextNUTRIENTLVL().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                nutrientLvl.setText(number);
            }
        });

        plantVitalsViewModel.getTextWATERLVL().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                waterLvl.setText(number);
            }
        });

        plantVitalsViewModel.getTextWATERTEMP().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                waterTemp.setText(number);
            }
        });

        plantVitalsViewModel.getTextDIST().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String number) {
                dist.setText(number);
            }
        });
    }
}