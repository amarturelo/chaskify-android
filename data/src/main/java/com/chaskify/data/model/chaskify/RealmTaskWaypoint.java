package com.chaskify.data.model.chaskify;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by alberto on 27/12/17.
 */

public class RealmTaskWaypoint extends RealmObject {
    private String id;
    private String taskId;
    private String customerName;
    private String emailAddress;
    private String contactNumber;
    private String deliveryAddress;
    private String taskLat;
    private String taskLng;
    private String priority;
    private Date dateCreated;
    private Date dateModified;
    private String type;
    private String status;
    private String customerSignature;
    private String customerPicture;
    private String ipAddress;
    private String totalTime;
    private String totalMiles;
    private String orderNumber;
    private String waypointDescription;
    private String customerSignatureUrl;
    private String taskStatus;


    public RealmTaskWaypoint() {
    }

    public String getId() {
        return id;
    }

    public RealmTaskWaypoint setId(String id) {
        this.id = id;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public RealmTaskWaypoint setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public RealmTaskWaypoint setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public RealmTaskWaypoint setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public RealmTaskWaypoint setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public RealmTaskWaypoint setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public String getTaskLat() {
        return taskLat;
    }

    public RealmTaskWaypoint setTaskLat(String taskLat) {
        this.taskLat = taskLat;
        return this;
    }

    public String getTaskLng() {
        return taskLng;
    }

    public RealmTaskWaypoint setTaskLng(String taskLng) {
        this.taskLng = taskLng;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public RealmTaskWaypoint setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public RealmTaskWaypoint setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public RealmTaskWaypoint setDateModified(Date dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public String getType() {
        return type;
    }

    public RealmTaskWaypoint setType(String type) {
        this.type = type;
        return this;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public RealmTaskWaypoint setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RealmTaskWaypoint setStatus(String status) {
        this.status = status;
        return this;
    }

    public Object getCustomerSignature() {
        return customerSignature;
    }

    public RealmTaskWaypoint setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
        return this;
    }

    public RealmTaskWaypoint setCustomerPicture(String customerPicture) {
        this.customerPicture = customerPicture;
        return this;
    }

    public RealmTaskWaypoint setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public Object getCustomerPicture() {
        return customerPicture;
    }


    public Object getIpAddress() {
        return ipAddress;
    }


    public String getTotalTime() {
        return totalTime;
    }

    public RealmTaskWaypoint setTotalTime(String totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    public String getTotalMiles() {
        return totalMiles;
    }

    public RealmTaskWaypoint setTotalMiles(String totalMiles) {
        this.totalMiles = totalMiles;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public RealmTaskWaypoint setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getWaypointDescription() {
        return waypointDescription;
    }

    public RealmTaskWaypoint setWaypointDescription(String waypointDescription) {
        this.waypointDescription = waypointDescription;
        return this;
    }

    public String getCustomerSignatureUrl() {
        return customerSignatureUrl;
    }

    public RealmTaskWaypoint setCustomerSignatureUrl(String customerSignatureUrl) {
        this.customerSignatureUrl = customerSignatureUrl;
        return this;
    }
}
