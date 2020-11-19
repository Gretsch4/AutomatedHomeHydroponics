package com.example.automatedhomehydroponics.wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.automatedhomehydroponics.ui.Plant_Search.Plant;
import com.example.automatedhomehydroponics.ui.Plant_Vitals.PlantVitalsFragment;
import com.example.automatedhomehydroponics.ui.Plant_Vitals.PlantVitalsViewModel;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WifiModule {
    private String serverAddress = "192.168.1.150";
    private String serverResponse = "";
    private Context context;
    private ArrayList<Plant> recentPlants = new ArrayList<Plant>();
    Handler handler = new Handler();
    private String previousMessage = "";
    private static WifiModule instance = new WifiModule();
    private Plant mostRecent;

    // variable of type String
    public String s;


    // static method to create instance of Singleton class
    public static WifiModule getInstance(){return instance;}

    public WifiModule(Context context){
        this.serverResponse = "0.0";
        this.context = context;
        //this.client = new OkHttpClient();
    }

    public WifiModule() {
        //recentPlants.add(new Plant(0.0, 1.2,1.3,4.56,235.3,1344.5,5134.5,4343.77,234.33,234.555));
        //this.client = new OkHttpClient();
    }

    public void startPoll(){
        handler.postDelayed(actualStatus, 0);
    }

    public Plant getMostRecentPlants(){
        if(recentPlants.isEmpty())
            return null;

        return recentPlants.get(0);
    }

    public ArrayList<Plant> getRecentPlants(){
        return recentPlants;
    }

    public void placeContext(Context context){
        this.context = context;
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

    public void sendCommand(String command){
        ConnectivityManager connManager = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);
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
                Log.i("debugPost", result);
                if(previousMessage != result) {
                    String[] split = result.split(",");
                    Plant newPlant = new Plant();
                    newPlant.setTds(Double.valueOf(split[1]));
                    newPlant.setPh(Double.valueOf(split[2]));
                    newPlant.setWaterTemp(Double.valueOf(split[3]));
                    newPlant.setAirTemp(Double.valueOf(split[4]));
                    newPlant.setHumid(Double.valueOf(split[5]));
                    newPlant.setLight(Double.valueOf(split[6]));
                    newPlant.setDist(Double.valueOf(split[7]));
                    newPlant.setWaterLvl(Double.valueOf(split[8]));
                    newPlant.setPhUp(Double.valueOf(split[9]));
                    newPlant.setPhDown(Double.valueOf(split[10]));
                    recentPlants.add(0,newPlant);
                }
            }
            else {
                Log.i("error","error");
            }
        }
    }
}


