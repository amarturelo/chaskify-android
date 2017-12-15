package com.chaskify.android.ui.fragments;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.domain.model.Task;

import java.util.List;

/**
 * Created by alberto on 14/12/17.
 */

public class TaskListContract {
    interface View extends BaseContract.View {
        void renderTaskListView(List<TaskItemModel> taskItemModels);

        void showProgress();

        void hideProgress();

        void showEmptyView();

        void showContentView();

        void showError(Throwable throwable);
    }

    interface Presenter extends BaseContract.Presenter<TaskListContract.View> {
        void tasks(String date);
    }
}
