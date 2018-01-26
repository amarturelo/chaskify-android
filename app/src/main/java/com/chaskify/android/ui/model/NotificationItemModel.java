package com.chaskify.android.ui.model;

import java.util.Date;

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
    public Date dateProcess;
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

    public Date getDateProcess() {
        return dateProcess;
    }

    public NotificationItemModel setDateProcess(Date dateProcess) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotificationItemModel)) return false;

        NotificationItemModel that = (NotificationItemModel) o;

        if (pushId != null ? !pushId.equals(that.pushId) : that.pushId != null) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null)
            return false;
        if (pushTitle != null ? !pushTitle.equals(that.pushTitle) : that.pushTitle != null)
            return false;
        if (pushMessage != null ? !pushMessage.equals(that.pushMessage) : that.pushMessage != null)
            return false;
        if (pushType != null ? !pushType.equals(that.pushType) : that.pushType != null)
            return false;
        if (actions != null ? !actions.equals(that.actions) : that.actions != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (driverId != null ? !driverId.equals(that.driverId) : that.driverId != null)
            return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null)
            return false;
        if (dateProcess != null ? !dateProcess.equals(that.dateProcess) : that.dateProcess != null)
            return false;
        return isRead != null ? isRead.equals(that.isRead) : that.isRead == null;
    }

    @Override
    public int hashCode() {
        int result = pushId != null ? pushId.hashCode() : 0;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (pushTitle != null ? pushTitle.hashCode() : 0);
        result = 31 * result + (pushMessage != null ? pushMessage.hashCode() : 0);
        result = 31 * result + (pushType != null ? pushType.hashCode() : 0);
        result = 31 * result + (actions != null ? actions.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (driverId != null ? driverId.hashCode() : 0);
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (dateProcess != null ? dateProcess.hashCode() : 0);
        result = 31 * result + (isRead != null ? isRead.hashCode() : 0);
        return result;
    }
}
