package com.example.automatedhomehydroponics.ui.Notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.RealmHelper.Manager;
import com.example.automatedhomehydroponics.ui.Plant_Search.PlantSearch;
import com.example.automatedhomehydroponics.ui.Plant_Search.PlantViewHolder;
import com.example.automatedhomehydroponics.wifi.WifiModule;

import org.bson.Document;

import java.util.ArrayList;

import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.DeleteResult;

public class CustomNotesAdapter extends RecyclerView.Adapter<NotesViewHolder>{
    Context context;
    ArrayList<Notes> plant;
    WifiModule plantData = new WifiModule();
    Notes p;

    public CustomNotesAdapter(Context context, ArrayList<Notes> plant){
        this.context = context;
        this.plant = plant;
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_card, parent, false);
        return new NotesViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        p = plant.get(position);
        //LogsViewHolder viewHolder = (LogsViewHolder) holder;
        holder.notes_title.setText(p.getTitle());
        holder.notes_body.setText(p.getNotes());

        holder.deleteNotes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = new Manager().getUser();
                MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
                MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
                MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("Notes");
                Document queryFilter = new Document("title", p.getTitle());
                mongoCollection.deleteOne(queryFilter).getAsync(new App.Callback<DeleteResult>() {
                    @Override
                    public void onResult(App.Result<DeleteResult> task) {
                        if (task.isSuccess()) {
                            long count = task.get().getDeletedCount();
                            if (count == 1) {
                                Log.v("EXAMPLE", "successfully deleted a document.");
                            } else {
                                Log.v("EXAMPLE", "did not delete a document.");
                            }
                        } else {
                            Log.e("EXAMPLE", "failed to delete document with: ", task.getError());
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return plant.size();
    }
}
