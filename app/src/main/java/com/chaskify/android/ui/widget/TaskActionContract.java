package com.chaskify.android.ui.widget;

import com.chaskify.android.shared.BaseContract;

/**
 * Created by alberto on 14/01/18.
 */

public interface TaskActionContract {
    interface View extends BaseContract.View {
        void renderActions(TaskActionWidget.TaskActionModel mTaskActionModel);
    }

    interface Presenter extends BaseContract.Presenter<TaskActionContract.View> {

        void acceptTask(String id);

        void arrivedTask(String id);

        void startTask(String id);

        void successful(String id);

        void declineTask(String id);

        void cancelTask(String taskId, String reason);
    }
}
