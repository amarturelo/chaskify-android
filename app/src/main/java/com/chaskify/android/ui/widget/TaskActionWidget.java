package com.chaskify.android.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskItemActionModel;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.TaskRepositoryImpl;
import com.chaskify.domain.interactors.TaskInteractor;

/**
 * Created by alberto on 14/01/18.
 */

public class TaskActionWidget extends LinearLayout implements TaskActionContract.View, View.OnClickListener {

    private TextView mPositive;
    private TextView mNegative;

    private TaskActionPresenter presenter;

    private String mTaskId;

    private TaskItemActionModel model;

    public TaskActionWidget(Context context) {
        this(context, null, 0);
    }

    public TaskActionWidget(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaskActionWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.widget_task_action_buttom, this);
        presenter = new TaskActionPresenter(
                new TaskInteractor(
                        new TaskRepositoryImpl(
                                new TaskCacheImpl()
                        )
                ));

        init();

        presenter.bindView(this);

    }

    private void init() {
        mPositive = findViewById(R.id.positive_action);
        mPositive.setOnClickListener(this);
        mNegative = findViewById(R.id.negative_action);
        mNegative.setOnClickListener(this);
    }

    public void setTaskId(String mTaskId) {
        this.mTaskId = mTaskId;
        presenter.getTask(this.mTaskId);
    }

    @Override
    public void renderActions(TaskItemActionModel taskItemActionModel) {
        this.model = taskItemActionModel;
        TASK_STATUS_ACTION taskStatusAction = TASK_STATUS_ACTION.toEnum(taskItemActionModel.getStatus());
        if (taskStatusAction == null)
            setVisibility(GONE);
        else {
            setVisibility(VISIBLE);
            mPositive.setText(taskStatusAction.getPositive());
            mNegative.setText(taskStatusAction.getNegative());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_action:
                switch (model.getStatus()) {
                    case "ASSIGNED":
                        presenter.accept(model.getId());
                        break;
                    case "IN ROUTE":
                        presenter.arrived(model.getId());
                        break;
                    case "ACCEPTED":
                        presenter.start(model.getId());
                        break;
                    case "ARRIVED":
                        presenter.successful(model.getId());
                        break;
                    case "UNASSIGNED":
                        presenter.accept(model.getId());
                        break;
                    default:
                        break;
                }
                break;
            case R.id.negative_action:
                switch (model.getStatus()) {

                    default:
                        break;
                }
                break;
        }
    }

    public enum TASK_STATUS_ACTION {
        ASSIGNED(R.string.title_task_accept, R.string.title_task_decline, R.color.task_accepted),
        ACCEPTED(R.string.title_task_start, R.string.title_task_cancel, R.color.task_start),
        IN_ROUTE(R.string.title_task_arrived, R.string.title_task_cancel, R.color.task_arrived),
        ARRIVED(R.string.title_task_successfult, R.string.title_task_failed, R.color.task_successful),
        UNASSIGNED(R.string.title_task_accept, R.string.title_task_decline, R.color.task_accepted);

        private int positive;
        private int negative;
        private int color;

        TASK_STATUS_ACTION(int positive, int negative, int color) {
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

        public static TASK_STATUS_ACTION toEnum(String status) {
            switch (status) {
                case "ASSIGNED":
                    return TASK_STATUS_ACTION.ASSIGNED;
                case "IN ROUTE":
                    return TASK_STATUS_ACTION.IN_ROUTE;
                case "ACCEPTED":
                    return TASK_STATUS_ACTION.ACCEPTED;
                case "ARRIVED":
                    return TASK_STATUS_ACTION.ARRIVED;
                case "UNASSIGNED":
                    return TASK_STATUS_ACTION.UNASSIGNED;
                default:
                    return null;
            }
        }
    }
}
