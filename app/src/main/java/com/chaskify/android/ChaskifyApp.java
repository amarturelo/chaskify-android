package com.chaskify.android;

import android.app.Application;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * Created by alberto on 5/12/17.
 */

public class ChaskifyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFragmentation();
    }

    private void initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();

    }
}
