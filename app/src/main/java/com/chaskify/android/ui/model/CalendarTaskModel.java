package com.chaskify.android.ui.model;

/**
 * Created by alberto on 15/12/17.
 */

public class CalendarTaskModel {
    public String title;
    public String id;
    public String year;
    public String month;
    public String day;

    public CalendarTaskModel() {
    }

    public String getTitle() {
        return title;
    }

    public CalendarTaskModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getId() {
        return id;
    }

    public CalendarTaskModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getYear() {
        return year;
    }

    public CalendarTaskModel setYear(String year) {
        this.year = year;
        return this;
    }

    public String getMonth() {
        return month;
    }

    public CalendarTaskModel setMonth(String month) {
        this.month = month;
        return this;
    }

    public String getDay() {
        return day;
    }

    public CalendarTaskModel setDay(String day) {
        this.day = day;
        return this;
    }
}
