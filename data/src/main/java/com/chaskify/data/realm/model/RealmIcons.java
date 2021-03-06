package com.chaskify.data.realm.model;

import com.chaskify.domain.model.Icons;

import io.realm.RealmObject;

public class RealmIcons extends RealmObject {

    private String driverIconOffline;
    private String logoCompany;
    private String logoDriver;
    private String logoDriverRoute;
    private String logoDeliveryLocation;
    private String logoCompletedDelivery;
    private String deliveryIconFailed;
    private String logoPickupLocation;
    private String logoCompletedPickup;
    private String pickupIconFailed;
    private String logoServiceLocation;
    private String logoCompletedService;
    private String serviceIconFailed;

    public RealmIcons() {
    }

    public String getDriverIconOffline() {
        return driverIconOffline;
    }

    public RealmIcons setDriverIconOffline(String driverIconOffline) {
        this.driverIconOffline = driverIconOffline;
        return this;
    }

    public String getLogoCompany() {
        return logoCompany;
    }

    public RealmIcons setLogoCompany(String logoCompany) {
        this.logoCompany = logoCompany;
        return this;
    }

    public String getLogoDriver() {
        return logoDriver;
    }

    public RealmIcons setLogoDriver(String logoDriver) {
        this.logoDriver = logoDriver;
        return this;
    }

    public String getLogoDriverRoute() {
        return logoDriverRoute;
    }

    public RealmIcons setLogoDriverRoute(String logoDriverRoute) {
        this.logoDriverRoute = logoDriverRoute;
        return this;
    }

    public String getLogoDeliveryLocation() {
        return logoDeliveryLocation;
    }

    public RealmIcons setLogoDeliveryLocation(String logoDeliveryLocation) {
        this.logoDeliveryLocation = logoDeliveryLocation;
        return this;
    }

    public String getLogoCompletedDelivery() {
        return logoCompletedDelivery;
    }

    public RealmIcons setLogoCompletedDelivery(String logoCompletedDelivery) {
        this.logoCompletedDelivery = logoCompletedDelivery;
        return this;
    }

    public String getDeliveryIconFailed() {
        return deliveryIconFailed;
    }

    public RealmIcons setDeliveryIconFailed(String deliveryIconFailed) {
        this.deliveryIconFailed = deliveryIconFailed;
        return this;
    }

    public String getLogoPickupLocation() {
        return logoPickupLocation;
    }

    public RealmIcons setLogoPickupLocation(String logoPickupLocation) {
        this.logoPickupLocation = logoPickupLocation;
        return this;
    }

    public String getLogoCompletedPickup() {
        return logoCompletedPickup;
    }

    public RealmIcons setLogoCompletedPickup(String logoCompletedPickup) {
        this.logoCompletedPickup = logoCompletedPickup;
        return this;
    }

    public String getPickupIconFailed() {
        return pickupIconFailed;
    }

    public RealmIcons setPickupIconFailed(String pickupIconFailed) {
        this.pickupIconFailed = pickupIconFailed;
        return this;
    }

    public String getLogoServiceLocation() {
        return logoServiceLocation;
    }

    public RealmIcons setLogoServiceLocation(String logoServiceLocation) {
        this.logoServiceLocation = logoServiceLocation;
        return this;
    }

    public String getLogoCompletedService() {
        return logoCompletedService;
    }

    public RealmIcons setLogoCompletedService(String logoCompletedService) {
        this.logoCompletedService = logoCompletedService;
        return this;
    }

    public String getServiceIconFailed() {
        return serviceIconFailed;
    }

    public RealmIcons setServiceIconFailed(String serviceIconFailed) {
        this.serviceIconFailed = serviceIconFailed;
        return this;
    }

    public Icons toDomain() {
        return new Icons()
                .setDriverIconOffline(getDriverIconOffline())
                .setLogoCompany(getLogoCompany())
                .setLogoDriver(getLogoDriver())
                .setLogoDriverRoute(getLogoDriverRoute())
                .setLogoDeliveryLocation(getLogoDeliveryLocation())
                .setDriverIconOffline(getDriverIconOffline())
                .setLogoDeliveryLocation(getLogoDeliveryLocation())
                .setLogoCompletedPickup(getLogoCompletedPickup())
                .setLogoPickupLocation(getLogoPickupLocation())
                .setLogoCompletedDelivery(getLogoCompletedDelivery())
                .setDeliveryIconFailed(getDeliveryIconFailed())
                .setPickupIconFailed(getPickupIconFailed())
                .setLogoServiceLocation(getLogoServiceLocation())
                .setLogoCompletedService(getLogoCompletedService())
                .setServiceIconFailed(getServiceIconFailed())
                ;

    }
}