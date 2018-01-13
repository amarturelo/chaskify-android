package com.chaskify.data.realm.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 27/12/17.
 */

public class RealmTaskHistory extends RealmObject {
    @PrimaryKey
    private String id;
    private String status;
    private String remarks;
    private String taskId;
    private String reason;
    private String customerSignature;
    private String customerPicture;
    private String notificationViewed;
    private String driverId;
    private String driverLocationLat;
    private String driverLocationLng;
    private Date dateCreated;
    private String ipAddress;
    private String read;
    private String statusRaw;
    private String time;
    private String date;

    public RealmTaskHistory() {
    }

    public String getId() {
        return id;
    }

    public RealmTaskHistory setId(String id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RealmTaskHistory setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public RealmTaskHistory setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public RealmTaskHistory setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public Object getReason() {
        return reason;
    }

    public RealmTaskHistory setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Object getCustomerSignature() {
        return customerSignature;
    }

    public RealmTaskHistory setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
        return this;
    }

    public Object getCustomerPicture() {
        return customerPicture;
    }

    public RealmTaskHistory setCustomerPicture(String customerPicture) {
        this.customerPicture = customerPicture;
        return this;
    }

    public String getNotificationViewed() {
        return notificationViewed;
    }

    public RealmTaskHistory setNotificationViewed(String notificationViewed) {
        this.notificationViewed = notificationViewed;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public RealmTaskHistory setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getDriverLocationLat() {
        return driverLocationLat;
    }

    public RealmTaskHistory setDriverLocationLat(String driverLocationLat) {
        this.driverLocationLat = driverLocationLat;
        return this;
    }

    public String getDriverLocationLng() {
        return driverLocationLng;
    }

    public RealmTaskHistory setDriverLocationLng(String driverLocationLng) {
        this.driverLocationLng = driverLocationLng;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public RealmTaskHistory setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public RealmTaskHistory setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getRead() {
        return read;
    }

    public RealmTaskHistory setRead(String read) {
        this.read = read;
        return this;
    }

    public String getStatusRaw() {
        return statusRaw;
    }

    public RealmTaskHistory setStatusRaw(String statusRaw) {
        this.statusRaw = statusRaw;
        return this;
    }

    public String getTime() {
        return time;
    }

    public RealmTaskHistory setTime(String time) {
        this.time = time;
        return this;
    }

    public String getDate() {
        return date;
    }

    public RealmTaskHistory setDate(String date) {
        this.date = date;
        return this;
    }
}
