package com.chaskify.android.ui.model;

import java.util.Date;

/**
 * Created by alberto on 6/12/17.
 */

public class TaskItemSnapModel {
    private String task_id;
    private String status;
    private String trans_type; //service
    private Date delivery_date;
    private String delivery_address;

    public TaskItemSnapModel() {
    }

    public String getTask_id() {
        return task_id;
    }

    public TaskItemSnapModel setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskItemSnapModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public TaskItemSnapModel setTrans_type(String trans_type) {
        this.trans_type = trans_type;
        return this;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public TaskItemSnapModel setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
        return this;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public TaskItemSnapModel setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
        return this;
    }
}
