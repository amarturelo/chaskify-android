package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by alberto on 6/12/17.
 */

public class ChaskifyNotification {
    /*
            "push_id": "2096",
            "customer_id": "33",
            "device_platform": "Android",
            "device_id": "86ba6653-00d5-4008-8025-bcd33a8d3a21",
            "push_title": "ASSIGN TASK",
            "push_message": "You have new assign task 3643",
            "push_type": "task",
            "actions": "ASSIGN_TASK",
            "mStatus": "process",
            "json_response": "{\"success\":200}",
            "driver_id": "175",
            "task_id": "3643",
            "date_created": "Dic 04,2017 14:00:40",
            "date_process": "2017-12-04 19:00:43",
            "ip_address": "172.31.0.231",
            "is_read": "2",
            "message": null
    * */
    @SerializedName("push_id")
    @Expose
    public String pushId;
    @SerializedName("customer_id")
    @Expose
    public String customerId;
    @SerializedName("device_platform")
    @Expose
    public String devicePlatform;
    @SerializedName("device_id")
    @Expose
    public String deviceId;
    @SerializedName("push_title")
    @Expose
    public String pushTitle;
    @SerializedName("push_message")
    @Expose
    public String pushMessage;
    @SerializedName("push_type")
    @Expose
    public String pushType;
    @SerializedName("actions")
    @Expose
    public String actions;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("json_response")
    @Expose
    public String jsonResponse;
    @SerializedName("driver_id")
    @Expose
    public String driverId;
    @SerializedName("task_id")
    @Expose
    public String taskId;
    @SerializedName("date_created")
    @Expose
    public String dateCreated;
    @SerializedName("date_process")
    @Expose
    public String dateProcess;
    @SerializedName("ip_address")
    @Expose
    public String ipAddress;
    @SerializedName("is_read")
    @Expose
    public String isRead;
    @SerializedName("message")
    @Expose
    public Object message;

    public ChaskifyNotification() {
    }

    public String getPushId() {
        return pushId;
    }

    public ChaskifyNotification setPushId(String pushId) {
        this.pushId = pushId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public ChaskifyNotification setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public ChaskifyNotification setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public ChaskifyNotification setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public ChaskifyNotification setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
        return this;
    }

    public String getPushMessage() {
        return pushMessage;
    }

    public ChaskifyNotification setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
        return this;
    }

    public String getPushType() {
        return pushType;
    }

    public ChaskifyNotification setPushType(String pushType) {
        this.pushType = pushType;
        return this;
    }

    public String getActions() {
        return actions;
    }

    public ChaskifyNotification setActions(String actions) {
        this.actions = actions;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ChaskifyNotification setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public ChaskifyNotification setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public ChaskifyNotification setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public ChaskifyNotification setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public ChaskifyNotification setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getDateProcess() {
        return dateProcess;
    }

    public ChaskifyNotification setDateProcess(String dateProcess) {
        this.dateProcess = dateProcess;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public ChaskifyNotification setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getIsRead() {
        return isRead;
    }

    public ChaskifyNotification setIsRead(String isRead) {
        this.isRead = isRead;
        return this;
    }

    public Object getMessage() {
        return message;
    }

    public ChaskifyNotification setMessage(Object message) {
        this.message = message;
        return this;
    }
}
