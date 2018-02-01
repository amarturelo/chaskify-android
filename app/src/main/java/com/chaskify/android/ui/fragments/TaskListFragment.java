package com.chaskify.android.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.adapters.TaskListAdapter;
import com.chaskify.android.helper.ToastIfError;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.widget.MultiStateView;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.domain.filter.Filter;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.TaskRepositoryImpl;
import com.chaskify.domain.interactors.TaskInteractor;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static com.chaskify.android.ui.activities.MainActivity.ARG_FILTER;


public class TaskListFragment extends BaseFragment implements TaskListContract.View, SwipeRefreshLayout.OnRefreshListener {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());

    private TaskListPresenter presenter;

    private RecyclerView taskList;

    private TaskListAdapter taskListAdapter;

    private SwipeRefreshLayout mSwipeRefresh;

    private MultiStateView mMultiStateView;

    private ChaskifySession mChaskifySession;

    private List<Filter> mFilter;

    public TaskListFragment() {
        // Required empty public constructor
    }

    public interface OnListenedTaskListFragment {
        void onRefresh();
    }

    private OnListenedTaskListFragment mOnListenedTaskListFragment;

    public static TaskListFragment newInstance() {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Chaskify.getInstance().getDefaultSession()
                .ifPresent(chaskifySession -> {
                    this.mChaskifySession = chaskifySession;
                    presenter = new TaskListPresenter(new TaskInteractor(
                            new TaskRepositoryImpl(
                                    new TaskCacheImpl()
                            )
                    )
                    );
                    presenter.bindView(this);
                });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

    }


    private void initViews(View view) {
        taskListAdapter = new TaskListAdapter();
        taskListAdapter.setOnItemListened((view1, position) -> Navigator.showTaskDetails(getFragmentManager()
                , mChaskifySession.getCredentials().getDriverId()
                , taskListAdapter.getItem(position).getTaskId()));

        taskList = view.findViewById(R.id.task_list);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(this);
        mMultiStateView = view.findViewById(R.id.multi_state);

        taskList.setLayoutManager(new LinearLayoutManager(getContext()));
        taskList.setAdapter(taskListAdapter);
        //open details
        taskListAdapter.setOnItemListened((view1, position) -> Chaskify.getInstance().getDefaultSession().ifPresent(chaskifySession -> Navigator.showTaskDetails(getFragmentManager()
                , chaskifySession.getCredentials().getDriverId()
                , taskListAdapter.getItem(position).getTaskId())));
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_task_list;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListenedTaskListFragment)
            mOnListenedTaskListFragment = (OnListenedTaskListFragment) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.release();
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
        mSwipeRefresh.setRefreshing(false);
        ToastIfError.showError(getContext(), (Exception) throwable);
    }

    @Override
    public void showContentView() {
        mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void renderTaskListView(List<TaskItemModel> tasks) {
        hideProgress();
        if (tasks.isEmpty())
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        else
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);

        taskListAdapter.update(Stream.of(tasks)
                .filter(value -> !value.getStatus().equals("CANCELED"))
                .toList());
    }

    @Override
    public void onRefresh() {
        //TODO este metodo tiene q decirle a la actividad padre q actualice ya que es ella la que tiene la responsabilidad
        if (mOnListenedTaskListFragment != null)
            mOnListenedTaskListFragment.onRefresh();
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        super.putNewBundle(newBundle);
        mFilter = newBundle.getParcelableArrayList(ARG_FILTER);
        presenter.tasks(mFilter);
    }

}
