package com.chaskify.android.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.polyak.iconswitch.IconSwitch;

import timber.log.Timber;

/**
 * Created by alberto on 14/12/17.
 */

public class DutyActionBar extends LinearLayout implements DutyContract.View {

    private Switch mActionDuty;
    private View mTaskViewMode;
    private View mTaskFilter;
    private ImageView mIvTaskViewMode;
    private IconSwitch mIconSwitchTask;

    private DutyPresenter presenter;

    @Override
    public void renderDutyStatus(boolean isDuty) {
        mActionDuty.setChecked(isDuty);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    public interface OnListenedTaskListChange {
        void onSwitchList(String state);
    }

    private OnListenedTaskListChange mListenedTaskListChange;

    public DutyActionBar setOnListenedTaskListChange(OnListenedTaskListChange mListenedTaskListChange) {
        this.mListenedTaskListChange = mListenedTaskListChange;
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

        presenter = new DutyPresenter(Chaskify.getInstance().getDefaultSession().get());

        mTaskFilter = findViewById(R.id.action_task_filter);
        mActionDuty = findViewById(R.id.switch_action_duty);
        mTaskViewMode = findViewById(R.id.action_task_view_mode);
        mIconSwitchTask = findViewById(R.id.icon_switch_task);
        mIconSwitchTask.setCheckedChangeListener(current -> {
            Timber.d(current.toString());
            if (mListenedTaskListChange != null)
                mListenedTaskListChange.onSwitchList(current.name());
        });

        mTaskViewMode.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mTaskFilter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilter();
            }
        });
        mIvTaskViewMode = findViewById(R.id.iv_task_view_mode);

        mActionDuty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    presenter.onDuty();
                else
                    presenter.offDuty();
            }
        });

        presenter.bindView(this);
    }

    private void showFilter() {
        /*BottomSheetMenuDialog dialog = new BottomSheetBuilder(getContext())
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setMenu(R.menu.menu_bottom_sheet_fitler)
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(MenuItem item) {

                    }
                })
                .createDialog();
        dialog.show();*/

    }

/*    private void taskViewToggle() {
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

        return this;
    }*/

}
