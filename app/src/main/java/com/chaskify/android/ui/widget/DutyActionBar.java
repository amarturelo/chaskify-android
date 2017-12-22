package com.chaskify.android.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.chaskify.android.R;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import com.github.rubensousa.bottomsheetbuilder.adapter.BottomSheetItemClickListener;

/**
 * Created by alberto on 14/12/17.
 */

public class DutyActionBar extends LinearLayout {

    private Switch mActionDuty;
    private View mTaskViewMode;
    private View mTaskFilter;
    private ImageView mIvTaskViewMode;

    public interface OnFragmentInteractionListenerDutyActionBar {
        void onDuty(DUTY_STATE dutyState);

        void onTaskView(TASK_VIEW_MODE taskView);

        void onFilter();
    }

    private OnFragmentInteractionListenerDutyActionBar mOnFragmentInteractionListenerDutyActionBar;

    public DutyActionBar setOnFragmentInteractionListenerDutyActionBar(OnFragmentInteractionListenerDutyActionBar onFragmentInteractionListenerDutyActionBar) {
        this.mOnFragmentInteractionListenerDutyActionBar = onFragmentInteractionListenerDutyActionBar;
        return this;
    }

    public DutyActionBar(Context context) {
        super(context);
        init(context, null, 0);
    }

    public DutyActionBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public DutyActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.widget_action_bar, this);
        mTaskFilter = findViewById(R.id.action_task_filter);
        mActionDuty = findViewById(R.id.switch_action_duty);
        mTaskViewMode = findViewById(R.id.action_task_view_mode);
        mTaskViewMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                taskViewToggle();
            }
        });
        mTaskFilter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilter();
            }
        });
        mIvTaskViewMode = findViewById(R.id.iv_task_view_mode);
    }

    private void showFilter() {
        BottomSheetMenuDialog dialog = new BottomSheetBuilder(getContext())
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setMenu(R.menu.menu_bottom_sheet_fitler)
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(MenuItem item) {

                    }
                })
                .createDialog();
        dialog.show();

    }

    private void taskViewToggle() {
        if (taskView == TASK_VIEW_MODE.LIST)
            setTaskView(TASK_VIEW_MODE.MAP);
        else
            setTaskView(TASK_VIEW_MODE.LIST);
    }


    public DUTY_STATE getDutyState() {
        return dutyState;
    }

    public DutyActionBar setDutyState(DUTY_STATE dutyState) {
        this.dutyState = dutyState;


        if (mOnFragmentInteractionListenerDutyActionBar != null)
            mOnFragmentInteractionListenerDutyActionBar.onDuty(this.dutyState);
        return this;
    }

    public TASK_VIEW_MODE getTaskView() {
        return taskView;
    }

    public DutyActionBar setTaskView(TASK_VIEW_MODE taskView) {
        this.taskView = taskView;

        if (taskView == TASK_VIEW_MODE.LIST)
            mIvTaskViewMode.setImageResource(R.drawable.ic_map_black_24dp);
        else
            mIvTaskViewMode.setImageResource(R.drawable.ic_view_list_black_24dp);

        if (mOnFragmentInteractionListenerDutyActionBar != null)
            mOnFragmentInteractionListenerDutyActionBar.onTaskView(this.taskView);
        return this;
    }

    private DUTY_STATE dutyState = DUTY_STATE.ON_DUTY;

    private TASK_VIEW_MODE taskView = TASK_VIEW_MODE.LIST;

    public enum DUTY_STATE {
        ON_DUTY, OF_DUTY
    }

    public enum TASK_VIEW_MODE {
        MAP, LIST
    }


}
