package com.chaskify.android.ui.widget;

import android.app.Dialog;
import android.app.FragmentManager;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.TaskRepositoryImpl;
import com.chaskify.domain.interactors.TaskInteractor;

public class TaskDialogFragment extends BottomSheetDialogFragment implements TaskDialogContract.View {

    public static final String ARG_TASK_ID = "task_id";
    public static final String ARG_DRIVER_ID = "driver_id";

    private TaskDialogPresenter taskDialogPresenter;

    private String mTaskId;
    private String mDriverId;

    private TextView textViewTaskId;
    private TextView textViewTaskType;
    private TextView textViewTaskDate;
    private TextView textViewTaskAddress;
    private TextView textViewTaskTime;
    private View viewTaskStatus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTaskId = getArguments().getString(ARG_TASK_ID);
            mDriverId = getArguments().getString(ARG_DRIVER_ID);

            taskDialogPresenter = new TaskDialogPresenter(new TaskInteractor(
                    new TaskRepositoryImpl(
                            Chaskify.getInstance().getDefaultSession().get().getTaskRestClient()
                            , new TaskCacheImpl()
                    )
            ));
        } else
            dismiss();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initComponents(View view) {
        textViewTaskId = view.findViewById(R.id.task_id);
        textViewTaskType = view.findViewById(R.id.task_type);
        textViewTaskDate = view.findViewById(R.id.task_date);
        textViewTaskTime = view.findViewById(R.id.task_time);
        textViewTaskAddress = view.findViewById(R.id.task_address);
        viewTaskStatus = view.findViewById(R.id.task_status);
    }

    @Override
    public void onResume() {
        super.onResume();
        taskDialogPresenter.taskById(mDriverId, mTaskId);
    }

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.buttom_sheet_task, null);
        dialog.setContentView(contentView);
        initComponents(contentView);
        taskDialogPresenter.bindView(this);
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

    @Override
    public void renderTask(TaskModel taskModel) {
        textViewTaskId.setText(taskModel.getTaskId());
        textViewTaskAddress.setText(taskModel.getDeliveryAddress());
        textViewTaskType.setText(taskModel.getTransType());
        textViewTaskDate.setText(DateUtils.formatDateTime(getContext(), taskModel.getDeliveryDate().getTime(), DateUtils.FORMAT_ABBREV_MONTH));
        textViewTaskTime.setText(DateUtils.formatDateTime(getContext(), taskModel.getDeliveryDate().getTime(), DateUtils.FORMAT_SHOW_TIME));

        switch (taskModel.getStatus()) {
            case "ASSIGNED":
                viewTaskStatus.setBackgroundResource(R.color.task_assigned);
                break;
            case "SUCCESSFUL":
                viewTaskStatus.setBackgroundResource(R.color.task_successful);
                break;
            case "COMPLETE":
                viewTaskStatus.setBackgroundResource(R.color.task_successful);
                break;
            case "IN ROUTE":
                viewTaskStatus.setBackgroundResource(R.color.task_in_route);
                break;
            case "ACCEPTED":
                viewTaskStatus.setBackgroundResource(R.color.task_accepted);
                break;
            case "SIGNATURE":
                viewTaskStatus.setBackgroundResource(R.color.task_signature);
                break;
            case "ARRIVED":
                viewTaskStatus.setBackgroundResource(R.color.task_arrived);
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        taskDialogPresenter.release();
    }

    public static TaskDialogFragment getCalling(String driver_id, String task_id) {
        TaskDialogFragment taskDialogFragment = new TaskDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DRIVER_ID, driver_id);
        args.putString(ARG_TASK_ID, task_id);
        taskDialogFragment.setArguments(args);
        return taskDialogFragment;
    }

}