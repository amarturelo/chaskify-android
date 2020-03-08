package com.chaskify.data.realm.mapper;

import com.annimon.stream.Stream;
import com.chaskify.data.realm.model.RealmIcons;
import com.chaskify.domain.model.Icons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 10/01/18.
 */

public class IconsDataMapper {
    public static List<Icons> transform(List<RealmIcons> entities) {
        final List<Icons> list = new ArrayList<>();
        Stream.of(entities).withoutNulls()
                .forEach(realmEvent -> list.add(transform(realmEvent)));
        return list;
    }

    public static Icons transform(RealmIcons chaskifyIcons) {
        Icons icons = new Icons();
        return icons;
    }
}
