package com.sagunpandey.ews.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sagun on 12/4/2017.
 */

public class River {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("stations")
    private List<Station> stations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}
