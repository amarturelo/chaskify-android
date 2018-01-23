package com.chaskify.android.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.helper.MethodCallHelper;
import com.chaskify.data.realm.cache.impl.NotificationsCacheImpl;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.realm.cache.impl.SettingsCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;

/**
 * Created by alberto on 14/01/18.
 */

public class TaskWayPointActionWidget extends LinearLayout implements View.OnClickListener, TaskWayPointActionContract.View {

    private TextView mPositive;
    private TextView mNegative;

    private TaskWayPointActionPresenter presenter;

    private TaskWayPointActionModel mTaskWayPointActionModel;

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
        mPositive = findViewById(R.id.positive_action);
        mPositive.setOnClickListener(this);
        mNegative = findViewById(R.id.negative_action);
        mNegative.setOnClickListener(this);
    }

    public void setTaskId(TaskWayPointActionModel mTaskWayPointActionModel) {
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
        TASK_WAY_POINT_STATUS_ACTION taskStatusAction = TASK_WAY_POINT_STATUS_ACTION.toEnum(mTaskActionModel.getStatus());
        if (taskStatusAction == null)
            hide();
        else {
            show();
            mPositive.setText(taskStatusAction.getPositive());
            mNegative.setText(taskStatusAction.getNegative());
        }
    }

    private void hide() {
        setVisibility(GONE);
    }

    private void show() {
        setVisibility(VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_action:
                /*switch (mTaskWayPointActionModel.getStatus()) {
                    case "ASSIGNED":
                        presenter.accept(mTaskWayPointActionModel.getTaskId());
                        break;
                    case "IN ROUTE":
                        presenter.arrivedTask(mTaskWayPointActionModel.getTaskId());
                        break;
                    case "ACCEPTED":
                        presenter.startTask(mTaskWayPointActionModel.getTaskId());
                        break;
                    case "ARRIVED":
                        presenter.successfulTask(mTaskWayPointActionModel.getTaskId());
                        break;
                    case "UNASSIGNED":
                        presenter.accept(mTaskWayPointActionModel.getTaskId());
                        break;
                    default:
                        break;
                }*/
                break;
            case R.id.negative_action:
                switch (mTaskWayPointActionModel.getStatus()) {

                    default:
                        break;
                }
                break;
        }
    }

    public static class TaskWayPointActionModel {
        private String driverId;
        private String taskId;
        private String status;

        public String getDriverId() {
            return driverId;
        }

        public TaskWayPointActionModel setDriverId(String driverId) {
            this.driverId = driverId;
            return this;
        }

        public String getTaskId() {
            return taskId;
        }

        public TaskWayPointActionModel setTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public String getStatus() {
            return status;
        }

        public TaskWayPointActionModel setStatus(String status) {
            this.status = status;
            return this;
        }
    }

    public enum TASK_WAY_POINT_STATUS_ACTION {
        ASSIGNED(R.string.title_task_accept, R.string.title_task_decline, R.color.task_accepted),
        ACCEPTED(R.string.title_task_start, R.string.title_task_cancel, R.color.task_start),
        IN_ROUTE(R.string.title_task_arrived, R.string.title_task_cancel, R.color.task_arrived),
        ARRIVED(R.string.title_task_successfult, R.string.title_task_failed, R.color.task_successful),
        UNASSIGNED(R.string.title_task_accept, R.string.title_task_decline, R.color.task_accepted);

        private int positive;
        private int negative;
        private int color;

        TASK_WAY_POINT_STATUS_ACTION(int positive, int negative, int color) {
            this.positive = positive;
            this.negative = negative;
            this.color = color;
        }

        public int getPositive() {
            return positive;
        }

        public int getNegative() {
            return negative;
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
