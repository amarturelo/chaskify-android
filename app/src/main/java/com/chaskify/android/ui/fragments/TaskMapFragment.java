package com.chaskify.android.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.adapters.TaskListAdapter;
import com.chaskify.android.adapters.TaskSnapListAdapter;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.model.TaskItemModel;
import com.chaskify.android.ui.model.TaskItemSnapModel;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.TaskRepositoryImpl;
import com.chaskify.domain.interactors.TaskInteractor;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskMapFragment extends BaseFragment implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>, DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>, OnItemListened, TaskMapContract.View {
    public static final String ARG_CURRENT_DATE = "CURRENT_DATE";

    private DiscreteScrollView taskPicker;

    private TaskSnapListAdapter mTaskSnapListAdapter;

    private OnFragmentInteractionListener mListener;
    private Date mCurrentDate;

    private TaskMapPresenter presenter;
    private ChaskifySession mChaskifySession;


    public TaskMapFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TaskMapFragment newInstance(Date currentDate) {
        TaskMapFragment fragment = new TaskMapFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_CURRENT_DATE, currentDate.getTime());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChaskifySession = Chaskify.getInstance().getDefaultSession().get();

        mCurrentDate = new Date(getArguments().getLong(ARG_CURRENT_DATE, new Date().getTime()));

        presenter = new TaskMapPresenter(new TaskInteractor(
                new TaskRepositoryImpl(
                        new TaskCacheImpl()
                        , mChaskifySession.getTaskRestClient()
                )
        ));
    }

    private void initDiscreteScrollView() {

        /*List<TaskItemSnapModel> taskItemSnapModels = new ArrayList<>();
        taskItemSnapModels.add(new TaskItemSnapModel()
                .setTask_id("1145")
                .setDelivery_address("Edificio 28b apto 7, Pueblo Griffo")
                .setDelivery_date(new Date())
                .setTrans_type("service")
                .setStatus("ARRIVED"));

        taskItemSnapModels.add(new TaskItemSnapModel()
                .setTask_id("1148")
                .setDelivery_date(new Date())
                .setTrans_type("delivery")
                .setDelivery_address("201 Worth St, New York, United Stated")
                .setStatus("SIGNATURE"));

        taskItemSnapModels.add(new TaskItemSnapModel()
                .setTask_id("1165")
                .setDelivery_date(new Date())
                .setTrans_type("picker")
                .setDelivery_address("calle 10 e/ aldabo y carretera, Havana, Cuba")
                .setStatus("ACCEPTED"));

        taskItemSnapModels.add(new TaskItemSnapModel()
                .setTask_id("1178")
                .setDelivery_date(new Date())
                .setTrans_type("service")
                .setDelivery_address("Edificio 28b apto 7, Pueblo Griffo")
                .setStatus("ASSIGNED"));

        taskItemSnapModels.add(new TaskItemSnapModel()
                .setTask_id("1189")
                .setDelivery_date(new Date())
                .setTrans_type("service")
                .setDelivery_address("201 Worth St, New York, United Stated")
                .setStatus("SUCCESSFUL"));

        taskItemSnapModels.add(new TaskItemSnapModel()
                .setTask_id("1178")
                .setDelivery_date(new Date())
                .setTrans_type("service")
                .setDelivery_address("calle 10 e/ aldabo y carretera, Havana, Cuba")
                .setStatus("IN ROUTE"));*/

        mTaskSnapListAdapter = new TaskSnapListAdapter();
        //mTaskSnapListAdapter.add(taskItemSnapModels);
        mTaskSnapListAdapter.setOnItemListened(this);

        taskPicker.setSlideOnFling(true);
        taskPicker.setAdapter(mTaskSnapListAdapter);
        taskPicker.addOnItemChangedListener(this);
        taskPicker.addScrollStateChangeListener(this);
        taskPicker.setItemTransitionTimeMillis(100);
        taskPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //mMapView.onPause();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        presenter.bindView(this);
    }

    private void initViews(View view) {
        taskPicker = view.findViewById(R.id.picker);
        initDiscreteScrollView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentLaunchInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /*@Override
    public void onMapReady(MapboxMap mapboxMap) {
        mMapboxMap = mapboxMap;

        // Add a marker in Sydney and move the camera
        *//*LatLng sydney = new LatLng(-34, 151);
        mMapboxMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMapboxMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*//*
    }*/

    @Override
    protected int getLayout() {
        return R.layout.fragment_task_map;
    }

    public void putArguments(Date date) {
        if (!mCurrentDate.equals(date)) {
            getArguments().putLong(ARG_CURRENT_DATE, date.getTime());
            mCurrentDate = date;

            //presenter.tasks(mChaskifySession.getCredentials().getDriverId(), mCurrentDate);
        }
    }


    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }

    @Override
    public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {

    }

    @Override
    public void onClickItem(View view, int position) {
        if (taskPicker.getCurrentItem() == position)
            taskView(mTaskSnapListAdapter.getItem(position));
        else
            taskPicker.smoothScrollToPosition(position);
    }

    private void taskView(TaskItemSnapModel item) {
        Navigator.showTaskDetails(getFragmentManager()
                , Chaskify.getInstance().getDefaultSession().get().getCredentials().getDriverId()
                , item.getTask_id());
    }

    @Override
    public void renderTaskListView(List<TaskItemSnapModel> taskItemModels) {
        mTaskSnapListAdapter.add(taskItemModels);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        //mMapView.onResume();
        super.onResume();
        //presenter.tasks(mChaskifySession.getCredentials().getDriverId(), mCurrentDate);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //mMapView.onLowMemory();
    }
}
