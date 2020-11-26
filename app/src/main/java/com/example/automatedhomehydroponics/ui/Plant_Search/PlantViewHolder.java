package com.example.automatedhomehydroponics.ui.Plant_Search;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;

public class PlantViewHolder extends RecyclerView.ViewHolder{
    TextView plant_title;
    TextView plant_body;
    Button setPlant;
    Button deletePlant;

    public PlantViewHolder(@NonNull View itemView) {
        super(itemView);

        plant_title = itemView.findViewById((R.id.plant_card_title));
        plant_body = itemView.findViewById((R.id.plant_card_body));
        setPlant = itemView.findViewById((R.id.set_plant));
        deletePlant = itemView.findViewById((R.id.delete_plant));
    }
}
