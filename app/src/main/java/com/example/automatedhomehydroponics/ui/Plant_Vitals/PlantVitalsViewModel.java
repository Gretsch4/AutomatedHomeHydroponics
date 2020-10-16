package com.example.automatedhomehydroponics.ui.Plant_Vitals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlantVitalsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlantVitalsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Plant Vitals");


    }

    public LiveData<String> getText() {
        return mText;
    }
}