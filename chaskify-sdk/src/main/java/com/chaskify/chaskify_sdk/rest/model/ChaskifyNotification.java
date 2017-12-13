package com.chaskify.chaskify_sdk.rest.model;

import java.util.Date;

/**
 * Created by alberto on 6/12/17.
 */

public class ChaskifyNotification {
    /*
            "push_id": "2096",
            "customer_id": "33",
            "device_platform": "Android",
            "device_id": "86ba6653-00d5-4008-8025-bcd33a8d3a21",
            "push_title": "ASSIGN TASK",
            "push_message": "You have new assign task 3643",
            "push_type": "task",
            "actions": "ASSIGN_TASK",
            "mStatus": "process",
            "json_response": "{\"success\":200}",
            "driver_id": "175",
            "task_id": "3643",
            "date_created": "Dic 04,2017 14:00:40",
            "date_process": "2017-12-04 19:00:43",
            "ip_address": "172.31.0.231",
            "is_read": "2",
            "message": null
    * */
    private String push_id;
    private String customer_id;
    private String device_platform;
    private String device_id;
    private String push_title;
    private String push_message;
    private String push_type;
    private String actions;
    private String mStatus;
    private String driver_id;
    private String task_id;
    private Date date_created;
    private Date date_process;
    private String is_read;
    private String message;

    public ChaskifyNotification() {
    }

    public String getPush_id() {
        return push_id;
    }

    public ChaskifyNotification setPush_id(String push_id) {
        this.push_id = push_id;
        return this;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public ChaskifyNotification setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public String getDevice_platform() {
        return device_platform;
    }

    public ChaskifyNotification setDevice_platform(String device_platform) {
        this.device_platform = device_platform;
        return this;
    }

    public String getDevice_id() {
        return device_id;
    }

    public ChaskifyNotification setDevice_id(String device_id) {
        this.device_id = device_id;
        return this;
    }

    public String getPush_title() {
        return push_title;
    }

    public ChaskifyNotification setPush_title(String push_title) {
        this.push_title = push_title;
        return this;
    }

    public String getPush_message() {
        return push_message;
    }

    public ChaskifyNotification setPush_message(String push_message) {
        this.push_message = push_message;
        return this;
    }

    public String getPush_type() {
        return push_type;
    }

    public ChaskifyNotification setPush_type(String push_type) {
        this.push_type = push_type;
        return this;
    }

    public String getActions() {
        return actions;
    }

    public ChaskifyNotification setActions(String actions) {
        this.actions = actions;
        return this;
    }

    public String getmStatus() {
        return mStatus;
    }

    public ChaskifyNotification setmStatus(String mStatus) {
        this.mStatus = mStatus;
        return this;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public ChaskifyNotification setDriver_id(String driver_id) {
        this.driver_id = driver_id;
        return this;
    }

    public String getTask_id() {
        return task_id;
    }

    public ChaskifyNotification setTask_id(String task_id) {
        this.task_id = task_id;
        return this;
    }

    public Date getDate_created() {
        return date_created;
    }

    public ChaskifyNotification setDate_created(Date date_created) {
        this.date_created = date_created;
        return this;
    }

    public Date getDate_process() {
        return date_process;
    }

    public ChaskifyNotification setDate_process(Date date_process) {
        this.date_process = date_process;
        return this;
    }

    public String getIs_read() {
        return is_read;
    }

    public ChaskifyNotification setIs_read(String is_read) {
        this.is_read = is_read;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ChaskifyNotification setMessage(String message) {
        this.message = message;
        return this;
    }
}
