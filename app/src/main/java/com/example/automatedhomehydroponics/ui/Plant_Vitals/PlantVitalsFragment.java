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
        //PlantVitalsViewModel plantVitals = new ViewModelProvider(this).get(PlantVitalsViewModel.class);

        final TextView tds = root.findViewById(R.id.editTextNumberTDS);
        final TextView ph = root.findViewById(R.id.editTextNumberPH);
        final TextView humid = root.findViewById(R.id.editTextNumberHUMID);
        final TextView light = root.findViewById(R.id.editTextNumberLIGHT);
        final TextView airTemp = root.findViewById(R.id.editTextNumberAIRTEMP);
        final TextView phUp = root.findViewById(R.id.editTextNumberPHUP);
        final TextView waterLvl = root.findViewById(R.id.editTextNumberWATERLVL);
        final TextView phDown = root.findViewById(R.id.editTextNumberPHDOWN);
        final TextView waterTemp = root.findViewById(R.id.editTextNumberWATERTEMP);
        final TextView dist = root.findViewById(R.id.editTextNumberDIST);
/*
        plantVitals.getTextTDS().observe(getViewLifecycleOwner(),new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                tds.setText(number.toString());
            }
        });
*/
/*
        plantVitalsViewModel.getTextTDS().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                tds.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextPH().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                ph.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextHUMID().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                humid.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextLIGHT().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                light.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextAIRTEMP().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                airTemp.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextPHUP().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                phUp.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextPHDOWN().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                phDown.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextWATERLVL().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                waterLvl.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextWATERTEMP().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                waterTemp.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextDIST().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                dist.setText(number.toString());
            }
        });

        plantVitalsViewModel.updateVitals();
    */
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
        final TextView waterTemp = view.findViewById(R.id.editTextNumberWATERTEMP);
        final TextView dist = view.findViewById(R.id.editTextNumberDIST);

        plantVitalsViewModel.getTextTDS().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                tds.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextPH().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                ph.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextHUMID().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                humid.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextLIGHT().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                light.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextAIRTEMP().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                airTemp.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextPHUP().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                phUp.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextPHDOWN().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                phDown.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextWATERLVL().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                waterLvl.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextWATERTEMP().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                waterTemp.setText(number.toString());
            }
        });

        plantVitalsViewModel.getTextDIST().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(@Nullable Double number) {
                dist.setText(number.toString());
            }
        });
    }

    public void updateVitals(){
        plantVitalsViewModel.updateVitals();
    }
}