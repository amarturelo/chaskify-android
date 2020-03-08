package com.chaskify.android;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.chaskify.android.helper.LocaleManager;
import com.chaskify.android.push.ExampleNotificationOpenedHandler;
import com.chaskify.android.push.ExampleNotificationReceivedHandler;
import com.chaskify.android.ui.activities.MainActivity;
import com.chaskify.data.realm.module.InMemoryModule;
import com.chaskify.logger.CrashReportingTree;
import com.mapbox.mapboxsdk.Mapbox;
import com.onesignal.OneSignal;
import com.tbruyelle.rxpermissions2.RxPermissions;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.yokeyword.fragmentation.Fragmentation;
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

        initRealm();
        initBugReport();
        initFragmentation();
        initMaps();
        //initPush();
        Chaskify.getInstance(getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
        Timber.d("attachBaseContext");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
        Timber.d("onConfigurationChanged: " + newConfig.locale.getLanguage());
    }

    private void initRealm() {
        Realm.init(getApplicationContext());

        RealmConfiguration realmNotification = new RealmConfiguration.Builder()
                .name("inMemory.realm")
                .inMemory()
                .modules(new InMemoryModule())
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmNotification);
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

}
