package com.chaskify.data.realm.module;

import com.chaskify.data.model.chaskify.RealmTask;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {RealmTask.class})
public class InMemoryModule {
}