package com.example.automatedhomehydroponics.ui.Plant_Search;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PlantLogs {
    @PrimaryKey
    private ObjectId _id;
    @Required
    private String NutrientLvl;
    @Required
    private String airTemp;
    @Required
    private String date;
    @Required
    private String dist;
    @Required
    private String humid;
    @Required
    private String light;
    @Required
    private String ph;
    @Required
    private String phDown;
    @Required
    private String phUp;
    @Required
    private String tds;
    @Required
    private String waterLvl;
    @Required
    private String waterTemp;

    // Standard getters & setters
    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String getNutrientLvl() { return NutrientLvl; }
    public void setNutrientLvl(String NutrientLvl) { this.NutrientLvl = NutrientLvl; }
    public String getAirTemp() { return airTemp; }
    public void setAirTemp(String airTemp) { this.airTemp = airTemp; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getDist() { return dist; }
    public void setDist(String dist) { this.dist = dist; }
    public String getHumid() { return humid; }
    public void setHumid(String humid) { this.humid = humid; }
    public String getLight() { return light; }
    public void setLight(String light) { this.light = light; }
    public String getPh() { return ph; }
    public void setPh(String ph) { this.ph = ph; }
    public String getPhDown() { return phDown; }
    public void setPhDown(String phDown) { this.phDown = phDown; }
    public String getPhUp() { return phUp; }
    public void setPhUp(String phUp) { this.phUp = phUp; }
    public String getTds() { return tds; }
    public void setTds(String tds) { this.tds = tds; }
    public String getWaterLvl() { return waterLvl; }
    public void setWaterLvl(String waterLvl) { this.waterLvl = waterLvl; }
    public String getWaterTemp() { return waterTemp; }
    public void setWaterTemp(String waterTemp) { this.waterTemp = waterTemp; }
}
