package com.chaskify.android.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.annimon.stream.Optional;
import com.chaskify.android.Chaskify;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.RxLocation;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by alberto on 13/12/17.
 */

public class ChaskifyService extends Service implements ChaskifySession.OnDutyChange {

    private RxLocation rxLocation;
    private LocationRequest locationRequest;


    public static final String EXTRA_CHASKIFY_ID = "ChaskifyService.EXTRA_CHASKIFY_ID";

    private CompositeDisposable compositeSubscription = new CompositeDisposable();
    /**
     * static instance
     */

    private ChaskifySession mChaskifySession;

    private RxPermissions rxPermissions;

    private static ChaskifyService mChaskifyService = null;

    public static ChaskifyService getInstance() {
        return mChaskifyService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        rxLocation = new RxLocation(this);

        this.locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000);
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

    @Override
    public void onState(ChaskifySession.STATE state) {

    }

    private void offDuty() {
        release();
    }

    public void release() {
        compositeSubscription.clear();
    }

    protected void addSubscription(Disposable subscription) {
        compositeSubscription.add(subscription);
    }

    protected void clearSubscriptions() {
        compositeSubscription.clear();
    }

    /*private Observable<Address> getAddressObservable(boolean success) {
        if (success) {
            return rxLocation.location().updates(locationRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(view::onLocationUpdate)
                    .flatMap(this::getAddressFromLocation);

        } else {
            return rxLocation.location().lastLocation()
                    .doOnSuccess(view::onLocationUpdate)
                    .flatMapObservable(this::getAddressFromLocation);
        }
    }

    private Observable<Address> getAddressFromLocation(Location location) {
        return rxLocation.geocoding().fromLocation(location).toObservable()
                .subscribeOn(Schedulers.io());
    }*/
}
