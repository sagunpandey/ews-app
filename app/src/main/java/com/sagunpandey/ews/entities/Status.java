package com.sagunpandey.ews.entities;

import com.google.gson.annotations.SerializedName;
import com.sagunpandey.ews.entities.enums.Alarm;
import com.sagunpandey.ews.entities.enums.Trend;

import java.util.Date;

/**
 * Created by sagun on 12/5/2017.
 */

public class Status {

    @SerializedName("stationId")
    private Integer stationId;

    @SerializedName("alarm")
    private Alarm alarm;

    @SerializedName("trend")
    private Trend trend;

    @SerializedName("waterLevel")
    private Double waterLevel;

    @SerializedName("readTime")
    private Date readingTime;

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public Trend getTrend() {
        return trend;
    }

    public void setTrend(Trend trend) {
        this.trend = trend;
    }

    public Double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(Double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public Date getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(Date readingTime) {
        this.readingTime = readingTime;
    }
}
