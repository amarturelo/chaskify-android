package com.chaskify.android.store;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.internal.RealmServerConfiguration;
import com.chaskify.data.realm.module.InternalModule;
import com.chaskify.domain.model.ServerConfiguration;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by alberto on 11/12/17.
 */

public class ConfigurationServerStorage {

    private final RealmConfiguration configuration;

    public ConfigurationServerStorage() {
        configuration = new RealmConfiguration.Builder()
                .name("configuration.realm")
                .modules(new InternalModule())
                .deleteRealmIfMigrationNeeded()
                .build();
    }

    public List<ServerConfiguration> getConfigurations() {
        Realm realm = Realm.getInstance(configuration);
        realm.where(RealmServerConfiguration.class).findAll();
        return Stream.of(realm.where(RealmServerConfiguration.class)
                .findAll())
                .map(RealmServerConfiguration::toDomain)
                .toList();
    }

    public void addConfiguration(ServerConfiguration serverConfiguration) {
        if (null != serverConfiguration) {
            RealmServerConfiguration realmCredentials = new RealmServerConfiguration()
                    .setDriverId(serverConfiguration.getUsername());

            Realm realm = Realm.getInstance(configuration);
            realm.beginTransaction();
            realm.insertOrUpdate(realmCredentials);
            realm.commitTransaction();
            realm.close();
        }
    }

    public void removeConfiguration(String username) {
        Realm realm = Realm.getInstance(configuration);
        realm.where(RealmServerConfiguration.class)
                .equalTo(RealmServerConfiguration.DRIVER_ID, username)
                .findAll()
                .deleteAllFromRealm();
    }

    /**
     * Clear the stored values
     */
    public void clear() {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        realm.where(RealmServerConfiguration.class).findAllAsync().deleteAllFromRealm();
    }
}
