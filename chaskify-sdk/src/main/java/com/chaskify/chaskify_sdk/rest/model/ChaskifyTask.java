package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by alberto on 6/12/17.
 */

public class ChaskifyTask {

    public enum STATUS {

        IN_ROUTE("IN ROUTE"), ACCEPTED("ACCEPTED"), ARRIVED("ARRIVED"), SUCCESSFUL("SUCCESSFUL"), DECLINED("DECLINED"), UNASSIGNED("UNASSIGNED"), CANCELED("CANCELED");
        private String text;

        STATUS(String s) {
            text = s;
        }

        public String getText() {
            return text;
        }

    }


    @SerializedName("task_id")
    @Expose
    private String taskId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("task_description")
    @Expose
    private String taskDescription;
    @SerializedName("trans_type")
    @Expose
    private String transType;
    @SerializedName("contact_number")
    @Expose
    private String contactNumber;
    @SerializedName("email_address")
    @Expose
    private String emailAddress;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("delivery_date")
    @Expose
    private Date deliveryDate;
    @SerializedName("delivery_address")
    @Expose
    private String deliveryAddress;
    @SerializedName("team_id")
    @Expose
    private String teamId;
    @SerializedName("driver_id")
    @Expose
    private String driverId;
    @SerializedName("task_lat")
    @Expose
    private double taskLat;
    @SerializedName("task_lng")
    @Expose
    private double taskLng;
    @SerializedName("customer_signature")
    @Expose
    private Object customerSignature;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("auto_assign_type")
    @Expose
    private String autoAssignType;
    @SerializedName("assign_started")
    @Expose
    private String assignStarted;
    @SerializedName("assignment_status")
    @Expose
    private Object assignmentStatus;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("driver_phone")
    @Expose
    private String driverPhone;
    @SerializedName("driver_email")
    @Expose
    private String driverEmail;
    @SerializedName("device_platform")
    @Expose
    private String devicePlatform;
    @SerializedName("enabled_push")
    @Expose
    private String enabledPush;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("customer_picture")
    @Expose
    private Object customerPicture;
    @SerializedName("task_tags")
    @Expose
    private String taskTags;
    @SerializedName("order_number")
    @Expose
    private String orderNumber;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("status_raw")
    @Expose
    private String statusRaw;
    @SerializedName("trans_type_raw")
    @Expose
    private String transTypeRaw;
    @SerializedName("waypoints")
    @Expose
    private List<ChaskifyTaskWayPoint> waypoints = null;
    @SerializedName("history")
    @Expose
    private List<ChaskifyTaskHistory> history = null;
    @SerializedName("customer_signature_url")
    @Expose
    private String customerSignatureUrl;

    public ChaskifyTask() {
    }

    public String getTaskId() {
        return taskId;
    }

    public ChaskifyTask setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public ChaskifyTask setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public ChaskifyTask setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
        return this;
    }

    public String getTransType() {
        return transType;
    }

    public ChaskifyTask setTransType(String transType) {
        this.transType = transType;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public ChaskifyTask setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ChaskifyTask setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ChaskifyTask setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public ChaskifyTask setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public ChaskifyTask setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    public String getTeamId() {
        return teamId;
    }

    public ChaskifyTask setTeamId(String teamId) {
        this.teamId = teamId;
        return this;
    }

    public String getDriverId() {
        return driverId;
    }

    public ChaskifyTask setDriverId(String driverId) {
        this.driverId = driverId;
        return this;
    }

    public double getTaskLat() {
        return taskLat;
    }

    public ChaskifyTask setTaskLat(double taskLat) {
        this.taskLat = taskLat;
        return this;
    }

    public double getTaskLng() {
        return taskLng;
    }

    public ChaskifyTask setTaskLng(double taskLng) {
        this.taskLng = taskLng;
        return this;
    }

    public List<ChaskifyTaskWayPoint> getWaypoints() {
        return waypoints;
    }

    public ChaskifyTask setWaypoints(List<ChaskifyTaskWayPoint> waypoints) {
        this.waypoints = waypoints;
        return this;
    }

    public List<ChaskifyTaskHistory> getHistory() {
        return history;
    }

    public ChaskifyTask setHistory(List<ChaskifyTaskHistory> history) {
        this.history = history;
        return this;
    }

    public Object getCustomerSignature() {
        return customerSignature;
    }

    public ChaskifyTask setCustomerSignature(Object customerSignature) {
        this.customerSignature = customerSignature;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ChaskifyTask setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public ChaskifyTask setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public String getDateModified() {
        return dateModified;
    }

    public ChaskifyTask setDateModified(String dateModified) {
        this.dateModified = dateModified;
        return this;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public ChaskifyTask setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public String getAutoAssignType() {
        return autoAssignType;
    }

    public ChaskifyTask setAutoAssignType(String autoAssignType) {
        this.autoAssignType = autoAssignType;
        return this;
    }

    public String getAssignStarted() {
        return assignStarted;
    }

    public ChaskifyTask setAssignStarted(String assignStarted) {
        this.assignStarted = assignStarted;
        return this;
    }

    public Object getAssignmentStatus() {
        return assignmentStatus;
    }

    public ChaskifyTask setAssignmentStatus(Object assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public ChaskifyTask setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public ChaskifyTask setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public ChaskifyTask setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
        return this;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public ChaskifyTask setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
        return this;
    }

    public String getDevicePlatform() {
        return devicePlatform;
    }

    public ChaskifyTask setDevicePlatform(String devicePlatform) {
        this.devicePlatform = devicePlatform;
        return this;
    }

    public String getEnabledPush() {
        return enabledPush;
    }

    public ChaskifyTask setEnabledPush(String enabledPush) {
        this.enabledPush = enabledPush;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public ChaskifyTask setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public Object getCustomerPicture() {
        return customerPicture;
    }

    public ChaskifyTask setCustomerPicture(Object customerPicture) {
        this.customerPicture = customerPicture;
        return this;
    }

    public String getTaskTags() {
        return taskTags;
    }

    public ChaskifyTask setTaskTags(String taskTags) {
        this.taskTags = taskTags;
        return this;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public ChaskifyTask setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public ChaskifyTask setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public String getStatusRaw() {
        return statusRaw;
    }

    public ChaskifyTask setStatusRaw(String statusRaw) {
        this.statusRaw = statusRaw;
        return this;
    }

    public String getTransTypeRaw() {
        return transTypeRaw;
    }

    public ChaskifyTask setTransTypeRaw(String transTypeRaw) {
        this.transTypeRaw = transTypeRaw;
        return this;
    }

    /*public List<ChaskifyTaskHistory> getHistory() {
        return history;
    }

    public ChaskifyTask setHistory(List<ChaskifyTaskHistory> history) {
        this.history = history;
        return this;
    }
*/
    public String getCustomerSignatureUrl() {
        return customerSignatureUrl;
    }

    public ChaskifyTask setCustomerSignatureUrl(String customerSignatureUrl) {
        this.customerSignatureUrl = customerSignatureUrl;
        return this;
    }

    @Override
    public String toString() {
        return "ChaskifyTask{" +
                "taskId='" + taskId + '\'' +
                ", driverId='" + driverId + '\'' +
                '}';
    }

   /* public ChaskifyDriverLocation getDriverLocation() {
        return driverLocation;
    }

    public ChaskifyTask setDriverLocation(ChaskifyDriverLocation driverLocation) {
        this.driverLocation = driverLocation;
        return this;
    }*/
}
