package com.example.automatedhomehydroponics.ui.Plant_Search;
import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class PlantSearch {
    @PrimaryKey
    private ObjectId _id;
    @Required
    private String airTempMax;
    @Required
    private String airTempMin;
    @Required
    private String dist;
    @Required
    private String humidMax;
    @Required
    private String humidMin;
    @Required
    private String lightMax;
    @Required
    private String lightMin;
    @Required
    private String name;
    @Required
    private String phMax;
    @Required
    private String phMin;
    @Required
    private String tdsMax;
    @Required
    private String tdsMin;
    @Required
    private String waterTempMax;
    @Required
    private String waterTempMin;
    @Required
    private String hours;

    // Standard getters & setters
    public ObjectId get_id() { return _id; }
    public void set_id(ObjectId _id) { this._id = _id; }
    public String getAirTempMax() { return airTempMax; }
    public void setAirTempMax(String airTempMax) { this.airTempMax = airTempMax; }
    public String getAirTempMin() { return airTempMin; }
    public void setAirTempMin(String airTempMin) { this.airTempMin = airTempMin; }
    public String getDist() { return dist; }
    public void setDist(String distMax) { this.dist = dist; }
    public String getHumidMax() { return humidMax; }
    public void setHumidMax(String humidMax) { this.humidMax = humidMax; }
    public String getHumidMin() { return humidMin; }
    public void setHumidMin(String humidMin) { this.humidMin = humidMin; }
    public String getLightMax() { return lightMax; }
    public void setLightMax(String lightMax) { this.lightMax = lightMax; }
    public String getLightMin() { return lightMin; }
    public void setLightMin(String lightMin) { this.lightMin = lightMin; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhMax() { return phMax; }
    public void setPhMax(String phMax) { this.phMax = phMax; }
    public String getPhMin() { return phMin; }
    public void setPhMin(String phMin) { this.phMin = phMin; }
    public String getTdsMax() { return tdsMax; }
    public void setTdsMax(String tdsMax) { this.tdsMax = tdsMax; }
    public String getTdsMin() { return tdsMin; }
    public void setTdsMin(String tdsMin) { this.tdsMin = tdsMin; }
    public String getWaterTempMax() { return waterTempMax; }
    public void setWaterTempMax(String waterTempMax) { this.waterTempMax = waterTempMax; }
    public String getWaterTempMin() { return waterTempMin; }
    public void setWaterTempMin(String waterTempMin) { this.waterTempMin = waterTempMin; }
    public String getHours() { return hours; }
    public void setHours(String hours) { this.hours = hours; }
}
