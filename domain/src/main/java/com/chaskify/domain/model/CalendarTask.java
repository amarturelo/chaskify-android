package com.chaskify.domain.model;

/**
 * Created by alberto on 15/12/17.
 */

public class CalendarTask {
    public String title;
    public String id;
    public String year;
    public String month;
    public String day;

    public CalendarTask() {
    }

    public String getTitle() {
        return title;
    }

    public CalendarTask setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getId() {
        return id;
    }

    public CalendarTask setId(String id) {
        this.id = id;
        return this;
    }

    public String getYear() {
        return year;
    }

    public CalendarTask setYear(String year) {
        this.year = year;
        return this;
    }

    public String getMonth() {
        return month;
    }

    public CalendarTask setMonth(String month) {
        this.month = month;
        return this;
    }

    public String getDay() {
        return day;
    }

    public CalendarTask setDay(String day) {
        this.day = day;
        return this;
    }
}
