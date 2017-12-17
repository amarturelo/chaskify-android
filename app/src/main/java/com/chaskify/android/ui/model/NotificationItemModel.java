package com.chaskify.android.ui.model;

/**
 * Created by alberto on 17/12/17.
 */

public class NotificationItemModel {
    public String pushId;
    public String customerId;
    public String pushTitle;
    public String pushMessage;
    public String pushType;
    public String actions;
    public String status;
    public String driverId;
    public String taskId;
    public String dateCreated;
    public String dateProcess;
    public String isRead;

    public NotificationItemModel() {
    }

    public String getPushId() {
        return pushId;
    }

    public NotificationItemModel setPushId(String pushId) {
        this.pushId = pushId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public NotificationItemModel setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public NotificationItemModel setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
        return this;
    }

    public String getPushMessage() {
        return pushMessage;
    }

    public NotificationItemModel setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
        return this;
    }

    public String getPushType() {
        return pushType;
    }

    public NotificationItemModel setPushType(String pushType) {
        this.pushType = pushType;
        return this;
    }

    public String getActions() {
        return actions;
    }

    public NotificationItemModel setActions(String actions) {
        this.actions = actions;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public NotificationItemModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public NotificationItemModel setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public NotificationItemModel setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public NotificationItemModel setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getDateProcess() {
        return dateProcess;
    }

    public NotificationItemModel setDateProcess(String dateProcess) {
        this.dateProcess = dateProcess;
        return this;
    }

    public String getIsRead() {
        return isRead;
    }

    public NotificationItemModel setIsRead(String isRead) {
        this.isRead = isRead;
        return this;
    }
}
