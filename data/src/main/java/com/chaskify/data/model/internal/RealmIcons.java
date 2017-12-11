package com.chaskify.data.model.internal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealmIcons {

    @SerializedName("driver_icon_offline")
    @Expose
    private String driverIconOffline;
    @SerializedName("logo_company")
    @Expose
    private String logoCompany;
    @SerializedName("logo_driver")
    @Expose
    private String logoDriver;
    @SerializedName("logo_driver_route")
    @Expose
    private String logoDriverRoute;
    @SerializedName("logo_delivery_location")
    @Expose
    private String logoDeliveryLocation;
    @SerializedName("logo_completed_delivery")
    @Expose
    private String logoCompletedDelivery;
    @SerializedName("delivery_icon_failed")
    @Expose
    private String deliveryIconFailed;
    @SerializedName("logo_pickup_location")
    @Expose
    private String logoPickupLocation;
    @SerializedName("logo_completed_pickup")
    @Expose
    private String logoCompletedPickup;
    @SerializedName("pickup_icon_failed")
    @Expose
    private String pickupIconFailed;
    @SerializedName("logo_service_location")
    @Expose
    private String logoServiceLocation;
    @SerializedName("logo_completed_service")
    @Expose
    private String logoCompletedService;
    @SerializedName("service_icon_failed")
    @Expose
    private String serviceIconFailed;

    public String getDriverIconOffline() {
        return driverIconOffline;
    }

    public void setDriverIconOffline(String driverIconOffline) {
        this.driverIconOffline = driverIconOffline;
    }

    public String getLogoCompany() {
        return logoCompany;
    }

    public void setLogoCompany(String logoCompany) {
        this.logoCompany = logoCompany;
    }

    public String getLogoDriver() {
        return logoDriver;
    }

    public void setLogoDriver(String logoDriver) {
        this.logoDriver = logoDriver;
    }

    public String getLogoDriverRoute() {
        return logoDriverRoute;
    }

    public void setLogoDriverRoute(String logoDriverRoute) {
        this.logoDriverRoute = logoDriverRoute;
    }

    public String getLogoDeliveryLocation() {
        return logoDeliveryLocation;
    }

    public void setLogoDeliveryLocation(String logoDeliveryLocation) {
        this.logoDeliveryLocation = logoDeliveryLocation;
    }

    public String getLogoCompletedDelivery() {
        return logoCompletedDelivery;
    }

    public void setLogoCompletedDelivery(String logoCompletedDelivery) {
        this.logoCompletedDelivery = logoCompletedDelivery;
    }

    public String getDeliveryIconFailed() {
        return deliveryIconFailed;
    }

    public void setDeliveryIconFailed(String deliveryIconFailed) {
        this.deliveryIconFailed = deliveryIconFailed;
    }

    public String getLogoPickupLocation() {
        return logoPickupLocation;
    }

    public void setLogoPickupLocation(String logoPickupLocation) {
        this.logoPickupLocation = logoPickupLocation;
    }

    public String getLogoCompletedPickup() {
        return logoCompletedPickup;
    }

    public void setLogoCompletedPickup(String logoCompletedPickup) {
        this.logoCompletedPickup = logoCompletedPickup;
    }

    public String getPickupIconFailed() {
        return pickupIconFailed;
    }

    public void setPickupIconFailed(String pickupIconFailed) {
        this.pickupIconFailed = pickupIconFailed;
    }

    public String getLogoServiceLocation() {
        return logoServiceLocation;
    }

    public void setLogoServiceLocation(String logoServiceLocation) {
        this.logoServiceLocation = logoServiceLocation;
    }

    public String getLogoCompletedService() {
        return logoCompletedService;
    }

    public void setLogoCompletedService(String logoCompletedService) {
        this.logoCompletedService = logoCompletedService;
    }

    public String getServiceIconFailed() {
        return serviceIconFailed;
    }

    public void setServiceIconFailed(String serviceIconFailed) {
        this.serviceIconFailed = serviceIconFailed;
    }

}