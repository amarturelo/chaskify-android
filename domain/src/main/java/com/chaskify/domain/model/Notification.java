package com.chaskify.domain.model;

/**
 * Created by alberto on 16/12/17.
 */

public class Notification {
    public String pushId;
    public String customerId;
    public String devicePlatform;
    public String deviceId;
    public String pushTitle;
    public String pushMessage;
    public String pushType;
    public String actions;
    public String status;
    public String jsonResponse;
    public String driverId;
    public String taskId;
    public String dateCreated;
    public String dateProcess;
    public String ipAddress;
    public String isRead;
    public Object message;

    public Notification() {
    }

    public String getPushId() {
        return pushId;
    }

    public Notification setPushId(String pushId) {
        this.pushId = pushId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Notification setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public Notification setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Notification setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public Notification setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
        return this;
    }

    public String getPushMessage() {
        return pushMessage;
    }

    public Notification setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
        return this;
    }

    public String getPushType() {
        return pushType;
    }

    public Notification setPushType(String pushType) {
        this.pushType = pushType;
        return this;
    }

    public String getActions() {
        return actions;
    }

    public Notification setActions(String actions) {
        this.actions = actions;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Notification setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public Notification setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public Notification setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public Notification setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public Notification setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getDateProcess() {
        return dateProcess;
    }

    public Notification setDateProcess(String dateProcess) {
        this.dateProcess = dateProcess;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Notification setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getIsRead() {
        return isRead;
    }

    public Notification setIsRead(String isRead) {
        this.isRead = isRead;
        return this;
    }

    public Object getMessage() {
        return message;
    }

    public Notification setMessage(Object message) {
        this.message = message;
        return this;
    }
}
