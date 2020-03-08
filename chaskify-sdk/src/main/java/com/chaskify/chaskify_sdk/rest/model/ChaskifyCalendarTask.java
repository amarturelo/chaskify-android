package com.chaskify.chaskify_sdk.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alberto on 15/12/17.
 */

public class ChaskifyCalendarTask {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("year")
    @Expose
    public String year;
    @SerializedName("month")
    @Expose
    public String month;
    @SerializedName("day")
    @Expose
    public String day;

    public ChaskifyCalendarTask() {
    }

    public String getTitle() {
        return title;
    }

    public ChaskifyCalendarTask setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getId() {
        return id;
    }

    public ChaskifyCalendarTask setId(String id) {
        this.id = id;
        return this;
    }

    public String getYear() {
        return year;
    }

    public ChaskifyCalendarTask setYear(String year) {
        this.year = year;
        return this;
    }

    public String getMonth() {
        return month;
    }

    public ChaskifyCalendarTask setMonth(String month) {
        this.month = month;
        return this;
    }

    public String getDay() {
        return day;
    }

    public ChaskifyCalendarTask setDay(String day) {
        this.day = day;
        return this;
    }
}
