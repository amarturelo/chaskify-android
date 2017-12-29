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
import com.chaskify.android.ui.fragments.settings.SettingsProfileFragment;
import com.chaskify.android.ui.model.ProfileModel;
import com.chaskify.data.repositories.ProfileRepositoryImpl;
import com.chaskify.domain.interactors.ProfileInteractor;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

public class SettingsProfileActivity extends AbstractSwipeBackActivity implements SettingsProfileContract.View {

    private SettingsProfilePresenter presenter;


    private CircleImageView profileImage;
    private TextView profileUsername;
    private TextView profileTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initActivity(savedInstanceState);

        initComponents();
    }

    private void initComponents() {
        profileImage = findViewById(R.id.cat_avatar);
        profileUsername = findViewById(R.id.cat_title);
        profileTeam = findViewById(R.id.subtitle);
    }

    private void initActivity(Bundle savedInstanceState) {
        if (findFragment(SettingsProfileFragment.class) == null) {
            loadRootFragment(R.id.fragment, SettingsProfileFragment.newInstance());

            presenter = new SettingsProfilePresenter(
                    new ProfileInteractor(
                            new ProfileRepositoryImpl(Chaskify
                                    .getInstance()
                                    .getDefaultSession()
                                    .get()
                                    .getProfileRestClient())
                    ));

            presenter.bindView(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings_profile, menu);
        return true;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_settings_profile;
    }

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SettingsProfileActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId())
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
