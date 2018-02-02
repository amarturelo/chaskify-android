package com.chaskify.android.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.annimon.stream.Stream;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.adapters.MapboxAdapter;
import com.chaskify.android.adapters.TaskSnapListAdapter;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.helper.ToastIfError;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.model.MarkerData;
import com.chaskify.android.ui.model.TaskItemSnapModel;
import com.chaskify.data.realm.cache.impl.TaskCacheImpl;
import com.chaskify.data.repositories.TaskRepositoryImpl;
import com.chaskify.domain.filter.Filter;
import com.chaskify.domain.interactors.TaskInteractor;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

import nz.co.trademe.mapme.annotations.MapAnnotation;
import nz.co.trademe.mapme.annotations.OnMapAnnotationClickListener;
import timber.log.Timber;

import static com.chaskify.android.ui.activities.MainActivity.ARG_FILTER;

public class TaskMapFragment extends BaseFragment implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>, DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>, OnItemListened, TaskMapContract.View, OnMapReadyCallback, OnMapAnnotationClickListener {

    private DiscreteScrollView taskPicker;

    private TaskSnapListAdapter mTaskSnapListAdapter;

    private TaskMapPresenter presenter;

    private List<Filter> mFilter;

    private MapboxMap mapboxMap;

    private SupportMapFragment mapFragment;

    private MapboxAdapter mMapboxAdapter;

    public TaskMapFragment() {
        // Required empty public constructor
        Timber.tag(this.getClass().getSimpleName());
    }


    public static TaskMapFragment newInstance() {
        TaskMapFragment fragment = new TaskMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(this.getClass().getSimpleName());
        Chaskify.getInstance().getDefaultSession()
                .ifPresent(chaskifySession -> {
                    presenter = new TaskMapPresenter(
                            new TaskInteractor(
                                    new TaskRepositoryImpl(
                                            new TaskCacheImpl())));
                    presenter.bindView(this);
                });
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        super.putNewBundle(newBundle);
        mFilter = newBundle.getParcelableArrayList(ARG_FILTER);
        if (mapboxMap != null)
            presenter.tasks(mFilter);
    }

    private void initDiscreteScrollView() {
        mTaskSnapListAdapter = new TaskSnapListAdapter();
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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(savedInstanceState);
        presenter.bindView(this);
    }

    private void initViews(Bundle savedInstanceState) {
        taskPicker = getView().findViewById(R.id.picker);

        initMap(savedInstanceState);
        initDiscreteScrollView();
    }

    private void initMap(Bundle savedInstanceState) {


        // Create supportMapFragment

        if (savedInstanceState == null) {

            // Create fragment
            final FragmentTransaction transaction = getChildFragmentManager().beginTransaction();


            // Build mapboxMap
            MapboxMapOptions options = new MapboxMapOptions();
            options.styleUrl(Style.MAPBOX_STREETS);
            options.camera(new CameraPosition.Builder()
                    .zoom(9)
                    .build());

            // Create map fragment
            mapFragment = SupportMapFragment.newInstance();

            // Add map fragment to parent container
            transaction.add(R.id.container, mapFragment, "com.mapbox.map");
            transaction.commit();
        } else {
            mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentByTag("com.mapbox.map");
        }

        mapFragment.getMapAsync(this);

        mMapboxAdapter = new MapboxAdapter(getActivity());
        mMapboxAdapter.setOnAnnotationClickListener(this);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.release();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_task_map;
    }


    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        Timber.d("onCurrentItemChanged " + adapterPosition);
    }

    private void moveCameraTo(double latitude, double longitude) {
        if (mapboxMap != null)
            mapboxMap.animateCamera(mapboxMap -> new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude))
                    .build());
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
        else {
            goToSnapItem(position);
            moveCameraTo(mTaskSnapListAdapter.getItem(position).getLat(), mTaskSnapListAdapter.getItem(position).getLng());
        }
    }

    private void taskView(TaskItemSnapModel item) {
        Navigator.showTaskDetails(getFragmentManager()
                , Chaskify.getInstance().getDefaultSession().get().getCredentials().getDriverId()
                , item.getTaskId());
    }

    @Override
    public void renderTaskListView(List<TaskItemSnapModel> taskItemModels) {
        Timber.d("renderTaskListView " + taskItemModels.toString());

        mMapboxAdapter.update(Stream.of(taskItemModels)
                .filter(value -> !value.getStatus().equals("CANCELED"))
                .map(taskItemSnapModel -> new MarkerData(taskItemSnapModel.getTaskId()
                        , new nz.co.trademe.mapme.LatLng(taskItemSnapModel.getLat()
                        , taskItemSnapModel.getLng())
                        , taskItemSnapModel.getStatus()
                        , taskItemSnapModel.getTaskId()))
                .toList());

        mTaskSnapListAdapter.update(Stream.of(taskItemModels)
                .filter(value -> !value.getStatus().equals("CANCELED"))
                .toList());

        /*if (this.mapboxMap != null) {
            Timber.d("paso por aca");
            mMapboxAdapter.attach(mapFragment.getView(), this.mapboxMap);
        }
*/
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Throwable throwable) {
        Timber.d(throwable);
        ToastIfError.showError(getContext(), (Exception) throwable);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;

        mMapboxAdapter.attach(mapFragment.getView(), this.mapboxMap);

        presenter.tasks(mFilter);
    }

    @Override
    public boolean onMapAnnotationClick(@NonNull MapAnnotation mapAnnotationObject) {
        Timber.d(mapAnnotationObject.toString());
        MarkerData markerData = mMapboxAdapter.getItem(mapAnnotationObject.getPosition());
        moveCameraTo(markerData.getLatLng().getLatitude(), markerData.getLatLng().getLongitude());
        goToSnapItem(mTaskSnapListAdapter.getItemPositionById(mMapboxAdapter.getItem(mapAnnotationObject.getPosition()).getId()));
        return true;
    }

    private void goToSnapItem(int id) {
        taskPicker.smoothScrollToPosition(id);
    }
}
