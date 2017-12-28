package com.chaskify.android.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by alberto on 20/12/17.
 */

public class TaskModel {
    private String taskId;
    private String status;
    private String transType; //service
    private String customerName;
    private Date deliveryDate;
    private String deliveryAddress;
    private String description;
    private String contactNumber;
    private String emailAddress;
    private List<TaskWaypointItemModel> taskWaypointItemModels;
    private List<TaskHistoryItemModel> taskHistoryItemModels;

    public TaskModel() {
    }

    public String getTaskId() {
        return taskId;
    }

    public TaskModel setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public TaskModel setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public TaskModel setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getTransType() {
        return transType;
    }

    public TaskModel setTransType(String transType) {
        this.transType = transType;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public TaskModel setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public TaskModel setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public TaskModel setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public List<TaskWaypointItemModel> getTaskWaypointItemModels() {
        return taskWaypointItemModels;
    }

    public TaskModel setTaskWaypointItemModels(List<TaskWaypointItemModel> taskWaypointItemModels) {
        this.taskWaypointItemModels = taskWaypointItemModels;
        return this;
    }

    public List<TaskHistoryItemModel> getTaskHistoryItemModels() {
        return taskHistoryItemModels;
    }

    public TaskModel setTaskHistoryItemModels(List<TaskHistoryItemModel> taskHistoryItemModels) {
        this.taskHistoryItemModels = taskHistoryItemModels;
        return this;
    }

    public TaskModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }
}
