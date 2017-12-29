package com.chaskify.domain.model;

import java.util.Date;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypoint {
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

    public TaskWaypoint() {
    }

    public String getId() {
        return id;
    }

    public TaskWaypoint setId(String id) {
        this.id = id;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public TaskWaypoint setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public TaskWaypoint setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public TaskWaypoint setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public TaskWaypoint setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public TaskWaypoint setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public TaskWaypoint setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getType() {
        return type;
    }

    public TaskWaypoint setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskWaypoint setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getWaypointDescription() {
        return waypointDescription;
    }

    public TaskWaypoint setWaypointDescription(String waypointDescription) {
        this.waypointDescription = waypointDescription;
        return this;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public TaskWaypoint setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }
}
