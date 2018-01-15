package com.chaskify.android.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.adapters.TaskHistoryListAdapter;
import com.chaskify.android.adapters.TaskWaypointListAdapter;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.model.TaskModel;
import com.chaskify.android.ui.model.TaskWaypointItemModel;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.TaskRepositoryImpl;
import com.chaskify.domain.filter.DriverFilter;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.filter.TaskIdFilter;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.ArrayList;
import java.util.List;

public class TaskViewDialogFragment extends BottomSheetDialogFragment implements TaskViewDialogContract.View {

    public static final String ARG_TASK_ID = "task_id";
    public static final String ARG_DRIVER_ID = "driver_id";

    private TaskViewDialogPresenter taskDialogPresenter;

    private String mTaskId;
    private String mDriverId;

    private TextView textViewTaskId;
    private TextView textViewTaskType;
    private TextView textViewTaskDate;
    private TextView textViewTaskAddress;
    private TextView textViewTaskTime;
    private TextView textViewTaskDescription;

    private TextView mTextViewTaskClientName;
    private TextView mTextViewTaskClientNumber;
    private TextView mTextViewTaskClientMail;


    private View viewTaskStatus;

    private View formTaskDescription;
    private View formTaskWaypoints;
    private View formTaskHistory;

    private RecyclerView mTaskHistoryList;
    private RecyclerView mTaskWaypointList;

