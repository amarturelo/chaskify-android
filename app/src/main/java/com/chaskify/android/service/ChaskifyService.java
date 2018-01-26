package com.chaskify.android.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.chaskify.android.Chaskify;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.RxLocation;

import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


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

    private static ChaskifyService mChaskifyService = null;

    public static ChaskifyService getInstance() {
        return mChaskifyService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.tag(this.getClass().getSimpleName());
        rxLocation = new RxLocation(this);

        this.locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("onStartCommand");
        mChaskifyService = this;

        if (intent != null) {
            String driverId = intent.getExtras().getString(EXTRA_CHASKIFY_ID);
            attachSession(Chaskify.getInstance().getSessionByDriverId(driverId).get());
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context activity, String driverId) {
        Timber.d("start");

        Intent intent = new Intent(activity, ChaskifyService.class);
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_CHASKIFY_ID, driverId);
        intent.putExtras(bundle);
        activity.startService(intent);
    }

    public static void stop(Context activity) {
        Timber.d(String.valueOf(activity.stopService(new Intent(activity, ChaskifyService.class))));
    }

    @Override
    public boolean stopService(Intent name) {
        if (mChaskifySession != null)
            mChaskifySession.removeDutyChangeListener(this);
        clearSubscriptions();
        return super.stopService(name);
    }

    @Override
    public void onState(ChaskifySession.STATE state) {
        switch (state) {
            case ON_DUTY:
                onDuty();
                break;
            case OFF_DUTY:
                offDuty();
                break;
        }
    }

    private void onDuty() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        addSubscription(
                rxLocation.settings().checkAndHandleResolution(locationRequest)
                        .flatMapObservable(this::getAddressObservable)
                        .flatMapCompletable(ChaskifyService.this::onLocationUpdate)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                        }, Timber::e)
        );
    }

    private void offDuty() {
        compositeSubscription.clear();
    }

    protected void addSubscription(Disposable subscription) {
        compositeSubscription.add(subscription);
    }

    protected void clearSubscriptions() {
        compositeSubscription.clear();
    }

    public void attachSession(ChaskifySession chaskifySession) {
        if (this.mChaskifySession != null) {
            if (!this.mChaskifySession.getCredentials().getDriverId().equals(chaskifySession.getCredentials().getDriverId())) {
                compositeSubscription.clear();
                this.mChaskifySession.removeDutyChangeListener(this);
                this.mChaskifySession = chaskifySession;
                this.mChaskifySession.addDutyChangeListener(this);
            }
        } else {
            this.mChaskifySession = chaskifySession;
            this.mChaskifySession.addDutyChangeListener(this);
        }

    }

    @SuppressLint("MissingPermission")
    private Observable<Location> getAddressObservable(boolean success) {
        if (success) {
            return rxLocation.location().updates(locationRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext(ChaskifyService.this::onLocationUpdate);

        } else {
            return rxLocation.location().lastLocation()
                    .doOnSuccess(ChaskifyService.this::onLocationUpdate)
                    .toObservable();
        }
    }

    private Completable onLocationUpdate(Location location) {
        return Completable.create(emitter -> mChaskifySession.updateDriverPosition(location.getLatitude(), location.getLongitude(), new ApiCallbackSuccess() {
            @Override
            public void onSuccess() {
                emitter.onComplete();
            }

            @Override
            public void onNetworkError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                emitter.onError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                emitter.onError(e);
            }
        }));
    }
}
