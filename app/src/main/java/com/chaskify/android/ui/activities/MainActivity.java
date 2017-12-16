package com.chaskify.android.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Consumer;
import com.annimon.stream.function.Function;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.Util;
import com.chaskify.android.ui.activities.settings.SettingsProfileActivity;
import com.chaskify.android.ui.base.BaseActivity;
import com.chaskify.android.ui.fragments.TaskListFragment;
import com.chaskify.android.ui.fragments.TaskMapFragment;
import com.chaskify.android.ui.model.CalendarTaskModel;
import com.chaskify.android.ui.widget.AppBarStateChangeListener;
import com.chaskify.android.ui.widget.DutyActionBar;
import com.chaskify.chaskify_sdk.rest.client.CalendarTaskRestClient;
import com.chaskify.data.repositories.CalendarTaskRepositoryImpl;
import com.chaskify.domain.interactors.CalendarTaskInteractor;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import timber.log.Timber;


public class MainActivity extends BaseActivity implements DutyActionBar.OnFragmentInteractionListenerDutyActionBar, MainContract.View {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM,d", /*Locale.getDefault()*/Locale.getDefault());

    private static final String ARG_TASK_VIEW_MODE = "TASK_VIEW_MODE";
    private static final String ARG_CURRENT_DATE = "CURRENT_DATE";
    private AppBarLayout appBarLayout;
    private CompactCalendarView compactCalendarView;

    private boolean isExpanded = false;

    private Toolbar toolbar;
    private SupportFragment[] mFragments = new SupportFragment[4];

    public static final int LIST = 0;
    public static final int MAP = 1;

    private DutyActionBar dutyActionBar;

    private Date currentDate;

    private MainPresenter mainPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.tag(this.getClass().getSimpleName());

        mainPresenter = new MainPresenter(
                new CalendarTaskInteractor(
                        new CalendarTaskRepositoryImpl(
                                Chaskify.getInstance().getDefaultSession().get().getCalendarTaskRestClient()
                        )
                )
        );

        if (savedInstanceState != null)
            currentDate = new Date(savedInstanceState.getLong(ARG_CURRENT_DATE, new Date().getTime()));
        else
            currentDate = new Date();

        initView();

        mainPresenter.bindView(this);

        initToolBar();

        initActivity(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(ARG_CURRENT_DATE, currentDate.getTime());
        outState.putInt(ARG_TASK_VIEW_MODE, dutyActionBar.getTaskView() == DutyActionBar.TASK_VIEW_MODE.LIST ? LIST : MAP);
    }

    private void initView() {
        appBarLayout = findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    isExpanded = true;

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(compactCalendarView.getFirstDayOfCurrentMonth());
                    cal.add(Calendar.MONTH, 1);

                    mainPresenter.calendarTasks(compactCalendarView.getFirstDayOfCurrentMonth(), cal.getTime());
                } else if (state == State.COLLAPSED)
                    isExpanded = false;
            }
        });
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
                findTask(dateClicked);
                setTitle(dateFormat.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                findTask(firstDayOfNewMonth);
                setTitle(dateFormat.format(firstDayOfNewMonth));
            }
        });

        // Set current date to today
        setCurrentDate(currentDate);

        RelativeLayout datePickerButton = findViewById(R.id.date_picker_button);

        datePickerButton.setOnClickListener(v -> {
            isExpanded = !isExpanded;
            appBarLayout.setExpanded(isExpanded, true);
        });
    }

    private void findTask(Date date) {
        Stream.of(mFragments)
                .forEach(supportFragment -> {
                    if (supportFragment instanceof TaskMapFragment)
                        ((TaskMapFragment) supportFragment).putArguments(date);
                    else if (supportFragment instanceof TaskListFragment)
                        ((TaskListFragment) supportFragment).putArguments(date);

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
            mFragments[LIST] = TaskListFragment.newInstance(currentDate);
            mFragments[MAP] = TaskMapFragment.newInstance(currentDate);

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

    @Override
    public void renderEvent(List<CalendarTaskModel> calendarTaskModels) {
        List<Event> events = Stream.of(calendarTaskModels)
                .map(calendarTaskModel -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.valueOf(calendarTaskModel.getYear()), Integer.valueOf(calendarTaskModel.getMonth()) - 1, Integer.valueOf(calendarTaskModel.getDay()));
                    return new Event(Color.GREEN, calendar.getTimeInMillis(), calendarTaskModel);
                }).toList();
        compactCalendarView.removeAllEvents();
        compactCalendarView.addEvents(events);

        List<Event> test = compactCalendarView.getEventsForMonth(events.get(0).getTimeInMillis());
        Timber.d(test.toString());
    }
}
