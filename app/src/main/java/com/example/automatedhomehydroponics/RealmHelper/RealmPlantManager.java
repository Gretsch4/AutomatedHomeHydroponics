package com.example.automatedhomehydroponics.RealmHelper;

import com.example.automatedhomehydroponics.ui.Plant_Search.PlantSearch;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmPlantManager {
    Realm realm;
    RealmResults<PlantSearch> plant;

    public RealmPlantManager(Realm realm) {
        this.realm = realm;
    }

    public void selectFromDB(){
        plant = realm.where(PlantSearch.class).findAll();
    }

    public ArrayList<PlantSearch> justRefresh() {
        ArrayList<PlantSearch> plantList = new ArrayList<>();
        for(PlantSearch p : plant) {
            plantList.add(p);
        }

        return plantList;
    }
}
