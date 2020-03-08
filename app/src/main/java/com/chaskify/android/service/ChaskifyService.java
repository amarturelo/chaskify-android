package com.chaskify.android.service;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.rx.RetryWithDelay;
import com.chaskify.android.ui.activities.LaunchActivity;
import com.chaskify.android.ui.activities.MainActivity;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallbackSuccess;
import com.google.android.gms.location.LocationRequest;
import com.patloew.rxlocation.RxLocation;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Created by alberto on 13/12/17.
 */

public class ChaskifyService extends Service {

    private RxLocation rxLocation;
    private LocationRequest locationRequest;

    private CompositeDisposable compositeSubscription = new CompositeDisposable();

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

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            mChaskifyService = this;
            String driverId = intent.getExtras().getString(Constants.ACTION.MAIN_ACTION);
            attach(driverId);
        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            stop();
        }
        return START_STICKY;
    }

    private void attach(String driverId) {
        Chaskify.getInstance().getSessionByDriverId(driverId)
                .executeIfAbsent(() -> stop())
                .ifPresent(
                        chaskifySession -> {

                            attachSession(chaskifySession);

                            Intent notificationIntent = new Intent(this, LaunchActivity.class);
                            notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
                            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                                    notificationIntent, 0);

                            Notification notification = new NotificationCompat.Builder(this)
                                    .setContentTitle("On Duty")
                                    .setTicker("On Duty")
                                    .setContentText(chaskifySession.getCredentials().getUsername())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setOngoing(true)
                                    .setContentIntent(pendingIntent)
                                    .build();

                            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                                    notification);

                            onDuty();
                        }
                );
    }

    private void stop() {
        mChaskifyService = null;
        clearSubscriptions();
        stopForeground(true);
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context context, String driverId) {
        Intent startIntent = new Intent(context, ChaskifyService.class);
        startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ACTION.MAIN_ACTION, driverId);
        startIntent.putExtras(bundle);
        context.startService(startIntent);
    }

    public static void stop(Context context) {
        Intent stopIntent = new Intent(context, ChaskifyService.class);
        stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        context.startService(stopIntent);
    }

    private void onDuty() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        addSubscription(
                rxLocation.settings().checkAndHandleResolution(locationRequest)
                        .flatMapObservable(this::getAddressObservable)
                        .flatMapCompletable(ChaskifyService.this::onLocationUpdate)
                        .retryWhen(new RetryWithDelay(5000))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(() -> {
                        }, Timber::e)
        );
    }

    protected void addSubscription(Disposable subscription) {
        compositeSubscription.add(subscription);
    }

    protected void clearSubscriptions() {
        compositeSubscription.clear();
    }

    private void attachSession(ChaskifySession chaskifySession) {
        if (this.mChaskifySession != null) {
            if (!this.mChaskifySession.getCredentials().getDriverId().equals(chaskifySession.getCredentials().getDriverId())) {
                compositeSubscription.clear();
                this.mChaskifySession = chaskifySession;
            }
        } else {
            this.mChaskifySession = chaskifySession;
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
                if (emitter != null && !emitter.isDisposed())
                    emitter.onComplete();
            }

            @Override
            public void onNetworkError(Exception e) {
                if (emitter != null && !emitter.isDisposed())
                    emitter.onError(e);
            }

            @Override
            public void onChaskifyError(Exception e) {
                if (emitter != null && !emitter.isDisposed())
                    emitter.onError(e);
            }

            @Override
            public void onUnexpectedError(Exception e) {
                if (emitter != null && !emitter.isDisposed())
                    emitter.onError(e);
            }
        }));
    }
}
