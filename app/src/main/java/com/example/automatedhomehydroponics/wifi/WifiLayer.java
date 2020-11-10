package com.example.automatedhomehydroponics.wifi;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WifiLayer {

    private Double tdsDeci;
    private Double phDeci;
    private Double humidDeci;
    private Double lightDeci;
    private Double airTempDeci;
    private Double waterTempDeci;
    private Double phUpDeci;
    private Double phDownDeci;
    private Double waterLvlDeci;
    private Double distanceDeci;

    private String serverAddress = "192.168.1.22";
    private String serverResponse = "";

    private final OkHttpClient client = new OkHttpClient();
    public WifiLayer(){

    }

    public void doGetRequest() throws IOException {
        Request request = new Request.Builder()
                .url(serverAddress)
                .build();

        client.newCall(request).enqueue(new Callback() {
         @Override
            public void onFailure(final Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
             try(ResponseBody responseBody = response.body()){
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(responseBody.string());
            }
        }
    });
    }

}

