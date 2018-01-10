package com.chaskify.data.repositories.datasource.cloud;

import com.annimon.stream.Optional;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.chaskify_sdk.rest.callback.ApiCallback;
import com.chaskify.chaskify_sdk.rest.client.SettingsRestClient;
import com.chaskify.chaskify_sdk.rest.model.ChaskifySettings;
import com.chaskify.data.model.chaskify.mapper.SettingsDataMapper;
import com.chaskify.data.realm.cache.SettingsCache;
import com.chaskify.data.repositories.datasource.SettingsDataStore;
import com.chaskify.domain.model.Settings;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Created by alberto on 9/01/18.
 */

public class CloudSettingsDataStore implements SettingsDataStore {

    private SettingsRestClient mSettingsRestClient;

    private SettingsCache mSettingsCache;

    public CloudSettingsDataStore(SettingsRestClient mSettingsRestClient, SettingsCache settingsCache) {
        this.mSettingsRestClient = mSettingsRestClient;
        this.mSettingsCache = settingsCache;
    }

    @Override
    public Flowable<Optional<Settings>> getByDriverId(String driverId) {
        return Single.create((SingleOnSubscribe<ChaskifySettings>) emitter -> mSettingsRestClient.getSettings(new ApiCallback<ChaskifySettings>() {
            @Override
            public void onSuccess(ChaskifySettings chaskifySettings) {
                emitter.onSuccess(chaskifySettings);
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
        }))
                .map(SettingsDataMapper::transform)
                .doOnSuccess(settings -> mSettingsCache.put(com.chaskify.data.realm.cache.impl.mapper.SettingsDataMapper.transform(settings)))
                .map(Optional::of)
                .toFlowable()
                ;
    }
}
