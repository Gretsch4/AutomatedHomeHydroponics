package com.example.automatedhomehydroponics.RealmHelper;
import com.example.automatedhomehydroponics.ui.Plant_Search.PlantLogs;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmManager {
    Realm realm;
    RealmResults<PlantLogs> plant;

    public RealmManager(Realm realm) {
        this.realm = realm;
    }

    public void selectFromDB(){
        //plant = realm.where(PlantLogs.class).findAll();


    }

    public ArrayList<PlantLogs> justRefresh() {
        ArrayList<PlantLogs> logList = new ArrayList<>();
        for(PlantLogs p : plant) {
            logList.add(p);
        }

        return logList;
    }
}
