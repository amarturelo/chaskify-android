package com.chaskify.data.realm.cache.impl.mapper;

import com.annimon.stream.Stream;
import com.chaskify.chaskify_sdk.rest.model.ChaskifyIcons;
import com.chaskify.data.realm.model.RealmIcons;
import com.chaskify.domain.model.Icons;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by alberto on 10/01/18.
 */

public class IconsDataMapper {
    public static RealmList<RealmIcons> transform(List<ChaskifyIcons> entities) {
        final RealmList<RealmIcons> list = new RealmList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static RealmIcons transform(ChaskifyIcons icons) {
        RealmIcons realmIcons = new RealmIcons();
        return realmIcons;
    }
}
