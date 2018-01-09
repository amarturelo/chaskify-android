package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chaskify.android.R;
import com.chaskify.android.adapters.ProfileListAdapter;
import com.chaskify.android.adapters.listened.OnItemListened;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.model.ProfileItemModel;
import com.chaskify.android.ui.widget.MultiStateView;

import java.util.List;

import timber.log.Timber;

public class LaunchFragment extends BaseFragment implements LaunchContract.View {

    private LaunchPresenter launchPresenter;

    private MultiStateView multiStateView;

    private RecyclerView mListProfile;

    private ProfileListAdapter profileListAdapter;

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
        launchPresenter = new LaunchPresenter(
        );
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        multiStateView = view.findViewById(R.id.multi_state_profile_cache);
        mListProfile = multiStateView.getView(MultiStateView.VIEW_STATE_CONTENT).findViewById(R.id.list_profile_cache);
        mListProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        profileListAdapter = new ProfileListAdapter();

        profileListAdapter.setOnListened((view1, position) -> launchLogin(profileListAdapter.getItem(position)));

        mListProfile.setAdapter(profileListAdapter);
        launchPresenter.bindView(this);
    }

    private void launchLogin(ProfileItemModel item) {
        start(LoginFragment.newInstance(item));
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        launchPresenter.release();
    }

    @Override
    public void launchLogin() {
        Timber.d("::Launch login::");
        startWithPop(LoginFragment.newInstance());
    }

    @Override
    public void renderCredentials(List<ProfileItemModel> cacheItemModels) {
        Timber.d("::Render credentials " + cacheItemModels + "::");
        profileListAdapter.render(cacheItemModels);
    }


    @Override
    public void launchSplash() {
        startWithPop(SplashFragment.newInstance());
        getActivity().finish();
    }

    @Override
    public void showProgress() {
        multiStateView.setVisibility(View.VISIBLE);
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void hideProgress() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }


}
