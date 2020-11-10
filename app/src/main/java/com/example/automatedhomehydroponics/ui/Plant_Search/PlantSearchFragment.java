package com.example.automatedhomehydroponics.ui.Plant_Search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.automatedhomehydroponics.R;

public class PlantSearchFragment extends Fragment {

    private PlantSearchViewModel plantSearchViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plantSearchViewModel =
                ViewModelProviders.of(this).get(PlantSearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_search, container, false);

        // Might want to change the final if it cant change values
        final TextView textView = root.findViewById(R.id.text_plant_search);



        plantSearchViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        return root;
    }
}