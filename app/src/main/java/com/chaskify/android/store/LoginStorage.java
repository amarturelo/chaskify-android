package com.chaskify.android.store;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.login.ChaskifyCredentials;
import com.chaskify.data.model.internal.RealmCredentials;
import com.chaskify.data.model.internal.RealmIcons;
import com.chaskify.data.model.internal.RealmServerConfiguration;
import com.chaskify.domain.model.Credentials;
import com.chaskify.domain.model.Icons;
import com.chaskify.domain.model.ServerConfiguration;

import java.util.List;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by alberto on 11/12/17.
 */

public class LoginStorage {

    public List<Credentials> getCredentialsList() {
        Realm realm = Realm.getDefaultInstance();
        realm.where(RealmCredentials.class).findAll();
        return Stream.of(realm.where(RealmCredentials.class)
                .findAll())
                .map(RealmCredentials::toDomain)
                .toList();
    }

    public void setDefault(Credentials credentials) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> Stream.of(realm1.where(RealmCredentials.class).findAll())
                        .forEach(realmCredentials -> {
                            if (realmCredentials.getUsername().equals(credentials.getUsername()))
                                realmCredentials.setDefault(true);
                            else
                                realmCredentials.setDefault(false);
                        })
                , () -> Timber.d("::Set Default " + credentials.getUsername() + "::"));
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
        realm.where(RealmServerConfiguration.class).findAllAsync().deleteAllFromRealm();
    }
}
