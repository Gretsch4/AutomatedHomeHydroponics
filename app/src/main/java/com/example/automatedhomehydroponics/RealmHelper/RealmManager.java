package com.example.automatedhomehydroponics.RealmHelper;
import com.example.automatedhomehydroponics.ui.Plant_Search.Plant;
import android.provider.Contacts;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmManager {
    Realm realm;
    RealmResults<Plant> plant;

    public RealmManager(Realm realm) {
        this.realm = realm;
    }

    public void selectFromDB(){
        plant = realm.where(Plant.class).findAll();
    }

    public ArrayList<Plant> justRefresh() {
        ArrayList<Plant> logList = new ArrayList<>();
        for(Plant p : plant) {
            logList.add(p);
        }

        return logList;
    }
}
