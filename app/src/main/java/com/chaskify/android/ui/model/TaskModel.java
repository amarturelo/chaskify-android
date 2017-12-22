package com.chaskify.android.ui.model;

import java.util.Date;

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
}
