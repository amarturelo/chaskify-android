package com.chaskify.data.cache;

import com.chaskify.data.model.internal.RealmCredentials;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 11/12/17.
 */

public interface CredentialsCache {
    Single<List<RealmCredentials>> findAll();

}
