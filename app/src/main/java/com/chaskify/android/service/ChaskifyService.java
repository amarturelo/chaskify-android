package com.chaskify.android.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by alberto on 13/12/17.
 */

public class ChaskifyService extends Service {

    public static final String EXTRA_CHASKIFY_ID = "ChaskifyService.EXTRA_CHASKIFY_ID";
    /**
     * static instance
     */
    private static ChaskifyService mChaskifyService = null;

    public static ChaskifyService getInstance() {
        return mChaskifyService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mChaskifyService = this;
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context activity) {
        activity.startService(new Intent(activity, ChaskifyService.class));
    }

    public static void stop(Context activity) {
        activity.stopService(new Intent(activity, ChaskifyService.class));
    }

    public void onDuty(String driverId) {

    }
}
