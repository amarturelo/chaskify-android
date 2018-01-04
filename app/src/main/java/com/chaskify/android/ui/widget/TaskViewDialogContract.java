package com.chaskify.android.ui.widget;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.TaskModel;

/**
 * Created by alberto on 20/12/17.
 */

public class TaskViewDialogContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void renderTask(TaskModel taskModel);
    }

    interface Presenter extends BaseContract.Presenter<TaskViewDialogContract.View> {
        void taskById(String driver_id, String task_id);
    }
}
