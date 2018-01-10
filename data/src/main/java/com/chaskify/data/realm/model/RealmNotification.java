package com.chaskify.data.realm.model;

import io.realm.RealmObject;

/**
 * Created by alberto on 17/12/17.
 */

public class RealmNotification extends RealmObject{
    public static String DRIVER_ID = "driverId";
    private String pushId;
    private String customerId;
    private String devicePlatform;
    private String deviceId;
    private String pushTitle;
    private String pushMessage;
    private String pushType;
    private String actions;
    private String status;
    private String jsonResponse;
    private String driverId;
    private String taskId;
    private String dateCreated;
    private String dateProcess;
    private String ipAddress;
    private String isRead;

    public RealmNotification() {
    }

    public String getPushId() {
        return pushId;
    }

    public RealmNotification setPushId(String pushId) {
        this.pushId = pushId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public RealmNotification setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public RealmNotification setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public RealmNotification setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public RealmNotification setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
        return this;
    }

    public String getPushMessage() {
        return pushMessage;
    }

    public RealmNotification setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
        return this;
    }

    public String getPushType() {
        return pushType;
    }

    public RealmNotification setPushType(String pushType) {
        this.pushType = pushType;
        return this;
    }

    public String getActions() {
        return actions;
    }

    public RealmNotification setActions(String actions) {
        this.actions = actions;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RealmNotification setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public RealmNotification setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public RealmNotification setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public RealmNotification setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public RealmNotification setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getDateProcess() {
        return dateProcess;
    }

    public RealmNotification setDateProcess(String dateProcess) {
        this.dateProcess = dateProcess;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public RealmNotification setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getIsRead() {
        return isRead;
    }

    public RealmNotification setIsRead(String isRead) {
        this.isRead = isRead;
        return this;
    }

}
