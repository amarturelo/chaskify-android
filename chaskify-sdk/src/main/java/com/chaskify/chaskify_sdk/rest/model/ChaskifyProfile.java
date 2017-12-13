package com.chaskify.chaskify_sdk.rest.model;

/**
 * Created by alberto on 7/12/17.
 */

public class ChaskifyProfile {
    /* "team_name": "Metropolis Team",
             "email": "amarturelo@gmail.com",
             "phone": "+5352950107",
             "transport_type_id": "truck",
             "transport_type_id2": "Truck",
             "transport_description": "Hunday Santa Fe, 2016",
             "licence_plate": "W456t",
             "color": "Red",
             "driver_id": "175",
             "driver_picture": ""*/
    private String team_name;
    private String email;
    private String phone;
    private String transport_type_id;
    private String transport_type_id2;
    private String transport_description;
    private String licence_plate;
    private String color;
    private String driver_id;
    private String driver_picture;

    public ChaskifyProfile() {
    }

    public String getTeam_name() {
        return team_name;
    }

    public ChaskifyProfile setTeam_name(String team_name) {
        this.team_name = team_name;
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

    public String getTransport_type_id() {
        return transport_type_id;
    }

    public ChaskifyProfile setTransport_type_id(String transport_type_id) {
        this.transport_type_id = transport_type_id;
        return this;
    }

    public String getTransport_type_id2() {
        return transport_type_id2;
    }

    public ChaskifyProfile setTransport_type_id2(String transport_type_id2) {
        this.transport_type_id2 = transport_type_id2;
        return this;
    }

    public String getTransport_description() {
        return transport_description;
    }

    public ChaskifyProfile setTransport_description(String transport_description) {
        this.transport_description = transport_description;
        return this;
    }

    public String getLicence_plate() {
        return licence_plate;
    }

    public ChaskifyProfile setLicence_plate(String licence_plate) {
        this.licence_plate = licence_plate;
        return this;
    }

    public String getColor() {
        return color;
    }

    public ChaskifyProfile setColor(String color) {
        this.color = color;
        return this;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public ChaskifyProfile setDriver_id(String driver_id) {
        this.driver_id = driver_id;
        return this;
    }

    public String getDriver_picture() {
        return driver_picture;
    }

    public ChaskifyProfile setDriver_picture(String driver_picture) {
        this.driver_picture = driver_picture;
        return this;
    }
}
