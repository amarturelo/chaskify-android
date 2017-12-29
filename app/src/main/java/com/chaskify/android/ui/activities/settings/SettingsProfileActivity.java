package com.chaskify.android.ui.activities.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.chaskify.android.R;
import com.chaskify.android.ui.base.AbstractSwipeBackActivity;
import com.chaskify.android.ui.fragments.settings.SettingsProfileFragment;

public class SettingsProfileActivity extends AbstractSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initActivity(savedInstanceState);
    }

    private void initActivity(Bundle savedInstanceState) {
        if (findFragment(SettingsProfileFragment.class) == null)
            loadRootFragment(R.id.fragment, SettingsProfileFragment.newInstance());
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
}
