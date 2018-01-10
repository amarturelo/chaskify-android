package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Optional;
import com.annimon.stream.function.Consumer;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.navigation.Navigator;
import com.chaskify.android.service.ChaskifyService;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.chaskify_sdk.ChaskifySession;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.domain.interactors.ProfileInteractor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SplashFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends BaseFragment {

    private SplashPresenter presenter;

    public SplashFragment() {
        // Required empty public constructor
    }

    private Optional<ChaskifySession> mChaskifySession;


    public static SplashFragment newInstance() {
        SplashFragment fragment = new SplashFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Chaskify.getInstance().getDefaultSession()
                .executeIfAbsent(this::goToLaunch)
                .ifPresent(chaskifySession -> {
                    this.mChaskifySession = Chaskify.getInstance().getDefaultSession();
                    presenter = new SplashPresenter(new ProfileInteractor(
                            new ProfileRepositoryImpl(
                                    new ProfileCacheImpl()
                                    , mChaskifySession.get().getProfileRestClient()
                            )
                    ));
                });
    }

    private void goToLaunch() {
        startWithPop(LaunchFragment.newInstance());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mChaskifySession.ifPresent(chaskifySession -> presenter.profile(chaskifySession.getCredentials().getDriverId()));

        //Navigator.goToMainActivity(getContext());
    }


}
