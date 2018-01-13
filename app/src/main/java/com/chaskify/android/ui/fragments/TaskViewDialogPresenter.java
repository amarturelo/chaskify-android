package com.chaskify.android.ui.fragments;

import android.support.annotation.NonNull;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.mapper.TaskModelDataMapper;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.domain.filter.Filter;
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

    public TaskViewDialogPresenter(ChaskifySession mChaskifySession, TaskInteractor taskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.taskInteractor = taskInteractor;
        this.mChaskifySession = mChaskifySession;
    }

    @Override
    public void bindView(@NonNull TaskViewDialogContract.View view) {
        super.bindView(view);
    }

    @Override
    public void taskById(List<Filter> filters) {
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
