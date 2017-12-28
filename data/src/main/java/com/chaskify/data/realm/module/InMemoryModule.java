package com.chaskify.data.realm.module;

import com.chaskify.data.model.chaskify.RealmTask;
import com.chaskify.data.model.chaskify.RealmTaskHistory;
import com.chaskify.data.model.chaskify.RealmTaskWaypoint;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {RealmTask.class, RealmTaskWaypoint.class, RealmTaskHistory.class})
public class InMemoryModule {
}