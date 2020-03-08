package com.chaskify.data.realm.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alberto on 12/01/18.
 */

public class RealmCalendarTask extends RealmObject {
    public String taskByDate;
    @PrimaryKey
    public String date;

    public RealmCalendarTask() {
    }

    public String getTaskByDate() {
        return taskByDate;
    }

    public RealmCalendarTask setTaskByDate(String taskByDate) {
        this.taskByDate = taskByDate;
        return this;
    }

    public String getDate() {
        return date;
    }

    public RealmCalendarTask setDate(String date) {
        this.date = date;
        return this;
    }
}
