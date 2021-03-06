package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by alberto on 28/12/17.
 */

public class ChaskifyTaskWayPoint {

    public enum STATUS {

        START("START"), IN_ROUTE("IN ROUTE"), ACCEPTED("ACCEPTED"), ARRIVED("ARRIVED"),  COMPLETED("COMPLETED");
        private String text;

        STATUS(String s) {
            text = s;
        }

        public String getText() {
            return text;
        }

    }

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("task_id")
    @Expose
    public String taskId;
    @SerializedName("customer_name")
    @Expose
    public String customerName;
    @SerializedName("email_address")
    @Expose
    public String emailAddress;
    @SerializedName("contact_number")
    @Expose
    public String contactNumber;
    @SerializedName("delivery_address")
    @Expose
    public String deliveryAddress;
    @SerializedName("task_lat")
    @Expose
    public String taskLat;
    @SerializedName("task_lng")
    @Expose
    public String taskLng;
    @SerializedName("priority")
    @Expose
    public String priority;
    @SerializedName("date_created")
    @Expose
    public Date dateCreated;
    @SerializedName("date_modified")
    @Expose
    public Date dateModified;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("totalTime")
    @Expose
    public String totalTime;
    @SerializedName("totalMiles")
    @Expose
    public String totalMiles;
    @SerializedName("order_number")
    @Expose
    public String orderNumber;
    @SerializedName("waypoint_description")
    @Expose
    public String waypointDescription;
    @SerializedName("task_status")
    @Expose
    public String taskStatus;

    public ChaskifyTaskWayPoint() {
    }

    public String getId() {
        return id;
    }

    public ChaskifyTaskWayPoint setId(String id) {
        this.id = id;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public ChaskifyTaskWayPoint setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ChaskifyTaskWayPoint setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ChaskifyTaskWayPoint setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public ChaskifyTaskWayPoint setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public ChaskifyTaskWayPoint setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public String getTaskLat() {
        return taskLat;
    }

    public ChaskifyTaskWayPoint setTaskLat(String taskLat) {
        this.taskLat = taskLat;
        return this;
    }

    public String getTaskLng() {
        return taskLng;
    }

    public ChaskifyTaskWayPoint setTaskLng(String taskLng) {
        this.taskLng = taskLng;
        return this;
    }

    public String getPriority() {
        return priority;
    }

    public ChaskifyTaskWayPoint setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public ChaskifyTaskWayPoint setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public ChaskifyTaskWayPoint setDateModified(Date dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public String getType() {
        return type;
    }

    public ChaskifyTaskWayPoint setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ChaskifyTaskWayPoint setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public ChaskifyTaskWayPoint setTotalTime(String totalTime) {
        this.totalTime = totalTime;
        return this;
    }

    public String getTotalMiles() {
        return totalMiles;
    }

    public ChaskifyTaskWayPoint setTotalMiles(String totalMiles) {
        this.totalMiles = totalMiles;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public ChaskifyTaskWayPoint setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getWaypointDescription() {
        return waypointDescription;
    }

    public ChaskifyTaskWayPoint setWaypointDescription(String waypointDescription) {
        this.waypointDescription = waypointDescription;
        return this;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public ChaskifyTaskWayPoint setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
        return this;
    }
}
