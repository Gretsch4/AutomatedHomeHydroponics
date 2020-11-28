package com.example.automatedhomehydroponics.ui.Plant_Vitals;

import android.os.Handler;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.automatedhomehydroponics.ui.Plant_Search.PlantLogs;
import com.example.automatedhomehydroponics.wifi.WifiModule;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class PlantVitalsViewModel extends ViewModel {

    private static MutableLiveData<String> tdsDeci;
    private static MutableLiveData<String> phDeci;
    private static MutableLiveData<String> humidDeci;
    private static MutableLiveData<String> lightDeci;
    private static MutableLiveData<String> airTempDeci;
    private static MutableLiveData<String> waterTempDeci;
    private static MutableLiveData<String> phUp;
    private static MutableLiveData<String> phDown;
    private static MutableLiveData<String> nutrient;
    private static MutableLiveData<String> waterLvl;
    private static MutableLiveData<String> distanceDeci;
    static WifiModule plantData = new WifiModule();
    static Handler handler = new Handler();
    private static ArrayList<PlantLogs> plantList;
    private static PlantLogs mostRecent;
    private static boolean threadStarted = false;

    private static Runnable actualStatus = new Runnable(){
        int counter=0;

        @Override
        public void run() {
            if(counter >= 2)
                counter = 0;

            if(counter%2 == 0) {
                plantData.sendCommand("I");
                handler.postDelayed(this, 5000);
            }
            else {
                updateVitals();
                handler.postDelayed(this, 30000);
            }
            counter++;
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

        phUp = new MutableLiveData<>();

        phDown = new MutableLiveData<>();

        nutrient = new MutableLiveData<>();

        waterLvl = new MutableLiveData<>();

        distanceDeci = new MutableLiveData<>();

        updateVitals();
    }

    public static void startThread(){
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
        setTextNUTRIENTLVL(mostRecent.getNutrientLvl());
        setTextWATERLVL(mostRecent.getWaterLvl());
        setTextDIST(mostRecent.getDist());
    }

    public static void setTextTDS(String num) { tdsDeci.setValue(num);}
    public MutableLiveData<String> getTextTDS() {
        return tdsDeci;
    }

    public static void setTextPH(String num) {
        phDeci.setValue(num);
    }
    public MutableLiveData<String> getTextPH() {
        return phDeci;
    }

    public static void setTextHUMID(String num) {
        humidDeci.setValue(num);
    }
    public MutableLiveData<String> getTextHUMID() {
        return humidDeci;
    }

    public static void setTextLIGHT(String num) {
        lightDeci.setValue(num);
    }
    public MutableLiveData<String> getTextLIGHT() {
        return lightDeci;
    }

    public static void setTextAIRTEMP(String num) {
        airTempDeci.setValue(num);
    }
    public MutableLiveData<String> getTextAIRTEMP() {
        return airTempDeci;
    }

    public static void setTextWATERTEMP(String num) {
        waterTempDeci.setValue(num);
    }
    public MutableLiveData<String> getTextWATERTEMP() {
        return waterTempDeci;
    }

    public static void setTextPHUP(String num) {
        phUp.setValue(num);
    }
    public MutableLiveData<String> getTextPHUP() {
        return phUp;
    }

    public static void setTextPHDOWN(String num) {
        phDown.setValue(num);
    }
    public MutableLiveData<String> getTextPHDOWN() {
        return phDown;
    }

    public static void setTextWATERLVL(String num) {
        waterLvl.setValue(num);
    }
    public MutableLiveData<String> getTextWATERLVL() {
        return waterLvl;
    }

    public static void setTextDIST(String num) {
        distanceDeci.setValue(num);
    }
    public MutableLiveData<String> getTextDIST() {
        return distanceDeci;
    }

    public static void setTextNUTRIENTLVL(String num) {
        nutrient.setValue(num);
    }
    public MutableLiveData<String> getTextNUTRIENTLVL() {
        return nutrient;
    }


}