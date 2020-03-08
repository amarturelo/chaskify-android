package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChaskifyDriverLocation {

    @SerializedName("location_lat")
    @Expose
    private String locationLat;
    @SerializedName("location_lng")
    @Expose
    private String locationLng;

    public ChaskifyDriverLocation() {
    }

    public String getLocationLat() {
        return locationLat;
    }

    public ChaskifyDriverLocation setLocationLat(String locationLat) {
        this.locationLat = locationLat;
        return this;
    }

    public String getLocationLng() {
        return locationLng;
    }

    public ChaskifyDriverLocation setLocationLng(String locationLng) {
        this.locationLng = locationLng;
        return this;
    }
}