package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Icons {

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

    public Icons() {
    }

    public String getDriverIconOffline() {
        return driverIconOffline;
    }

    public Icons setDriverIconOffline(String driverIconOffline) {
        this.driverIconOffline = driverIconOffline;
        return this;
    }

    public String getLogoCompany() {
        return logoCompany;
    }

    public Icons setLogoCompany(String logoCompany) {
        this.logoCompany = logoCompany;
        return this;
    }

    public String getLogoDriver() {
        return logoDriver;
    }

    public Icons setLogoDriver(String logoDriver) {
        this.logoDriver = logoDriver;
        return this;
    }

    public String getLogoDriverRoute() {
        return logoDriverRoute;
    }

    public Icons setLogoDriverRoute(String logoDriverRoute) {
        this.logoDriverRoute = logoDriverRoute;
        return this;
    }

    public String getLogoDeliveryLocation() {
        return logoDeliveryLocation;
    }

    public Icons setLogoDeliveryLocation(String logoDeliveryLocation) {
        this.logoDeliveryLocation = logoDeliveryLocation;
        return this;
    }

    public String getLogoCompletedDelivery() {
        return logoCompletedDelivery;
    }

    public Icons setLogoCompletedDelivery(String logoCompletedDelivery) {
        this.logoCompletedDelivery = logoCompletedDelivery;
        return this;
    }

    public String getDeliveryIconFailed() {
        return deliveryIconFailed;
    }

    public Icons setDeliveryIconFailed(String deliveryIconFailed) {
        this.deliveryIconFailed = deliveryIconFailed;
        return this;
    }

    public String getLogoPickupLocation() {
        return logoPickupLocation;
    }

    public Icons setLogoPickupLocation(String logoPickupLocation) {
        this.logoPickupLocation = logoPickupLocation;
        return this;
    }

    public String getLogoCompletedPickup() {
        return logoCompletedPickup;
    }

    public Icons setLogoCompletedPickup(String logoCompletedPickup) {
        this.logoCompletedPickup = logoCompletedPickup;
        return this;
    }

    public String getPickupIconFailed() {
        return pickupIconFailed;
    }

    public Icons setPickupIconFailed(String pickupIconFailed) {
        this.pickupIconFailed = pickupIconFailed;
        return this;
    }

    public String getLogoServiceLocation() {
        return logoServiceLocation;
    }

    public Icons setLogoServiceLocation(String logoServiceLocation) {
        this.logoServiceLocation = logoServiceLocation;
        return this;
    }

    public String getLogoCompletedService() {
        return logoCompletedService;
    }

    public Icons setLogoCompletedService(String logoCompletedService) {
        this.logoCompletedService = logoCompletedService;
        return this;
    }

    public String getServiceIconFailed() {
        return serviceIconFailed;
    }

    public Icons setServiceIconFailed(String serviceIconFailed) {
        this.serviceIconFailed = serviceIconFailed;
        return this;
    }
}