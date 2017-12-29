package com.chaskify.android.ui.widget;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.android.ui.model.TaskWaypointModel;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointViewBottomSheetDialogContract {
    interface View extends BaseContract.View {

        void showProgress();

        void hideProgress();

        void showError(Throwable throwable);

        void renderWayPoint(TaskWaypointModel waypointModel);
    }

    interface Presenter extends BaseContract.Presenter<TaskWaypointViewBottomSheetDialogContract.View> {
        void wayPointById(String driver_id, String task_id);
    }
}
