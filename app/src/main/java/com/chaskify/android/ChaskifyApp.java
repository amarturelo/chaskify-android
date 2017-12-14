package com.chaskify.android;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.chaskify.android.push.ExampleNotificationOpenedHandler;
import com.chaskify.android.push.ExampleNotificationReceivedHandler;
import com.chaskify.android.ui.activities.MainActivity;
import com.chaskify.logger.CrashReportingTree;
import com.mapbox.mapboxsdk.Mapbox;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;


import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import timber.log.Timber;

/**
 * Created by alberto on 5/12/17.
 */

public class ChaskifyApp extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.tag(this.getClass().getSimpleName());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Chaskify.getInstance(getApplicationContext());

        initRealm();
        initBugReport();
        initFragmentation();
        initMaps();
        initPush();

    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initPush() {

        OneSignal.startInit(this)
                .setNotificationReceivedHandler(new ExampleNotificationReceivedHandler())
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler(getApplicationContext()))
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .init();
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                Timber.d("UserId: " + userId + " registrationId: " + registrationId);
            }
        });
    }

    private void initMaps() {
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_maps_key));
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
                .handleException(Timber::e)
                .install();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
