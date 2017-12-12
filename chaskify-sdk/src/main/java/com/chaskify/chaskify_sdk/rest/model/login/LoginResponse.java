package com.chaskify.chaskify_sdk.rest.model.login;

import com.chaskify.chaskify_sdk.rest.model.Icons;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("remember")
    @Expose
    private String remember;
    @SerializedName("todays_date")
    @Expose
    private String todaysDate;    //public String refreshToken;
    @SerializedName("on_duty")
    @Expose
    private int onDuty;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("duty_status")
    @Expose
    private String dutyStatus;
    @SerializedName("location_accuracy")
    @Expose
    private int locationAccuracy;
    @SerializedName("icons")
    @Expose
    private Icons icons;

    public LoginResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public String getTodaysDate() {
        return todaysDate;
    }

    public void setTodaysDate(String todaysDate) {
        this.todaysDate = todaysDate;
    }

    public int getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(int onDuty) {
        this.onDuty = onDuty;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDutyStatus() {
        return dutyStatus;
    }

    public void setDutyStatus(String dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    public int getLocationAccuracy() {
        return locationAccuracy;
    }

    public void setLocationAccuracy(int locationAccuracy) {
        this.locationAccuracy = locationAccuracy;
    }

    public Icons getIcons() {
        return icons;
    }

    public LoginResponse setIcons(Icons icons) {
        this.icons = icons;
        return this;
    }




    /*{
        "username": "amarturelo",
            "password": "demo",
            "remember": "",
            "todays_date": "Dec, 07",
            "on_duty": 1,
            "token": "50e6136270e33c7ce418e9c9581b4d18",
            "duty_status": "1",
            "location_accuracy": 1,
            "icons": {
        "logo_company": "https://s3-us-west-2.amazonaws.com/chaskify-backend-uploads/logo_company-42261060377.png",
                "logo_customer_location": "logo_customer_location-GAKSJMKC86PUZVGWQV98.png",
                "driver_icon_offline": "http://customer.chaskify.com/assets/images/driver-offline.png",
                "logo_driver": "http://customer.chaskify.com/assets/images/logo_driver.png",
                "logo_driver_route": "http://customer.chaskify.com/assets/images/logo_driver_route.png",
                "logo_delivery_location": "http://customer.chaskify.com/assets/images/logo_delivery_location.png",
                "logo_completed_delivery": "http://customer.chaskify.com/assets/images/logo_completed_delivery.png",
                "delivery_icon_failed": "http://customer.chaskify.com/assets/images/delivery_icon_failed.png",
                "logo_pickup_location": "http://customer.chaskify.com/assets/images/logo_pickup_location.png",
                "logo_completed_pickup": "http://customer.chaskify.com/assets/images/logo_completed_pickup.png",
                "pickup_icon_failed": "http://customer.chaskify.com/assets/images/delivery_icon_failed.png",
                "logo_service_location": "http://customer.chaskify.com/assets/images/logo_service_location.png",
                "logo_completed_service": "http://customer.chaskify.com/assets/images/logo_completed_service.png",
                "service_icon_failed": "http://customer.chaskify.com/assets/images/delivery_icon_failed.png"
    }
    }*/

}