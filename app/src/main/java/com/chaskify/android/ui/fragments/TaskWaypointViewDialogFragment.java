package com.chaskify.android.ui.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.ui.model.TaskWaypointModel;
import com.chaskify.data.realm.cache.impl.TaskWayPointCacheImpl;
import com.chaskify.data.repositories.TaskWaypointRepositoryImpl;
import com.chaskify.domain.filter.DriverFilter;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.filter.WaypointIdFilter;
import com.chaskify.domain.interactors.TaskWaypointInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alberto on 28/12/17.
 */

public class TaskWaypointViewDialogFragment extends BottomSheetDialogFragment implements TaskWaypointViewDialogContract.View {
    public static final String ARG_TASK_WAY_POINT_ID = "task_id";
    public static final String ARG_DRIVER_ID = "driver_id";

    private String mTaskWaypointId;
    private String mDriverId;

    private TextView textViewTaskWaypointId;
    private TextView textViewWaypointType;
    private TextView textViewWaypointDate;
    private TextView textViewWaypointAddress;
    private TextView textViewWaypointTime;
    private TextView textViewWayPointDescription;


    private TextView textViewWayPointClientName;
    private TextView textViewWayPointClientNumber;
    private TextView textViewWayPointClientEmail;


    private View textViewWaypointStatusColor;

    private View formWaypointDescription;

    private TaskWaypointViewDialogPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTaskWaypointId = getArguments().getString(ARG_TASK_WAY_POINT_ID);
            mDriverId = getArguments().getString(ARG_DRIVER_ID);

            Chaskify.getInstance().getSessionByDriverId(mDriverId)
                    .ifPresent(chaskifySession -> presenter = new TaskWaypointViewDialogPresenter(
                            chaskifySession
                            , new TaskWaypointInteractor(
                            new TaskWaypointRepositoryImpl(
                                    new TaskWayPointCacheImpl()
                            )
                    )
                    ));
        } else
            dismiss();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_way_point, null);
        dialog.setContentView(contentView);
        initComponents(contentView);
        if (presenter != null) {
            presenter.bindView(this);

            List<Filter> filters = new ArrayList<>();
            filters.add(new WaypointIdFilter()
                    .setWayPointId(mTaskWaypointId)
            );
            filters.add(new DriverFilter()
                    .setDriver(mDriverId));

            presenter.wayPointById(filters);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.release();
    }

    private void initComponents(View view) {
        textViewTaskWaypointId = view.findViewById(R.id.task_way_point_id);
        textViewWaypointType = view.findViewById(R.id.way_point_type);
        textViewWaypointDate = view.findViewById(R.id.way_point_date);
        textViewWaypointTime = view.findViewById(R.id.way_point_time);
        textViewWaypointAddress = view.findViewById(R.id.way_point_address);
        textViewWaypointStatusColor = view.findViewById(R.id.task_way_point_status_color);
        textViewWayPointDescription = view.findViewById(R.id.task_waypoint_description);
        formWaypointDescription = view.findViewById(R.id.form_task_waypoint_description);

        textViewWayPointClientName = view.findViewById(R.id.way_point_client_name);
        textViewWayPointClientNumber = view.findViewById(R.id.way_point_client_number);
        textViewWayPointClientEmail = view.findViewById(R.id.way_point_client_mail);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static TaskWaypointViewDialogFragment newInstance(String driver_id, String task_id) {
        TaskWaypointViewDialogFragment taskDialogFragment = new TaskWaypointViewDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DRIVER_ID, driver_id);
        args.putString(ARG_TASK_WAY_POINT_ID, task_id);
        taskDialogFragment.setArguments(args);
        return taskDialogFragment;
    }

    public static TaskWaypointViewDialogFragment getCalling(String driver_id, String task_id) {
        return TaskWaypointViewDialogFragment.newInstance(driver_id, task_id);
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
        textViewTaskWaypointId.setText(getResources().getText(R.string.title_way_point) + " #" + waypointModel.getId());

        textViewWaypointAddress.setText(waypointModel.getDeliveryAddress());
        textViewWaypointType.setText(waypointModel.getType());

        textViewWayPointClientName.setText(waypointModel.getCustomerName());
        textViewWayPointClientNumber.setText(waypointModel.getContactNumber());
        textViewWayPointClientEmail.setText(waypointModel.getEmailAddress());

        if (waypointModel.getWaypointDescription() == null || waypointModel.getWaypointDescription().isEmpty())
            formWaypointDescription.setVisibility(View.GONE);
        else {
            formWaypointDescription.setVisibility(View.VISIBLE);
            textViewWayPointDescription.setText(waypointModel.getWaypointDescription());
        }


        textViewWaypointDate.setText(DateUtils.formatDateTime(getContext(), waypointModel.getDateCreated().getTime(), DateUtils.FORMAT_ABBREV_MONTH));
        textViewWaypointTime.setText(DateUtils.formatDateTime(getContext(), waypointModel.getDateCreated().getTime(), DateUtils.FORMAT_SHOW_TIME));


        switch (waypointModel.getStatus()) {
            case "ASSIGNED":
                textViewTaskWaypointId.setTextColor(getResources().getColor(R.color.task_assigned));
                textViewWaypointStatusColor.setBackgroundResource(R.color.task_assigned);
                break;
            case "SUCCESSFUL":
                textViewTaskWaypointId.setTextColor(getResources().getColor(R.color.task_successful));
                textViewWaypointStatusColor.setBackgroundResource(R.color.task_successful);
                break;
            case "COMPLETE":
                textViewTaskWaypointId.setTextColor(getResources().getColor(R.color.task_successful));
                textViewWaypointStatusColor.setBackgroundResource(R.color.task_successful);
                break;
            case "IN ROUTE":
                textViewTaskWaypointId.setTextColor(getResources().getColor(R.color.task_in_route));
                textViewWaypointStatusColor.setBackgroundResource(R.color.task_in_route);
                break;
            case "ACCEPTED":
                textViewTaskWaypointId.setTextColor(getResources().getColor(R.color.task_accepted));
                textViewWaypointStatusColor.setBackgroundResource(R.color.task_accepted);
                break;
            case "SIGNATURE":
                textViewTaskWaypointId.setTextColor(getResources().getColor(R.color.task_signature));
                textViewWaypointStatusColor.setBackgroundResource(R.color.task_signature);
                break;
            case "ARRIVED":
                textViewTaskWaypointId.setTextColor(getResources().getColor(R.color.task_arrived));
                textViewWaypointStatusColor.setBackgroundResource(R.color.task_arrived);
                break;

            case "PENDING":
                textViewTaskWaypointId.setTextColor(getResources().getColor(R.color.task_pending));
                textViewWaypointStatusColor.setBackgroundResource(R.color.task_pending);
                break;
        }
    }
}
