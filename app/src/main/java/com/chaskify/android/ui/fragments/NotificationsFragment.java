package com.chaskify.android.ui.fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.adapters.NotificationsListAdapter;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.model.NotificationItemModel;
import com.chaskify.android.ui.widget.MultiStateView;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.repositories.NotificationRepositoryImpl;
import com.chaskify.domain.interactors.NotificationsInteractor;
import com.chaskify.domain.model.Notification;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class NotificationsFragment extends BaseFragment implements NotificationsContract.View, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefresh;

    private MultiStateView stateView;

    private RecyclerView notificationsList;

    private NotificationsPresenter notificationsPresenter;

    private NotificationsListAdapter notificationsListAdapter;

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
        initView(view);

        notificationsPresenter.bindView(this);
        notificationsPresenter.notificationsByDriverId(Chaskify
                .getInstance()
                .getDefaultSession()
                .get()
                .getCredentials()
                .getDriverId());
    }

    private void initView(View view) {
        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        stateView = view.findViewById(R.id.multi_state);
        swipeRefresh.setOnRefreshListener(this);
        notificationsList = view.findViewById(R.id.notifications_list);
        notificationsList.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationsListAdapter = new NotificationsListAdapter();
        notificationsList.setAdapter(notificationsListAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_notifications;
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void renderNotifications(List<NotificationItemModel> notifications) {
        if (!notifications.isEmpty()) {
            stateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            notificationsListAdapter.add(notifications);
        } else
            stateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        notificationsPresenter.release();
    }

    @Override
    public void onRefresh() {
        notificationsPresenter.notificationsByDriverId(Chaskify
                .getInstance()
                .getDefaultSession()
                .get()
                .getCredentials()
                .getDriverId());
    }
}
