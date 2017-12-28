package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ChaskifyTaskHistory {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("task_id")
    @Expose
    private String taskId;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("customer_signature")
    @Expose
    private String customerSignature;
    @SerializedName("customer_picture")
    @Expose
    private String customerPicture;
    @SerializedName("notification_viewed")
    @Expose
    private String notificationViewed;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("driver_location_lat")
    @Expose
    private String driverLocationLat;
    @SerializedName("driver_location_lng")
    @Expose
    private String driverLocationLng;
    @SerializedName("date_created")
    @Expose
    private Date dateCreated;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("read")
    @Expose
    private String read;
    @SerializedName("status_raw")
    @Expose
    private String statusRaw;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("date")
    @Expose
    private String date;

    public ChaskifyTaskHistory() {
    }

    public String getId() {
        return id;
    }

    public ChaskifyTaskHistory setId(String id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ChaskifyTaskHistory setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public ChaskifyTaskHistory setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public ChaskifyTaskHistory setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public ChaskifyTaskHistory setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public ChaskifyTaskHistory setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
        return this;
    }

    public ChaskifyTaskHistory setCustomerPicture(String customerPicture) {
        this.customerPicture = customerPicture;
        return this;
    }

    public Object getCustomerSignature() {
        return customerSignature;
    }



    public Object getCustomerPicture() {
        return customerPicture;
    }



    public String getNotificationViewed() {
        return notificationViewed;
    }

    public ChaskifyTaskHistory setNotificationViewed(String notificationViewed) {
        this.notificationViewed = notificationViewed;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public ChaskifyTaskHistory setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getDriverLocationLat() {
        return driverLocationLat;
    }

    public ChaskifyTaskHistory setDriverLocationLat(String driverLocationLat) {
        this.driverLocationLat = driverLocationLat;
        return this;
    }

    public String getDriverLocationLng() {
        return driverLocationLng;
    }

    public ChaskifyTaskHistory setDriverLocationLng(String driverLocationLng) {
        this.driverLocationLng = driverLocationLng;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public ChaskifyTaskHistory setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public ChaskifyTaskHistory setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getRead() {
        return read;
    }

    public ChaskifyTaskHistory setRead(String read) {
        this.read = read;
        return this;
    }

    public String getStatusRaw() {
        return statusRaw;
    }

    public ChaskifyTaskHistory setStatusRaw(String statusRaw) {
        this.statusRaw = statusRaw;
        return this;
    }

    public String getTime() {
        return time;
    }

    public ChaskifyTaskHistory setTime(String time) {
        this.time = time;
        return this;
    }

    public String getDate() {
        return date;
    }

    public ChaskifyTaskHistory setDate(String date) {
        this.date = date;
        return this;
    }
}