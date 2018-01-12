package com.chaskify.data.realm.cache.impl;

import android.os.Looper;
import android.util.Pair;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.data.realm.model.RealmSettings;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by alberto on 9/01/18.
 */

public class SettingsCacheImpl extends RealmCache implements SettingsCache {

    public SettingsCacheImpl() {
    }

    @Override
    public void put(RealmSettings settings) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insertOrUpdate(settings);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Flowable<Optional<RealmSettings>> getByDriverId(String driverId) {
        return Flowable.defer(() -> Flowable.using(() -> new Pair<>(Realm.getDefaultInstance(), Looper.myLooper()), pair -> {
                    RealmResults<RealmSettings> realmResults = pair.first.where(RealmSettings.class)
                            .equalTo(RealmSettings.DRIVER_ID, driverId)
                            .findAll();

                    return realmResults.<RealmSettings>asFlowable()
                            .switchMap((Function<RealmResults<RealmSettings>, Publisher<Optional<RealmSettings>>>) value -> {
                                if (value.isEmpty())
                                    return Flowable.just(Optional.empty());
                                else
                                    return Flowable.just(Optional.of(value))
                                            .map(Optional::get)
                                            .map(v -> v.get(0))
                                            .filter(
                                                    profile -> profile.isLoaded()
                                                            && profile.isValid())
                                            .map(Optional::of);
                            });
                }
                , pair -> close(pair.first, pair.second))
                .unsubscribeOn(AndroidSchedulers.from(Looper.myLooper())))
                .map(optional -> {
                    if (optional.isPresent()) {
                        return Optional.of(optional.get());
                    }
                    return Optional.empty();
                });
    }

}

