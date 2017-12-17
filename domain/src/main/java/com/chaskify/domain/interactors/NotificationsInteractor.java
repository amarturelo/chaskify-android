package com.chaskify.domain.interactors;

import com.chaskify.domain.model.Notification;
import com.chaskify.domain.repositories.NotificationsRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alberto on 17/12/17.
 */

public class NotificationsInteractor {

    private NotificationsRepository notificationsRepository;

    public NotificationsInteractor(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    public Single<List<Notification>> notifications(String driver_id) {
        return notificationsRepository.findAllByDriverId(driver_id);
    }
}
