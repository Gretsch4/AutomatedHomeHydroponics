package com.example.automatedhomehydroponics.ui.Logs;
import com.example.automatedhomehydroponics.ui.Plant_Search.Plant;
import android.content.Context;
import android.provider.Contacts;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<LogsViewHolder> {
    Context context;
    ArrayList<Plant> plant;

    public CustomAdapter (Context context, ArrayList<Plant> plant){
        this.context = context;
        this.plant = plant;
    }
    @NonNull
    @Override
    public LogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_logs, parent, false);
        return new LogsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsViewHolder holder, int position) {
        Plant p = plant.get(position);

        holder.txt_log.setText(p.getAirTemp().toString());

    }

    @Override
    public int getItemCount() {
        return plant.size();
    }
}
