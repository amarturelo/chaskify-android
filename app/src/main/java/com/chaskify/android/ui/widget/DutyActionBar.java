package com.chaskify.android.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.service.ChaskifyService;
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

        mActionDuty.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                presenter.onDuty();
            else
                presenter.offDuty();
        });
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void onDuty() {
        ChaskifyService.start(getContext(), Chaskify.getInstance().getDefaultSession().get().getCredentials().getDriverId());
    }

    @Override
    public void offDuty() {
        ChaskifyService.stop(getContext());
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

        mTaskFilter = findViewById(R.id.action_task_filter);
        mActionDuty = findViewById(R.id.switch_action_duty);
        mTaskViewMode = findViewById(R.id.action_task_view_mode);
        mIconSwitchTask = findViewById(R.id.icon_switch_task);

        mIconSwitchTask.setCheckedChangeListener(current -> {
            Timber.d(current.toString());
            if (mListenedTaskListChange != null)
                mListenedTaskListChange.onSwitchList(current.name());
        });

        mTaskViewMode.setOnClickListener(v -> {

        });
        mTaskFilter.setOnClickListener(v -> showFilter());
        mIvTaskViewMode = findViewById(R.id.iv_task_view_mode);

        Chaskify.getInstance().getDefaultSession().ifPresent(chaskifySession -> {
            presenter = new DutyPresenter(chaskifySession);

            presenter.bindView(DutyActionBar.this);
        });

    }

    private void showFilter() {

    }
}
