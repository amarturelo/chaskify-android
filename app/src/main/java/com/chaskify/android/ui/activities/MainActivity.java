package com.chaskify.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.chaskify.android.R;
import com.chaskify.android.ui.activities.settings.SettingsProfileActivity;
import com.chaskify.android.ui.base.BaseActivity;
import com.chaskify.android.ui.fragments.TaskListFragment;
import com.chaskify.android.ui.fragments.TaskMapFragment;
import com.chaskify.android.ui.widget.DutyActionBar;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import timber.log.Timber;


public class MainActivity extends BaseActivity implements DutyActionBar.OnFragmentInteractionListenerDutyActionBar {

    private static final String ARG_TASK_VIEW_MODE = "TASK_VIEW_MODE";
    private static final String ARG_CURRENT_DATE = "CURRENT_DATE";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", /*Locale.getDefault()*/Locale.ENGLISH);
    private AppBarLayout appBarLayout;
    private CompactCalendarView compactCalendarView;

    private boolean isExpanded = false;

    private Toolbar toolbar;
    private SupportFragment[] mFragments = new SupportFragment[4];

    public static final int LIST = 0;
    public static final int MAP = 1;

    private DutyActionBar dutyActionBar;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        initToolBar();

        initSpinner();

        initCalendar();

        initActivity(savedInstanceState);

    }

    private void initCalendar() {

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_TASK_VIEW_MODE, dutyActionBar.getTaskView() == DutyActionBar.TASK_VIEW_MODE.LIST ? LIST : MAP);
    }

    private void initView() {
        appBarLayout = findViewById(R.id.app_bar_layout);

        dutyActionBar = findViewById(R.id.duty_action_bar);

        dutyActionBar.setOnFragmentInteractionListenerDutyActionBar(this);


        // Set up the CompactCalendarView
        compactCalendarView = findViewById(R.id.compactcalendar_view);

        // Force English
        compactCalendarView.setLocale(TimeZone.getDefault(), /*Locale.getDefault()*/Locale.ENGLISH);

        compactCalendarView.setShouldDrawDaysHeader(true);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                setTitle(dateFormat.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setTitle(dateFormat.format(firstDayOfNewMonth));
            }
        });

        // Set current date to today
        setCurrentDate(new Date());

        RelativeLayout datePickerButton = findViewById(R.id.date_picker_button);

        datePickerButton.setOnClickListener(v -> {
            isExpanded = !isExpanded;
            appBarLayout.setExpanded(isExpanded, true);
        });
    }

    private void setCurrentDate(Date date) {
        setTitle(dateFormat.format(date));
        if (compactCalendarView != null) {
            compactCalendarView.setCurrentDate(date);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        TextView tvTitle = findViewById(R.id.date_picker_text_view);

        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    private void initActivity(Bundle savedInstanceState) {
        SupportFragment firstFragment = findFragment(TaskListFragment.class);
        if (firstFragment == null) {
            mFragments[LIST] = TaskListFragment.newInstance();
            mFragments[MAP] = TaskMapFragment.newInstance();

            loadMultipleRootFragment(R.id.container, savedInstanceState != null ? savedInstanceState.getInt(ARG_TASK_VIEW_MODE, LIST) : LIST,
                    mFragments[LIST],
                    mFragments[MAP]
            );
        } else {
            mFragments[LIST] = firstFragment;
            mFragments[MAP] = findFragment(TaskMapFragment.class);
        }
    }

    private void renderTaskMap() {
        showHideFragment(mFragments[MAP], mFragments[LIST]);
    }

    private void renderTaskList() {
        showHideFragment(mFragments[LIST], mFragments[MAP]);
    }

    private void initSpinner() {
        // Setup spinner
        /*Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new MyAdapter(
                toolbar.getContext()
                , new String[]{
                "NOV, 05",
        }));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Intent intent = new Intent(this, SettingsProfileActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_notification) {
            Intent intent = new Intent(this, NotificationsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public void onDuty(DutyActionBar.DUTY_STATE dutyState) {

    }

    @Override
    public void onTaskView(DutyActionBar.TASK_VIEW_MODE taskView) {
        if (taskView == DutyActionBar.TASK_VIEW_MODE.LIST)
            renderTaskList();
        else
            renderTaskMap();
    }

    @Override
    public void onFilter() {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator();
    }

}
