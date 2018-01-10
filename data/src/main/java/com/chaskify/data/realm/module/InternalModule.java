package com.chaskify.data.realm.module;

import com.chaskify.data.realm.model.RealmIcons;
import com.chaskify.data.realm.model.internal.RealmServerConfiguration;

import io.realm.annotations.RealmModule;

@RealmModule(classes = {RealmServerConfiguration.class, RealmIcons.class})
public class InternalModule {
}