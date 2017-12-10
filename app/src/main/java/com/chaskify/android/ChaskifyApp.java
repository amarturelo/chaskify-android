package com.chaskify.android;

import android.app.Application;
import android.content.Context;

import com.chaskify.logger.CrashReportingTree;

import org.acra.annotation.ReportsCrashes;

import me.yokeyword.fragmentation.Fragmentation;
import timber.log.Timber;

/**
 * Created by alberto on 5/12/17.
 */
@ReportsCrashes(
        formUri = "https://collector.tracepot.com/72e42953"
)
public class ChaskifyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initBugReport();
        initFragmentation();
        initMaps();
    }

    private void initMaps() {
        // Mapbox Access token
        //Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_maps_key));
    }

    private void initBugReport() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private void initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
