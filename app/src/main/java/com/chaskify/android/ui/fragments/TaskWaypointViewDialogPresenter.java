package com.chaskify.android.ui.fragments;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.android.helper.MethodCallHelper;
import com.chaskify.android.helper.LogIfError;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskWayPointModel;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.filter.WaypointIdFilter;
import com.chaskify.domain.interactors.TaskWaypointInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointViewDialogPresenter extends BasePresenter<TaskWaypointViewDialogContract.View>
        implements TaskWaypointViewDialogContract.Presenter {

    private TaskWaypointInteractor taskWaypointInteractor;

    private MethodCallHelper mMethodCallHelper;

    public TaskWaypointViewDialogPresenter(ChaskifySession chaskifySession, TaskWaypointInteractor taskWaypointInteractor) {
        this.mMethodCallHelper = new MethodCallHelper(chaskifySession
                , new TaskCacheImpl()
                , new NotificationsCacheImpl()
                , new ProfileCacheImpl()
                , new SettingsCacheImpl()
                , new TaskWayPointCacheImpl());
        this.taskWaypointInteractor = taskWaypointInteractor;
    }

    @Override
    public void wayPointById(List<Filter> filters) {
        Stream.of(filters)
                .filter(value -> value instanceof WaypointIdFilter)
                .findFirst()
                .ifPresent(filter -> mMethodCallHelper
                        .getWaypointById(((WaypointIdFilter) filter).getWayPointId())
                        .continueWith(new LogIfError()));

        addSubscription(taskWaypointInteractor.wayPointById(filters)
                .map(waypoint -> Stream.of(waypoint).findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(taskWaypoint -> view.renderWayPoint(new TaskWayPointModel()
                                .setId(taskWaypoint.getId())
                                .setDriverId(mMethodCallHelper.getChaskifySession().getCredentials().getDriverId())
                                .setTaskId(taskWaypoint.getTaskId())
                                .setContactNumber(taskWaypoint.getContactNumber())
                                .setCustomerName(taskWaypoint.getCustomerName())
                                .setDateCreated(taskWaypoint.getDateCreated())
                                .setDeliveryAddress(taskWaypoint.getDeliveryAddress())
                                .setEmailAddress(taskWaypoint.getEmailAddress())
                                .setStatus(taskWaypoint.getStatus())
                                .setTaskStatus(taskWaypoint.getTaskStatus())
                                .setType(taskWaypoint.getType())
                                .setWaypointDescription(taskWaypoint.getWaypointDescription()))
                        , throwable -> view.showError(throwable)));
    }
}
