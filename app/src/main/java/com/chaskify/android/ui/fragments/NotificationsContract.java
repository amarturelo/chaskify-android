package com.chaskify.android.ui.fragments;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.NotificationItemModel;
import com.chaskify.domain.model.Notification;

import java.util.List;

/**
 * Created by alberto on 17/12/17.
 */

public class NotificationsContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void renderNotifications(List<NotificationItemModel> notifications);
    }

    interface Presenter extends BaseContract.Presenter<NotificationsContract.View> {
        void notificationsByDriverId(String driver_id);
    }
}
