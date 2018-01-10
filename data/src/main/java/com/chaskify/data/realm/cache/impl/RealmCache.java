package com.chaskify.data.realm.cache.impl;

import android.os.Handler;
import android.os.Looper;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by alberto on 9/01/18.
 */

public class RealmCache {
    protected void close(Realm realm, Looper looper) {
        if (realm == null || looper == null) {
            return;
        }
        Timber.d("::closing realm getByDriverId::");
        //new Handler(looper).post(realm::close);
        realm.close();
    }
}
