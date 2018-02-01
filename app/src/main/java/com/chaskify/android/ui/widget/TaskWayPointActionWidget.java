package com.chaskify.android.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.helper.MethodCallHelper;
import com.chaskify.android.helper.ToastIfError;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;
import com.fxn.cue.Cue;
import com.fxn.cue.enums.Duration;
import com.fxn.cue.enums.Type;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import timber.log.Timber;

/**
 * Created by alberto on 14/01/18.
 */

public class TaskWayPointActionWidget extends LinearLayout implements View.OnClickListener, TaskWayPointActionContract.View {

    private TextView mPositive;

    private TaskWayPointActionPresenter presenter;

    private TaskWayPointActionModel mTaskWayPointActionModel;
    private TextView mNegative;

    private ProgressBar pbActionProgress;

    private View formAction;

    public TaskWayPointActionWidget(Context context) {
        this(context, null, 0);
    }

    public TaskWayPointActionWidget(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaskWayPointActionWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.widget_task_action_buttom, this);
        init();
    }

    private void init() {
        Timber.tag(this.getClass().getSimpleName());
        pbActionProgress = findViewById(R.id.pb_action_progress);
        formAction = findViewById(R.id.form_actions);
        mPositive = findViewById(R.id.positive_action);
        mPositive.setOnClickListener(this);
        mNegative = findViewById(R.id.negative_action);
    }

    public void attachWayPoint(TaskWayPointActionModel mTaskWayPointActionModel) {
        this.mTaskWayPointActionModel = mTaskWayPointActionModel;

        if (presenter != null)
            presenter.release();

        Chaskify.getInstance().getSessionByDriverId(mTaskWayPointActionModel.getDriverId())
                .executeIfAbsent(this::hide)
                .ifPresent(chaskifySession -> {
                    presenter = new TaskWayPointActionPresenter(new MethodCallHelper(
                            chaskifySession
                            , new TaskCacheImpl()
                            , new NotificationsCacheImpl()
                            , new ProfileCacheImpl()
                            , new SettingsCacheImpl()
                            , new TaskWayPointCacheImpl()));
                    presenter.bindView(this);
                    renderActions(this.mTaskWayPointActionModel);
                });
    }

    @Override
    public void renderActions(TaskWayPointActionModel mTaskActionModel) {
        Timber.d(mTaskActionModel.toString());

        TASK_WAY_POINT_STATUS_ACTION taskStatusAction = TASK_WAY_POINT_STATUS_ACTION.toEnum(mTaskActionModel.getStatus());
        if (taskStatusAction == null)
            hide();
        else {
            show();
            mPositive.setText(taskStatusAction.getPositive());
            mNegative.setVisibility(INVISIBLE);
        }
    }

    private void hide() {
        setVisibility(GONE);
    }

    private void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void showError(Exception e) {
        ToastIfError.showError(getContext(), e);
    }

    @Override
    public void showProgress() {
        pbActionProgress.setVisibility(VISIBLE);
        formAction.setVisibility(INVISIBLE);
    }

    @Override
    public void hideProgress() {
        formAction.setVisibility(VISIBLE);
        pbActionProgress.setVisibility(INVISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_action:
                switch (mTaskWayPointActionModel.getStatus()) {
                    case "IN ROUTE":
                        presenter.successfulTaskWayPoint(mTaskWayPointActionModel.getTaskWayPointId());
                        break;
                    case "ACCEPTED":
                        presenter.startTaskWayPoint(mTaskWayPointActionModel.getTaskWayPointId());
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    public static class TaskWayPointActionModel {
        private String driverId;
        private String taskWayPointId;
        private String taskStatus;
        private String status;

        public String getDriverId() {
            return driverId;
        }

        public TaskWayPointActionModel setDriverId(String driverId) {
            this.driverId = driverId;
            return this;
        }

        public String getStatus() {
            return status;
        }

        public TaskWayPointActionModel setStatus(String status) {
            this.status = status;
            return this;
        }

        public String getTaskWayPointId() {
            return taskWayPointId;
        }

        public TaskWayPointActionModel setTaskWayPointId(String taskWayPointId) {
            this.taskWayPointId = taskWayPointId;
            return this;
        }

        public String getTaskStatus() {
            return taskStatus;
        }

        public TaskWayPointActionModel setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
            return this;
        }

        @Override
        public String toString() {
            return "TaskWayPointActionModel{" +
                    "driverId='" + driverId + '\'' +
                    ", taskWayPointId='" + taskWayPointId + '\'' +
                    ", taskStatus='" + taskStatus + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }


    public enum TASK_WAY_POINT_STATUS_ACTION {
        ASSIGNED(R.string.title_task_accept, R.color.task_accepted),
        ACCEPTED(R.string.title_task_start, R.color.task_start),
        IN_ROUTE(R.string.title_task_way_point_successful, R.color.task_arrived),
        ARRIVED(R.string.title_task_successful, R.color.task_successful),
        UNASSIGNED(R.string.title_task_accept, R.color.task_accepted);

        private int positive;
        private int color;

        TASK_WAY_POINT_STATUS_ACTION(int positive, int color) {
            this.positive = positive;
            this.color = color;
        }

        public int getPositive() {
            return positive;
        }

        public int getColor() {
            return color;
        }

        public static TASK_WAY_POINT_STATUS_ACTION toEnum(String status) {
            switch (status) {
                case "ASSIGNED":
                    return TASK_WAY_POINT_STATUS_ACTION.ASSIGNED;
                case "IN ROUTE":
                    return TASK_WAY_POINT_STATUS_ACTION.IN_ROUTE;
                case "ACCEPTED":
                    return TASK_WAY_POINT_STATUS_ACTION.ACCEPTED;
                case "ARRIVED":
                    return TASK_WAY_POINT_STATUS_ACTION.ARRIVED;
                case "UNASSIGNED":
                    return TASK_WAY_POINT_STATUS_ACTION.UNASSIGNED;
                default:
                    return null;
            }
        }
    }
}
