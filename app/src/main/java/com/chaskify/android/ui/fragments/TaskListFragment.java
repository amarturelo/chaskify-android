package com.chaskify.android.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.adapters.TaskListAdapter;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.widget.MultiStateView;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.TaskRepositoryImpl;
import com.chaskify.domain.interactors.TaskInteractor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TaskListFragment extends BaseFragment implements TaskListContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String ARG_CURRENT_DATE = "CURRENT_DATE";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());


    private TaskListPresenter taskListPresenter;

    private RecyclerView taskList;

    private TaskListAdapter taskListAdapter;

    private SwipeRefreshLayout mSwipeRefresh;

    private MultiStateView mMultiStateView;
    private Date mCurrentDate;

    public TaskListFragment() {
        // Required empty public constructor
    }

    public static TaskListFragment newInstance(Date currentDate) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_CURRENT_DATE, currentDate.getTime());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentDate = new Date(getArguments().getLong(ARG_CURRENT_DATE, new Date().getTime()));

        taskListPresenter = new TaskListPresenter(new TaskInteractor(
                new TaskRepositoryImpl(
                        Chaskify.getInstance().getDefaultSession().get().getTaskRestClient()
                        , new TaskCacheImpl()
                )
        ));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        taskListPresenter.bindView(this);
    }


    private void initViews(View view) {
        taskListAdapter = new TaskListAdapter();
        taskListAdapter.setOnItemListened((view1, position) -> Navigator.showTaskDetails(getFragmentManager()
                , Chaskify.getInstance().getDefaultSession().get().getCredentials().getDriverId()
                , taskListAdapter.getItem(position).getTask_id()));

        taskList = view.findViewById(R.id.task_list);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(this);
        mMultiStateView = view.findViewById(R.id.multi_state);

        taskList.setLayoutManager(new LinearLayoutManager(getContext()));
        taskList.setAdapter(taskListAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_task_list;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        taskListPresenter.release();
    }

    @Override
    public void showProgress() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showEmptyView() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showContentView() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void renderTaskListView(List<TaskItemModel> tasks) {
        taskListAdapter.clear();
        taskListAdapter.add(tasks);
    }

    @Override
    public void onRefresh() {
        taskListPresenter.tasks(mCurrentDate);
    }

    public void putArguments(Date date) {
        if (!mCurrentDate.equals(date)) {
            getArguments().putLong(ARG_CURRENT_DATE, date.getTime());
            mCurrentDate = date;
            onRefresh();
        }
    }
}
