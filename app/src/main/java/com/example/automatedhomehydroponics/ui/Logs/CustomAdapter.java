package com.example.automatedhomehydroponics.ui.Logs;
import com.example.automatedhomehydroponics.ui.Plant_Search.PlantLogs;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<LogsViewHolder> {
    Context context;
    ArrayList<PlantLogs> plant;

    public CustomAdapter (Context context, ArrayList<PlantLogs> plant){
        this.context = context;
        this.plant = plant;
    }
    @NonNull
    @Override
    public LogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.log_card, parent, false);
        return new LogsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsViewHolder holder, int position) {


        PlantLogs p = (PlantLogs) plant.get(position);
        //LogsViewHolder viewHolder = (LogsViewHolder) holder;
        holder.log_title.setText(p.getDate());
        holder.log_body.setText("TDS: " + p.getTds() + " , PH: " + p.getPh() +
                                "\nHumidity: " + p.getHumid() + " , Light: " +  p.getLight() +
                                "\nAir Temperature: " + p.getAirTemp() + " , Water Temperature: " + p.getWaterTemp() +
                                "\nWater Level: " + p.getWaterLvl() + " , Plant Height: " + p.getDist() +
                                "\nPH Up Level: " + p.getPhUp() + " , PH Down Level: " + p.getPhDown() + " , Nutrient Level" + p.getNutrientLvl());

    }

    @Override
    public int getItemCount() {
        return plant.size();
    }
}
