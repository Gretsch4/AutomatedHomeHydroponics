package com.example.automatedhomehydroponics.ui.Plant_Search;

import io.realm.RealmObject;

public class Plant extends RealmObject{

    private int plantId;
    private Double tds = 0.0;
    private Double ph = 0.0;
    private Double humid = 0.0;
    private Double light = 0.0;
    private Double airTemp = 0.0;
    private Double phUp = 0.0;
    private Double phDown = 0.0;
    private Double waterLvl = 0.0;
    private Double waterTemp = 0.0;
    private Double dist = 0.0;
/*
    public Plant(Double tds, Double ph, Double humid, Double light, Double airTemp, Double phUp, Double phDown, Double waterLvl, Double waterTemp, Double dist){
        this.setTds(tds);
        this.setPh(ph);
        this.setHumid(humid);
        this.setLight(light);
        this.setAirTemp(airTemp);
        this.setPhUp(phUp);
        this.setPhDown(phDown);
        this.setWaterLvl(waterLvl);
        this.setWaterTemp(waterTemp);
        this.setDist(dist);
    }

*/
    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getPlantId() {
        return plantId;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getTds() {
        return tds;
    }

    public void setTds(Double tds) {
        this.tds = tds;
    }

    public Double getHumid() {
        return humid;
    }

    public void setHumid(Double humid) {
        this.humid = humid;
    }

    public Double getLight() {
        return light;
    }

    public void setLight(Double light) {
        this.light = light;
    }

    public Double getAirTemp() {
        return airTemp;
    }

    public void setAirTemp(Double airTemp) {
        this.airTemp = airTemp;
    }

    public Double getPhUp() {
        return phUp;
    }

    public void setPhUp(Double phUp) {
        this.phUp = phUp;
    }

    public Double getPhDown() {
        return phDown;
    }

    public void setPhDown(Double phDown) {
        this.phDown = phDown;
    }

    public Double getWaterLvl() {
        return waterLvl;
    }

    public void setWaterLvl(Double waterLvl) {
        this.waterLvl = waterLvl;
    }

    public Double getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(Double waterTemp) {
        this.waterTemp = waterTemp;
    }

    public Double getDist() {
        return dist;
    }

    public void setDist(Double dist) {
        this.dist = dist;
    }
}
