package com.chaskify.android.ui.fragments;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.model.TaskItemSnapModel;
import com.chaskify.android.ui.model.mapper.TaskSnapItemModelDataMapper;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskMapPresenter extends BasePresenter<TaskMapContract.View>
        implements TaskMapContract.Presenter {

    private TaskInteractor taskInteractor;

    public TaskMapPresenter(TaskInteractor taskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.taskInteractor = taskInteractor;
    }

    @Override
    public void tasks(String driverId, Date date) {
        addSubscription(taskInteractor.tasks(driverId, date)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress())
                .doOnNext(tasks -> view.hideProgress())
                .subscribe(tasks -> view.renderTaskListView(TaskSnapItemModelDataMapper.transform(tasks))
                        , throwable -> view.showError(throwable)));

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
