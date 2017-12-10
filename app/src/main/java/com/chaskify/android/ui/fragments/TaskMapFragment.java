package com.chaskify.android.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.chaskify.android.R;
import com.chaskify.android.adapters.TaskSnapListAdapter;
import com.chaskify.android.model.TaskSnapModel;
import com.chaskify.android.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskMapFragment extends BaseFragment {

    //private MapView mMapView;
    //private MapboxMap mMapboxMap;

    private RecyclerView mTaskSnapList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TaskMapFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TaskMapFragment newInstance() {
        TaskMapFragment fragment = new TaskMapFragment();
        Bundle args = new Bundle();
        /*args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //mMapView = view.findViewById(R.id.map);
        mTaskSnapList = view.findViewById(R.id.task_snap_list);
        //mMapView.onCreate(savedInstanceState);
        //mMapView.getMapAsync(this);

        initTaskSnapList();
    }

    private void initTaskSnapList() {
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mTaskSnapList);

        mTaskSnapList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));


        List<TaskSnapModel> taskSnapModels = new ArrayList<>();
        taskSnapModels.add(new TaskSnapModel()
                .setAddress("Edificio 28b apto 7, Pueblo Griffo")
                .setClientName("Mike Hussey")
                .setStatus("started"));

        taskSnapModels.add(new TaskSnapModel()
                .setAddress("201 Worth St, New York, United Stated")
                .setClientName("Mike Hussey")
                .setStatus("started"));

        taskSnapModels.add(new TaskSnapModel()
                .setAddress("calle 10 e/ aldabo y carretera, Havana, Cuba")
                .setClientName("Contantinopla de la Luz")
                .setStatus("started"));

        TaskSnapListAdapter taskSnapListAdapter = new TaskSnapListAdapter(taskSnapModels);
        mTaskSnapList.setAdapter(taskSnapListAdapter);

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
