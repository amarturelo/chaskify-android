package com.chaskify.android.ui.activities;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.adapters.TaskListAdapter;
import com.chaskify.android.helper.ToastIfError;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.base.BaseActivity;
import com.chaskify.android.ui.fragments.TaskListFragment;
import com.chaskify.android.ui.fragments.TaskMapFragment;
import com.chaskify.android.ui.model.TaskCalendarItemModel;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.widget.AppBarStateChangeListener;
import com.chaskify.android.ui.widget.DutyActionBar;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.CalendarTaskRepositoryImpl;
import com.chaskify.data.repositories.TaskRepositoryImpl;
import com.chaskify.domain.filter.DateFilter;
import com.chaskify.domain.filter.DriverFilter;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.interactors.CalendarTaskInteractor;
import com.chaskify.domain.interactors.TaskInteractor;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.polyak.iconswitch.IconSwitch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import me.yokeyword.fragmentation.anim.FragmentAnimator;
import timber.log.Timber;


public class MainActivity extends BaseActivity implements MainContract.View, DutyActionBar.OnListenedTaskListChange {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM,d", /*Locale.getDefault()*/Locale.getDefault());

    private static final String ARG_TASK_VIEW_MODE = "TASK_VIEW_MODE";
    private static final String ARG_CURRENT_DATE = "CURRENT_DATE";

    public static final String ARG_FILTER = "arg_filters";

    private AppBarLayout appBarLayout;
    private CompactCalendarView compactCalendarView;

    private boolean isExpanded = false;

    private Toolbar toolbar;

    private DutyActionBar dutyActionBar;

    private Date currentDate;

    private MainPresenter presenter;

    private TaskMapFragment mTaskMapFragment;
    private TaskListFragment mTaskListFragment;

    private Interpolator contentInInterpolator;
    private Interpolator contentOutInterpolator;

    private View content;
    private Window window;
    private Point revealCenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.tag(this.getClass().getSimpleName());

        Chaskify.getInstance().getDefaultSession()
                .executeIfAbsent(this::goToLaunch)
                .ifPresent(chaskifySession -> presenter = new MainPresenter(
                        chaskifySession
                        , new CalendarTaskInteractor(
                        new CalendarTaskRepositoryImpl(
                                chaskifySession.getCalendarTaskRestClient()
                        ))
                        , new TaskInteractor(
                        new TaskRepositoryImpl(
                                new TaskCacheImpl()
                        )
                )
                ));

        if (savedInstanceState != null)
            currentDate = new Date(savedInstanceState.getLong(ARG_CURRENT_DATE, new Date().getTime()));
        else
            currentDate = new Date();

        initView();

        presenter.bindView(this);

        initToolBar();


        filterTask(currentDate);
    }


    private void goToLaunch() {
        Navigator
                .goToLaunchActivity(this);
    }

    private void initView() {
        appBarLayout = findViewById(R.id.app_bar_layout);
        content = findViewById(R.id.fragmentTaskList);

        mTaskListFragment = (TaskListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTaskList);
        mTaskMapFragment = (TaskMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTaskMap);

        window = getWindow();

        initAnimationRelatedFields();

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    isExpanded = true;

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(compactCalendarView.getFirstDayOfCurrentMonth());
                    cal.add(Calendar.MONTH, 1);

                    presenter.calendarTasks(compactCalendarView.getFirstDayOfCurrentMonth(), cal.getTime());
                } else if (state == State.COLLAPSED)
                    isExpanded = false;
            }
        });
        dutyActionBar = findViewById(R.id.duty_action_bar);

        dutyActionBar.setOnListenedTaskListChange(this);

        // Set up the CompactCalendarView
        compactCalendarView = findViewById(R.id.compactcalendar_view);

        // Force English
        compactCalendarView.setLocale(TimeZone.getDefault(), /*Locale.getDefault()*/Locale.ENGLISH);

        compactCalendarView.setShouldDrawDaysHeader(true);

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                filterTask(dateClicked);
                setTitle(dateFormat.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                filterTask(firstDayOfNewMonth);
                setTitle(dateFormat.format(firstDayOfNewMonth));
            }
        });

        setCurrentDate(currentDate);

        RelativeLayout datePickerButton = findViewById(R.id.date_picker_button);

        datePickerButton.setOnClickListener(v -> {
            isExpanded = !isExpanded;
            appBarLayout.setExpanded(isExpanded, true);
        });
    }

    private void initAnimationRelatedFields() {
        revealCenter = new Point();
        contentInInterpolator = new OvershootInterpolator(0.5f);
        contentOutInterpolator = new DecelerateInterpolator();
    }

    private void filterTask(Date date) {
        Chaskify.getInstance()
                .getDefaultSession()
                .ifPresent(chaskifySession -> {
                    ArrayList<Filter> filters = new ArrayList<>();
                    filters.add(new DriverFilter()
                            .setDriver(chaskifySession.getCredentials().getDriverId()));

                    filters.add(new DateFilter()
                            .setDate(date));

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(ARG_FILTER, filters);

                    presenter.tasks(filters);

                    mTaskListFragment.putNewBundle(bundle);
                    mTaskMapFragment.putNewBundle(bundle);
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
            Navigator.goToProfileSettings(this);
            return true;
        }

        if (id == R.id.action_notification) {
            Navigator.goToNotificationsActivity(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Intent getCallingIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new FragmentAnimator();
    }

    @Override
    public void showError(Throwable throwable) {
        ToastIfError.showError(this, (Exception) throwable);
    }

    @Override
    public void renderEvent(List<TaskCalendarItemModel> taskCalendarItemModels) {
        List<Event> events = Stream.of(taskCalendarItemModels)
                .map(taskCalendarItemModel -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Integer.valueOf(taskCalendarItemModel.getYear()), Integer.valueOf(taskCalendarItemModel.getMonth()) - 1, Integer.valueOf(taskCalendarItemModel.getDay()));
                    return new Event(Color.GREEN, calendar.getTimeInMillis(), taskCalendarItemModel);
                }).toList();
        compactCalendarView.removeAllEvents();
        compactCalendarView.addEvents(events);
    }

    private static final int DURATION_COLOR_CHANGE_MS = 400;

    @Override
    public void onSwitchList(String state) {
        int targetTranslation = 0;
        Interpolator interpolator = null;
        switch (state) {
            case "LEFT":
                targetTranslation = 0;
                interpolator = contentInInterpolator;
                break;
            case "RIGHT":
                targetTranslation = content.getHeight();
                interpolator = contentOutInterpolator;
                break;
        }
        content.animate().cancel();
        content.animate()
                .translationY(targetTranslation)
                .setInterpolator(interpolator)
                .setDuration(DURATION_COLOR_CHANGE_MS)
                .start();
    }
}
