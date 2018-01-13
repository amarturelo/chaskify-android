package com.chaskify.domain.filter;


import android.os.Parcel;

/**
 * Created by alberto on 12/01/18.
 */

public class TaskIdFilter implements Filter {

    private String taskId;

    public TaskIdFilter() {
    }

    protected TaskIdFilter(Parcel in) {
        taskId = in.readString();
    }

    public static final Creator<TaskIdFilter> CREATOR = new Creator<TaskIdFilter>() {
        @Override
        public TaskIdFilter createFromParcel(Parcel in) {
            return new TaskIdFilter(in);
        }

        @Override
        public TaskIdFilter[] newArray(int size) {
            return new TaskIdFilter[size];
        }
    };

    public String getTaskId() {
        return taskId;
    }

    public TaskIdFilter setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(taskId);

    }
}
