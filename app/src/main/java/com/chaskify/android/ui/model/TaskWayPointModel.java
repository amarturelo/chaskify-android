package com.chaskify.android.ui.model;

import java.util.Date;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWayPointModel {
    private String id;
    private String taskId;
    private String customerName;
    private String emailAddress;
    private String contactNumber;
    private String deliveryAddress;
    private Date dateCreated;
    private String type;
    private String status;
    private String waypointDescription;
    private String taskStatus;


    public TaskWayPointModel() {
    }

    public String getId() {
        return id;
    }

    public TaskWayPointModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public TaskWayPointModel setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public TaskWayPointModel setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public TaskWayPointModel setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public TaskWayPointModel setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public TaskWayPointModel setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public TaskWayPointModel setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getType() {
        return type;
    }

    public TaskWayPointModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskWayPointModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getWaypointDescription() {
        return waypointDescription;
    }

    public TaskWayPointModel setWaypointDescription(String waypointDescription) {
        this.waypointDescription = waypointDescription;
        return this;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public TaskWayPointModel setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }
}
