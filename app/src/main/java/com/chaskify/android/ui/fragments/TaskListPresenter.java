package com.chaskify.android.ui.fragments;

import com.annimon.stream.Stream;
import com.chaskify.android.Util;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskListPresenter extends BasePresenter<TaskListContract.View>
        implements TaskListContract.Presenter {

    private TaskInteractor taskInteractor;

    public TaskListPresenter(TaskInteractor taskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.taskInteractor = taskInteractor;
    }

    @Override
    public void tasks(Date date) {
        addSubscription(taskInteractor.tasks(date)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(tasks -> {
                    if (tasks.isEmpty())
                        view.showEmptyView();
                    else {
                        view.showContentView();
                        view.renderTaskListView(Stream.of(tasks)
                                .map(task -> new TaskItemModel()
                                        .setTask_id(task.getTask_id())
                                        .setTrans_type(task.getTrans_type())
                                        .setStatus(task.getStatus())
                                        .setDelivery_address(task.getDelivery_address())
                                        .setDelivery_date(task.getDelivery_date())
                                        .setCustomer_name(task.getCustomer_name()))
                                .toList());
                    }
                }, throwable -> view.showError(throwable)));

        /*List<TaskItemModel> taskItemModels = new ArrayList<>();
        taskItemModels.add(new TaskItemModel()
                .setTask_id("1145")
                .setTrans_type("service")
                .setStatus("ACCEPTED")
                .setDelivery_address("Edificio 28 b apto7 Pueblo Griffo")
                .setDelivery_date(new Date())
                .setCustomer_name("Alberto Marturelo Lorenzo"));
        view.showContentView();
        view.renderTaskListView(taskItemModels);
        view.hideProgress();*/

    }
}
