package com.example.automatedhomehydroponics.wifi;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection.*;
import java.util.ArrayList;

import com.example.automatedhomehydroponics.ui.Plant_Search.Plant;

public class WifiModuleComms extends AsyncTask<String, Void, String> {

        private String serverAddress = "192.168.1.22";
        private String serverResponse = "";

        private AlertDialog dialog;

        private ArrayList<Plant> recentPlantSnaps = new ArrayList<Plant>();

        public WifiModuleComms() {
        }

        public String getServerResponse(){
            return serverAddress;
        }
        public ArrayList<Plant> getRecentPlantSnaps(){
            return recentPlantSnaps;
        }

        @Override
        protected String doInBackground(String... params) {

            String val = params[0];
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://" + serverAddress);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream serverResponse = new BufferedInputStream(urlConnection.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } finally {
                urlConnection.disconnect();
            }

            return serverResponse;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.println(Log.DEBUG, "Sent", s);
        }

}
