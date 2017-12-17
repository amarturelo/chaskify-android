package com.chaskify.domain.repositories;

import com.chaskify.domain.model.Notification;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 16/12/17.
 */

public interface NotificationsRepository {
    Single<List<Notification>> findAllByDriverId(String driver_id);
}
