package com.example.automatedhomehydroponics.ui.Logs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;

public class LogsViewHolder extends RecyclerView.ViewHolder{
    TextView txt_log;

    public LogsViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_log = itemView.findViewById((R.id.txt_log));
    }
}
