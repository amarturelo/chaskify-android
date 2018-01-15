package com.chaskify.android.ui.activities;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.chaskify.android.MethodCallHelper;
import com.chaskify.android.helper.LogIfError;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskCalendarItemModel;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.model.mapper.TaskSnapItemModelDataMapper;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;
import com.chaskify.domain.filter.DateFilter;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.interactors.CalendarTaskInteractor;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 15/12/17.
 */

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    private ChaskifySession mChaskifySession;

    private MethodCallHelper methodCallHelper;

    private CalendarTaskInteractor calendarTaskInteractor;

    private TaskInteractor mTaskInteractor;

    public MainPresenter(ChaskifySession mChaskifySession, CalendarTaskInteractor calendarTaskInteractor, TaskInteractor mTaskInteractor) {
        this.mChaskifySession = mChaskifySession;

        this.methodCallHelper = new MethodCallHelper(this.mChaskifySession
                , new TaskCacheImpl()
                , new NotificationsCacheImpl()
                , new ProfileCacheImpl()
                , new SettingsCacheImpl()
                , new TaskWayPointCacheImpl());
        this.calendarTaskInteractor = calendarTaskInteractor;
        this.mTaskInteractor = mTaskInteractor;
    }

    @Override
    public void calendarTasks(Date start, Date end) {
        addSubscription(calendarTaskInteractor.calendarTasks(start, end)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(calendarTasks -> view.renderEvent(
                        Stream.of(calendarTasks)
                                .map(calendarTask -> new TaskCalendarItemModel()
                                        .setDay(calendarTask.getDay())
                                        .setId(calendarTask.getId())
                                        .setMonth(calendarTask.getMonth())
                                        .setTitle(calendarTask.getTitle())
                                        .setYear(calendarTask.getYear())
                                )
                                .toList()
                ), throwable -> view.showError(throwable)));
    }

    @Override
    public void tasks(List<Filter> filters) {
        Stream.of(filters)
                .filter(value -> value instanceof DateFilter)
                .findFirst()
                .ifPresent(filter -> methodCallHelper.getTasksByDate(((DateFilter) filter).getDate())
                        .continueWith(new LogIfError()));
    }

}
