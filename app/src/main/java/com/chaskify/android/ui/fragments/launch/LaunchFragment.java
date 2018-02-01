package com.chaskify.android.ui.fragments.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chaskify.android.R;
import com.chaskify.android.adapters.ProfileListAdapter;
import com.chaskify.android.adapters.viewholder.ProfileItemViewHolder;
import com.chaskify.android.ui.base.BaseFragment;
import com.chaskify.android.ui.model.ProfileItemModel;

import java.util.List;

import timber.log.Timber;

public class LaunchFragment extends BaseFragment implements LaunchContract.View, View.OnClickListener {

    private LaunchPresenter launchPresenter;


    private RecyclerView mListProfile;

    private ProfileListAdapter mProfileListAdapter;

    private View signInButton;

    private View formHasCredentials;

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
        signInButton = view.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);
        formHasCredentials = view.findViewById(R.id.form_has_credentials);
        mListProfile = view.findViewById(R.id.list_profile_cache);
        mListProfile.setLayoutManager(new LinearLayoutManager(getContext()));
        mProfileListAdapter = new ProfileListAdapter();

        mProfileListAdapter.setOnListened(new ProfileItemViewHolder.OnProfileItemListened() {
            @Override
            public void onClickItem(View view, int position) {
                launchLogin(mProfileListAdapter.getItem(position));
            }

            @Override
            public void onRemove(ProfileItemModel profileItemModel) {
                launchPresenter.remove(profileItemModel.getDriverId());
            }
        });

        mListProfile.setAdapter(mProfileListAdapter);
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
        formHasCredentials.setVisibility(View.VISIBLE);
        Timber.d("::Render credentials " + cacheItemModels + "::");
        mProfileListAdapter.update(cacheItemModels);
    }


    @Override
    public void launchSplash() {
        startWithPop(SplashFragment.newInstance());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                launchLoginAndBack();
                break;
        }
    }

    private void launchLoginAndBack() {
        start(LoginFragment.newInstance());
    }
}
