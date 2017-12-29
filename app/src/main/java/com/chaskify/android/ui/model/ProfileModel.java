package com.chaskify.android.ui.model;

/**
 * Created by alberto on 29/12/17.
 */

public class ProfileModel {
    private String teamName;
    private String email;
    private String phone;
    private String transportTypeId;
    private String transportTypeId2;
    private String transportDescription;
    private String licencePlate;
    private String color;
    private String driverId;
    private String driverPicture;
    private String username;

    public ProfileModel() {
    }

    public String getTeamName() {
        return teamName;
    }

    public ProfileModel setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ProfileModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getTransportTypeId() {
        return transportTypeId;
    }

    public ProfileModel setTransportTypeId(String transportTypeId) {
        this.transportTypeId = transportTypeId;
        return this;
    }

    public String getTransportTypeId2() {
        return transportTypeId2;
    }

    public ProfileModel setTransportTypeId2(String transportTypeId2) {
        this.transportTypeId2 = transportTypeId2;
        return this;
    }

    public String getTransportDescription() {
        return transportDescription;
    }

    public ProfileModel setTransportDescription(String transportDescription) {
        this.transportDescription = transportDescription;
        return this;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public ProfileModel setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
        return this;
    }

    public String getColor() {
        return color;
    }

    public ProfileModel setColor(String color) {
        this.color = color;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public ProfileModel setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getDriverPicture() {
        return driverPicture;
    }

    public ProfileModel setDriverPicture(String driverPicture) {
        this.driverPicture = driverPicture;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProfileModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "teamName='" + teamName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", transportTypeId='" + transportTypeId + '\'' +
                ", transportTypeId2='" + transportTypeId2 + '\'' +
                ", transportDescription='" + transportDescription + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", color='" + color + '\'' +
                ", driverId='" + driverId + '\'' +
                ", driverPicture='" + driverPicture + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
