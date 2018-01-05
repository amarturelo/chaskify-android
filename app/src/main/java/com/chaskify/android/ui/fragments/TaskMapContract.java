package com.chaskify.android.ui.fragments;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.model.TaskItemSnapModel;

import java.util.Date;
import java.util.List;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskMapContract {
    interface View extends BaseContract.View {
        void renderTaskListView(List<TaskItemSnapModel> taskItemModels);

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);
    }

    interface Presenter extends BaseContract.Presenter<TaskMapContract.View> {
        void tasks(String id, Date date);
    }
}
