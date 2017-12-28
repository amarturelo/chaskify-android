package com.chaskify.android.ui.activities;

import com.chaskify.android.shared.BaseContract;
import com.chaskify.android.ui.model.TaskCalendarItemModel;

import java.util.Date;
import java.util.List;

/**
 * Created by alberto on 15/12/17.
 */

public class MainContract {
    interface View extends BaseContract.View {

        void renderEvent(List<TaskCalendarItemModel> taskCalendarItemModels);

        void showError(Throwable throwable);
    }

    interface Presenter extends BaseContract.Presenter<MainContract.View> {
        void calendarTasks(Date start, Date end);
    }
}
