package com.chaskify.android.store;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;
import com.chaskify.data.model.internal.RealmCredentials;

import java.util.List;

import io.realm.Realm;

/**
 * Created by alberto on 11/12/17.
 */

public class LoginStorage {

    public List<Credentials> getCredentialsList() {
        Realm realm = Realm.getDefaultInstance();
        realm.where(RealmCredentials.class).findAll();
        return Stream.of(realm.where(RealmCredentials.class)
                .findAll())
                .map(value ->
                        new Credentials()
                                .setAccessToken(value.getAccessToken())
                                .setUsername(value.getUsername())
                                .setPassword(value.getPassword())

                )
                .toList();
    }


    public void addCredentials(Credentials credentials) {
        if (null != credentials) {
            RealmCredentials realmCredentials = new RealmCredentials()
                    .setAccessToken(credentials.getAccessToken())
                    .setUsername(credentials.getUsername())
                    .setPassword(credentials.getPassword());

            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.insertOrUpdate(realmCredentials);
            realm.commitTransaction();
            realm.close();
        }
    }

    public void removeCredentials(Credentials credentials) {
        if (null != credentials) {
            Realm realm = Realm.getDefaultInstance();
            realm.where(RealmCredentials.class)
                    .equalTo(RealmCredentials.USERNAME, credentials.getUsername())
                    .findAll()
                    .deleteAllFromRealm();
        }
    }

    /**
     * Clear the stored values
     */
    public void clear() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.where(RealmCredentials.class).findAllAsync().deleteAllFromRealm();
    }
}
