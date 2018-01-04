package com.chaskify.data.model.chaskify;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 3/01/18.
 */

public class RealmProfile extends RealmObject {
    public static final String DRIVER_ID = "driverId";
    public String teamName;
    public String email;
    public String phone;
    public String transportTypeId;
    public String transportTypeId2;
    public String transportDescription;
    public String licencePlate;
    public String color;
    @PrimaryKey
    public String driverId;
    public String driverPicture;
    public String username;

    public RealmProfile() {
    }

    public String getTeamName() {
        return teamName;
    }

    public RealmProfile setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RealmProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public RealmProfile setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getTransportTypeId() {
        return transportTypeId;
    }

    public RealmProfile setTransportTypeId(String transportTypeId) {
        this.transportTypeId = transportTypeId;
        return this;
    }

    public String getTransportTypeId2() {
        return transportTypeId2;
    }

    public RealmProfile setTransportTypeId2(String transportTypeId2) {
        this.transportTypeId2 = transportTypeId2;
        return this;
    }

    public String getTransportDescription() {
        return transportDescription;
    }

    public RealmProfile setTransportDescription(String transportDescription) {
        this.transportDescription = transportDescription;
        return this;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public RealmProfile setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
        return this;
    }

    public String getColor() {
        return color;
    }

    public RealmProfile setColor(String color) {
        this.color = color;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public RealmProfile setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getDriverPicture() {
        return driverPicture;
    }

    public RealmProfile setDriverPicture(String driverPicture) {
        this.driverPicture = driverPicture;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RealmProfile setUsername(String username) {
        this.username = username;
        return this;
    }
}
