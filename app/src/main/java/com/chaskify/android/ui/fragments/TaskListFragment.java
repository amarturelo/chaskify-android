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
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.widget.MultiStateView;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.ChaskifyTaskRepositoryImpl;
import com.chaskify.domain.interactors.TaskInteractor;

import java.util.Date;
import java.util.List;


public class TaskListFragment extends BaseFragment implements TaskListContract.View, SwipeRefreshLayout.OnRefreshListener {

    private TaskListPresenter taskListPresenter;

    private RecyclerView taskList;

    private TaskListAdapter taskListAdapter;

    private SwipeRefreshLayout mSwipeRefresh;

    private MultiStateView mMultiStateView;

    public TaskListFragment() {
        // Required empty public constructor
    }

    public static TaskListFragment newInstance() {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskListPresenter = new TaskListPresenter(new TaskInteractor(
                new ChaskifyTaskRepositoryImpl(
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
        if (savedInstanceState == null) {
            taskListPresenter.tasks("2017-12-16");
        }
    }

    private void initViews(View view) {
        taskListAdapter = new TaskListAdapter();
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
        tasks.add(new TaskItemModel()
                .setCustomer_name("Joseito")
                .setDelivery_address("Calle 34 e/ 10 de octubre y azul")
                .setTask_id("456")
                .setStatus("OTRO")
                .setTrans_type("SERVICE")
                .setDelivery_date(new Date()));

        tasks.add(new TaskItemModel()
                .setCustomer_name("Arysmaida")
                .setDelivery_address("Avenida de los Presidentes")
                .setTask_id("458")
                .setStatus("IN PROGRESS")
                .setTrans_type("SERVICE")
                .setDelivery_date(new Date()));
        taskListAdapter.add(tasks);
    }

    @Override
    public void onRefresh() {
        taskListPresenter.tasks("2017-12-16");
    }
}
