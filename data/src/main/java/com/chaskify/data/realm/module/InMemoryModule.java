package com.chaskify.data.realm.module;

import com.chaskify.data.realm.model.RealmIcons;
import com.chaskify.data.realm.model.RealmProfile;
import com.chaskify.data.realm.model.RealmSettings;
import com.chaskify.data.realm.model.RealmTask;
import com.chaskify.data.realm.model.RealmTaskHistory;
import com.chaskify.data.realm.model.RealmTaskWaypoint;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {RealmTask.class, RealmTaskWaypoint.class, RealmTaskHistory.class, RealmProfile.class, RealmSettings.class, RealmIcons.class})
public class InMemoryModule {
}