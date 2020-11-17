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

public class PlantVitalsViewModel extends ViewModel {

    private MutableLiveData<Double> tdsDeci;
    private MutableLiveData<Double> phDeci;
    private MutableLiveData<Double> humidDeci;
    private MutableLiveData<Double> lightDeci;
    private MutableLiveData<Double> airTempDeci;
    private MutableLiveData<Double> waterTempDeci;
    private MutableLiveData<Double> phUpDeci;
    private MutableLiveData<Double> phDownDeci;
    private MutableLiveData<Double> waterLvlDeci;
    private MutableLiveData<Double> distanceDeci;
    WifiModule plantData = WifiModule.getInstance();

    private Plant mostRecent;

    public PlantVitalsViewModel() throws IOException {
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
        //String str = plantData.getServerResponse();
        setTextTDS(0.0);
        setTextPH(0.0);
        setTextHUMID(0.0);
        setTextLIGHT(0.0);
        setTextAIRTEMP(0.0);
        setTextWATERTEMP(0.0);
        setTextPHUP(0.0);
        setTextPHDOWN(0.0);
        setTextWATERLVL(0.0);
        setTextDIST(0.0);
    }

    public void updateVitals(){
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

    public void setTextTDS(Double num) { tdsDeci.setValue(num);}
    public LiveData<Double> getTextTDS() {
        return tdsDeci;
    }

    public void setTextPH(Double num) {
        phDeci.setValue(num);
    }
    public LiveData<Double> getTextPH() {
        return phDeci;
    }

    public void setTextHUMID(Double num) {
        humidDeci.setValue(num);
    }
    public LiveData<Double> getTextHUMID() {
        return humidDeci;
    }

    public void setTextLIGHT(Double num) {
        lightDeci.setValue(num);
    }
    public LiveData<Double> getTextLIGHT() {
        return lightDeci;
    }

    public void setTextAIRTEMP(Double num) {
        airTempDeci.setValue(num);
    }
    public LiveData<Double> getTextAIRTEMP() {
        return airTempDeci;
    }

    public void setTextWATERTEMP(Double num) {
        waterTempDeci.setValue(num);
    }
    public LiveData<Double> getTextWATERTEMP() {
        return waterTempDeci;
    }

    public void setTextPHUP(Double num) {
        phUpDeci.setValue(num);
    }
    public LiveData<Double> getTextPHUP() {
        return phUpDeci;
    }

    public void setTextPHDOWN(Double num) {
        phDownDeci.setValue(num);
    }
    public LiveData<Double> getTextPHDOWN() {
        return phDownDeci;
    }

    public void setTextWATERLVL(Double num) {
        waterLvlDeci.setValue(num);
    }
    public LiveData<Double> getTextWATERLVL() {
        return waterLvlDeci;
    }

    public void setTextDIST(Double num) {
        distanceDeci.postValue(num);
    }
    public LiveData<Double> getTextDIST() {
        return distanceDeci;
    }


}