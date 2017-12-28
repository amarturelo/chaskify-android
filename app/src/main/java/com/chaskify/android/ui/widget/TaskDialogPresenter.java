package com.chaskify.android.ui.widget;

import android.support.annotation.NonNull;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.TaskHistoryItemModel;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.android.ui.model.TaskWaypointItemModel;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 20/12/17.
 */

public class TaskDialogPresenter extends BasePresenter<TaskDialogContract.View>
        implements TaskDialogContract.Presenter {

    private TaskInteractor taskInteractor;

    public TaskDialogPresenter(TaskInteractor taskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.taskInteractor = taskInteractor;
    }

    @Override
    public void bindView(@NonNull TaskDialogContract.View view) {
        super.bindView(view);
    }

    @Override
    public void taskById(String driver_id, String task_id) {
        Timber.d("::taskById " + "Driver Id: " + driver_id + " Task Id: " + task_id + "::");
        /*addSubscription(taskInteractor.taskById(driver_id, task_id)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(task -> view.renderTask(new TaskModel()
                        .setTaskId(task.getTask_id())
                        .setCustomerName(task.getCustomer_name())
                        .setDeliveryAddress(task.getDelivery_address())
                        .setDeliveryDate(task.getDelivery_date())
                        .setDescription(task.getTask_description())
                        .setStatus(task.getStatus())
                        .setTransType(task.getTrans_type())
                ), throwable -> view.showError(throwable)));*/


        TaskModel taskModel = new TaskModel()
                .setTaskId(task_id)
                .setCustomerName("ALberto Marturelo Lorenzo")
                .setDeliveryAddress("Edificio 28b apto 7 Pueblo Griffo")
                .setDeliveryDate(new Date())
                .setContactNumber("+5352950107")
                .setEmailAddress("amarturelo@gmail.com")
                .setDescription("Una breve desscripcion de lo que va una tarea")
                .setStatus("ACCEPTED")
                .setTransType("service");


        List<TaskHistoryItemModel> taskHistoryItemModels = new ArrayList<>();

        taskHistoryItemModels.add(new TaskHistoryItemModel()
                .setId("12")
                .setTaskId(task_id)
                .setDateCreated(new Date())
                .setStatus("ACCEPTED"));

        taskHistoryItemModels.add(new TaskHistoryItemModel()
                .setId("15")
                .setTaskId(task_id)
                .setDateCreated(new Date())
                .setStatus("ARRIVED"));

        taskModel.setTaskHistoryItemModels(taskHistoryItemModels);

        view.renderTask(taskModel);
        view.hideProgress();
    }
}
