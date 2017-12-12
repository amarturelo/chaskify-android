package com.chaskify.data.cache.impl;

import com.chaskify.data.cache.CredentialsCache;
import com.chaskify.data.model.internal.RealmCredentials;

import java.util.List;

import io.reactivex.Single;
import io.realm.Realm;

/**
 * Created by alberto on 11/12/17.
 */

public class CredentialsCacheImpl implements CredentialsCache {

    public CredentialsCacheImpl() {
    }

    @Override
    public Single<List<RealmCredentials>> findAll() {
        return Single
                .defer(() -> Single
                        .create(e -> e.onSuccess(Realm
                                .getDefaultInstance()
                                .where(RealmCredentials.class)
                                .findAllAsync())));
    }
}

