package com.chaskify.android.ui.fragments;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.chaskify.android.MethodCallHelper;
import com.chaskify.android.helper.LogIfError;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.mapper.TaskModelDataMapper;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.filter.TaskIdFilter;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 20/12/17.
 */

public class TaskViewDialogPresenter extends BasePresenter<TaskViewDialogContract.View>
        implements TaskViewDialogContract.Presenter {

    private TaskInteractor taskInteractor;

    private ChaskifySession mChaskifySession;

    private MethodCallHelper mMethodCallHelper;

    public TaskViewDialogPresenter(ChaskifySession mChaskifySession, TaskInteractor taskInteractor) {
        Timber.tag(this.getClass().getSimpleName());

        this.mMethodCallHelper = new MethodCallHelper(mChaskifySession
                , new TaskCacheImpl()
                , new NotificationsCacheImpl()
                , new ProfileCacheImpl()
                , new SettingsCacheImpl()
                , new TaskWayPointCacheImpl());

        this.taskInteractor = taskInteractor;
        this.mChaskifySession = mChaskifySession;
    }

    @Override
    public void bindView(@NonNull TaskViewDialogContract.View view) {
        super.bindView(view);
    }

    @Override
    public void taskById(List<Filter> filters) {
        Stream.of(filters)
                .filter(value -> value instanceof TaskIdFilter)
                .findFirst()
                .ifPresent(filter -> mMethodCallHelper
                        .getTaskDetails(((TaskIdFilter) filter).getTaskId())
                        .continueWith(new LogIfError()));

        addSubscription(taskInteractor.tasks(filters)
                .map(tasks -> Stream.of(tasks).findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        task -> view.renderTask(TaskModelDataMapper.transform(task))
                        , throwable -> view.showError(throwable)));
    }
}
