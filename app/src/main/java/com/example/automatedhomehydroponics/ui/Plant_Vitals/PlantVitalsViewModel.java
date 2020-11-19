package com.example.automatedhomehydroponics.ui.Plant_Vitals;

import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.automatedhomehydroponics.ui.Plant_Search.Plant;
import com.example.automatedhomehydroponics.wifi.WifiLayer;
import com.example.automatedhomehydroponics.wifi.WifiModule;
import com.example.automatedhomehydroponics.wifi.WifiModuleComms;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PlantVitalsViewModel extends ViewModel {

    private static MutableLiveData<Double> tdsDeci;
    private static MutableLiveData<Double> phDeci;
    private static MutableLiveData<Double> humidDeci;
    private static MutableLiveData<Double> lightDeci;
    private static MutableLiveData<Double> airTempDeci;
    private static MutableLiveData<Double> waterTempDeci;
    private static MutableLiveData<Double> phUpDeci;
    private static MutableLiveData<Double> phDownDeci;
    private static MutableLiveData<Double> waterLvlDeci;
    private static MutableLiveData<Double> distanceDeci;
    static WifiModule plantData = WifiModule.getInstance();
    static Handler handler = new Handler();
    private static Plant mostRecent;
    private static boolean threadStarted = false;

    private static Runnable actualStatus = new Runnable(){
        int counter=0;

        @Override
        public void run() {
            plantData.sendCommand("" + counter++);
            updateVitals();
            updateVitals();
            handler.postDelayed(this, 5000);
        }
    };

    public PlantVitalsViewModel() throws IOException {
        startThread();

        tdsDeci = new MutableLiveData<>();

        phDeci = new MutableLiveData<>();

        humidDeci = new MutableLiveData<>();

        lightDeci = new MutableLiveData<>();

        airTempDeci = new MutableLiveData<>();

        waterTempDeci = new MutableLiveData<>();

        phUpDeci = new MutableLiveData<>();

        phDownDeci = new MutableLiveData<>();

        waterLvlDeci = new MutableLiveData<>();

        distanceDeci = new MutableLiveData<>();

        updateVitals();
    }

    public void startThread(){
        if(threadStarted == false) {
            handler.postDelayed(actualStatus, 0);
            threadStarted = true;
        }
    }

    public static void updateVitals(){
        mostRecent = plantData.getMostRecentPlants();
        if(mostRecent == null){return;}
        setTextTDS(mostRecent.getTds());
        setTextPH(mostRecent.getPh());
        setTextHUMID(mostRecent.getHumid());
        setTextLIGHT(mostRecent.getLight());
        setTextAIRTEMP(mostRecent.getAirTemp());
        setTextWATERTEMP(mostRecent.getWaterTemp());
        setTextPHUP(mostRecent.getPhUp());
        setTextPHDOWN(mostRecent.getPhDown());
        setTextWATERLVL(mostRecent.getWaterLvl());
        setTextDIST(mostRecent.getDist());
    }

    public static void setTextTDS(Double num) { tdsDeci.setValue(num);}
    public MutableLiveData<Double> getTextTDS() {
        return tdsDeci;
    }

    public static void setTextPH(Double num) {
        phDeci.setValue(num);
    }
    public MutableLiveData<Double> getTextPH() {
        return phDeci;
    }

    public static void setTextHUMID(Double num) {
        humidDeci.setValue(num);
    }
    public MutableLiveData<Double> getTextHUMID() {
        return humidDeci;
    }

    public static void setTextLIGHT(Double num) {
        lightDeci.setValue(num);
    }
    public MutableLiveData<Double> getTextLIGHT() {
        return lightDeci;
    }

    public static void setTextAIRTEMP(Double num) {
        airTempDeci.setValue(num);
    }
    public MutableLiveData<Double> getTextAIRTEMP() {
        return airTempDeci;
    }

    public static void setTextWATERTEMP(Double num) {
        waterTempDeci.setValue(num);
    }
    public MutableLiveData<Double> getTextWATERTEMP() {
        return waterTempDeci;
    }

    public static void setTextPHUP(Double num) {
        phUpDeci.setValue(num);
    }
    public MutableLiveData<Double> getTextPHUP() {
        return phUpDeci;
    }

    public static void setTextPHDOWN(Double num) {
        phDownDeci.setValue(num);
    }
    public MutableLiveData<Double> getTextPHDOWN() {
        return phDownDeci;
    }

    public static void setTextWATERLVL(Double num) {
        waterLvlDeci.setValue(num);
    }
    public MutableLiveData<Double> getTextWATERLVL() {
        return waterLvlDeci;
    }

    public static void setTextDIST(Double num) {
        distanceDeci.postValue(num);
    }
    public MutableLiveData<Double> getTextDIST() {
        return distanceDeci;
    }


}