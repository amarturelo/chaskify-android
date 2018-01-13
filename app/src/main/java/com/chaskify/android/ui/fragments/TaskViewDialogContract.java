package com.chaskify.android.ui.fragments;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.domain.filter.Filter;

import java.util.List;

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
        void taskById(List<Filter> filters);
    }
}
