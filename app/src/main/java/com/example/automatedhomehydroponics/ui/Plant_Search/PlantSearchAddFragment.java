package com.example.automatedhomehydroponics.ui.Plant_Search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.automatedhomehydroponics.R;

public class PlantSearchAddFragment extends Fragment {
    // PlantSearchViewModel plantSearchViewModel;
    public PlantSearchAddFragment(){}
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //plantSearchViewModel =
                //ViewModelProviders.of(this).get(PlantSearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_search_add, container, false);

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
    }
}
