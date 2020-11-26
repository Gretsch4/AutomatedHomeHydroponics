package com.example.automatedhomehydroponics.ui.Logs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.automatedhomehydroponics.R;
import com.example.automatedhomehydroponics.RealmHelper.RealmManager;
import com.example.automatedhomehydroponics.ui.Plant_Search.PlantLogs;
import com.example.automatedhomehydroponics.wifi.WifiModule;
import com.google.android.gms.tasks.Task;

import org.bson.Document;
import org.bson.json.JsonReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.mongodb.App;
import io.realm.mongodb.RealmEventStreamAsyncTask;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.events.BaseChangeEvent;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class LogsFragment extends Fragment {

    private LogsViewModel logsViewModel;
    Realm realm;
    RecyclerView rv;
    RealmManager manager;
    Context context;
    RealmChangeListener realmChangeListener;
    private static ArrayList<PlantLogs> plantLogsList;
    static ArrayList<PlantLogs> plantLogsArrayList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logsViewModel =
                ViewModelProviders.of(this).get(LogsViewModel.class);

        View root = inflater.inflate(R.layout.fragment_logs, container, false);
        return root;
    }


    private ArrayList<PlantLogs> update(){
        //realmChangeListener = new RealmChangeListener() {
            //@Override
            //public void onChange(Object O){
                //CustomAdapter adapter = new CustomAdapter(context, manager.justRefresh());
                //rv.setAdapter(adapter);
          //  }
       // };
        //realm.addChangeListener(realmChangeListener);

        User user = new WifiModule().getUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
        MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("PlantLogs");


        RealmResultTask<MongoCursor<Document>> findTask = mongoCollection.find().iterator();
        plantLogsArrayList = new ArrayList<PlantLogs>();
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
                            String date = test.getString("date");
                            String tds = test.getString("tds");
                            String ph = test.getString("ph");
                            String humid = test.getString("humid");
                            String light =test.getString("light");
                            String airTemp = test.getString("airTemp");
                            String waterTemp = test.getString("waterTemp");
                            String waterLvl = test.getString("waterLvl");
                            String dist = test.getString("dist");
                            String phUp = test.getString("phUp");
                            String phDown = test.getString("phDown");
                            String nutrientLvl = test.getString("nutrientLvl");

                            PlantLogs plant = new PlantLogs();
                            plant.setDate(date);
                            plant.setTds(tds);
                            plant.setPh(ph);
                            plant.setWaterTemp(waterTemp);
                            plant.setAirTemp(airTemp);
                            plant.setHumid(humid);
                            plant.setLight(light);
                            plant.setWaterLvl(waterLvl);
                            plant.setDist(dist);
                            plant.setPhUp(phUp);
                            plant.setPhDown(phDown);
                            plant.setNutrientLvl(nutrientLvl);
                            plantLogsArrayList.add(plant);
                            Log.e("EXAMPLE", "failed to find documents with: "+ plantLogsArrayList.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    Log.e("EXAMPLE", "failed to find documents with: ", task.getError());
                }
                setPlantList(plantLogsArrayList);
                Log.e("EXAMPLE", "failed to find documents with:  FIANLLLL0"+ plantLogsArrayList.size());
            }
        });
        Log.e("EXAMPLE", "failed to find documents with:  FIANLLLL"+ plantLogsArrayList.size());
        return plantLogsArrayList;
/*
        RealmEventStreamAsyncTask<Document> watcher = mongoCollection.watchAsync();
        watcher.get(new App.Callback<BaseChangeEvent<Document>>() {
            @Override
            public void onResult(App.Result<BaseChangeEvent<Document>> result) {
                if (result.isSuccess()) {
                    Log.v(
                            "EXAMPLE",
                            "Event type: " + result.get().getOperationType() + " full document: " + result.get().getFullDocument()
                    );


                } else {
                    Log.e("EXAMPLE", "failed to subscribe to changes in the collection with : ", result.getError());
                }
            }
        });
*/
    }

    public void setPlantList(ArrayList<PlantLogs> plants){
        this.plantLogsList = plants;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       // realm.removeChangeListener(realmChangeListener);
        //realm.close();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.log_RV);
        //view.getLogList().observe
        //realm = Realm.getDefaultInstance();
        //manager = new RealmManager(realm);
        //manager.selectFromDB();
        context = getActivity();
        //update();
        //ArrayList<PlantLogs> result = update();
        User user = new WifiModule().getUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
        MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("PlantLogs");

        final ArrayList<PlantLogs> backup;
        RealmResultTask<MongoCursor<Document>> findTask = mongoCollection.find().iterator();
        plantLogsArrayList = new ArrayList<PlantLogs>();
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
                            String date = test.getString("date");
                            String tds = test.getString("tds");
                            String ph = test.getString("ph");
                            String humid = test.getString("humid");
                            String light =test.getString("light");
                            String airTemp = test.getString("airTemp");
                            String waterTemp = test.getString("waterTemp");
                            String waterLvl = test.getString("waterLvl");
                            String dist = test.getString("dist");
                            String phUp = test.getString("phUp");
                            String phDown = test.getString("phDown");
                            String nutrientLvl = test.getString("nutrientLvl");

                            PlantLogs plant = new PlantLogs();
                            plant.setDate(date);
                            plant.setTds(tds);
                            plant.setPh(ph);
                            plant.setWaterTemp(waterTemp);
                            plant.setAirTemp(airTemp);
                            plant.setHumid(humid);
                            plant.setLight(light);
                            plant.setWaterLvl(waterLvl);
                            plant.setDist(dist);
                            plant.setPhUp(phUp);
                            plant.setPhDown(phDown);
                            plant.setNutrientLvl(nutrientLvl);
                            plantLogsArrayList.add(plant);
                            Log.e("EXAMPLE", "failed to find documents with: "+ plantLogsArrayList.size());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    plantLogsList = plantLogsArrayList;
                    final CustomAdapter adapter = new CustomAdapter(context, plantLogsArrayList);
                    rv.setLayoutManager(new LinearLayoutManager(context));
                    rv.setAdapter(adapter);

                } else {
                    Log.e("EXAMPLE", "failed to find documents with: ", task.getError());
                    final CustomAdapter adapter = new CustomAdapter(context, plantLogsList);
                    rv.setHasFixedSize(true);
                    rv.setLayoutManager(new LinearLayoutManager(context));
                    rv.setAdapter(adapter);

                }
                //setPlantList(plantLogsArrayList);
                Log.e("EXAMPLE", "failed to find documents with:  FIANLLLL0"+ plantLogsArrayList.size());
            }
        });
        //Log.e("EXAMPLE", "failed to find documents with:  FIANLLLL"+ plantLogsArrayList.size());

        //Log.e("EXAMPLE", " "+ plantLogsList.size());

        //final CustomAdapter adapter = new CustomAdapter(context, plantLogsList);
        //rv.setHasFixedSize(true);
        //rv.setLayoutManager(new LinearLayoutManager(context));
        //rv.setAdapter(adapter);
        //update();
    }
}
