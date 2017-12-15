package com.chaskify.android.ui.model;

import java.util.Date;

/**
 * Created by alberto on 15/12/17.
 */

public class TaskItemModel {
    private String task_id;
    private String status;
    private String trans_type; //service
    private String customer_name;
    private Date delivery_date;
    private String delivery_address;




    public TaskItemModel() {
    }

    public String getTask_id() {
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

    public String getTrans_type() {
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

        return task_id != null ? task_id.equals(that.task_id) : that.task_id == null;
    }

    @Override
    public int hashCode() {
        return task_id != null ? task_id.hashCode() : 0;
    }
}
