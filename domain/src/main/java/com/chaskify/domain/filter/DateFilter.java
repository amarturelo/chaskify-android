package com.chaskify.domain.filter;

import android.os.Parcel;

import java.util.Date;

/**
 * Created by alberto on 12/01/18.
 */

public class DateFilter implements Filter {
    private Date date;

    public DateFilter() {
    }

    protected DateFilter(Parcel in) {
    }

    public static final Creator<DateFilter> CREATOR = new Creator<DateFilter>() {
        @Override
        public DateFilter createFromParcel(Parcel in) {
            return new DateFilter(in);
        }

        @Override
        public DateFilter[] newArray(int size) {
            return new DateFilter[size];
        }
    };

    public Date getDate() {
        return date;
    }

    public DateFilter setDate(Date date) {
        this.date = date;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
