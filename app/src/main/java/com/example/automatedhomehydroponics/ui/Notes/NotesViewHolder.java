package com.example.automatedhomehydroponics.ui.Notes;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;

public class NotesViewHolder extends RecyclerView.ViewHolder{
    TextView notes_title;
    TextView notes_body;
    Button deleteNotes;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_title = itemView.findViewById((R.id.note_card_title));
        notes_body = itemView.findViewById((R.id.note_card_body));
        deleteNotes = itemView.findViewById((R.id.delete_note));
    }

}
