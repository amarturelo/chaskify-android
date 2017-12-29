package com.chaskify.domain.model;

import java.util.Date;

/**
 * Created by alberto on 29/12/17.
 */

public class TaskHistory {
    private String id;
    private String status;
    private String remarks;
    private String driverLocationLat;
    private String driverLocationLng;
    private Date dateCreated;

    public TaskHistory() {
    }

    public String getId() {
        return id;
    }

    public TaskHistory setId(String id) {
        this.id = id;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskHistory setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public TaskHistory setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public String getDriverLocationLat() {
        return driverLocationLat;
    }

    public TaskHistory setDriverLocationLat(String driverLocationLat) {
        this.driverLocationLat = driverLocationLat;
        return this;
    }

    public String getDriverLocationLng() {
        return driverLocationLng;
    }

    public TaskHistory setDriverLocationLng(String driverLocationLng) {
        this.driverLocationLng = driverLocationLng;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public TaskHistory setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }
}
