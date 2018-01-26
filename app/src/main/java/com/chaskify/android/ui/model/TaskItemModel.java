package com.chaskify.android.ui.model;

import java.util.Date;

/**
 * Created by alberto on 15/12/17.
 */

public class TaskItemModel {
    private String task_id;
    private String status;
    private String orderNumber;
    private String trans_type; //service
    private String customer_name;
    private Date delivery_date;
    private String delivery_address;


    public TaskItemModel() {
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public TaskItemModel setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getTaskId() {
        return task_id;
    }

    public TaskItemModel setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskItemModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public TaskItemModel setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
        return this;
    }

    public String getTransType() {
        return trans_type;
    }

    public TaskItemModel setTrans_type(String trans_type) {
        this.trans_type = trans_type;
        return this;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public TaskItemModel setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
        return this;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public TaskItemModel setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskItemModel)) return false;

        TaskItemModel that = (TaskItemModel) o;

        if (task_id != null ? !task_id.equals(that.task_id) : that.task_id != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null)
            return false;
        if (trans_type != null ? !trans_type.equals(that.trans_type) : that.trans_type != null)
            return false;
        if (customer_name != null ? !customer_name.equals(that.customer_name) : that.customer_name != null)
            return false;
        if (delivery_date != null ? !delivery_date.equals(that.delivery_date) : that.delivery_date != null)
            return false;
        return delivery_address != null ? delivery_address.equals(that.delivery_address) : that.delivery_address == null;
    }

    @Override
    public int hashCode() {
        return task_id != null ? task_id.hashCode() : 0;
    }
}
