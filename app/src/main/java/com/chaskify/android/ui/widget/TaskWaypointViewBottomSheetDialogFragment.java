package com.chaskify.android.ui.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskWaypointModel;
import com.chaskify.data.repositories.TaskWaypointRepositoryImpl;
import com.chaskify.domain.interactors.TaskWaypointInteractor;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointViewBottomSheetDialogFragment extends BottomSheetDialogFragment implements TaskWaypointViewBottomSheetDialogContract.View {
    public static final String ARG_TASK_WAY_POINT_ID = "task_id";
    public static final String ARG_DRIVER_ID = "driver_id";

    private String mTaskWaypointId;
    private String mDriverId;

    private TextView textViewTaskWaypointId;
    private TextView textViewTaskType;
    private TextView textViewTaskDate;
    private TextView textViewTaskAddress;
    private TextView textViewTaskTime;
    private TextView textViewTaskWayPointDescription;
    private View viewTaskStatus;

    private View formTaskWaypointDescription;

    private TaskWaypointViewBottomSheetDialogPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTaskWaypointId = getArguments().getString(ARG_TASK_WAY_POINT_ID);
            mDriverId = getArguments().getString(ARG_DRIVER_ID);

            presenter = new TaskWaypointViewBottomSheetDialogPresenter(
                    new TaskWaypointInteractor(
                            new TaskWaypointRepositoryImpl(
                                    Chaskify.getInstance().getDefaultSession().get().getTaskWaypointRestClient()
                            )
                    )
            );
        } else
            dismiss();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_waypoint, null);
        dialog.setContentView(contentView);
        initComponents(contentView);
        presenter.bindView(this);
    }

    private void initComponents(View view) {
        textViewTaskWaypointId = view.findViewById(R.id.task_waypoint_id);
        textViewTaskType = view.findViewById(R.id.task_type);
        textViewTaskDate = view.findViewById(R.id.task_date);
        textViewTaskTime = view.findViewById(R.id.task_time);
        textViewTaskAddress = view.findViewById(R.id.task_address);
        viewTaskStatus = view.findViewById(R.id.task_status);
        textViewTaskWayPointDescription = view.findViewById(R.id.task_waypoint_description);
        formTaskWaypointDescription = view.findViewById(R.id.form_task_waypoint_description);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.wayPointById(mDriverId, mTaskWaypointId);
    }

    public static TaskWaypointViewBottomSheetDialogFragment newInstance(String driver_id, String task_id) {
        TaskWaypointViewBottomSheetDialogFragment taskDialogFragment = new TaskWaypointViewBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DRIVER_ID, driver_id);
        args.putString(ARG_TASK_WAY_POINT_ID, task_id);
        taskDialogFragment.setArguments(args);
        return taskDialogFragment;
    }

    public static TaskWaypointViewBottomSheetDialogFragment getCalling(String driver_id, String task_id) {
        return TaskWaypointViewBottomSheetDialogFragment.newInstance(driver_id, task_id);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_LONG).show();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void renderWayPoint(TaskWaypointModel waypointModel) {
        textViewTaskWaypointId.setText("#" + waypointModel.getId());
    }
}
