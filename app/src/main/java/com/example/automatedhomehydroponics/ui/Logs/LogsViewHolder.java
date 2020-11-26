package com.example.automatedhomehydroponics.ui.Logs;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;

public class LogsViewHolder extends RecyclerView.ViewHolder{
    TextView log_title;
    TextView log_body;

    public LogsViewHolder(@NonNull View itemView) {
        super(itemView);

        log_title = itemView.findViewById((R.id.log_card_title));
        log_body = itemView.findViewById((R.id.log_card_body));
    }
}
