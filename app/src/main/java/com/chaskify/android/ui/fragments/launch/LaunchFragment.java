package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chaskify.android.R;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.data.repositories.RealmServerConfigurationRepository;
import com.chaskify.domain.interactors.HomeServerConfigurationInteractor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentLaunchInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LaunchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LaunchFragment extends BaseFragment implements LaunchContract.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private LaunchPresenter launchPresenter;

    private OnFragmentLaunchInteractionListener mListener;

    public LaunchFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LaunchFragment newInstance() {
        LaunchFragment fragment = new LaunchFragment();
        Bundle args = new Bundle();
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

        launchPresenter = new LaunchPresenter(
                new HomeServerConfigurationInteractor(
                        new RealmServerConfigurationRepository()
                )
        );
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        launchPresenter.bindView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_launch;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentLaunchInteractionListener) {
            mListener = (OnFragmentLaunchInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentLaunchInteractionListener");*/
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        launchPresenter.release();
    }

    @Override
    public void showLogin() {
        /*if (mListener != null)
            mListener.onLogin();*/
        start(LoginFragment.newInstance("",""));
    }

    @Override
    public void launch() {
        if (mListener != null)
            mListener.onLaunch();
    }

    @Override
    public void renderProfiles() {

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
    public interface OnFragmentLaunchInteractionListener {
        void onLogin();

        void onLaunch();
    }
}
