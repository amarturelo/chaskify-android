package com.chaskify.data.realm.cache.impl;

import android.os.Looper;
import android.util.Pair;

import com.annimon.stream.Optional;
import com.chaskify.data.realm.model.RealmProfile;
import com.chaskify.data.realm.model.RealmSettings;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.domain.model.Settings;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;

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
                    RealmSettings realmSettings = pair.first.where(RealmSettings.class)
                            .equalTo(RealmSettings.DRIVER_ID, driverId)
                            .findFirst();

                    if (realmSettings == null) {
                        return Flowable.just(com.annimon.stream.Optional.empty());
                    }

                    return realmSettings.<RealmSettings>asFlowable()
                            .filter(
                                    value -> value.isLoaded()
                                            && value.isValid())
                            .map(Optional::of);
                }
                , pair -> close(pair.first, pair.second))
                .unsubscribeOn(AndroidSchedulers.from(Looper.myLooper())))
                .map(optional -> {
                    if (optional.isPresent()) {
                        return Optional.of((RealmSettings) optional.get());
                    }
                    return Optional.empty();
                });
    }

}

