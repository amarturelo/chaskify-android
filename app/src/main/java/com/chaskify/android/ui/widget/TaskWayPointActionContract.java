package com.chaskify.android.ui.widget;

import com.chaskify.android.shared.BaseContract;

/**
 * Created by alberto on 14/01/18.
 */

public interface TaskWayPointActionContract {
    interface View extends BaseContract.View {
        void showProgress();

        void hideProgress();

        void renderActions(TaskWayPointActionWidget.TaskWayPointActionModel taskWayPointActionModel);
    }

    interface Presenter extends BaseContract.Presenter<TaskWayPointActionContract.View> {

        void startTaskWayPoint(String id);

        void successfulTaskWayPoint(String id);
    }
}
