package com.chaskify.android.ui.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.repositories.NotificationRepositoryImpl;
import com.chaskify.domain.interactors.NotificationsInteractor;
import com.chaskify.domain.model.Notification;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotificationsFragment extends BaseFragment implements NotificationsContract.View {

    private NotificationsPresenter notificationsPresenter;

    public NotificationsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationsPresenter = new NotificationsPresenter(
                new NotificationsInteractor(
                        new NotificationRepositoryImpl(
                                Chaskify.getInstance().getDefaultSession().get().getNotificationRestClient()
                                , new NotificationsCacheImpl()
                        )
                ));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notificationsPresenter.bindView(this);
        notificationsPresenter.notificationsByDriverId(Chaskify
                .getInstance()
                .getDefaultSession()
                .get()
                .getCredentials()
                .getUsername());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_notifications;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void renderNotifications(List<Notification> notifications) {

    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_LONG)
                .show();
    }
}
