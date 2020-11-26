package com.example.automatedhomehydroponics.wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.example.automatedhomehydroponics.ui.Plant_Search.PlantLogs;

import org.bson.BsonObjectId;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.InsertOneResult;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WifiModule {
    private String serverAddress = "192.168.1.150";
    private String serverResponse = "";
    private static Context context;
    private static ArrayList<PlantLogs> recentPlants = new ArrayList<PlantLogs>();
    Handler handler = new Handler();
    private static String previousMessage = "";
    private static WifiModule instance = new WifiModule();
    private static PlantLogs mostRecent;
    private static App app;
    private static User user;

    Realm realm;

    // variable of type String
    public String s;


    // static method to create instance of Singleton class
    public static WifiModule getInstance(){return instance;}

    public WifiModule(Context context){
        this.serverResponse = "0.0";
        WifiModule.context = context;
        //this.client = new OkHttpClient();
    }

    public WifiModule() {
        //recentPlants.add(new Plant(0.0, 1.2,1.3,4.56,235.3,1344.5,5134.5,4343.77,234.33,234.555));
        //this.client = new OkHttpClient();
    }

    public void startPoll(){
        handler.postDelayed(actualStatus, 0);
    }

    public static PlantLogs getMostRecentPlants(){
        //if(recentPlants.isEmpty())
            //return null;

        return mostRecent;
    }

    public ArrayList<PlantLogs> getRecentPlants(){
        return recentPlants;
    }

    public void placeContext(Context context){
        WifiModule.context = context;
    }


    public void placeApp(App app){
        WifiModule.app = app;
    }

    private Runnable actualStatus = new Runnable(){
        int counter=0;
        @Override
        public void run() {
            sendCommand("" + counter++);

            handler.postDelayed(this, 10000);
        }
    };


    public static String getPlantInfo(String serverAddress) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(serverAddress)
                .build();

        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static User getUser() {
        return user;
    }

    public void sendCommand(String command){
        ConnectivityManager connManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){
            String url = "http://192.168.1.150/";
            new Async().execute(url + command);
        }
    }

    public String getServerResponse(){
        return serverResponse;
    }

    private class Async extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            Log.i("debugBack",url[0]);
            return WifiModule.getPlantInfo(url[0]);
        }

        @SuppressLint("WrongConstant")
        @Override
        protected void onPostExecute(String result){
            if(result != null){
                serverResponse = result;
                Log.i("debugPost0", previousMessage + " = " + result);
                if(!previousMessage.equals(result)) {
                    Log.i("debugPost", result);
                    String[] split = result.split(",");

                    final PlantLogs newPlant = new PlantLogs();
                    newPlant.setTds(split[0]);
                    newPlant.setPh(split[1]);
                    newPlant.setWaterTemp(split[2]);
                    newPlant.setAirTemp(split[3]);
                    newPlant.setHumid(split[4]);
                    newPlant.setLight(split[5]);
                    newPlant.setDist(split[6]);
                    newPlant.setWaterLvl(split[7]);
                    newPlant.setNutrientLvl(split[8]);
                    newPlant.setPhUp(split[9]);
                    newPlant.setPhDown(split[10]);

                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, (EEE, d MMM yyyy)");
                    String currentDateandTime = sdf.format(new Date());
                    newPlant.setDate(currentDateandTime);

                    recentPlants.add(0,newPlant);
                    mostRecent = newPlant;

                    user = app.currentUser();
                    MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
                    MongoDatabase mongoDatabase = mongoClient.getDatabase("HydroponicsMobileApp");
                    MongoCollection<Document> mongoCollection  = mongoDatabase.getCollection("PlantLogs");

                    Document PlantLogs = new Document("date", currentDateandTime)
                            .append("tds", split[0])
                            .append("ph", split[1])
                            .append("humid", split[2])
                            .append("light", split[3])
                            .append("airTemp", split[4])
                            .append("waterTemp", split[5])
                            .append("waterLvl", split[6])
                            .append("dist", split[7])
                            .append("phUp", split[8])
                            .append("phDown", split[9])
                            .append("nutrientLvl", split[10]);

                    mongoCollection.insertOne(PlantLogs).getAsync(new App.Callback<InsertOneResult>() {
                        @Override
                        public void onResult(App.Result<InsertOneResult> task) {
                            if (task.isSuccess()) {
                                BsonObjectId insertedId = task.get().getInsertedId().asObjectId();
                                Log.v("EXAMPLE", "successfully inserted a document with id " + insertedId);
                            } else {
                                Log.e("EXAMPLE", "failed to insert document with: ", task.getError());
                            }
                        }
                    });

/*
                    realm = Realm.getDefaultInstance();
                    Log.i("realmSchema", "before exe" + realm.getSchema().toString());
                        realm.executeTransactionAsync(new Realm.Transaction(){
                            @Override
                            public void execute(Realm realm) {
                                PlantLogs plant = realm.createObject(PlantLogs.class);
                                plant.set_id(new ObjectId());
                                plant.setTds(newPlant.getTds());
                                plant.setPh(newPlant.getPh());
                                plant.setWaterTemp(newPlant.getWaterTemp());
                                plant.setAirTemp(newPlant.getAirTemp());
                                plant.setHumid(newPlant.getHumid());
                                plant.setLight(newPlant.getLight());
                                plant.setDist(newPlant.getDist());
                                plant.setWaterLvl(newPlant.getWaterLvl());
                                plant.setNutrientLvl(newPlant.getNutrientLvl());
                                plant.setPhUp(newPlant.getPhUp());
                                plant.setPhDown(newPlant.getPhDown());
                                plant.setDate((newPlant.getDate()));

                                Log.i("realmExe", "passed through realm");
                                //realm.insert(newPlant);
                            }
                        }, new Realm.Transaction.OnSuccess() {
                            @Override
                            public void onSuccess(){
                                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                                Log.i("realmExe", "exe confirmed");
                                realm.close();
                            }

                        }, new Realm.Transaction.OnError() {
                            @Override
                            public  void onError(Throwable error) {
                                Toast.makeText(context, "Input Failed", Toast.LENGTH_SHORT).show();
                                realm.close();
                            }
                        });

 */
                    previousMessage = serverResponse;
                }
            }
            else {
                Log.i("error","error");
            }
        }
    }
}


