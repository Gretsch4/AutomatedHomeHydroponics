package com.example.automatedhomehydroponics.ui.Plant_Search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.ui.Logs.CustomAdapter;
import com.example.automatedhomehydroponics.ui.Logs.LogsViewHolder;
import com.example.automatedhomehydroponics.wifi.WifiModule;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;
import io.realm.mongodb.mongo.result.DeleteResult;

public class CustomPlantAdapter extends RecyclerView.Adapter<PlantViewHolder> {
    Context context;
    ArrayList<PlantSearch> plant;
    WifiModule plantData = new WifiModule();
    PlantSearch p;

    public CustomPlantAdapter(Context context, ArrayList<PlantSearch> plant){
        this.context = context;
        this.plant = plant;
    }
    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plant_card, parent, false);
        return new PlantViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        p = plant.get(position);
        //LogsViewHolder viewHolder = (LogsViewHolder) holder;
        holder.plant_title.setText(p.getName());
        holder.plant_body.setText("TDS Range: " + p.getTdsMax() + " - " + p.getTdsMin() + ", PH Range: " + p.getPhMax() + " - " + p.getPhMin() +
                                "\nHumidity Range: " + p.getHumidMax() + " - " + p.getHumidMin() +  " , Light Range: " +  p.getLightMax() +  " - " + p.getLightMin() +
                                "\nAir Temperature Range: " + p.getAirTempMax() + " - "+ p.getAirTempMin() + " , Water Temperature Range: " + p.getWaterTempMax() + " - " + p.getWaterTempMin() +
                                "\nPlant Height: " + p.getDist() +
                                " , Hours: " + p.getHours());

        holder.setPlant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                plantData.sendCommand(p.getTdsMax() + "," + p.getTdsMin() + "," + p.getPhMax() + "," +p.getPhMin() + "," +p.getWaterTempMax() + "," + p.getWaterTempMin() + "," + p.getAirTempMax() + "," + p.getAirTempMin() + "," + p.getHumidMax() + "," + p.getHumidMin() + "," + p.getLightMax() + "," + p.getHours());
            }
        });

        holder.deletePlant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                User user = plantData.getUser();
                MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
                MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
                MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("PlantSearch");
                Document queryFilter = new Document("name", p.getName());
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
