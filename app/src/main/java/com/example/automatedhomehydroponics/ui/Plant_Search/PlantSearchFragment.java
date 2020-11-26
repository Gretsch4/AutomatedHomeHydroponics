package com.example.automatedhomehydroponics.ui.Plant_Search;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import android.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.RealmHelper.RealmManager;
import com.example.automatedhomehydroponics.RealmHelper.RealmPlantManager;
import com.example.automatedhomehydroponics.ui.Logs.CustomAdapter;
import com.example.automatedhomehydroponics.wifi.WifiModule;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.mongodb.App;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class PlantSearchFragment extends Fragment {

    private PlantSearchViewModel plantSearchViewModel;
    Realm realm;
    RealmPlantManager manager;
    Context context;
    RecyclerView rv;
    private static ArrayList<PlantSearch> plantList;
    static ArrayList<PlantSearch> plantArrayList;
    WifiModule plantData = new WifiModule();
    Button setPlant;
    Button deletePlant;
    Button addPlant;
    Fragment fragment;
    View pA;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        plantSearchViewModel =
                ViewModelProviders.of(this).get(PlantSearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_plant_search, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.plant_recycle);
        setPlant = view.findViewById(R.id.set_plant);
        deletePlant = view.findViewById(R.id.delete_plant);
        addPlant = view.findViewById(R.id.button_add_plant);
        pA =  view.findViewById(R.id.navigation_plant_search_add);

        addPlant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.action_navigation_plant_search_to_navigation_plant_search_add);

            }
        });

        context = getActivity();
        User user = new WifiModule().getUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
        MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("PlantSearch");

        RealmResultTask<MongoCursor<Document>> findTask = mongoCollection.find().iterator();
        plantArrayList = new ArrayList<PlantSearch>();
        findTask.getAsync(new App.Callback<MongoCursor<Document>>() {
            @Override
            public void onResult(App.Result<MongoCursor<Document>> task) {
                if (task.isSuccess()) {
                    MongoCursor<Document> results = task.get();
                    Log.v("EXAMPLE", "successfully found all plants for Store 42:");
                    while (results.hasNext()) {
                        Document docu = results.next();
                        String text = docu.toString();
                        Log.v("EXAMPLE", text);
                        //jsonReader = new JsonReader(docu.toJson());

                        try {
                            JSONObject test = new JSONObject(docu.toJson());
                            String name = test.getString("name");
                            String tdsMax = test.getString("tdsMax");
                            String tdsMin = test.getString("tdsMin");
                            String phMax = test.getString("phMax");
                            String phMin = test.getString("phMin");
                            String humidMax = test.getString("humidMax");
                            String humidMin = test.getString("humidMin");
                            String lightMax =test.getString("lightMax");
                            String lightMin =test.getString("lightMin");
                            String airTempMax = test.getString("airTempMax");
                            String airTempMin = test.getString("airTempMin");
                            String waterTempMax = test.getString("waterTempMax");
                            String waterTempMin = test.getString("waterTempMin");
                            String dist = test.getString("dist");
                            String hours = test.getString("hours");

                            PlantSearch plant = new PlantSearch();
                            plant.setName(name);
                            plant.setTdsMax(tdsMax);
                            plant.setTdsMin(tdsMin);
                            plant.setPhMax(phMax);
                            plant.setPhMin(phMin);
                            plant.setWaterTempMax(waterTempMax);
                            plant.setWaterTempMin(waterTempMin);
                            plant.setAirTempMax(airTempMax);
                            plant.setAirTempMin(airTempMin);
                            plant.setHumidMax(humidMax);
                            plant.setHumidMin(humidMin);
                            plant.setLightMax(lightMax);
                            plant.setLightMin(lightMin);
                            plant.setDist(dist);
                            plant.setHours(hours);

                            plantArrayList.add(plant);
                            Log.e("EXAMPLE", "failed to find documents with: "+ plantArrayList.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    plantList = plantArrayList;
                    final CustomPlantAdapter adapter = new CustomPlantAdapter(context, plantArrayList);
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(new LinearLayoutManager(context));
                    rv.setAdapter(adapter);
                } else {
                    Log.e("EXAMPLE", "failed to find documents with: ", task.getError());
                    final CustomPlantAdapter adapter = new CustomPlantAdapter(context, plantList);
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(new LinearLayoutManager(context));
                    rv.setAdapter(adapter);

                }
                //setPlantList(plantLogsArrayList);
                Log.e("EXAMPLE", "failed to find documents with:  FIANLLLL0"+ plantArrayList.size());
            }
        });
    }
}