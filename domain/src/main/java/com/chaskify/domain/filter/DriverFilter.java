package com.chaskify.domain.filter;


import android.os.Parcel;

/**
 * Created by alberto on 12/01/18.
 */

public class DriverFilter implements Filter {
    private String driver;

    public DriverFilter() {
    }

    protected DriverFilter(Parcel in) {
        driver = in.readString();
    }

    public static final Creator<DriverFilter> CREATOR = new Creator<DriverFilter>() {
        @Override
        public DriverFilter createFromParcel(Parcel in) {
            return new DriverFilter(in);
        }

        @Override
        public DriverFilter[] newArray(int size) {
            return new DriverFilter[size];
        }
    };

    public String getDriver() {
        return driver;
    }

    public DriverFilter setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(driver);
    }
}
