package com.chaskify.android.ui.activities;

import com.annimon.stream.Stream;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskCalendarItemModel;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.domain.interactors.CalendarTaskInteractor;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by alberto on 15/12/17.
 */

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    private CalendarTaskInteractor calendarTaskInteractor;

    private TaskInteractor mTaskInteractor;

    public MainPresenter(CalendarTaskInteractor calendarTaskInteractor) {
        this.calendarTaskInteractor = calendarTaskInteractor;
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

}
