package com.example.automatedhomehydroponics.ui.Plant_Search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.RealmHelper.Manager;

import org.bson.BsonObjectId;
import org.bson.Document;

import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.InsertOneResult;

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
        final TextView name = view.findViewById(R.id.editTextTextPersonName2);
        final TextView tdsMax = view.findViewById(R.id.editTextTDS);
        final TextView tdsMin = view.findViewById(R.id.editTextTDS2);
        final TextView phMax = view.findViewById(R.id.editTextPH);
        final TextView phMin = view.findViewById(R.id.editTextPH2);
        final TextView humidMax = view.findViewById(R.id.editTextHUMID);
        final TextView humidMin = view.findViewById(R.id.editTextHUMID2);
        final TextView lightMax = view.findViewById(R.id.editTextLIGHT);
        final TextView lightMin = view.findViewById(R.id.editTextLIGHT2);
        final TextView airTempMax = view.findViewById(R.id.editTextAIRTEMP);
        final TextView airTempMin = view.findViewById(R.id.editTextAIRTEMP2);
        final TextView waterTempMax = view.findViewById(R.id.editTextWATERTEMP);
        final TextView waterTempMin = view.findViewById(R.id.editTextWATERTEMP2);
        final TextView hours = view.findViewById(R.id.editTextHRS2);
        final TextView dist = view.findViewById(R.id.editTextDIST2);
        final Button add = view.findViewById(R.id.button_add_plant);


        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                User user = new Manager().getUser();
                MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
                MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
                MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("PlantSearch");

                Document PlantSearch = new Document("name", name.getText().toString())
                        .append("tdsMax", tdsMax.getText().toString())
                        .append("tdsMin", tdsMin.getText().toString())
                        .append("phMax", phMax.getText().toString())
                        .append("phMin", phMin.getText().toString())
                        .append("humidMax", humidMax.getText().toString())
                        .append("humidMin", humidMin.getText().toString())
                        .append("lightMax", lightMax.getText().toString())
                        .append("lightMin", lightMin.getText().toString())
                        .append("airTempMax", airTempMax.getText().toString())
                        .append("airTempMin", airTempMin.getText().toString())
                        .append("waterTempMax", waterTempMax.getText().toString())
                        .append("waterTempMin", waterTempMin.getText().toString())
                        .append("dist", dist.getText().toString())
                        .append("hours", hours.getText().toString());

                mongoCollection.insertOne(PlantSearch).getAsync(new App.Callback<InsertOneResult>() {
                    @Override
                    public void onResult(App.Result<InsertOneResult> task) {
                        if (task.isSuccess()) {
                            BsonObjectId insertedId = task.get().getInsertedId().asObjectId();
                            Log.v("EXAMPLE", "successfully inserted a document with id " + insertedId);
                            Navigation.findNavController(v).navigate(R.id.action_navigation_plant_search_add_to_navigation_plant_search);
                        } else {
                            Log.e("EXAMPLE", "failed to insert document with: ", task.getError());
                        }
                    }
                });
            }
        });
    }
}
