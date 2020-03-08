package com.chaskify.domain.filter;

import android.os.Parcel;

/**
 * Created by alberto on 14/01/18.
 */

public class WaypointIdFilter implements Filter {

    private String mWayPointId;

    public WaypointIdFilter() {
    }

    public String getWayPointId() {
        return mWayPointId;
    }

    public WaypointIdFilter setWayPointId(String mWayPointId) {
        this.mWayPointId = mWayPointId;
        return this;
    }

    protected WaypointIdFilter(Parcel in) {
        mWayPointId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mWayPointId);
    }

    public static final Creator<WaypointIdFilter> CREATOR = new Creator<WaypointIdFilter>() {
        @Override
        public WaypointIdFilter createFromParcel(Parcel in) {
            return new WaypointIdFilter(in);
        }

        @Override
        public WaypointIdFilter[] newArray(int size) {
            return new WaypointIdFilter[size];
        }
    };
}
