package com.chaskify.data.realm.cache;

import com.annimon.stream.Optional;
import com.chaskify.data.model.chaskify.RealmTask;

import java.util.List;


/**
 * Created by alberto on 14/12/17.
 */

public interface TaskCache {
    List<RealmTask> findAll();

    Optional<RealmTask> find(String taskId);

    void put(List<RealmTask> realmTasks);

    void put(RealmTask realmTask);
}
