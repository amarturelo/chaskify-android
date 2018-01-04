package com.chaskify.android.ui.activities.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chaskify.android.Chaskify;
import com.chaskify.android.R;
import com.chaskify.android.ui.base.AbstractSwipeBackActivity;
import com.chaskify.android.ui.fragments.ChangePasswordDialogFragment;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.data.realm.cache.impl.ProfileCacheImpl;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.data.repositories.datasource.cloud.CloudProfileDataStore;
import com.chaskify.data.repositories.datasource.disk.DiskProfileDataStore;
import com.chaskify.domain.interactors.ProfileInteractor;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

public class ProfileActivity extends AbstractSwipeBackActivity implements ProfileContract.View {

    private ProfilePresenter presenter;


    private CircleImageView profileImage;
    private TextView profileUsername;
    private TextView profileTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initActivity(savedInstanceState);

        initComponents();
    }

    private void initComponents() {
        /*profileImage = findViewById(R.id.cat_avatar);
        profileUsername = findViewById(R.id.cat_title);
        profileTeam = findViewById(R.id.subtitle);*/
    }

    private void initActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            presenter = new ProfilePresenter(
                    new ProfileInteractor(
                            new ProfileRepositoryImpl(
                                    new DiskProfileDataStore(
                                            new ProfileCacheImpl()
                                    )
                                    , new CloudProfileDataStore(
                                    Chaskify
                                            .getInstance()
                                            .getDefaultSession()
                                            .get()
                                            .getProfileRestClient()
                                    , new ProfileCacheImpl()
                            ))
                    ));

            presenter.bindView(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_profile;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ProfileActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId())
            onBackPressed();
        if (R.id.action_change_password == item.getItemId())
            changePassword();
        return super.onOptionsItemSelected(item);
    }

    private void changePassword() {
        ChangePasswordDialogFragment changePasswordDialogFragment = ChangePasswordDialogFragment.newInstance("amarturelo");
        changePasswordDialogFragment.show(getSupportFragmentManager(), ChangePasswordDialogFragment.class.getSimpleName());
    }

    @Override
    public void showProgress() {
        Timber.d("loading profile");
    }

    @Override
    public void hideProgress() {
        Timber.d("loading profile complete");
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void renderProfile(ProfileModel taskModel) {
        Timber.d(taskModel.toString());
        Glide.with(this).load(taskModel.getDriverPicture())
                .into(profileImage);
        profileUsername.setText(taskModel.getUsername());
        profileTeam.setText(taskModel.getTeamName());
    }
}
