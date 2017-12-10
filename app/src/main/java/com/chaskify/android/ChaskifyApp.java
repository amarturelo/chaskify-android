package com.chaskify.android;

import android.app.Application;
import android.content.Context;

import com.bugsee.library.Bugsee;
import com.chaskify.logger.CrashReportingTree;
import com.mapbox.mapboxsdk.Mapbox;

import org.acra.annotation.ReportsCrashes;

import java.util.HashMap;

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
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_maps_key));
    }

    private void initBugReport() {
        HashMap<String, Object> options = new HashMap<>();
        options.put(Bugsee.Option.MaxRecordingTime, 60);
        options.put(Bugsee.Option.ShakeToTrigger, false);
        options.put(Bugsee.Option.NotificationBarTrigger, false);
        options.put(Bugsee.Option.UseSdCard, false);
        options.put (Bugsee.Option.VideoEnabled, false);
        options.put(Bugsee.Option.ScreenshotEnabled, false);
        options.put(Bugsee.Option.ExtendedVideoMode, false);

        Bugsee.launch(this, getString(R.string.bugsee), options);

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
