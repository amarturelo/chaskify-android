package com.chaskify.android.store;

import com.annimon.stream.Stream;
import com.chaskify.data.model.internal.RealmServerConfiguration;
import com.chaskify.domain.model.ServerConfiguration;

import java.util.List;

import io.realm.Realm;

/**
 * Created by alberto on 11/12/17.
 */

public class ConfigurationServerStorage {

    public List<ServerConfiguration> getConfigurations() {
        Realm realm = Realm.getDefaultInstance();
        realm.where(RealmServerConfiguration.class).findAll();
        return Stream.of(realm.where(RealmServerConfiguration.class)
                .findAll())
                .map(RealmServerConfiguration::toDomain)
                .toList();
    }

    public void addConfiguration(ServerConfiguration serverConfiguration) {
        if (null != serverConfiguration) {
            RealmServerConfiguration realmCredentials = new RealmServerConfiguration()
                    .setUsername(serverConfiguration.getUsername());

            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.insertOrUpdate(realmCredentials);
            realm.commitTransaction();
            realm.close();
        }
    }

    public void removeConfiguration(String username) {
        Realm realm = Realm.getDefaultInstance();
        realm.where(RealmServerConfiguration.class)
                .equalTo(RealmServerConfiguration.USERNAME, username)
                .findAll()
                .deleteAllFromRealm();
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
