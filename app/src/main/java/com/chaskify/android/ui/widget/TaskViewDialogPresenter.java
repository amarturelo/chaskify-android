package com.chaskify.android.ui.widget;

import android.support.annotation.NonNull;

import com.chaskify.android.looper.BackgroundLooper;
import com.chaskify.android.shared.BasePresenter;
import com.chaskify.android.ui.model.mapper.TaskModelDataMapper;
import com.chaskify.domain.interactors.TaskInteractor;

import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by alberto on 20/12/17.
 */

public class TaskViewDialogPresenter extends BasePresenter<TaskViewDialogContract.View>
        implements TaskViewDialogContract.Presenter {

    private TaskInteractor taskInteractor;

    public TaskViewDialogPresenter(TaskInteractor taskInteractor) {
        Timber.tag(this.getClass().getSimpleName());
        this.taskInteractor = taskInteractor;
    }

    @Override
    public void bindView(@NonNull TaskViewDialogContract.View view) {
        super.bindView(view);
    }

    @Override
    public void taskById(String driver_id, String task_id) {
        Timber.d("::taskById " + "Driver Id: " + driver_id + " Task Id: " + task_id + "::");
        addSubscription(taskInteractor.taskById(driver_id, task_id)
                .subscribeOn(AndroidSchedulers.from(BackgroundLooper.get()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress())
                .doFinally(() -> view.hideProgress())
                .subscribe(
                        task -> view.renderTask(TaskModelDataMapper.transform(task))
                        , throwable -> view.showError(throwable)));


        /*TaskModel taskModel = new TaskModel()
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

        List<TaskWaypointItemModel> taskWaypointItemModels = new ArrayList<>();

        taskWaypointItemModels.add(new TaskWaypointItemModel()
                .setId("654")
                .setDeliveryAddress("Frente a la pizzeria del vedado")
                .setTaskStatus("ACCEPTED")
                .setTaskType("service"));

        taskWaypointItemModels.add(new TaskWaypointItemModel()
                .setId("658")
                .setDeliveryAddress("Al doblar del Hospital")
                .setTaskStatus("ARRIVED")
                .setTaskType("delivery"));

        taskModel.setTaskWaypointItemModels(taskWaypointItemModels);

        view.renderTask(taskModel);
        view.hideProgress();*/
    }
}
