package com.example.automatedhomehydroponics.ui.Plant_Search;

import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.automatedhomehydroponics.R;

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