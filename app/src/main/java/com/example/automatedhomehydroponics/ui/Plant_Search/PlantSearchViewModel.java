package com.example.automatedhomehydroponics.ui.Plant_Search;

import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.automatedhomehydroponics.R;

import java.util.ArrayList;

public class PlantSearchViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> plantListData;
    private ArrayList<String> plantList;

    public PlantSearchViewModel() {
        plantListData = new MutableLiveData<>();
        plantListData.setValue(plantList);
    }

    public MutableLiveData<ArrayList<String>> getPlantList()
    {
        return plantListData;
    }
}