    private TaskWaypointListAdapter taskWaypointListAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTaskId = getArguments().getString(ARG_TASK_ID);
            mDriverId = getArguments().getString(ARG_DRIVER_ID);
            Chaskify.getInstance().getSessionByDriverId(mDriverId)
                    .executeIfAbsent(this::dismiss)
                    .ifPresent(chaskifySession -> {
                        taskDialogPresenter = new TaskViewDialogPresenter(chaskifySession, new TaskInteractor(
                                new TaskRepositoryImpl(
                                        new TaskCacheImpl()
                                )
                        ));
                        taskDialogPresenter.bindView(this);
                    });
        } else
            dismiss();
    }

    private void initComponents(View view) {
        textViewTaskId = view.findViewById(R.id.task_id);
        textViewTaskType = view.findViewById(R.id.task_type);
        textViewTaskDate = view.findViewById(R.id.task_date);
        textViewTaskTime = view.findViewById(R.id.task_time);
        textViewTaskAddress = view.findViewById(R.id.task_address);
        viewTaskStatus = view.findViewById(R.id.task_status);
        textViewTaskDescription = view.findViewById(R.id.task_description);
        formTaskDescription = view.findViewById(R.id.form_task_description);
        formTaskWaypoints = view.findViewById(R.id.form_task_way_points);
        formTaskHistory = view.findViewById(R.id.form_task_history);

        mTextViewTaskClientName = view.findViewById(R.id.task_client_name);
        mTextViewTaskClientNumber = view.findViewById(R.id.task_client_number);
        mTextViewTaskClientMail = view.findViewById(R.id.task_client_mail);

        //Waypoint list
        mTaskWaypointList = view.findViewById(R.id.task_way_points_list);
        mTaskWaypointList.setLayoutManager(new LinearLayoutManager(getContext()));
        //mTaskWaypointList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));

        //history list
        mTaskHistoryList = view.findViewById(R.id.task_history_list);
        mTaskHistoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        //mTaskHistoryList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_task, null);
        dialog.setContentView(contentView);


        initComponents(contentView);
        if (taskDialogPresenter != null) {
            List<Filter> filters = new ArrayList<>();
            filters.add(new DriverFilter()
                    .setDriver(mDriverId));
            filters.add(new TaskIdFilter()
                    .setTaskId(mTaskId));
            taskDialogPresenter.taskById(filters);
        }

        getDialog().setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = (FrameLayout) d.findViewById(R.id.design_bottom_sheet);
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) bottomSheet.getParent();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                bottomSheetBehavior.setPeekHeight(d.findViewById(R.id.form_task_actions).getHeight());
                coordinatorLayout.getParent().requestLayout();
                goToScrollStart();
            }

        });

    }

    @Override
    public void onStart() {
        super.onStart();
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
    public void renderTask(TaskModel taskModel) {
        textViewTaskId.setText(getText(R.string.title_task) + " #" + taskModel.getTaskId());
        textViewTaskAddress.setText(taskModel.getDeliveryAddress());
        textViewTaskType.setText(taskModel.getTransType());

        if (taskModel.getDescription() == null || taskModel.getDescription().isEmpty())
            formTaskDescription.setVisibility(View.GONE);
        else {
            formTaskDescription.setVisibility(View.VISIBLE);
            textViewTaskDescription.setText(taskModel.getDescription());
        }

        mTextViewTaskClientName.setText(taskModel.getCustomerName());
        mTextViewTaskClientNumber.setText(taskModel.getContactNumber());
        mTextViewTaskClientMail.setText(taskModel.getEmailAddress());


        textViewTaskDate.setText(DateUtils.formatDateTime(getContext(), taskModel.getDeliveryDate().getTime(), DateUtils.FORMAT_ABBREV_MONTH));
        textViewTaskTime.setText(DateUtils.formatDateTime(getContext(), taskModel.getDeliveryDate().getTime(), DateUtils.FORMAT_SHOW_TIME));

        switch (taskModel.getStatus()) {
            case "ASSIGNED":
                textViewTaskId.setTextColor(getResources().getColor(R.color.task_assigned));
                viewTaskStatus.setBackgroundResource(R.color.task_assigned);
                break;
            case "SUCCESSFUL":
                textViewTaskId.setTextColor(getResources().getColor(R.color.task_successful));
                viewTaskStatus.setBackgroundResource(R.color.task_successful);
                break;
            case "COMPLETE":
                textViewTaskId.setTextColor(getResources().getColor(R.color.task_successful));
                viewTaskStatus.setBackgroundResource(R.color.task_successful);
                break;
            case "IN ROUTE":
                textViewTaskId.setTextColor(getResources().getColor(R.color.task_in_route));
                viewTaskStatus.setBackgroundResource(R.color.task_in_route);
                break;
            case "ACCEPTED":
                textViewTaskId.setTextColor(getResources().getColor(R.color.task_accepted));
                viewTaskStatus.setBackgroundResource(R.color.task_accepted);
                break;
            case "SIGNATURE":
                textViewTaskId.setTextColor(getResources().getColor(R.color.task_signature));
                viewTaskStatus.setBackgroundResource(R.color.task_signature);
                break;
            case "ARRIVED":
                textViewTaskId.setTextColor(getResources().getColor(R.color.task_arrived));
                viewTaskStatus.setBackgroundResource(R.color.task_arrived);
                break;
        }

        if (!taskModel.getTaskHistoryItemModels().isEmpty()) {
            formTaskHistory.setVisibility(View.VISIBLE);
            mTaskHistoryList.setAdapter(new TaskHistoryListAdapter(taskModel.getTaskHistoryItemModels()));
        } else
            formTaskHistory.setVisibility(View.GONE);


        if (!taskModel.getTaskWaypointItemModels().isEmpty()) {
            formTaskWaypoints.setVisibility(View.VISIBLE);

            List<TaskWaypointItemModel> itemModels = new ArrayList<>();
            itemModels.addAll(taskModel.getTaskWaypointItemModels());
            itemModels.addAll(taskModel.getTaskWaypointItemModels());
            itemModels.addAll(taskModel.getTaskWaypointItemModels());
            itemModels.addAll(taskModel.getTaskWaypointItemModels());

            taskWaypointListAdapter = new TaskWaypointListAdapter(itemModels);
            taskWaypointListAdapter.setOnItemListened((view, position) -> {
                Navigator.showTaskWaypointDetails(getChildFragmentManager()
                        , mDriverId
                        , taskWaypointListAdapter.getItem(position).getId());
                taskWaypointListAdapter.getItem(position);
            });
            mTaskWaypointList.setAdapter(taskWaypointListAdapter);
        } else
            formTaskWaypoints.setVisibility(View.GONE);


        //goToScrollStart();
    }

    private void goToScrollStart() {
        getDialog().findViewById(R.id.form_task_scroll_view).setScrollY(0);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        taskDialogPresenter.release();
    }

    public static TaskViewDialogFragment newInstance(String driver_id, String task_id) {
        TaskViewDialogFragment taskDialogFragment = new TaskViewDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DRIVER_ID, driver_id);
        args.putString(ARG_TASK_ID, task_id);
        taskDialogFragment.setArguments(args);
        return taskDialogFragment;
    }

    public static TaskViewDialogFragment getCalling(String driver_id, String task_id) {
        return TaskViewDialogFragment.newInstance(driver_id, task_id);
    }


}