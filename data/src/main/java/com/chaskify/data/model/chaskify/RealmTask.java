package com.chaskify.data.model.chaskify;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by alberto on 14/12/17.
 */

public class RealmTask extends RealmObject {
    public static String TASK_ID = "task_id";
    private String task_id;
    private String customer_id;
    private String task_description;
    private String trans_type; //service
    private String contact_number;
    private String email_address;
    private String customer_name;
    private Date delivery_date;
    private String delivery_address;
    private String team_id;
    private String driver_id;

    private String task_lat;
    private String task_lng;

    private String status;
    private Date date_created;
    private Date date_modified;
    private Date assign_started;

    private String delivery_time;

    private String status_raw;//ASSIGNED

    private String trans_type_raw;

    public RealmTask() {
    }

    public String getTask_id() {
        return task_id;
    }

    public RealmTask setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public RealmTask setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public String getTask_description() {
        return task_description;
    }

    public RealmTask setTask_description(String task_description) {
        this.task_description = task_description;
        return this;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public RealmTask setTrans_type(String trans_type) {
        this.trans_type = trans_type;
        return this;
    }

    public String getContact_number() {
        return contact_number;
    }

    public RealmTask setContact_number(String contact_number) {
        this.contact_number = contact_number;
        return this;
    }

    public String getEmail_address() {
        return email_address;
    }

    public RealmTask setEmail_address(String email_address) {
        this.email_address = email_address;
        return this;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public RealmTask setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
        return this;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public RealmTask setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
        return this;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public RealmTask setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
        return this;
    }

    public String getTeam_id() {
        return team_id;
    }

    public RealmTask setTeam_id(String team_id) {
        this.team_id = team_id;
        return this;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public RealmTask setDriver_id(String driver_id) {
        this.driver_id = driver_id;
        return this;
    }

    public String getTask_lat() {
        return task_lat;
    }

    public RealmTask setTask_lat(String task_lat) {
        this.task_lat = task_lat;
        return this;
    }

    public String getTask_lng() {
        return task_lng;
    }

    public RealmTask setTask_lng(String task_lng) {
        this.task_lng = task_lng;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RealmTask setStatus(String status) {
        this.status = status;
        return this;
    }

    public Date getDate_created() {
        return date_created;
    }

    public RealmTask setDate_created(Date date_created) {
        this.date_created = date_created;
        return this;
    }

    public Date getDate_modified() {
        return date_modified;
    }

    public RealmTask setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
        return this;
    }

    public Date getAssign_started() {
        return assign_started;
    }

    public RealmTask setAssign_started(Date assign_started) {
        this.assign_started = assign_started;
        return this;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public RealmTask setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
        return this;
    }

    public String getStatus_raw() {
        return status_raw;
    }

    public RealmTask setStatus_raw(String status_raw) {
        this.status_raw = status_raw;
        return this;
    }

    public String getTrans_type_raw() {
        return trans_type_raw;
    }

    public RealmTask setTrans_type_raw(String trans_type_raw) {
        this.trans_type_raw = trans_type_raw;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RealmTask)) return false;

        RealmTask realmTask = (RealmTask) o;

        if (task_id != null ? !task_id.equals(realmTask.task_id) : realmTask.task_id != null)
            return false;
        if (customer_id != null ? !customer_id.equals(realmTask.customer_id) : realmTask.customer_id != null)
            return false;
        if (team_id != null ? !team_id.equals(realmTask.team_id) : realmTask.team_id != null)
            return false;
        return driver_id != null ? driver_id.equals(realmTask.driver_id) : realmTask.driver_id == null;
    }

    @Override
    public int hashCode() {
        int result = task_id != null ? task_id.hashCode() : 0;
        result = 31 * result + (customer_id != null ? customer_id.hashCode() : 0);
        result = 31 * result + (team_id != null ? team_id.hashCode() : 0);
        result = 31 * result + (driver_id != null ? driver_id.hashCode() : 0);
        return result;
    }
}
