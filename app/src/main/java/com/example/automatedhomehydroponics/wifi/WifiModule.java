package com.example.automatedhomehydroponics.wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.example.automatedhomehydroponics.ui.Plant_Search.Plant;

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
    //OkHttpClient client = new OkHttpClient();
    private static WifiModule instance = new WifiModule();

    // variable of type String
    public String s;


    // static method to create instance of Singleton class
    public static WifiModule getInstance()
    {
        return instance;
    }

    public WifiModule(Context context){
        this.serverResponse = "0.0";
        this.context = context;
        //this.client = new OkHttpClient();
    }

    public WifiModule(){
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
            handler.postDelayed(this, 20000);
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

                Log.i("debugPost",result);
                String[] split = result.split(",");
                recentPlants.add(new Plant(Double.valueOf(split[0]),Double.valueOf(split[1]),Double.valueOf(split[2]),Double.valueOf(split[3]),Double.valueOf(split[4]),Double.valueOf(split[5]),Double.valueOf(split[6]),Double.valueOf(split[7]),Double.valueOf(split[8]),Double.valueOf(split[9])));
                //recentPlants.add(new Plant(Double.valueOf(splitUpResponse[0]), 1.2,1.3,4.56,235.3,1344.5,5134.5,4343.77,234.33,234.555));
            }
            else {
                Log.i("error","error");
            }
        }
    }
}


