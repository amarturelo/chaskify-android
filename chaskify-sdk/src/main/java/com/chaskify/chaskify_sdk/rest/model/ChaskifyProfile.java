package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alberto on 7/12/17.
 */

public class ChaskifyProfile {
    @SerializedName("team_name")
    @Expose
    public String teamName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("transport_type_id")
    @Expose
    public String transportTypeId;
    @SerializedName("transport_type_id2")
    @Expose
    public String transportTypeId2;
    @SerializedName("transport_description")
    @Expose
    public String transportDescription;
    @SerializedName("licence_plate")
    @Expose
    public String licencePlate;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("driver_id")
    @Expose
    public String driverId;
    @SerializedName("driver_picture")
    @Expose
    public String driverPicture;
    @SerializedName("username")
    @Expose
    public String username;

    public ChaskifyProfile() {
    }

    public String getTeamName() {
        return teamName;
    }

    public ChaskifyProfile setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ChaskifyProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ChaskifyProfile setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getTransportTypeId() {
        return transportTypeId;
    }

    public ChaskifyProfile setTransportTypeId(String transportTypeId) {
        this.transportTypeId = transportTypeId;
        return this;
    }

    public String getTransportTypeId2() {
        return transportTypeId2;
    }

    public ChaskifyProfile setTransportTypeId2(String transportTypeId2) {
        this.transportTypeId2 = transportTypeId2;
        return this;
    }

    public String getTransportDescription() {
        return transportDescription;
    }

    public ChaskifyProfile setTransportDescription(String transportDescription) {
        this.transportDescription = transportDescription;
        return this;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public ChaskifyProfile setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
        return this;
    }

    public String getColor() {
        return color;
    }

    public ChaskifyProfile setColor(String color) {
        this.color = color;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public ChaskifyProfile setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getDriverPicture() {
        return driverPicture;
    }

    public ChaskifyProfile setDriverPicture(String driverPicture) {
        this.driverPicture = driverPicture;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ChaskifyProfile setUsername(String username) {
        this.username = username;
        return this;
    }
}
