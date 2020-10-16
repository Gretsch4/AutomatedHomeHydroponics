package com.example.automatedhomehydroponics.ui.Plant_Search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlantSearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlantSearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Plant Search");
    }

    public LiveData<String> getText() {
        return mText;
    }
}